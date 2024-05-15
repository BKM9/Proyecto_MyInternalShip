package com.example.internalship.iu.menu.calendario;

import static com.example.internalship.utils.Util.fechaVal;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.internalship.R;
import com.example.internalship.db.Funcionalidad_Actividades;
import com.example.internalship.utils.Alertas;
import com.example.internalship.vo.actividadesvo.ActividadVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Menu_Agenda_Detalle extends AppCompatActivity {

    EditText txtAsunto, txtDescripcion,txtFechaHora;
    ImageButton btnActualizar,btnEliminar;

    Funcionalidad_Actividades funcionalidadActividades = new Funcionalidad_Actividades(Menu_Agenda_Detalle.this);

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_agenda_detalle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        ActividadVO objetoActividad = getIntent().getParcelableExtra("ObjetoCalendario");
        assert objetoActividad != null;
        int id = objetoActividad.getId();
        setTxt(objetoActividad);


        txtFechaHora.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            Calendar calendar = Calendar.getInstance();
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            int mes = calendar.get(Calendar.MONTH);
            int anio = calendar.get(Calendar.YEAR);

            // crea una nueva configuración de localización en español
            Locale locale = new Locale("es", "ES");
            Locale.setDefault(locale);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            res.updateConfiguration(conf, dm);

            // crea los diálogos con la nueva configuración
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                @SuppressLint("SetTextI18n") TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
                    String formatoHora = hourOfDay < 12 ? "AM" : "PM";
                    hourOfDay = hourOfDay % 12;
                    if (hourOfDay == 0) hourOfDay = 12;
                    txtFechaHora.setText(fechaVal(dayOfMonth) + "-" + fechaVal((month + 1)) + "-" + year + " " + fechaVal(hourOfDay) + ":" + fechaVal(minute) + " " + formatoHora);
                }, 0, 0, false); // usa el formato de 12 horas

                timePickerDialog.show();
            }, anio, mes, dia);

            datePickerDialog.show();
        });

        btnActualizar.setOnClickListener(v -> {
            if (txtAsunto.getText().toString().isEmpty() ||
                    txtFechaHora.getText().toString().isEmpty() ||
                    txtDescripcion.getText().toString().isEmpty()) {

                Toast.makeText(Menu_Agenda_Detalle.this, "Falta completar campos", Toast.LENGTH_LONG).show();

            } else {

                updateAgenda(id,txtAsunto.getText().toString(),
                        txtFechaHora.getText().toString(),txtDescripcion.getText().toString());
            }
        });

        btnEliminar.setOnClickListener(v -> Alertas.showConfirmationDialog(Menu_Agenda_Detalle.this, "Confirmación", "¿Está seguro que desea eliminar actividad?", new Alertas.ConfirmationListener() {
            @Override
            public void onConfirmed() {
                long response = funcionalidadActividades.eliminarActividad(id);
                if (response > 0){

                    Toast.makeText(Menu_Agenda_Detalle.this, "Actividad eliminada ".concat(String.valueOf(objetoActividad.getAsunto())), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Menu_Agenda_Detalle.this,Menu_Agenda.class);
                    intent.putParcelableArrayListExtra("actividadesDelDia", (ArrayList<? extends Parcelable>) actualizarAgenda());
                    startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    startActivity(intent);

                }else{
                    Toast.makeText(Menu_Agenda_Detalle.this, "Ocurrio un error durante la eliminación", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCanceled() {
                Toast.makeText(Menu_Agenda_Detalle.this, "Actividad no eliminada".concat(String.valueOf(id)), Toast.LENGTH_SHORT).show();
            }
        }));

    }

    public void init(){
        txtAsunto = findViewById(R.id.txtAsunto_Calendario_Detalle);
        txtFechaHora = findViewById(R.id.txtFechaHora_Detalle);
        txtDescripcion = findViewById(R.id.txtDescripcion_Calendario_Detalle);

        btnEliminar = findViewById(R.id.btnCancelar_Calendario_Detalle);
        btnActualizar = findViewById(R.id.btnUpdate_Calendario_Detalle);
    }

    @SuppressLint("SetTextI18n")
    public void setTxt(ActividadVO objetoActividad){
        txtAsunto.setText(objetoActividad.getAsunto());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatoNuevo = new SimpleDateFormat("dd-MM-yyyy");

        Date fechaFormato = null;

        try {
            fechaFormato = formatoOriginal.parse(objetoActividad.getFecha());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String fechaParse = formatoNuevo.format(fechaFormato);

        txtFechaHora.setText(fechaParse+" "+objetoActividad.getHora());
        txtDescripcion.setText(objetoActividad.getAsunto());
    }
    public void updateAgenda(int idUpdate,String asunto, String fechahora,String descripcion){
        try {

            String fecha = fechahora.split(" ")[0];

            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatoOriginal = new SimpleDateFormat("dd-MM-yyyy");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatoNuevo  = new SimpleDateFormat("yyyy-MM-dd");

            Date fechaFormato = formatoOriginal.parse(fecha);
            String fechaParse = formatoNuevo.format(fechaFormato);

            String hora = fechahora.split(" ")[1].concat(" ").concat(fechahora.split(" ")[2]);

            long id = funcionalidadActividades.actualizarActividad(idUpdate,asunto, fechaParse, hora, descripcion);
            if (id > 0) {

                Intent intent = new Intent(Menu_Agenda_Detalle.this,Menu_Agenda.class);
                intent.putParcelableArrayListExtra("actividadesDelDia", (ArrayList<? extends Parcelable>) actualizarAgenda());
                startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                startActivity(intent);

            } else {
                Toast.makeText(this, "ERROR AL AGREGAR LA ACTIVIDAD", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Nullable
    private List<ActividadVO> actualizarAgenda(){
        try {
            Funcionalidad_Actividades funcionalidad_actividades = new Funcionalidad_Actividades(Menu_Agenda_Detalle.this);
            return funcionalidad_actividades.getMostrarActividades();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
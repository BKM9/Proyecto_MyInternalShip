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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.internalship.MainActivity;
import com.example.internalship.R;
import com.example.internalship.db.Funcionalidad_Actividades;
import com.example.internalship.utils.Alertas;
import com.example.internalship.vo.activityVO.ActividadVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Menu_Agenda_Add extends AppCompatActivity {

    EditText txtAsuntoCalendario, txtFechaHora, txtDesCalendario;
    Button btnAdd_Calendario;
    ImageButton btnCancelar_Calendario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_agenda_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtAsuntoCalendario = findViewById(R.id.txtAsunto_Calendario);
        txtFechaHora = findViewById(R.id.txtFechaHora);
        txtDesCalendario = findViewById(R.id.txtDescripcion_Calendario);
        btnAdd_Calendario = findViewById(R.id.btnAdd_Calendario);
        btnCancelar_Calendario = findViewById(R.id.btnCancelar_Calendario);

        btnCancelar_Calendario.setOnClickListener(v -> {

            Alertas.showConfirmationDialog(Menu_Agenda_Add.this, "Confirmación", "¿Está seguro que desea descartar agenda?", new Alertas.ConfirmationListener() {
                @Override
                public void onConfirmed() {

                    Intent intent = new Intent(Menu_Agenda_Add.this, Menu_Agenda.class);
                    intent.putParcelableArrayListExtra("actividadesDelDia", (ArrayList<? extends Parcelable>) actualizarAgenda());
                    startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    startActivity(intent);

                }

                @Override
                public void onCanceled() {

                }
            });
        });

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

        btnAdd_Calendario.setOnClickListener(v -> {
            if (txtAsuntoCalendario.getText().toString().isEmpty() ||
                    txtFechaHora.getText().toString().isEmpty() ||
                    txtDesCalendario.getText().toString().isEmpty()) {
                Toast.makeText(Menu_Agenda_Add.this, "Falta completar campos", Toast.LENGTH_LONG).show();
            } else {
                guardarCalendario(txtAsuntoCalendario.getText().toString(),
                        txtFechaHora.getText().toString(),txtDesCalendario.getText().toString());
            }
        });

    }

    private void guardarCalendario(String asunto, String fechahora, String descripcion) {

        try {
            Funcionalidad_Actividades funcionalidad_actividades = new Funcionalidad_Actividades(Menu_Agenda_Add.this);

            String fecha = fechahora.split(" ")[0];

            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatoOriginal = new SimpleDateFormat("dd-MM-yyyy");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatoNuevo  = new SimpleDateFormat("yyyy-MM-dd");

            Date fechaFormato = formatoOriginal.parse(fecha);
            String fechaParse = formatoNuevo.format(fechaFormato);

            String hora = fechahora.split(" ")[1].concat(" ").concat(fechahora.split(" ")[2]);

            long id = funcionalidad_actividades.insertarActividad(asunto, fechaParse, hora, descripcion);
            if (id > 0) {

                Toast.makeText(this, "Actividad Agregada", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Menu_Agenda_Add.this, MainActivity.class);
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

    private List<ActividadVO> actualizarAgenda(){
        Funcionalidad_Actividades funcionalidad_actividades = new Funcionalidad_Actividades(Menu_Agenda_Add.this);
        return funcionalidad_actividades.getobtenerConFechaActualActividad();
    }
}
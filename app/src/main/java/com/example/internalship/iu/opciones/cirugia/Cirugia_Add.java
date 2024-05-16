package com.example.internalship.iu.opciones.cirugia;

import static com.example.internalship.utils.Util.fechaVal;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.internalship.R;
import com.example.internalship.db.Funcionalidad_Cirugia;
import com.example.internalship.utils.Util;

import java.util.Calendar;
import java.util.Locale;

public class Cirugia_Add extends AppCompatActivity {

    TextView textViewNombrePaciente, textViewCama, textViewhc, textViewedad, textViewanamnesis,twreevaluacionadd;
    TextView textViewantecedentes, textViewdx, textViewtto, textViewte, textViewplan,txthoraingreso,txtidoculto;
    EditText textViewfi;
    Button btnCancelar, btnGuardar;
    private boolean isDeleting = false;

    Funcionalidad_Cirugia funcionalidad_cirugia = new Funcionalidad_Cirugia(Cirugia_Add.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cirugia_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        funcionalidad_BTN_EDITEXT();
    }

    public void init(){

        textViewNombrePaciente = findViewById(R.id.twAddNombreyApellido_Cirugia);
        textViewCama = findViewById(R.id.twAddAddNcama_Cirugia);
        textViewhc = findViewById(R.id.twAddhc_Cirugia);
        textViewfi = findViewById(R.id.twAddfi_Cirugia);
        txthoraingreso = findViewById(R.id.txtHoraingresodeta_Cirugia);
        textViewedad = findViewById(R.id.twAddEdad_Cirugia);
        textViewanamnesis = findViewById(R.id.twAddAddAnamnesis_Cirugia);
        textViewantecedentes = findViewById(R.id.twAddAddantecedentes_qx_personales_alergias_Cirugia);
        textViewdx = findViewById(R.id.twAddAdddx_Cirugia);
        textViewtto = findViewById(R.id.twAddTTO_Cirugia);
        textViewte = findViewById(R.id.twAddAddte_Cirugia);
        textViewplan = findViewById(R.id.twAddPlan_Cirugia);
        twreevaluacionadd = findViewById(R.id.twreevaluacionadd_Cirugia);
        btnCancelar = this.findViewById(R.id.btn_CancelarAdd_Cirugia);
        btnGuardar = this.findViewById(R.id.btnAgregar_Cirugia);
    }

    public void funcionalidad_BTN_EDITEXT(){

        textViewfi.setOnClickListener(v -> {
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
                textViewfi.setText(fechaVal(dayOfMonth) + "-" + fechaVal((month + 1)) + "-" + year);
            }, anio, mes, dia);

            datePickerDialog.show();
        });

        txthoraingreso.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            // crea una nueva configuración de localización en español
            Locale locale = new Locale("es", "ES");
            Locale.setDefault(locale);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            res.updateConfiguration(conf, dm);

            // crea los diálogos con la nueva configuración
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                String formatoHora = hourOfDay < 12 ? "AM" : "PM";
                hourOfDay = hourOfDay % 12;
                if (hourOfDay == 0) hourOfDay = 12;
                txthoraingreso.setText(fechaVal(hourOfDay) + ":" + fechaVal(minute) + " " + formatoHora);
            }, 0, 0, false); // usa el formato de 12 horas

            timePickerDialog.show();
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Cirugia_Add.this, Cirugia_Main.class);
                startActivity(intent);

            }
        });

        btnGuardar.setOnClickListener(v -> guardarDatos());
    }

    private void guardarDatos() {

        String nombre = textViewNombrePaciente.getText().toString();
        String cama = textViewCama.getText().toString();
        String hc = textViewhc.getText().toString();
        String fi = textViewfi.getText().toString();
        String hora = txthoraingreso.getText().toString();
        String edad = textViewedad.getText().toString();
        String anamnesis = textViewanamnesis.getText().toString();
        String antecedentes_qx_personales_alergias = textViewantecedentes.getText().toString();
        String dx = textViewdx.getText().toString();
        String tto = textViewtto.getText().toString();
        String te = textViewte.getText().toString();
        String plan = textViewplan.getText().toString();
        String reevaluacion = twreevaluacionadd.getText().toString();

        if (!Util.esNulo(this,nombre, cama)) {
            ProgressDialog progressDialog = ProgressDialog.show(this,
                    "Registrando Paciente",
                    "Espera por Favor",
                    true,
                    false);


            long code = funcionalidad_cirugia.insertar_Cirugia_Paciente(nombre, cama, hc, edad,fi, hora,te, anamnesis, antecedentes_qx_personales_alergias, dx,tto,plan,reevaluacion);

            if (code > 0) {
                Toast.makeText(Cirugia_Add.this, "Datos Almacenados", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Cirugia_Add.this, Cirugia_Main.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                progressDialog.dismiss();
            }else{
                Toast.makeText(Cirugia_Add.this, "Error al insetar los datos", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }
    }
}
package com.example.internalship.iu.opciones.cirugia.obs;

import static com.example.internalship.utils.Util.fechaVal;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.internalship.R;
import com.example.internalship.db.Funcionalidad_Cirugia;
import com.example.internalship.iu.opciones.cirugia.Cirugia_Detalle;
import com.example.internalship.utils.Util;
import com.example.internalship.vo.cirugiaVO.CObservacionesVO;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Cirugia_OBS_Add extends AppCompatActivity {

    String idPac, idCama;
    EditText txtfechAddObs, txtevolucionaddobs, txtdxobsadd, txtaddplanobs, txttratamientoobsadd;
    EditText txtlabaddobs, txtresimagenobsadd, txtprocedimientoobsadd, txtexmaenfisicoobsadd, txthoraingresoaddobs;
    Button btnaddobs, btncancelarobsadd;
    TextView lbhoraingresoaddobs, twTituloCamaObservacionADD;
    Switch primeravez;
    private boolean isDeleting = false;
    int banderaingreso;
    Funcionalidad_Cirugia funcionalidad_cirugia = new Funcionalidad_Cirugia(Cirugia_OBS_Add.this);

    List<CObservacionesVO> listOBS;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cirugia_obs_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getBundle();

        listOBS = funcionalidad_cirugia.list_ObtenerOBS(idCama);

        init();
        
    }
    
    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        idPac = bundle.getString("idPac");
        idCama = bundle.getString("idCama");
    }

    @SuppressLint("SetTextI18n")
    private void init(){

        txtexmaenfisicoobsadd = findViewById(R.id.txtexmaenfisicoobsadd_Cirugia);
        txtfechAddObs = findViewById(R.id.txtingresohoraobsadd_Cirugia);
        txtevolucionaddobs = findViewById(R.id.txtevolucionaddobs_Cirugia);
        txtdxobsadd = findViewById(R.id.txtdxobsadd_Cirugia);
        txtaddplanobs = findViewById(R.id.txtaddplanobs_Cirugia);
        txttratamientoobsadd = findViewById(R.id.txttratamientoobsadd_Cirugia);
        txtlabaddobs = findViewById(R.id.txtlabaddobs_Cirugia);
        txtresimagenobsadd = findViewById(R.id.txtresimagenobsadd_Cirugia);
        txtprocedimientoobsadd = findViewById(R.id.txtprocedimientoobsadd_Cirugia);
        primeravez = findViewById(R.id.swrecienllegado_Cirugia);
        txthoraingresoaddobs = findViewById(R.id.txthoraingresoaddobs_Cirugia);
        lbhoraingresoaddobs = findViewById(R.id.lbhoraingresoaddobs_Cirugia);
        twTituloCamaObservacionADD = findViewById(R.id.twTituloCamaObservacionADD_Cirugia);
        btncancelarobsadd = findViewById(R.id.btncancelarobsadd_Cirugia);
        btnaddobs = findViewById(R.id.btnaddobs_Cirugia);

        twTituloCamaObservacionADD.setText("OBSERVACION CAMA : ".concat(idCama));

        txtfechAddObs.setOnClickListener(v -> {
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
            @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                txtfechAddObs.setText(fechaVal(dayOfMonth) + "-" + fechaVal((month + 1)) + "-" + year);
            }, anio, mes, dia);

            datePickerDialog.show();
        });

        txthoraingresoaddobs.setOnClickListener(v -> {
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
                txthoraingresoaddobs.setText(fechaVal(hourOfDay) + ":" + fechaVal(minute) + " " + formatoHora);
            }, 0, 0, false); // usa el formato de 12 horas

            timePickerDialog.show();
        });

        btnaddobs.setOnClickListener(v -> guardarDatos(idCama));

        btncancelarobsadd.setOnClickListener(v -> {
            Intent intent = new Intent(Cirugia_OBS_Add.this, Cirugia_Detalle.class);
            Bundle bundle = new Bundle();
            //bundle.putParcelable("ObjetoPaciente", (Parcelable) ObjetoPaciente);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        primeravez.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(!listOBS.isEmpty()){
                        if(Util.existefechaprimerregistro(listOBS, Cirugia_OBS_Add.this)){
                            primeravez.setChecked(false);
                            banderaingreso = 0;
                        }else{
                            txthoraingresoaddobs.setVisibility(View.VISIBLE);
                            lbhoraingresoaddobs.setVisibility(View.VISIBLE);
                            banderaingreso = 1;
                        }
                    }else{
                        txthoraingresoaddobs.setVisibility(View.VISIBLE);
                        lbhoraingresoaddobs.setVisibility(View.VISIBLE);
                        banderaingreso = 1;
                    }
                } else {
                    txthoraingresoaddobs.setVisibility(View.GONE);
                    lbhoraingresoaddobs.setVisibility(View.GONE);
                    banderaingreso = 0;
                    txthoraingresoaddobs.setText("");
                }
            }
        });

    }

    private void guardarDatos(String cama) {
        String valfisico = txtexmaenfisicoobsadd.getText().toString();
        String valfechIngreso = txtfechAddObs.getText().toString();
        String valevoluciones = txtevolucionaddobs.getText().toString();
        String valdx = txtdxobsadd.getText().toString();
        String valplan = txtaddplanobs.getText().toString();
        String valtratamiento = txttratamientoobsadd.getText().toString();
        String valReslab = txtlabaddobs.getText().toString();
        String valResImagen = txtresimagenobsadd.getText().toString();
        String valProcedimiento = txtprocedimientoobsadd.getText().toString();
        String valtxthoraingresoaddobs = txthoraingresoaddobs.getText().toString();

        if (!Util.existefechaendatosalmacenadosOBS_Cirugia(listOBS, valfechIngreso)) {
            if(primeravez.isChecked()){
                if (!Util.esNulo(Cirugia_OBS_Add.this, valfechIngreso, cama)) {

                    float code = funcionalidad_cirugia.insertar_Cirugia_OBS(cama,valfechIngreso,valfisico,valevoluciones,valdx,valplan,valtratamiento,valReslab,valResImagen,valProcedimiento,valtxthoraingresoaddobs,String.valueOf(banderaingreso));
                    if (code >= 0) {
                        Toast.makeText(Cirugia_OBS_Add.this, "Datos Almacenados", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Cirugia_OBS_Add.this, Cirugia_OBS.class);
                        Bundle bundle = new Bundle();

                        intent.putExtra("idPac", idPac);
                        intent.putExtra("idCama", idCama);

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(Cirugia_OBS_Add.this, "Falta la fecha", Toast.LENGTH_LONG).show();
                }
            }
            else{
                if (!Util.esNulo(Cirugia_OBS_Add.this, valfechIngreso, cama) /* && Util.esFechaMayorAladeIngreso(ObjetoPaciente.getObservaciones(),valfechIngreso,this) */) {

                    float code = funcionalidad_cirugia.insertar_Cirugia_OBS(cama,valfechIngreso,valfisico,valevoluciones,valdx,valplan,valtratamiento,valReslab,valResImagen,valProcedimiento,valtxthoraingresoaddobs,String.valueOf(banderaingreso));
                    if (code >= 0) {
                        Toast.makeText(Cirugia_OBS_Add.this, "Datos Almacenados", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Cirugia_OBS_Add.this, Cirugia_OBS.class);
                        Bundle bundle = new Bundle();

                        intent.putExtra("idPac", idPac);
                        intent.putExtra("idCama", idCama);

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(Cirugia_OBS_Add.this, "Falta la fecha", Toast.LENGTH_LONG).show();
                }
            }
        }
        else {
            Toast.makeText(Cirugia_OBS_Add.this, "La fecha ya existe", Toast.LENGTH_LONG).show();
        }
    }
    
}
package com.example.internalship.iu.opciones.cirugia.uci;

import static com.example.internalship.utils.Constantes.OPC_TABLA_UCI;
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
import com.example.internalship.vo.ObjetoVO;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Cirugia_UCI_Add extends AppCompatActivity {

    String idPac, idCama;
    EditText txtfechAdd, txtevolucionadd, txtdxadd, txtaddplan, txttratamientoadd;
    EditText txtlabadd, txtresimagenadd, txtprocedimientoadd, txtexmaenfisicoadd, txthoraingresoadd;
    Button btnadd, btncancelaradd;
    TextView lbhoraingresoadd, twTituloCamaervacionADD;
    Switch primeravez;
    int banderaingreso;
    Funcionalidad_Cirugia funcionalidad_cirugia = new Funcionalidad_Cirugia(Cirugia_UCI_Add.this);

    List<ObjetoVO> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cirugia_uci_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getBundle();

        list = funcionalidad_cirugia.list_Obtener(idCama,OPC_TABLA_UCI);

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

        txtexmaenfisicoadd = findViewById(R.id.ET_EFISICO_UCI_CIRUGIA_ADD);
        txtfechAdd = findViewById(R.id.ET_FECHA_UCI_CIRUGIA_ADD);
        txtevolucionadd = findViewById(R.id.ET_EVOLUCION_UCI_CIRUGIA_ADD);
        txtdxadd = findViewById(R.id.ET_DX_UCI_CIRUGIA_ADD);
        txtaddplan = findViewById(R.id.ET_PLAN_UCI_CIRUGIA_ADD);
        txttratamientoadd = findViewById(R.id.ET_TRATAMIENTO_UCI_CIRUGIA_ADD);
        txtlabadd = findViewById(R.id.ET_RESLAB_UCI_CIRUGIA_ADD);
        txtresimagenadd = findViewById(R.id.ET_RESIMAGEN_UCI_CIRUGIA_ADD);
        txtprocedimientoadd = findViewById(R.id.ET_PROCEDIMIENTO_UCI_CIRUGIA_ADD);
        primeravez = findViewById(R.id.SWITCH_PRIMERAVEZ_UCI_CIRUGIA_ADD);
        txthoraingresoadd = findViewById(R.id.ET_HORA_UCI_CIRUGIA_ADD);
        lbhoraingresoadd = findViewById(R.id.TV_HORA_UCI_CIRUGIA_ADD);
        twTituloCamaervacionADD = findViewById(R.id.TV_TITULO_UCI_CIRUGIA_ADD);
        btncancelaradd = findViewById(R.id.BTN_CANCELAR_UCI_CIRUGIA_ADD);
        btnadd = findViewById(R.id.BTN_AGREGAR_UCI_CIRUGIA_ADD);

        twTituloCamaervacionADD.setText("T.SHOCK CAMA : ".concat(idCama));

        txtfechAdd.setOnClickListener(v -> {
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
                txtfechAdd.setText(fechaVal(dayOfMonth) + "-" + fechaVal((month + 1)) + "-" + year);
            }, anio, mes, dia);

            datePickerDialog.show();
        });

        txthoraingresoadd.setOnClickListener(v -> {
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
                txthoraingresoadd.setText(fechaVal(hourOfDay) + ":" + fechaVal(minute) + " " + formatoHora);
            }, 0, 0, false); // usa el formato de 12 horas

            timePickerDialog.show();
        });

        btnadd.setOnClickListener(v -> guardarDatos(idCama));

        btncancelaradd.setOnClickListener(v -> {
            Intent intent = new Intent(Cirugia_UCI_Add.this, Cirugia_Detalle.class);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            startActivity(intent);
        });

        primeravez.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(!list.isEmpty()){
                        if(Util.existefechaprimerregistro(list, Cirugia_UCI_Add.this)){
                            primeravez.setChecked(false);
                            banderaingreso = 0;
                        }else{
                            txthoraingresoadd.setVisibility(View.VISIBLE);
                            lbhoraingresoadd.setVisibility(View.VISIBLE);
                            banderaingreso = 1;
                        }
                    }else{
                        txthoraingresoadd.setVisibility(View.VISIBLE);
                        lbhoraingresoadd.setVisibility(View.VISIBLE);
                        banderaingreso = 1;
                    }
                } else {
                    txthoraingresoadd.setVisibility(View.GONE);
                    lbhoraingresoadd.setVisibility(View.GONE);
                    banderaingreso = 0;
                    txthoraingresoadd.setText("");
                }
            }
        });

    }

    private void guardarDatos(String cama) {

        String valfisico = txtexmaenfisicoadd.getText().toString();
        String valfechIngreso = txtfechAdd.getText().toString();
        String valevoluciones = txtevolucionadd.getText().toString();
        String valdx = txtdxadd.getText().toString();
        String valplan = txtaddplan.getText().toString();
        String valtratamiento = txttratamientoadd.getText().toString();
        String valReslab = txtlabadd.getText().toString();
        String valResImagen = txtresimagenadd.getText().toString();
        String valProcedimiento = txtprocedimientoadd.getText().toString();
        String valtxthoraingresoadd = txthoraingresoadd.getText().toString();

        ObjetoVO objetoVO = new ObjetoVO(cama,valfechIngreso,valfisico,valevoluciones,valdx,valplan,valtratamiento,valReslab,valResImagen,valProcedimiento,valtxthoraingresoadd,String.valueOf(banderaingreso));

        if (!Util.existefechaendatosalmacenados(list, valfechIngreso)) {

            if(primeravez.isChecked()){
                if (!Util.esNulo(Cirugia_UCI_Add.this, valfechIngreso, cama)) {

                    float code = funcionalidad_cirugia.insertar_Cirugia_TIPOTABLA(objetoVO,OPC_TABLA_UCI);
                    if (code >= 0) {
                        Toast.makeText(Cirugia_UCI_Add.this, "Datos Almacenados", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Cirugia_UCI_Add.this, Cirugia_UCI.class);
                        Bundle bundle = new Bundle();

                        intent.putExtra("idPac", idPac);
                        intent.putExtra("idCama", idCama);

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(Cirugia_UCI_Add.this, "Falta la fecha", Toast.LENGTH_LONG).show();
                }
            }
            else{
                if (!Util.esNulo(Cirugia_UCI_Add.this, valfechIngreso, cama)) {

                    float code = funcionalidad_cirugia.insertar_Cirugia_TIPOTABLA(objetoVO,OPC_TABLA_UCI);
                    if (code >= 0) {
                        Toast.makeText(Cirugia_UCI_Add.this, "Datos Almacenados", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Cirugia_UCI_Add.this, Cirugia_UCI.class);
                        Bundle bundle = new Bundle();

                        intent.putExtra("idPac", idPac);
                        intent.putExtra("idCama", idCama);

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(Cirugia_UCI_Add.this, "Falta la fecha", Toast.LENGTH_LONG).show();
                }
            }
        }
        else {
            Toast.makeText(Cirugia_UCI_Add.this, "La fecha ya existe", Toast.LENGTH_LONG).show();
        }
    }
}
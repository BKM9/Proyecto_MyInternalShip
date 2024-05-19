package com.example.internalship.iu.opciones.cirugia;

import static com.example.internalship.utils.Constantes.RUTA_ALMACENAMIENTO_IMG_RAIZ;
import static com.example.internalship.utils.Constantes.TIPO_ECOGRAFIA;
import static com.example.internalship.utils.Constantes.TIPO_EXTRA;
import static com.example.internalship.utils.Constantes.TIPO_RAYOS_X;
import static com.example.internalship.utils.Constantes.TIPO_TOMOGRAFIA;
import static com.example.internalship.utils.Util.fechaVal;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.internalship.MainActivity;
import com.example.internalship.R;
import com.example.internalship.db.Funcionalidad_Cirugia;
import com.example.internalship.iu.opciones.cirugia.hcirugia.Cirugia_HCirugia;
import com.example.internalship.iu.opciones.cirugia.obs.Cirugia_OBS;
import com.example.internalship.iu.opciones.cirugia.tshock.Cirugia_TSHOCK;
import com.example.internalship.iu.opciones.cirugia.uci.Cirugia_UCI;
import com.example.internalship.utils.Alertas;
import com.example.internalship.vo.cirugiaVO.CPacienteVO;
import com.example.internalship.vo.photo.foto;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Cirugia_Detalle extends AppCompatActivity {

    CPacienteVO ObjetoPaciente;
    Button btnactualizar, btnaDELETE, btnPrueba;
    TextView txtidoculto;
    EditText textViewfi, textViewNombrePaciente, textViewanamnesis, textViewantecedentes, textViewdx;
    String idPac, idCama;
    EditText textViewtto, textViewte, textViewplan, txthora, txthc, txtedad, twreevaluacion, txtcama;
    Funcionalidad_Cirugia funcionalidad_cirugia = new Funcionalidad_Cirugia(Cirugia_Detalle.this);
    private ProgressBar BarprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cirugia_detalle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        funcionalidad_item();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            ObjetoPaciente = getIntent().getParcelableExtra("ObjetoPaciente");

            if(ObjetoPaciente != null){
                ObjetoPaciente.ordenarObservacionesPorFecha();

                idPac = String.valueOf(ObjetoPaciente.getId());
                idCama = ObjetoPaciente.getCama();

                txtcama.setText(ObjetoPaciente.getCama());
                textViewNombrePaciente.setText(ObjetoPaciente.getNombre());
                textViewanamnesis.setText(ObjetoPaciente.getAnamnesis());
                textViewantecedentes.setText(ObjetoPaciente.getAntecedentes_qx_personales_alergias());
                textViewfi.setText(ObjetoPaciente.getFi());
                txthora.setText(ObjetoPaciente.getHoraingreso());
                textViewdx.setText(ObjetoPaciente.getDx());
                textViewtto.setText(ObjetoPaciente.getTto());
                textViewte.setText(ObjetoPaciente.getTe());
                textViewplan.setText(ObjetoPaciente.getPlan());
                txthc.setText(ObjetoPaciente.getHc());
                txtedad.setText(ObjetoPaciente.getEdad());
                twreevaluacion.setText(ObjetoPaciente.getReevaluacion());
                txtidoculto.setText(String.valueOf(ObjetoPaciente.getId()));
            }else{
                idPac = bundle.getString("idPac");
                idCama = bundle.getString("idCama");

                ObjetoPaciente = funcionalidad_cirugia.buscar_Cirugia_Paciente(idPac, idCama);

                txtcama.setText(ObjetoPaciente.getCama());
                textViewNombrePaciente.setText(ObjetoPaciente.getNombre());
                textViewanamnesis.setText(ObjetoPaciente.getAnamnesis());
                textViewantecedentes.setText(ObjetoPaciente.getAntecedentes_qx_personales_alergias());
                textViewfi.setText(ObjetoPaciente.getFi());
                txthora.setText(ObjetoPaciente.getHoraingreso());
                textViewdx.setText(ObjetoPaciente.getDx());
                textViewtto.setText(ObjetoPaciente.getTto());
                textViewte.setText(ObjetoPaciente.getTe());
                textViewplan.setText(ObjetoPaciente.getPlan());
                txthc.setText(ObjetoPaciente.getHc());
                txtedad.setText(ObjetoPaciente.getEdad());
                twreevaluacion.setText(ObjetoPaciente.getReevaluacion());
                txtidoculto.setText(String.valueOf(ObjetoPaciente.getId()));

            }
        }
    }

    public void init(){
        txtcama = findViewById(R.id.twTituloCama_Cirugia);
        textViewNombrePaciente = findViewById(R.id.twPacienteNombre_Cirugia);
        textViewanamnesis = findViewById(R.id.twAnamnesis_Cirugia);
        textViewantecedentes = findViewById(R.id.twantecedentes_qx_personales_alergias_Cirugia);
        textViewfi = findViewById(R.id.twfi_Cirugia);
        txthora = findViewById(R.id.txtHoraingreso_Cirugia);
        textViewdx = findViewById(R.id.twdx_Cirugia);
        textViewtto = findViewById(R.id.twTTO_Cirugia);
        textViewte = findViewById(R.id.twte_Cirugia);
        textViewplan = findViewById(R.id.twPlan_Cirugia);
        txthc = findViewById(R.id.txtHistoriadetacama_Cirugia);
        txtedad = findViewById(R.id.txtedaddeta_Cirugia);
        twreevaluacion = findViewById(R.id.twreevaluacion_Cirugia);
        txtidoculto = findViewById(R.id.txtidoculto_Cirugia);
        BarprogressBar = findViewById(R.id.progressBar_pac_cirugia_delete);
        btnactualizar = findViewById(R.id.btnActualizar_Cirugia);
        btnaDELETE = findViewById(R.id.btnEliminar_Cirugia);
        btnPrueba = findViewById(R.id.btnDesplegableCirugia);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void funcionalidad_item(){

        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Cirugia_Detalle.this, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_opc_cirugia, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if (menuItem.getItemId() == R.id.btn_cirugia_OBS) {

                            Intent intent = new Intent(Cirugia_Detalle.this, Cirugia_OBS.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("idPac", String.valueOf(ObjetoPaciente.getId()));
                            bundle.putString("idCama", ObjetoPaciente.getCama());
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                        else if (menuItem.getItemId() == R.id.btn_cirugia_UCI) {

                            Intent intent = new Intent(Cirugia_Detalle.this, Cirugia_UCI.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("idPac", String.valueOf(ObjetoPaciente.getId()));
                            bundle.putString("idCama", ObjetoPaciente.getCama());
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                        else if (menuItem.getItemId() == R.id.btn_cirugia_TSHOCK) {

                            Intent intent = new Intent(Cirugia_Detalle.this, Cirugia_TSHOCK.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("idPac", String.valueOf(ObjetoPaciente.getId()));
                            bundle.putString("idCama", ObjetoPaciente.getCama());
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                        else if (menuItem.getItemId() == R.id.btn_cirugia_HCirugia) {

                            Intent intent = new Intent(Cirugia_Detalle.this, Cirugia_HCirugia.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("idPac", String.valueOf(ObjetoPaciente.getId()));
                            bundle.putString("idCama", ObjetoPaciente.getCama());
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                        else if (menuItem.getItemId() == R.id.btn_cirugia_export) {
                            Toast.makeText(Cirugia_Detalle.this, "Exportando", Toast.LENGTH_SHORT).show();
                            return true;
                        }

                        return false;
                    }
                });

                popup.show();
            }
        });

        textViewantecedentes.setOnTouchListener((v, event) -> {
            if (textViewantecedentes.hasFocus()) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                }
            }
            return false;
        });

        textViewanamnesis.setOnTouchListener((v, event) -> {
            if (textViewanamnesis.hasFocus()) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                }
            }
            return false;
        });

        textViewdx.setOnTouchListener((v, event) -> {
            if (textViewdx.hasFocus()) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                }
            }
            return false;
        });

        textViewtto.setOnTouchListener((v, event) -> {
            if (textViewtto.hasFocus()) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                }
            }
            return false;
        });

        textViewplan.setOnTouchListener((v, event) -> {
            if (textViewplan.hasFocus()) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                }
            }
            return false;
        });

        twreevaluacion.setOnTouchListener((v, event) -> {
            if (twreevaluacion.hasFocus()) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                }
            }
            return false;
        });

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
            @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                textViewfi.setText(fechaVal(dayOfMonth) + "-" + fechaVal((month + 1)) + "-" + year);
            }, anio, mes, dia);

            datePickerDialog.show();
        });
        txthora.setOnClickListener(v -> {
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
            @SuppressLint("SetTextI18n") TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                String formatoHora = hourOfDay < 12 ? "AM" : "PM";
                hourOfDay = hourOfDay % 12;
                if (hourOfDay == 0) hourOfDay = 12;
                txthora.setText(fechaVal(hourOfDay) + ":" + fechaVal(minute) + " " + formatoHora);
            }, 0, 0, false); // usa el formato de 12 horas

            timePickerDialog.show();
        });

        btnactualizar.setOnClickListener(v -> notificacionAdvertenciaActualizar());

        btnaDELETE.setOnClickListener(v -> notificacionAdvertenciaEliminar());

    }

    private void updatepaciente() {
        String nombreApellido = textViewNombrePaciente.getText().toString();

        String cama = txtcama.getText().toString();
        String hc = txthc.getText().toString();
        String fi = textViewfi.getText().toString();
        String horaingreso = txthora.getText().toString();
        String edad = txtedad.getText().toString();
        String anamnesis = textViewanamnesis.getText().toString();
        String antecendetedx = textViewantecedentes.getText().toString();
        String dx = textViewdx.getText().toString();
        String tto = textViewtto.getText().toString();
        String te = textViewte.getText().toString();
        String plan = textViewplan.getText().toString();
        String reevaluacion = twreevaluacion.getText().toString();
        String id = txtidoculto.getText().toString();

        int code = funcionalidad_cirugia.actualizar_Paciente_Cirugia(Integer.parseInt(id), nombreApellido, cama, hc,edad, fi, horaingreso, te, anamnesis, antecendetedx, dx, tto, plan, reevaluacion);

        if (code > 0) {
            Toast.makeText(Cirugia_Detalle.this, "Datos Actualizados Correctamente", Toast.LENGTH_LONG).show();
        }
    }

    private void notificacionAdvertenciaEliminar() {

        Alertas.showConfirmationDialog(Cirugia_Detalle.this, "Confirmación", "¿Está seguro que desea eliminar datos del paciente?", new Alertas.ConfirmationListener() {
            @Override
            public void onConfirmed() {
                deletePaciente();
                deleteImg();
            }

            @Override
            public void onCanceled() {
                Toast.makeText(Cirugia_Detalle.this, "Eliminacion cancelada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void notificacionAdvertenciaActualizar() {

        Alertas.showConfirmationDialog(Cirugia_Detalle.this, "Confirmación", "¿Está seguro que desea actualizar datos del paciente?", new Alertas.ConfirmationListener() {
            @Override
            public void onConfirmed() {

                updatepaciente();

            }

            @Override
            public void onCanceled() {
                Toast.makeText(Cirugia_Detalle.this, "Actualizacion cancelada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deletePaciente() {
        ProgressDialog progressDialog = ProgressDialog.show(this,
                "Elimiando Paciente",
                "Espera por Favor",
                true,
                false);

        String id = txtidoculto.getText().toString();
        String cama = txtcama.getText().toString();

        float code = funcionalidad_cirugia.eliminar_Cirugia_Paciente(Integer.parseInt(id),cama);
        if (code >= 0) {
            progressDialog.dismiss();
            Toast.makeText(Cirugia_Detalle.this, "Paciente eliminado", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Cirugia_Detalle.this, MainActivity.class);
            startActivity(intent);

        }else {
            progressDialog.dismiss();
            Toast.makeText(Cirugia_Detalle.this, "Error al eliminar", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteImg(){

        BarprogressBar.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    List<foto> list_Fotos = funcionalidad_cirugia.list_Fotos(idPac, TIPO_RAYOS_X );

                    if(!list_Fotos.isEmpty()){
                        for (foto foto : list_Fotos) {

                            String rutaImagen = RUTA_ALMACENAMIENTO_IMG_RAIZ.concat(foto.getUrl());
                            File archivo = new File(rutaImagen);
                            archivo.delete();

                            funcionalidad_cirugia.eliminar_Foto(String.valueOf(foto.getId()));
                        }
                    }

                    list_Fotos = funcionalidad_cirugia.list_Fotos(idPac,TIPO_ECOGRAFIA);

                    if(!list_Fotos.isEmpty()){
                        for (foto foto : list_Fotos) {

                            String rutaImagen = RUTA_ALMACENAMIENTO_IMG_RAIZ.concat(foto.getUrl());
                            File archivo = new File(rutaImagen);
                            archivo.delete();

                            funcionalidad_cirugia.eliminar_Foto(String.valueOf(foto.getId()));
                        }
                    }

                    list_Fotos = funcionalidad_cirugia.list_Fotos(idPac,TIPO_TOMOGRAFIA);

                    if(!list_Fotos.isEmpty()){
                        for (foto foto : list_Fotos) {

                            String rutaImagen = RUTA_ALMACENAMIENTO_IMG_RAIZ.concat(foto.getUrl());
                            File archivo = new File(rutaImagen);
                            archivo.delete();

                            funcionalidad_cirugia.eliminar_Foto(String.valueOf(foto.getId()));
                        }
                    }

                    list_Fotos = funcionalidad_cirugia.list_Fotos(idPac, TIPO_EXTRA);

                    if(!list_Fotos.isEmpty()){
                        for (foto foto : list_Fotos) {

                            String rutaImagen = RUTA_ALMACENAMIENTO_IMG_RAIZ.concat(foto.getUrl());
                            File archivo = new File(rutaImagen);
                            archivo.delete();

                            funcionalidad_cirugia.eliminar_Foto(String.valueOf(foto.getId()));
                        }
                    }


                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Hide the ProgressBar
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BarprogressBar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();

    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Cirugia_Detalle.this, MainActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putParcelable("ObjetoPaciente", (Parcelable) ObjetoPaciente);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
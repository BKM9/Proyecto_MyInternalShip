package com.example.internalship.iu.opciones.cirugia.uci;

import static com.example.internalship.utils.Constantes.ALETAR_OPERACION_CANCELADA;
import static com.example.internalship.utils.Constantes.LILA_CLARO;
import static com.example.internalship.utils.Constantes.AMARILLO_OSCURO;
import static com.example.internalship.utils.Constantes.CONFIRMACION_ELIMINAR;
import static com.example.internalship.utils.Constantes.CONSULTA_OPCION_CONFIRMACION;
import static com.example.internalship.utils.Constantes.LILA_OSCURO;
import static com.example.internalship.utils.Constantes.MOSTRAR_CAMPOS;
import static com.example.internalship.utils.Constantes.NOMBRE_OPC_ACTUALIZAR;
import static com.example.internalship.utils.Constantes.NOMBRE_OPC_CANCELAR;
import static com.example.internalship.utils.Constantes.NOMBRE_OPC_ELIMINAR;
import static com.example.internalship.utils.Constantes.NO_INFORMACION;
import static com.example.internalship.utils.Constantes.OCULTAR_CAMPOS;
import static com.example.internalship.utils.Constantes.OPC_TABLA_UCI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.example.internalship.utils.Alertas;
import com.example.internalship.utils.Util;
import com.example.internalship.vo.ObjetoVO;

import java.util.ArrayList;
import java.util.List;

public class Cirugia_UCI extends AppCompatActivity {

    String idPac, idCama;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchModificar;

    EditText textfecha, txthoraingrreso, twnumdias;

    TextView textView, txfisico;

    Boolean banderaVisible = false;

    private List<EditText> editTextList;

    Button btnadd;

    Funcionalidad_Cirugia funcionalidad_cirugia = new Funcionalidad_Cirugia(Cirugia_UCI.this);

    List<ObjetoVO> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cirugia_uci);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getBundle();

        list = funcionalidad_cirugia.list_Obtener(idCama,OPC_TABLA_UCI);

        init();

        cargarRowservaciones();

        ocultarItems();
    }

    private void getBundle() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            idPac = bundle.getString("idPac");
            idCama = bundle.getString("idCama");
        }
    }

    private void init() {
        textfecha = findViewById(R.id.ET_FECHA_UCI_CIRUGIA);
        txthoraingrreso = findViewById(R.id.ET_HORA_UCI_CIRUGIA);
        twnumdias = findViewById(R.id.ET_DIAS_UCI_CIRUGIA);
        switchModificar = findViewById(R.id.SWITCH_CAMPOS_UCI_CIRUGIA);
        btnadd = this.findViewById(R.id.BT_AGREGAR_UCI_CIRUGIA);

        String fechIngresoHora = NO_INFORMACION;
        String horaingreso = NO_INFORMACION;
        String numdias = Util.obtenerdiasenestadoporlista(list);

        for (ObjetoVO objVO : list) {
            if (objVO.getPrimeringreso().equals("1")) {
                fechIngresoHora = objVO.getDia();
                horaingreso = objVO.getHoraingreso();
                break;
            }
        }

        textfecha.setText(fechIngresoHora);
        txthoraingrreso.setText(horaingreso);
        twnumdias.setText(numdias);
        switchModificar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    banderaVisible = true;
                    switchModificar.setText(OCULTAR_CAMPOS);
                    ocultarItems();
                } else {
                    banderaVisible = false;
                    switchModificar.setText(MOSTRAR_CAMPOS);
                    ocultarItems();
                }
            }
        });

        btnadd.setOnClickListener(v -> {
            Intent intent = new Intent(Cirugia_UCI.this, Cirugia_UCI_Add.class);
            intent.putExtra("idPac", idPac);
            intent.putExtra("idCama", idCama);
            startActivity(intent);
        });

    }

    private void ocultarItems() {

        LinearLayout linearLayout = findViewById(R.id.LY_DATA_UCI_CIRUGIA);

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View view = linearLayout.getChildAt(i);

            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                String texto = editText.getText().toString().trim();

                if (texto.isEmpty() && !banderaVisible) {
                    editText.setVisibility(View.GONE);
                } else {
                    editText.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void cargarRowservaciones() {

        LinearLayout linearLayout = findViewById(R.id.LY_DATA_UCI_CIRUGIA);

        editTextList = new ArrayList<>();

        for (int x = 0; x < list.size(); x++) {
            // TITULO FECHA
            textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    200 // alto
            ));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
            params.setMargins(10, 10, 10, 5); // márgenes
            textView.setLayoutParams(params);
            textView.setBackgroundColor(Color.parseColor(LILA_OSCURO)); // fondo personalizado
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL); // gravedad
            if (list.get(x).getPrimeringreso().equals("1")) {
                textView.setText(list.get(x).getDia().concat("\n(Nota de Ingreso)"));
            } else {
                textView.setText(list.get(x).getDia());
            }
            textView.setTextColor(Color.parseColor("#000000")); // color de texto
            textView.setTextSize(18); // tamaño de texto
            textView.setTypeface(null, Typeface.BOLD); // estilo de texto
            linearLayout.addView(textView);

            int finalX = x;
            textView.setOnClickListener(v -> {
                mostrarOpciones(finalX);
            });

            // --------------------------------------------------------------------------------------
            //ESCONDIDOS
            // CAMA ESCONDIDA
            EditText camaEscondida = new EditText(this);
            camaEscondida.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) camaEscondida.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            camaEscondida.setLayoutParams(params);
            camaEscondida.setTextColor(Color.parseColor("#000000")); // color de texto
            camaEscondida.setText(list.get(x).getCama()); // texto
            camaEscondida.setId(View.generateViewId()); // asignar ID único

            editTextList.add(camaEscondida);

            // DIA ESCONDIDA
            EditText diaescondida = new EditText(this);
            diaescondida.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) diaescondida.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            diaescondida.setLayoutParams(params);
            diaescondida.setTextColor(Color.parseColor("#000000")); // color de texto
            diaescondida.setText(list.get(x).getDia()); // texto
            diaescondida.setId(View.generateViewId()); // asignar ID único

            editTextList.add(diaescondida);
            // --------------------------------------------------------------------------------------
            //SUBTITULO FISICO
            txfisico = new TextView(this);
            txfisico.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) txfisico.getLayoutParams();
            params.setMargins(10, 2, 10, 10); // márgenes
            txfisico.setLayoutParams(params);
            txfisico.setBackgroundColor(Color.parseColor(LILA_CLARO)); // fondo personalizado
            txfisico.setGravity(Gravity.START | Gravity.CENTER_VERTICAL); // gravedad
            txfisico.setText("1. Examen físico"); // texto
            txfisico.setTextColor(Color.parseColor("#000000")); // color de texto
            txfisico.setTextSize(18); // tamaño de texto
            txfisico.setTypeface(null, Typeface.BOLD); // estilo de texto
            txfisico.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(txfisico);

            // TEXTO DEL SUBTITULO
            EditText textCaja = new EditText(this);
            textCaja.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) textCaja.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            textCaja.setLayoutParams(params);
            textCaja.setTextColor(Color.parseColor("#000000")); // color de texto
            textCaja.setText(list.get(x).getEfisico()); // texto
            textCaja.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(textCaja);

            editTextList.add(textCaja);

            // --------------------------------------------------------------------------------------
            //SUBTITULO EVOLUCION
            TextView twEvolucion = new TextView(this);
            twEvolucion.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) twEvolucion.getLayoutParams();
            params.setMargins(10, 2, 10, 10); // márgenes
            twEvolucion.setLayoutParams(params);
            twEvolucion.setBackgroundColor(Color.parseColor(LILA_CLARO)); // fondo personalizado
            twEvolucion.setGravity(Gravity.START | Gravity.CENTER_VERTICAL); // gravedad
            twEvolucion.setText("2. Evolucion"); // texto
            twEvolucion.setTextColor(Color.parseColor("#000000")); // color de texto
            twEvolucion.setTextSize(18); // tamaño de texto
            twEvolucion.setTypeface(null, Typeface.BOLD); // estilo de texto
            twEvolucion.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(twEvolucion);

            // TEXTO DEL SUBTITULO
            EditText edevolucion = new EditText(this);
            edevolucion.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) edevolucion.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            edevolucion.setLayoutParams(params);
            edevolucion.setTextColor(Color.parseColor("#000000")); // color de texto
            edevolucion.setText(list.get(x).getEvolucion()); // texto
            edevolucion.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(edevolucion);

            editTextList.add(edevolucion);

            // --------------------------------------------------------------------------------------
            //SUBTITULO DX
            TextView twdx = new TextView(this);
            twdx.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) twdx.getLayoutParams();
            params.setMargins(10, 2, 10, 10); // márgenes
            twdx.setLayoutParams(params);
            twdx.setBackgroundColor(Color.parseColor(LILA_CLARO)); // fondo personalizado
            twdx.setGravity(Gravity.START | Gravity.CENTER_VERTICAL); // gravedad
            twdx.setText("3. DX"); // texto
            twdx.setTextColor(Color.parseColor("#000000")); // color de texto
            twdx.setTextSize(18); // tamaño de texto
            twdx.setTypeface(null, Typeface.BOLD); // estilo de texto
            twdx.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(twdx);

            // TEXTO DEL SUBTITULO
            EditText txtdx = new EditText(this);
            txtdx.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) txtdx.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            txtdx.setLayoutParams(params);
            txtdx.setTextColor(Color.parseColor("#000000")); // color de texto
            txtdx.setText(list.get(x).getDx()); // texto
            txtdx.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(txtdx);

            editTextList.add(txtdx);

            // --------------------------------------------------------------------------------------
            //SUBTITULO PLAN
            TextView twplan = new TextView(this);
            twplan.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) twplan.getLayoutParams();
            params.setMargins(10, 2, 10, 10); // márgenes
            twplan.setLayoutParams(params);
            twplan.setBackgroundColor(Color.parseColor(LILA_CLARO)); // fondo personalizado
            twplan.setGravity(Gravity.START | Gravity.CENTER_VERTICAL); // gravedad
            twplan.setText("4. Plan"); // texto
            twplan.setTextColor(Color.parseColor("#000000")); // color de texto
            twplan.setTextSize(18); // tamaño de texto
            twplan.setTypeface(null, Typeface.BOLD); // estilo de texto
            twplan.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(twplan);

            // TEXTO DEL SUBTITULO
            EditText txtplan = new EditText(this);
            txtplan.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) txtplan.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            txtplan.setLayoutParams(params);
            txtplan.setTextColor(Color.parseColor("#000000")); // color de texto
            txtplan.setText(list.get(x).getPlan()); // texto
            txtplan.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(txtplan);

            editTextList.add(txtplan);

            // --------------------------------------------------------------------------------------
            //SUBTITULO TRATAMIENTO
            TextView lbTratamiento = new TextView(this);
            lbTratamiento.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) lbTratamiento.getLayoutParams();
            params.setMargins(10, 2, 10, 10); // márgenes
            lbTratamiento.setLayoutParams(params);
            lbTratamiento.setBackgroundColor(Color.parseColor(LILA_CLARO)); // fondo personalizado
            lbTratamiento.setGravity(Gravity.START | Gravity.CENTER_VERTICAL); // gravedad
            lbTratamiento.setText("5. Tratamiento"); // texto
            lbTratamiento.setTextColor(Color.parseColor("#000000")); // color de texto
            lbTratamiento.setTextSize(18); // tamaño de texto
            lbTratamiento.setTypeface(null, Typeface.BOLD); // estilo de texto
            lbTratamiento.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(lbTratamiento);

            // TEXTO DEL SUBTITULO
            EditText txtratamiento = new EditText(this);
            txtratamiento.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) txtratamiento.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            txtratamiento.setLayoutParams(params);
            txtratamiento.setTextColor(Color.parseColor("#000000")); // color de texto
            txtratamiento.setText(list.get(x).getTratamiento()); // texto
            txtratamiento.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(txtratamiento);

            editTextList.add(txtratamiento);

            // --------------------------------------------------------------------------------------
            //SUBTITULO RESULTADO DE LAB
            TextView lbResLab = new TextView(this);
            lbResLab.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) lbResLab.getLayoutParams();
            params.setMargins(10, 2, 10, 10); // márgenes
            lbResLab.setLayoutParams(params);
            lbResLab.setBackgroundColor(Color.parseColor(LILA_CLARO)); // fondo personalizado
            lbResLab.setGravity(Gravity.START | Gravity.CENTER_VERTICAL); // gravedad
            lbResLab.setText("6. Resultados de laboratorio"); // texto
            lbResLab.setTextColor(Color.parseColor("#000000")); // color de texto
            lbResLab.setTextSize(18); // tamaño de texto
            lbResLab.setTypeface(null, Typeface.BOLD); // estilo de texto
            lbResLab.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(lbResLab);


            // TEXTO DEL SUBTITULO
            EditText txtreslab = new EditText(this);
            txtreslab.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) txtreslab.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            txtreslab.setLayoutParams(params);
            txtreslab.setTextColor(Color.parseColor("#000000")); // color de texto
            txtreslab.setText(list.get(x).getResLab()); // texto
            txtreslab.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(txtreslab);

            editTextList.add(txtreslab);


            // --------------------------------------------------------------------------------------
            //SUBTITULO RESULTADOS IMAGENES
            TextView lbresimagen = new TextView(this);
            lbresimagen.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) lbresimagen.getLayoutParams();
            params.setMargins(10, 2, 10, 10); // márgenes
            lbresimagen.setLayoutParams(params);
            lbresimagen.setBackgroundColor(Color.parseColor(LILA_CLARO)); // fondo personalizado
            lbresimagen.setGravity(Gravity.START | Gravity.CENTER_VERTICAL); // gravedad
            lbresimagen.setText("7. Resultados de imágenes"); // texto
            lbresimagen.setTextColor(Color.parseColor("#000000")); // color de texto
            lbresimagen.setTextSize(18); // tamaño de texto
            lbresimagen.setTypeface(null, Typeface.BOLD); // estilo de texto
            lbresimagen.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(lbresimagen);

            // TEXTO DEL SUBTITULO
            EditText txtresimagen = new EditText(this);
            txtresimagen.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) txtresimagen.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            txtresimagen.setLayoutParams(params);
            txtresimagen.setTextColor(Color.parseColor("#000000")); // color de texto
            txtresimagen.setText(list.get(x).getResImagen()); // texto
            txtresimagen.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(txtresimagen);

            editTextList.add(txtresimagen);

            // --------------------------------------------------------------------------------------
            //SUBTITULO PROCEDIMIENTO
            TextView twprocedimiento = new TextView(this);
            twprocedimiento.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) twprocedimiento.getLayoutParams();
            params.setMargins(10, 2, 10, 10); // márgenes
            twprocedimiento.setLayoutParams(params);
            twprocedimiento.setBackgroundColor(Color.parseColor(LILA_CLARO)); // fondo personalizado
            twprocedimiento.setGravity(Gravity.START | Gravity.CENTER_VERTICAL); // gravedad
            twprocedimiento.setText("8. Procedimientos"); // texto
            twprocedimiento.setTextColor(Color.parseColor("#000000")); // color de texto
            twprocedimiento.setTextSize(18); // tamaño de texto
            twprocedimiento.setTypeface(null, Typeface.BOLD); // estilo de texto
            twprocedimiento.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(twprocedimiento);

            // TEXTO DEL SUBTITULO
            EditText txtprocedimiento = new EditText(this);
            txtprocedimiento.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) textCaja.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            txtprocedimiento.setLayoutParams(params);
            txtprocedimiento.setTextColor(Color.parseColor("#000000")); // color de texto
            txtprocedimiento.setText(list.get(x).getProcedimiento()); // texto
            txtprocedimiento.setId(View.generateViewId()); // asignar ID único
            linearLayout.addView(txtprocedimiento);

            editTextList.add(txtprocedimiento);

            // --------------------------------------------------------------------------------------
            // --------------------------------------------------------------------------------------
            //ESCONDIDOS
            // HORA ESCONDIDA
            EditText horaingresoEscondida = new EditText(this);
            horaingresoEscondida.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) horaingresoEscondida.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            horaingresoEscondida.setLayoutParams(params);
            horaingresoEscondida.setTextColor(Color.parseColor("#000000")); // color de texto
            horaingresoEscondida.setText(list.get(x).getHoraingreso()); // texto
            horaingresoEscondida.setId(View.generateViewId()); // asignar ID único

            editTextList.add(horaingresoEscondida);

            // PRIMER INGRESO ESCONDIDA
            EditText primerIngresoEscondido = new EditText(this);
            primerIngresoEscondido.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) primerIngresoEscondido.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            primerIngresoEscondido.setLayoutParams(params);
            primerIngresoEscondido.setTextColor(Color.parseColor("#000000")); // color de texto
            primerIngresoEscondido.setText(list.get(x).getPrimeringreso()); // texto
            primerIngresoEscondido.setId(View.generateViewId()); // asignar ID único

            editTextList.add(primerIngresoEscondido);
            // --------------------------------------------------------------------------------------
            // --------------------------------------------------------------------------------------
            //ESCONDIDOS
            // HORA ESCONDIDA
            EditText idEscondida = new EditText(this);
            idEscondida.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // ancho
                    LinearLayout.LayoutParams.WRAP_CONTENT // alto
            ));
            params = (LinearLayout.LayoutParams) idEscondida.getLayoutParams();
            params.setMargins(10, 0, 10, 10); // márgenes
            idEscondida.setLayoutParams(params);
            idEscondida.setTextColor(Color.parseColor("#000000")); // color de texto
            idEscondida.setText(String.valueOf(list.get(x).getId())); // texto
            idEscondida.setId(View.generateViewId()); // asignar ID único

            editTextList.add(idEscondida);
        }
    }

    private void mostrarOpciones(int indice) {


        final CharSequence[] opciones = {NOMBRE_OPC_ELIMINAR, NOMBRE_OPC_ACTUALIZAR, NOMBRE_OPC_CANCELAR};
        AlertDialog.Builder builder = new AlertDialog.Builder(Cirugia_UCI.this);
        builder.setTitle("Elige una opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                if (opciones[seleccion] == NOMBRE_OPC_ELIMINAR) {
                    eliminar(indice);
                } else if (opciones[seleccion] == NOMBRE_OPC_ACTUALIZAR) {
                    update(indice*13);
                } else if (opciones[seleccion] == NOMBRE_OPC_CANCELAR) {
                    Toast.makeText(Cirugia_UCI.this, ALETAR_OPERACION_CANCELADA, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void eliminar(int indice){
        Alertas.showConfirmationDialog(Cirugia_UCI.this, CONSULTA_OPCION_CONFIRMACION, CONFIRMACION_ELIMINAR, new Alertas.ConfirmationListener() {
            @Override
            public void onConfirmed() {
                float code = funcionalidad_cirugia.eliminar_Cirugia_TIPO(String.valueOf(list.get(indice).getId()),OPC_TABLA_UCI);
                if (code >= 0) {
                    Toast.makeText(Cirugia_UCI.this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
                    list.remove(indice);
                    Intent intent = new Intent(Cirugia_UCI.this, Cirugia_UCI.class);
                    intent.putExtra("idPac", idPac);
                    intent.putExtra("idCama", idCama);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCanceled() {

                Toast.makeText(Cirugia_UCI.this, ALETAR_OPERACION_CANCELADA, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void update(int indice){

        String txtcama = editTextList.get(indice).getText().toString();
        String txtdia = editTextList.get(indice + 1).getText().toString();
        String txtefisico = editTextList.get(indice + 2).getText().toString();
        String txtevolucion = editTextList.get(indice + 3).getText().toString();
        String txtdx = editTextList.get(indice + 4).getText().toString();
        String txtplan = editTextList.get(indice + 5).getText().toString();
        String txttratamiento = editTextList.get(indice + 6).getText().toString();
        String txtreslab = editTextList.get(indice + 7).getText().toString();
        String txtresimagen = editTextList.get(indice + 8).getText().toString();
        String txtprocedimiento = editTextList.get(indice + 9).getText().toString();
        String txthoraingreso = editTextList.get(indice + 10).getText().toString();
        String txtprimeringreso = editTextList.get(indice + 11).getText().toString();
        int id = Integer.parseInt(editTextList.get(indice + 12).getText().toString());


        ObjetoVO objeto = new ObjetoVO(id, txtcama, txtdia, txtefisico, txtevolucion, txtdx, txtplan, txttratamiento, txtreslab, txtresimagen, txtprocedimiento, txthoraingreso, txtprimeringreso);

        int code = funcionalidad_cirugia.actualizar_Paciente_Cirugia(objeto,OPC_TABLA_UCI);

        if(code >= 0){
            Toast.makeText(Cirugia_UCI.this, "Se actualizo ".concat(objeto.getDia()), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Cirugia_UCI.this, Cirugia_Detalle.class);
        intent.putExtra("idPac", idPac);
        intent.putExtra("idCama", idCama);
        startActivity(intent);
        finish();
    }
}
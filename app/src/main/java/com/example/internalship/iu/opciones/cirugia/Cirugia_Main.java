package com.example.internalship.iu.opciones.cirugia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.internalship.R;
import com.example.internalship.VPAdapter;
import com.example.internalship.ViewPageItem;
import com.example.internalship.adapter.ListAdapterPacientesCirugia;
import com.example.internalship.db.Funcionalidad_Cirugia;
import com.example.internalship.vo.cirugiaVO.CPacienteVO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Cirugia_Main extends AppCompatActivity {

    List<CPacienteVO> listaPacientes = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cirugia_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText buscarPaciente = this.findViewById(R.id.buscarPaciente_Cirugia);
        FloatingActionButton btnAgregar = this.findViewById(R.id.btnfloatingActionButton_Cirugia);

        buscarPaciente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Este método se llama antes de que el texto en el EditText cambie
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String texto = s.toString(); // Obtener el texto actual en el EditText
                filtrarpaciente(texto);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Este método se llama después de que el texto en el EditText cambia
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cirugia_Main.this, Cirugia_Add.class);

                startActivity(intent);
            }
        });

        cargarTodo();

        init();
    }

    public void cargarTodo(){
        Funcionalidad_Cirugia cirugia = new Funcionalidad_Cirugia(Cirugia_Main.this);
        listaPacientes = cirugia.listar_Cirugia_Paciente();
    }

    public void filtrarpaciente(String textoBusqueda) {
        List<CPacienteVO> resultados = new ArrayList<>();

        for (CPacienteVO paciente : listaPacientes) {
            if (paciente.getNombre().toLowerCase().contains(textoBusqueda.toLowerCase())) {
                resultados.add(paciente);
            }
        }

        setPacienteAdapter();

        ListAdapterPacientesCirugia listAdapter = new ListAdapterPacientesCirugia(resultados, this);
        RecyclerView recyclerView = findViewById(R.id.listRV_Cirugia);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    private void setPacienteAdapter() {

        LinearLayoutManager manager = new LinearLayoutManager(Cirugia_Main.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        ListAdapterPacientesCirugia listAdapter = new ListAdapterPacientesCirugia(listaPacientes, this);
        RecyclerView recyclerView = findViewById(R.id.listRV_Cirugia);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }

    public void init() {
        ListAdapterPacientesCirugia listAdapter = new ListAdapterPacientesCirugia(listaPacientes, this);
        RecyclerView recyclerView = findViewById(R.id.listRV_Cirugia);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
    
}
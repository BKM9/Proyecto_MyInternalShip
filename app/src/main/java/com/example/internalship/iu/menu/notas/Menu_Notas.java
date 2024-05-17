package com.example.internalship.iu.menu.notas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internalship.MainActivity;
import com.example.internalship.R;
import com.example.internalship.adapter.ListAdapterNotes;
import com.example.internalship.db.Funcionalidad_Notas;
import com.example.internalship.vo.notevo.NoteVO;

import java.util.List;

public class Menu_Notas extends AppCompatActivity {

    Button btnAddNotas;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_notas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAddNotas = findViewById(R.id.btnvista_addNotas);
        init();

        btnAddNotas.setOnClickListener(v -> {
            Intent intent = new Intent(Menu_Notas.this, Menu_Notas_Add.class);
            startActivity(intent);
        });
    }

    public void init() {

        try {
            Funcionalidad_Notas funcionalidad_notas = new Funcionalidad_Notas(this);
            List<NoteVO> listanotas = funcionalidad_notas.listarNotas();

            ListAdapterNotes listAdapter = new ListAdapterNotes(listanotas, this);
            RecyclerView recyclerView = findViewById(R.id.listRV_Notes);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(listAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Menu_Notas.this, MainActivity.class);
        startActivity(intent);
    }
}
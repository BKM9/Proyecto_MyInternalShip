package com.example.internalship.iu.menu.calendario;

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
import com.example.internalship.adapter.ListAdapterAgenda;
import com.example.internalship.vo.actividadesvo.ActividadVO;

import java.util.ArrayList;
import java.util.List;

public class Menu_Agenda extends AppCompatActivity {

    List<ActividadVO> listActividades = new ArrayList<>();
    Button btnvista_addAgenda;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_agenda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnvista_addAgenda = findViewById(R.id.btn_addCalendario);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        listActividades = new ArrayList<>();
        if (bundle != null) {
            listActividades = intent.getParcelableArrayListExtra("actividadesDelDia");
            init();
        }


        btnvista_addAgenda.setOnClickListener(v -> {
            Intent intent1 = new Intent(Menu_Agenda.this, Menu_Agenda_Add.class);
            startActivity(intent1);
        });

    }

    public void init() {
        try {
            ListAdapterAgenda listAdapter = new ListAdapterAgenda(listActividades, this);
            RecyclerView recyclerView = findViewById(R.id.listRV_Agenda);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(listAdapter);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Menu_Agenda.this, MainActivity.class);
        startActivity(intent);
    }
}
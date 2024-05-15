package com.example.internalship.iu.menu.terminos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.internalship.MainActivity;
import com.example.internalship.R;

public class Menu_Terminos_Condiciones extends AppCompatActivity {

    ImageButton regresarmain;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_terminos_condiciones);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        regresarmain = findViewById(R.id.btnTerminosCondiciones_Retroceder);

        regresarmain.setOnClickListener(v -> {
            Intent intent = new Intent(Menu_Terminos_Condiciones.this, MainActivity.class);
            startActivity(intent);
        });
    }

}
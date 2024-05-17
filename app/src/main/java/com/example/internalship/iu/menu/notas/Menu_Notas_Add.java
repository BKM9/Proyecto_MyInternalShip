package com.example.internalship.iu.menu.notas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.internalship.R;
import com.example.internalship.db.Funcionalidad_Notas;
import com.example.internalship.utils.Alertas;

public class Menu_Notas_Add extends AppCompatActivity {

    Button btnGuardar;
    ImageButton btnCancelar;
    EditText etTitulo, etContenido;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_notas_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnGuardar = findViewById(R.id.btnAddNota);
        btnCancelar = findViewById(R.id.btnCancelarNota);
        etTitulo = findViewById(R.id.txtAsunto_nota);
        etContenido = findViewById(R.id.txtCuerpoNota);

        btnGuardar.setOnClickListener(v -> {

            if(etTitulo.getText().toString().isEmpty() && etContenido.getText().toString().isEmpty()){
                Toast.makeText(Menu_Notas_Add.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            }
            else{
                Funcionalidad_Notas notas = new Funcionalidad_Notas(Menu_Notas_Add.this);
                long id = notas.insertarNotas(etTitulo.getText().toString(), etContenido.getText().toString());
                if (id > 0) {
                    Toast.makeText(Menu_Notas_Add.this, "Nota guardada correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Menu_Notas_Add.this, Menu_Notas.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Menu_Notas_Add.this, "Error al guardar nota", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar.setOnClickListener(v -> {
            Alertas.showConfirmationDialog(Menu_Notas_Add.this, "Confirmación", "¿Está seguro que desea descartar nota?", new Alertas.ConfirmationListener() {
                @Override
                public void onConfirmed() {

                    Intent intent = new Intent(Menu_Notas_Add.this, Menu_Notas.class);
                    startActivity(intent);

                }

                @Override
                public void onCanceled() {

                }
            });
        });
        
    }
}
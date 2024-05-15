package com.example.internalship.iu.menu.notas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.internalship.vo.notevo.NoteVO;

public class Menu_Notas_Detalle extends AppCompatActivity {

    EditText txtAsunto, txtDescripcion;
    ImageButton btnActualizar,btnEliminar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_notas_detalle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtAsunto = findViewById(R.id.txtAsunto_nota_MOD);
        txtDescripcion = findViewById(R.id.txtCuerpoNota_MOD);
        btnActualizar = findViewById(R.id.btnActualizarNota_MOD);
        btnEliminar = findViewById(R.id.btnEliminarNota_MOD);

        NoteVO ObjetoNota = getIntent().getParcelableExtra("ObjetoNotas");
        txtAsunto.setText(ObjetoNota.getAsunto());
        txtDescripcion.setText(ObjetoNota.getCuerpo());
        int id = ObjetoNota.getId();

        Funcionalidad_Notas funcionalidad_notas = new Funcionalidad_Notas(Menu_Notas_Detalle.this);

        btnActualizar.setOnClickListener(v -> Alertas.showConfirmationDialog(Menu_Notas_Detalle.this, "Confirmación", "¿Confirma la actualización?", new Alertas.ConfirmationListener() {
            @Override
            public void onConfirmed() {
                long response = funcionalidad_notas.updateNotas(id, txtAsunto.getText().toString(), txtDescripcion.getText().toString());
                if (response > 0){
                    Toast.makeText(Menu_Notas_Detalle.this, "Nota eliminada ".concat(ObjetoNota.getAsunto()), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Menu_Notas_Detalle.this, Menu_Notas.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(Menu_Notas_Detalle.this, "Ocurrio un error durante la eliminación", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCanceled() {
            }
        }));

        btnEliminar.setOnClickListener(v -> Alertas.showConfirmationDialog(Menu_Notas_Detalle.this, "Confirmación", "¿Está seguro que desea eliminar nota?", new Alertas.ConfirmationListener() {
            @Override
            public void onConfirmed() {
                long response = funcionalidad_notas.deleteNotas(id);
                if (response > 0){
                    Toast.makeText(Menu_Notas_Detalle.this, "Nota eliminada ".concat(ObjetoNota.getAsunto()), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Menu_Notas_Detalle.this, Menu_Notas.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(Menu_Notas_Detalle.this, "Ocurrio un error durante la eliminación", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCanceled() {
                Toast.makeText(Menu_Notas_Detalle.this, "Nota no eliminada".concat(ObjetoNota.getAsunto()), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Menu_Notas_Detalle.this, Menu_Notas.class);
        startActivity(intent);
    }

}
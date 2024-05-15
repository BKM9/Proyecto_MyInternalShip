package com.example.internalship;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.internalship.db.DbHelper;
import com.example.internalship.db.Funcionalidad_Actividades;
import com.example.internalship.db.Funcionalidad_Cirugia;
import com.example.internalship.iu.menu.calendario.Menu_Agenda;
import com.example.internalship.iu.menu.notas.Menu_Notas;
import com.example.internalship.vo.actividadesvo.ActividadVO;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton imageButton, btn_main_medicina,btn_main_cirugia;
    Button btnNotificaciones;

    NavigationView navigationViewPrincipal;
    List<ActividadVO> actividadesDelDia = new ArrayList<>();
    Funcionalidad_Actividades funcionalidad_actividades = new Funcionalidad_Actividades(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iniciarbd();
        cantidadActividades();

        btnNotificaciones = findViewById(R.id.btnNotificaciones);
        drawerLayout = findViewById(R.id.drawerLayout);
        imageButton = findViewById(R.id.btnDrowerLayout);
        btn_main_medicina = findViewById(R.id.btn_main_medicina);
        btn_main_cirugia = findViewById(R.id.btn_main_cirugia);
        navigationViewPrincipal = findViewById(R.id.navigationViewPrincipal);

        btnNotificaciones.setText(String.valueOf(actividadesDelDia.size()));

        btnNotificaciones.setOnClickListener(v -> {
            if(!actividadesDelDia.isEmpty()){

                actividadesDelDia.clear();
                actividadesDelDia = funcionalidad_actividades.getobtenerConFechaActualActividad();

                Intent intent = new Intent(MainActivity.this, Menu_Agenda.class);
                intent.putParcelableArrayListExtra("actividadesDelDia", (ArrayList<? extends Parcelable>) actividadesDelDia);
                startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }else{

                Toast.makeText(MainActivity.this,"No tienes actividades en el dia",Toast.LENGTH_LONG).show();

            }

        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

//        btn_main_medicina.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, Vista_Medicina.class);
//            startActivity(intent);
//        });

//        btn_main_cirugia.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, Vista_Cirugia.class);
//            startActivity(intent);
//        });

        navigationViewPrincipal.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                if(itemId == R.id.nav_notas){

                    Intent intent = new Intent(MainActivity.this, Menu_Notas.class);

                    startActivity(intent);

                    return true;
                }else if(itemId == R.id.nav_agenda){
                    actividadesDelDia.clear();
                    actividadesDelDia = funcionalidad_actividades.getMostrarActividades();
                    Intent intent = new Intent(MainActivity.this, Menu_Agenda.class);
                    intent.putParcelableArrayListExtra("actividadesDelDia", (ArrayList<? extends Parcelable>) actividadesDelDia);
                    startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                }

                return false;
            }
        });

    }

    private void iniciarbd(){
        DbHelper dbHelper=new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db != null){
            Toast.makeText(this,"Datos Cargados",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"ERROR AL CONECTAR CON LA BD",Toast.LENGTH_LONG).show();
        }
    }

    private int cantidadActividades(){
        actividadesDelDia.clear();
        actividadesDelDia = funcionalidad_actividades.getobtenerConFechaActualActividad();
        return actividadesDelDia.size();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Estás seguro?");
        builder.setMessage("¿Quieres salir de la aplicación?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}
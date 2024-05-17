package com.example.internalship;

import static com.example.internalship.utils.Constantes.TIPO_ECOGRAFIA;
import static com.example.internalship.utils.Constantes.TIPO_EXTRA;
import static com.example.internalship.utils.Constantes.TIPO_RAYOS_X;
import static com.example.internalship.utils.Constantes.TIPO_TOMOGRAFIA;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.internalship.db.Funcionalidad_Cirugia;
import com.example.internalship.vo.photo.foto;

import java.util.ArrayList;
import java.util.List;

public class Cirugia_Imagenes extends AppCompatActivity {

    ViewPager2 viewPager2Ecogracia,viewPager2Resonancia,viewPager2Radiografia,viewPager2Extras;

    ArrayList<ViewPageItem> viewPageItemsRayosX,viewPageItemsEcografia,viewPageItemsTomografia,viewPageItemsExtras;

    Button btnImg_Cirugia;

    Funcionalidad_Cirugia funcionalidad_cirugia = new Funcionalidad_Cirugia(Cirugia_Imagenes.this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cirugia_imagen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager2Ecogracia = findViewById(R.id.viewpager_ECOGRAFIA_CIRUGIA);
        viewPager2Resonancia = findViewById(R.id.viewpager_RESONANCIA_CIRUGIA);
        viewPager2Radiografia = findViewById(R.id.viewpager_RADIOLOGIA_CIRUGIA);
        viewPager2Extras = findViewById(R.id.viewpager_OTROS_CIRUGIA);

        btnImg_Cirugia = findViewById(R.id.btnImg_Cirugia);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int idPac = 100000;
        if (bundle != null) {
            idPac = bundle.getInt("idPaciente");
        }
        int finalIdPac = idPac;

        btnImg_Cirugia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cirugia_Imagenes.this, Cirugia_Imagen_Add.class);
                Bundle bundle = new Bundle();
                bundle.putInt("idPaciente", finalIdPac);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        viewPageItemsEcografia = cargarImagenesPaciente_Ecografia(String.valueOf(finalIdPac));
        if(!viewPageItemsEcografia.isEmpty()){
            VewPagerAdapterCirugia viewPagerAdapterEcografia = new VewPagerAdapterCirugia(viewPageItemsEcografia, Cirugia_Imagenes.this);
            viewPager2Ecogracia.setClipToPadding(false);
            viewPager2Ecogracia.setClipChildren(false);
            viewPager2Ecogracia.setOffscreenPageLimit(2);
            viewPager2Ecogracia.setAdapter(viewPagerAdapterEcografia);
            viewPager2Ecogracia.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
            viewPager2Ecogracia.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        }

        viewPageItemsTomografia = cargarImagenesPaciente_Tomografia(String.valueOf(finalIdPac));

        if(!viewPageItemsTomografia.isEmpty()){
            VewPagerAdapterCirugia viewPagerAdapterTomografia = new VewPagerAdapterCirugia(viewPageItemsTomografia, Cirugia_Imagenes.this);
            viewPager2Resonancia.setClipToPadding(false);
            viewPager2Resonancia.setClipChildren(false);
            viewPager2Resonancia.setOffscreenPageLimit(2);
            viewPager2Resonancia.setAdapter(viewPagerAdapterTomografia);
            viewPager2Resonancia.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
            viewPager2Resonancia.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        }

        viewPageItemsRayosX = cargarImagenesPaciente_RayosX(String.valueOf(finalIdPac));

        if(!viewPageItemsRayosX.isEmpty()){
            VewPagerAdapterCirugia viewPagerAdapterRadiografia = new VewPagerAdapterCirugia(viewPageItemsRayosX, Cirugia_Imagenes.this);
            viewPager2Radiografia.setClipToPadding(false);
            viewPager2Radiografia.setClipChildren(false);
            viewPager2Radiografia.setOffscreenPageLimit(2);
            viewPager2Radiografia.setAdapter(viewPagerAdapterRadiografia);
            viewPager2Radiografia.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
            viewPager2Radiografia.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        }

        viewPageItemsExtras = cargarImagenesPaciente_Extras(String.valueOf(finalIdPac));

        if(!viewPageItemsExtras.isEmpty()){
            VewPagerAdapterCirugia viewPagerAdapterExtra = new VewPagerAdapterCirugia(viewPageItemsExtras, Cirugia_Imagenes.this);
            viewPager2Extras.setClipToPadding(false);
            viewPager2Extras.setClipChildren(false);
            viewPager2Extras.setOffscreenPageLimit(2);
            viewPager2Extras.setAdapter(viewPagerAdapterExtra);
            viewPager2Extras.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
            viewPager2Extras.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        }
    }

    public ArrayList<ViewPageItem> cargarImagenesPaciente_RayosX(String idPac){
        ArrayList<ViewPageItem> viewPageItems = new ArrayList<>();

        List<foto> list_Fotos = funcionalidad_cirugia.list_Fotos(idPac, TIPO_RAYOS_X );

        for (int i = 0; i < list_Fotos.size(); i++) {
            ViewPageItem viewPageItem = new ViewPageItem(String.valueOf(list_Fotos.get(i).getId()),list_Fotos.get(i).getUrl(), list_Fotos.get(i).getTitulo(), list_Fotos.get(i).getDescripcion());
            viewPageItems.add(viewPageItem);
        }

        return viewPageItems;
    }

    public ArrayList<ViewPageItem> cargarImagenesPaciente_Ecografia(String idPac){
        ArrayList<ViewPageItem> viewPageItems = new ArrayList<>();

        List<foto> list_Fotos = funcionalidad_cirugia.list_Fotos(idPac,TIPO_ECOGRAFIA);

        for (int i = 0; i < list_Fotos.size(); i++) {
            ViewPageItem viewPageItem = new ViewPageItem(String.valueOf(list_Fotos.get(i).getId()),list_Fotos.get(i).getUrl(), list_Fotos.get(i).getTitulo(), list_Fotos.get(i).getDescripcion());
            viewPageItems.add(viewPageItem);
        }

        return viewPageItems;
    }

    public ArrayList<ViewPageItem> cargarImagenesPaciente_Tomografia(String idPac){
        ArrayList<ViewPageItem> viewPageItems = new ArrayList<>();

        List<foto> list_Fotos = funcionalidad_cirugia.list_Fotos(idPac,TIPO_TOMOGRAFIA);

        for (int i = 0; i < list_Fotos.size(); i++) {
            ViewPageItem viewPageItem = new ViewPageItem(String.valueOf(list_Fotos.get(i).getId()),list_Fotos.get(i).getUrl(), list_Fotos.get(i).getTitulo(), list_Fotos.get(i).getDescripcion());
            viewPageItems.add(viewPageItem);
        }

        return viewPageItems;
    }

    public ArrayList<ViewPageItem> cargarImagenesPaciente_Extras(String idPac){
        ArrayList<ViewPageItem> viewPageItems = new ArrayList<>();

        List<foto> list_Fotos = funcionalidad_cirugia.list_Fotos(idPac, TIPO_EXTRA);

        for (int i = 0; i < list_Fotos.size(); i++) {
            ViewPageItem viewPageItem = new ViewPageItem(String.valueOf(list_Fotos.get(i).getId()),list_Fotos.get(i).getUrl(), list_Fotos.get(i).getTitulo(), list_Fotos.get(i).getDescripcion());
            viewPageItems.add(viewPageItem);
        }

        return viewPageItems;
    }
}
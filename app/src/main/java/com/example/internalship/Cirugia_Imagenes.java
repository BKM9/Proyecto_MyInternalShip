package com.example.internalship;

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

import java.util.ArrayList;

public class Cirugia_Imagenes extends AppCompatActivity {

    ViewPager2 viewPager2Ecogracia,viewPager2Resonancia,viewPager2Radiografia;

    ArrayList<ViewPageItem> viewPageItems;

    Button btnImg_Cirugia;

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

        int[] images = { R.mipmap.fondoterminosycondiciones , R.mipmap.fondoterminosycondiciones , R.mipmap.fondoterminosycondiciones};
        String[] heading = {"12-12-2023", "15-12-2023","24-12-2023"};
        String[] description = {"Prueba de la imagen 1",
                "Prueba de la imagen 2",
                "Prueba de la imagen 3"};

        viewPageItems = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {
            ViewPageItem viewPageItem = new ViewPageItem(images[i], heading[i], description[i]);
            viewPageItems.add(viewPageItem);
        }

        viewPager2Ecogracia = findViewById(R.id.viewpager_ECOGRAFIA);
        viewPager2Resonancia = findViewById(R.id.viewpager_RESONANCIA);
        viewPager2Radiografia = findViewById(R.id.viewpager_RADIOLOGIA);
        btnImg_Cirugia = findViewById(R.id.btnImg_Cirugia);

        btnImg_Cirugia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cirugia_Imagenes.this, Cirugia_Imagen_Add.class);
                startActivity(intent);
            }
        });


        VPAdapter viewPagerAdapterEcografia = new VPAdapter(viewPageItems);
        viewPager2Ecogracia.setClipToPadding(false);
        viewPager2Ecogracia.setClipChildren(false);
        viewPager2Ecogracia.setOffscreenPageLimit(2);
        viewPager2Ecogracia.setAdapter(viewPagerAdapterEcografia);
        viewPager2Ecogracia.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager2Ecogracia.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);


        VPAdapter viewPagerAdapterResonancia = new VPAdapter(viewPageItems);
        viewPager2Resonancia.setClipToPadding(false);
        viewPager2Resonancia.setClipChildren(false);
        viewPager2Resonancia.setOffscreenPageLimit(2);
        viewPager2Resonancia.setAdapter(viewPagerAdapterResonancia);
        viewPager2Resonancia.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager2Resonancia.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        VPAdapter viewPagerAdapterRadiografia = new VPAdapter(viewPageItems);
        viewPager2Radiografia.setClipToPadding(false);
        viewPager2Radiografia.setClipChildren(false);
        viewPager2Radiografia.setOffscreenPageLimit(2);
        viewPager2Radiografia.setAdapter(viewPagerAdapterRadiografia);
        viewPager2Radiografia.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager2Radiografia.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
    }
}
package com.example.internalship.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    //NO TOCAR LA DATABASE_VERSION SI NO SE BORRA LO ALMACENADO
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "notas.db";
    public static final String TABLE_NOTAS = "t_notas";
    public static final String TABLE_ACTIVIDADES = "t_actividades";

    // ---  TABLAS DE MEDICINA   ( ESTA GUARDADAD EN BD ONLINE )
    public static final String TABLE_PACIENTES_MEDICINA = "t_pacientes_medicina";
    public static final String TABLE_PACIENTES_MEDICINA_OBS = "t_pacientes_cirugia_OBS";
    public static final String TABLE_PACIENTES_MEDICINA_UCI = "t_pacientes_cirugia_UCI";
    public static final String TABLE_PACIENTES_MEDICINA_UCIN = "t_pacientes_cirugia_UCIN";
    public static final String TABLE_PACIENTES_MEDICINA_HMED = "t_pacientes_cirugia_HMED";
    public static final String TABLE_PACIENTES_MEDICINA_UTS = "t_pacientes_cirugia_UTS";

    // ---  TABLAS DE CIRUGIA
    public static final String TABLE_PACIENTES_CIRUGIA = "t_pacientes_cirugia";
    public static final String TABLE_PACIENTES_CIRUGIA_HCIRUGIA = "t_pacientes_cirugia_HCIRUGIA";
    public static final String TABLE_PACIENTES_CIRUGIA_UCI = "t_pacientes_cirugia_UCI";
    public static final String TABLE_PACIENTES_CIRUGIA_OBS = "t_pacientes_cirugia_OBS";
    public static final String TABLE_PACIENTES_CIRUGIA_TSHOCK = "t_pacientes_cirugia_TSHOCK";

    // --- TABLA IMAGENES
    public static final String TABLE_IMAGENES = "t_imagenes";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NOTAS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "asunto TEXT NOT NULL," +
                "cuerpo TEXT NOT NULL )");


        db.execSQL("CREATE TABLE " + TABLE_ACTIVIDADES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "asunto TEXT NOT NULL," +
                "fecha TEXT NOT NULL," +
                "hora TEXT NOT NULL," +
                "descripcion TEXT NOT NULL)");

        generarTablas_Cirugia(db);
    }

    public void generarTablas_Cirugia(@NonNull SQLiteDatabase db){

        db.execSQL("CREATE TABLE " + TABLE_PACIENTES_CIRUGIA + " (" +
                "id_pac INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "cama TEXT," +
                "hc TEXT," +
                "edad TEXT," +
                "antecedentes_qx_personales_alergias TEXT," +
                "fi TEXT," +
                "horaingreso TEXT," +
                "te TEXT," +
                "anamnesis TEXT," +
                "dx TEXT," +
                "tto TEXT," +
                "plann TEXT," +
                "reevaluacion TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_PACIENTES_CIRUGIA_HCIRUGIA + " (" +
                "id_pac_hcirugia INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cama TEXT," +
                "dia TEXT," +
                "efisico TEXT," +
                "evolucion TEXT," +
                "dx TEXT," +
                "plann TEXT," +
                "tratamiento TEXT," +
                "resLab TEXT," +
                "resImagen TEXT," +
                "procedimiento TEXT," +
                "horaingreso TEXT," +
                "primeringreso TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_PACIENTES_CIRUGIA_UCI + " (" +
                "id_pac_uci INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cama TEXT," +
                "dia TEXT," +
                "efisico TEXT," +
                "evolucion TEXT," +
                "dx TEXT," +
                "plann TEXT," +
                "tratamiento TEXT," +
                "resLab TEXT," +
                "resImagen TEXT," +
                "procedimiento TEXT," +
                "horaingreso TEXT," +
                "primeringreso TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_PACIENTES_CIRUGIA_OBS + " (" +
                "id_pac_obs INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cama TEXT," +
                "dia TEXT," +
                "efisico TEXT," +
                "evolucion TEXT," +
                "dx TEXT," +
                "plann TEXT," +
                "tratamiento TEXT," +
                "resLab TEXT," +
                "resImagen TEXT," +
                "procedimiento TEXT," +
                "horaingreso TEXT," +
                "primeringreso TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_PACIENTES_CIRUGIA_TSHOCK + " (" +
                "id_pac_tshock INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cama TEXT," +
                "dia TEXT," +
                "efisico TEXT," +
                "evolucion TEXT," +
                "dx TEXT," +
                "plann TEXT," +
                "tratamiento TEXT," +
                "resLab TEXT," +
                "resImagen TEXT," +
                "procedimiento TEXT," +
                "horaingreso TEXT," +
                "primeringreso TEXT)");

    }

    public void generarTabla_Imagenes(@NonNull SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_IMAGENES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "url TEXT NOT NULL," +
                "idPaciente INTEGER NOT NULL," +
                "tipoFoto TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                generarTabla_Imagenes(db);
        }
    }
}

package com.example.internalship.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.internalship.vo.activityVO.ActividadVO;

import java.util.ArrayList;
import java.util.List;

public class Funcionalidad_Actividades extends DbHelper {
    Context context;
    public Funcionalidad_Actividades(Context context) {
        super(context);
        this.context = context;
    }

    public long insertarActividad(String asunto, String fecha, String hora, String descripcion) {

        long id = 0;
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("asunto", asunto);
            contentValues.put("fecha", fecha);
            contentValues.put("hora", hora);
            contentValues.put("descripcion", descripcion);

            id = db.insert(TABLE_ACTIVIDADES, null, contentValues);

        } catch (Exception e){
            e.toString();
        }

        return id;
    }

    public int actualizarActividad(int id, String asunto, String fecha, String hora, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("asunto", asunto);
        contentValues.put("fecha", fecha);
        contentValues.put("hora", hora);
        contentValues.put("descripcion", descripcion);
        return db.update(TABLE_ACTIVIDADES, contentValues, "id = ?", new String[]{String.valueOf(id)});
    }

    public int eliminarActividad(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_ACTIVIDADES, "id = ?", new String[]{String.valueOf(id)});
    }

    public List<ActividadVO> getMostrarActividades() {
        List<ActividadVO> datos = new ArrayList<>();

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACTIVIDADES, null);

            if (cursor.moveToFirst()){
                do {
                    ActividadVO actividadVO = new ActividadVO();
                    actividadVO.setId(cursor.getInt(0));
                    actividadVO.setAsunto(cursor.getString(1));
                    actividadVO.setFecha(cursor.getString(2));
                    actividadVO.setHora(cursor.getString(3));
                    actividadVO.setDescripcion(cursor.getString(4));
                    datos.add(actividadVO);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return datos;
    }

    public List<ActividadVO>  getobtenerConFechaActualActividad() {
        List<ActividadVO> datos = new ArrayList<>();

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACTIVIDADES + " WHERE fecha = date('now')", null);

            if (cursor.moveToFirst()){
                do {
                    ActividadVO actividadVO = new ActividadVO();
                    actividadVO.setId(cursor.getInt(0));
                    actividadVO.setAsunto(cursor.getString(1));
                    actividadVO.setFecha(cursor.getString(2));
                    actividadVO.setHora(cursor.getString(3));
                    actividadVO.setDescripcion(cursor.getString(4));
                    datos.add(actividadVO);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return datos;
    }

}

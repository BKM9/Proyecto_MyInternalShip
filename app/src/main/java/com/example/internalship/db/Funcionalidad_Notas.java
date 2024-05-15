package com.example.internalship.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import com.example.internalship.vo.notevo.NoteVO;

import java.util.ArrayList;
import java.util.List;

public class Funcionalidad_Notas extends DbHelper{

    Context context;

    public Funcionalidad_Notas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarNotas(String asunto, String cuerpo){

        long id = 0;

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("asunto", asunto);
            values.put("cuerpo", cuerpo);

            id = db.insert(TABLE_NOTAS, null, values);

        } catch (Exception e){
            e.toString();
        }

        return id;
    }

    public List<NoteVO> listarNotas(){

        List<NoteVO> datos = new ArrayList<>();

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = cursor = db.rawQuery("SELECT * FROM " + TABLE_NOTAS, null);

            if (cursor.moveToFirst()){
                do {
                    NoteVO nota = new NoteVO();
                    nota.setId(cursor.getInt(0));
                    nota.setAsunto(cursor.getString(1));
                    nota.setCuerpo(cursor.getString(2));
                    datos.add(nota);
                } while (cursor.moveToNext());
            }
        } catch (Exception e){
            e.toString();
        }

        return datos;
    }

    public long deleteNotas(int id){
        int response = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            response = db.delete(TABLE_NOTAS, "id = ?", new String[]{String.valueOf(id)});
        } catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    public long updateNotas(int id, String asunto, String descripcion){
        int response = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("asunto", asunto);
            values.put("cuerpo", descripcion);

            response = db.update(TABLE_NOTAS,values, "id = ?", new String[]{String.valueOf(id)});
        } catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }
}

package com.example.internalship.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.internalship.vo.cirugiaVO.CHCirugiaVO;
import com.example.internalship.vo.cirugiaVO.CObservacionesVO;
import com.example.internalship.vo.cirugiaVO.CPacienteVO;
import com.example.internalship.vo.cirugiaVO.CShockVO;
import com.example.internalship.vo.cirugiaVO.CUcisVO;

import java.util.ArrayList;
import java.util.List;

public class Funcionalidad_Cirugia extends DbHelper {
    Context context;
    public Funcionalidad_Cirugia(Context context) {
        super(context);
        this.context = context;
    }

    public long insertar_Cirugia_Paciente(String nombre,String cama, String hc, String edad, String fi,
                                          String horaingreso, String te, String anamnesis, String antecedentes_qx_personales_alergias,
                                          String dx, String tto, String plann, String reevaluacion) {

        long id = 0;
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("nombre", nombre);
            contentValues.put("cama", cama);
            contentValues.put("hc", hc);
            contentValues.put("edad", edad);
            contentValues.put("fi", fi);
            contentValues.put("horaingreso", horaingreso);
            contentValues.put("te", te);
            contentValues.put("anamnesis", anamnesis);
            contentValues.put("antecedentes_qx_personales_alergias", antecedentes_qx_personales_alergias);
            contentValues.put("dx", dx);
            contentValues.put("tto", tto);
            contentValues.put("plann", plann);
            contentValues.put("reevaluacion", reevaluacion);

            id = db.insert(TABLE_PACIENTES_CIRUGIA, null, contentValues);

        } catch (Exception e){
            e.toString();
        }

        return id;
    }

    public int actualizar_Paciente_Cirugia(int id,String nombre,String cama, String hc, String edad, String fi,
                                           String horaingreso, String te, String anamnesis, String antecedentes_qx_personales_alergias,
                                           String dx, String tto, String plann, String reevaluacion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("cama", cama);
        contentValues.put("hc", hc);
        contentValues.put("edad", edad);
        contentValues.put("fi", fi);
        contentValues.put("horaingreso", horaingreso);
        contentValues.put("te", te);
        contentValues.put("anamnesis", anamnesis);
        contentValues.put("antecedentes_qx_personales_alergias", antecedentes_qx_personales_alergias);
        contentValues.put("dx", dx);
        contentValues.put("tto", tto);
        contentValues.put("plann", plann);
        contentValues.put("reevaluacion", reevaluacion);

        return db.update(TABLE_PACIENTES_CIRUGIA, contentValues, "id_pac = ?", new String[]{String.valueOf(id)});
    }

    public long insertar_Cirugia_HCirugia(String cama,String dia, String efisico, String evolucion,
                                          String dx, String plan, String tratamiento,
                                          String resLab, String resImagen, String procedimiento,
                                          String horaingreso, String primeringreso) {

        long id = 0;
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("cama", cama);
            contentValues.put("dia", dia);
            contentValues.put("efisico", efisico);
            contentValues.put("evolucion", evolucion);
            contentValues.put("dx", dx);
            contentValues.put("plann", plan);
            contentValues.put("tratamiento", tratamiento);
            contentValues.put("resLab", resLab);
            contentValues.put("resImagen", resImagen);
            contentValues.put("procedimiento", procedimiento);
            contentValues.put("horaingreso", horaingreso);
            contentValues.put("primeringreso", primeringreso);

            id = db.insert(TABLE_PACIENTES_CIRUGIA_HCIRUGIA, null, contentValues);

        } catch (Exception e){
            e.toString();
        }
        return id;
    }

    public long insertar_Cirugia_UCI(String cama,String dia, String efisico, String evolucion,
                                          String dx, String plan, String tratamiento,
                                          String resLab, String resImagen, String procedimiento,
                                          String horaingreso, String primeringreso) {

        long id = 0;
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("cama", cama);
            contentValues.put("dia", dia);
            contentValues.put("efisico", efisico);
            contentValues.put("evolucion", evolucion);
            contentValues.put("dx", dx);
            contentValues.put("plann", plan);
            contentValues.put("tratamiento", tratamiento);
            contentValues.put("resLab", resLab);
            contentValues.put("resImagen", resImagen);
            contentValues.put("procedimiento", procedimiento);
            contentValues.put("horaingreso", horaingreso);
            contentValues.put("primeringreso", primeringreso);

            id = db.insert(TABLE_PACIENTES_CIRUGIA_UCI, null, contentValues);

        } catch (Exception e){
            e.toString();
        }
        return id;
    }

    public long insertar_Cirugia_OBS(String cama,String dia, String efisico, String evolucion,
                                     String dx, String plan, String tratamiento,
                                     String resLab, String resImagen, String procedimiento,
                                     String horaingreso, String primeringreso) {

        long id = 0;
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("cama", cama);
            contentValues.put("dia", dia);
            contentValues.put("efisico", efisico);
            contentValues.put("evolucion", evolucion);
            contentValues.put("dx", dx);
            contentValues.put("plann", plan);
            contentValues.put("tratamiento", tratamiento);
            contentValues.put("resLab", resLab);
            contentValues.put("resImagen", resImagen);
            contentValues.put("procedimiento", procedimiento);
            contentValues.put("horaingreso", horaingreso);
            contentValues.put("primeringreso", primeringreso);

            id = db.insert(TABLE_PACIENTES_CIRUGIA_OBS, null, contentValues);

        } catch (Exception e){
            e.toString();
        }
        return id;
    }

    public long insertar_Cirugia_TSHOCK(String cama,String dia, String efisico, String evolucion,
                                     String dx, String plan, String tratamiento,
                                     String resLab, String resImagen, String procedimiento,
                                     String horaingreso, String primeringreso) {

        long id = 0;
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("cama", cama);
            contentValues.put("dia", dia);
            contentValues.put("efisico", efisico);
            contentValues.put("evolucion", evolucion);
            contentValues.put("dx", dx);
            contentValues.put("plann", plan);
            contentValues.put("tratamiento", tratamiento);
            contentValues.put("resLab", resLab);
            contentValues.put("resImagen", resImagen);
            contentValues.put("procedimiento", procedimiento);
            contentValues.put("horaingreso", horaingreso);
            contentValues.put("primeringreso", primeringreso);

            id = db.insert(TABLE_PACIENTES_CIRUGIA_TSHOCK, null, contentValues);

        } catch (Exception e){
            e.toString();
        }
        return id;
    }

    public int actualizar_Paciente_Cirugia_HCIRUGIA(int id, String cama,String dia, String efisico, String evolucion,
                                                    String dx, String plan, String tratamiento,
                                                    String resLab, String resImagen, String procedimiento,
                                                    String horaingreso, String primeringreso) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cama", cama);
        contentValues.put("dia", dia);
        contentValues.put("efisico", efisico);
        contentValues.put("evolucion", evolucion);
        contentValues.put("dx", dx);
        contentValues.put("plann", plan);
        contentValues.put("tratamiento", tratamiento);
        contentValues.put("resLab", resLab);
        contentValues.put("resImagen", resImagen);
        contentValues.put("procedimiento", procedimiento);
        contentValues.put("horaingreso", horaingreso);
        contentValues.put("primeringreso", primeringreso);

        return db.update(TABLE_PACIENTES_CIRUGIA_HCIRUGIA, contentValues, "id_pac_hcirugia = ?", new String[]{String.valueOf(id)});
    }

    public int actualizar_Paciente_Cirugia_UCI(int id, String cama,String dia, String efisico, String evolucion,
                                                    String dx, String plan, String tratamiento,
                                                    String resLab, String resImagen, String procedimiento,
                                                    String horaingreso, String primeringreso) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cama", cama);
        contentValues.put("dia", dia);
        contentValues.put("efisico", efisico);
        contentValues.put("evolucion", evolucion);
        contentValues.put("dx", dx);
        contentValues.put("plann", plan);
        contentValues.put("tratamiento", tratamiento);
        contentValues.put("resLab", resLab);
        contentValues.put("resImagen", resImagen);
        contentValues.put("procedimiento", procedimiento);
        contentValues.put("horaingreso", horaingreso);
        contentValues.put("primeringreso", primeringreso);

        return db.update(TABLE_PACIENTES_CIRUGIA_UCI, contentValues, "id_pac_uci = ?", new String[]{String.valueOf(id)});
    }

    public int actualizar_Paciente_Cirugia_OBS(int id, String cama,String dia, String efisico, String evolucion,
                                               String dx, String plan, String tratamiento,
                                               String resLab, String resImagen, String procedimiento,
                                               String horaingreso, String primeringreso) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cama", cama);
        contentValues.put("dia", dia);
        contentValues.put("efisico", efisico);
        contentValues.put("evolucion", evolucion);
        contentValues.put("dx", dx);
        contentValues.put("plann", plan);
        contentValues.put("tratamiento", tratamiento);
        contentValues.put("resLab", resLab);
        contentValues.put("resImagen", resImagen);
        contentValues.put("procedimiento", procedimiento);
        contentValues.put("horaingreso", horaingreso);
        contentValues.put("primeringreso", primeringreso);

        return db.update(TABLE_PACIENTES_CIRUGIA_OBS, contentValues, "id = ?", new String[]{String.valueOf(id)});
    }

    public int actualizar_Paciente_Cirugia_TSHOCK(int id, String cama,String dia, String efisico, String evolucion,
                                               String dx, String plan, String tratamiento,
                                               String resLab, String resImagen, String procedimiento,
                                               String horaingreso, String primeringreso) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cama", cama);
        contentValues.put("dia", dia);
        contentValues.put("efisico", efisico);
        contentValues.put("evolucion", evolucion);
        contentValues.put("dx", dx);
        contentValues.put("plann", plan);
        contentValues.put("tratamiento", tratamiento);
        contentValues.put("resLab", resLab);
        contentValues.put("resImagen", resImagen);
        contentValues.put("procedimiento", procedimiento);
        contentValues.put("horaingreso", horaingreso);
        contentValues.put("primeringreso", primeringreso);

        return db.update(TABLE_PACIENTES_CIRUGIA_TSHOCK, contentValues, "id = ?", new String[]{String.valueOf(id)});
    }

    public int eliminar_Cirugia_Paciente(int id, String cama) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PACIENTES_CIRUGIA, "cama = ?", new String[]{cama});
        db.delete(TABLE_PACIENTES_CIRUGIA_UCI, "cama = ?", new String[]{cama});
        db.delete(TABLE_PACIENTES_CIRUGIA_OBS, "cama = ?", new String[]{cama});
        db.delete(TABLE_PACIENTES_CIRUGIA_TSHOCK, "cama = ?", new String[]{cama});

        return db.delete(TABLE_PACIENTES_CIRUGIA, "id_pac = ?", new String[]{String.valueOf(id)});
    }

    public List<CPacienteVO> listar_Cirugia_Paciente() {
        List<CPacienteVO> datos = new ArrayList<>();

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES_CIRUGIA, null);

            if (cursor.moveToFirst()){
                do {

                    CPacienteVO paciente = new CPacienteVO();
                    paciente.setId(cursor.getInt(0));
                    paciente.setNombre(cursor.getString(1));
                    String camaslect = cursor.getString(2);
                    paciente.setCama(camaslect);
                    paciente.setHc(cursor.getString(3));
                    paciente.setEdad(cursor.getString(4));
                    paciente.setAntecedentes_qx_personales_alergias(cursor.getString(5));
                    paciente.setFi(cursor.getString(6));
                    paciente.setHoraingreso(cursor.getString(7));
                    paciente.setTe(cursor.getString(8));
                    paciente.setAnamnesis(cursor.getString(9));
                    paciente.setDx(cursor.getString(10));
                    paciente.setTto(cursor.getString(11));
                    paciente.setPlan(cursor.getString(12));
                    paciente.setReevaluacion(cursor.getString(13));

                    paciente.setHcirugia(list_CHCirugiaVO(camaslect));
                    paciente.setUci(list_CUCIVO(camaslect));
                    paciente.setObservaciones(list_COBS(camaslect));
                    paciente.setShock(list_TSHOCK(camaslect));

                    datos.add(paciente);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return datos;
    }
    public List<CHCirugiaVO> list_CHCirugiaVO(String cama){
        List<CHCirugiaVO> listdatos = new ArrayList<>();

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES_CIRUGIA_HCIRUGIA + " WHERE cama = ?", new String[]{cama});

            if (cursor.moveToFirst()){
                do {

                    CHCirugiaVO datos = new CHCirugiaVO();
                    datos.setId(cursor.getInt(0));
                    datos.setCama(cursor.getString(1));
                    datos.setDia(cursor.getString(2));
                    datos.setEfisico(cursor.getString(3));
                    datos.setEvolucion(cursor.getString(4));
                    datos.setDx(cursor.getString(5));
                    datos.setPlan(cursor.getString(6));
                    datos.setTratamiento(cursor.getString(7));
                    datos.setResLab(cursor.getString(8));
                    datos.setResImagen(cursor.getString(9));
                    datos.setProcedimiento(cursor.getString(10));
                    datos.setHoraingreso(cursor.getString(11));
                    datos.setPrimeringreso(cursor.getString(12));

                    listdatos.add(datos);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return listdatos;
    }
    public List<CUcisVO> list_CUCIVO(String cama){
        List<CUcisVO> listdatos = new ArrayList<>();

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES_CIRUGIA_UCI + " WHERE cama = ?", new String[]{cama});

            if (cursor.moveToFirst()){
                do {

                    CUcisVO datos = new CUcisVO();
                    datos.setId(cursor.getInt(0));
                    datos.setCama(cursor.getString(1));
                    datos.setDia(cursor.getString(2));
                    datos.setEfisico(cursor.getString(3));
                    datos.setEvolucion(cursor.getString(4));
                    datos.setDx(cursor.getString(5));
                    datos.setPlan(cursor.getString(6));
                    datos.setTratamiento(cursor.getString(7));
                    datos.setResLab(cursor.getString(8));
                    datos.setResImagen(cursor.getString(9));
                    datos.setProcedimiento(cursor.getString(10));
                    datos.setHoraingreso(cursor.getString(11));
                    datos.setPrimeringreso(cursor.getString(12));

                    listdatos.add(datos);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return listdatos;
    }
    public List<CObservacionesVO> list_COBS(String cama){

        List<CObservacionesVO> listdatos = new ArrayList<>();

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES_CIRUGIA_OBS + " WHERE cama = ?", new String[]{cama});

            if (cursor.moveToFirst()){
                do {

                    CObservacionesVO datos = new CObservacionesVO();
                    datos.setId(cursor.getInt(0));
                    datos.setCama(cursor.getString(1));
                    datos.setDia(cursor.getString(2));
                    datos.setEfisico(cursor.getString(3));
                    datos.setEvolucion(cursor.getString(4));
                    datos.setDx(cursor.getString(5));
                    datos.setPlan(cursor.getString(6));
                    datos.setTratamiento(cursor.getString(7));
                    datos.setResLab(cursor.getString(8));
                    datos.setResImagen(cursor.getString(9));
                    datos.setProcedimiento(cursor.getString(10));
                    datos.setHoraingreso(cursor.getString(11));
                    datos.setPrimeringreso(cursor.getString(12));

                    listdatos.add(datos);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return listdatos;
    }
    public List<CShockVO> list_TSHOCK(String cama){
        List<CShockVO> listdatos = new ArrayList<>();

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES_CIRUGIA_TSHOCK + " WHERE cama = ?", new String[]{cama});

            if (cursor.moveToFirst()){
                do {

                    CShockVO datos = new CShockVO();
                    datos.setId(cursor.getInt(0));
                    datos.setCama(cursor.getString(1));
                    datos.setDia(cursor.getString(2));
                    datos.setEfisico(cursor.getString(3));
                    datos.setEvolucion(cursor.getString(4));
                    datos.setDx(cursor.getString(5));
                    datos.setPlan(cursor.getString(6));
                    datos.setTratamiento(cursor.getString(7));
                    datos.setResLab(cursor.getString(8));
                    datos.setResImagen(cursor.getString(9));
                    datos.setProcedimiento(cursor.getString(10));
                    datos.setHoraingreso(cursor.getString(11));
                    datos.setPrimeringreso(cursor.getString(12));

                    listdatos.add(datos);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return listdatos;
    }

}

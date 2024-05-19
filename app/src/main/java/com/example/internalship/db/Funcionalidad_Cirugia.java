package com.example.internalship.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.internalship.vo.ObjetoVO;
import com.example.internalship.vo.cirugiaVO.CHCirugiaVO;
import com.example.internalship.vo.cirugiaVO.CObservacionesVO;
import com.example.internalship.vo.cirugiaVO.CPacienteVO;
import com.example.internalship.vo.cirugiaVO.CShockVO;
import com.example.internalship.vo.cirugiaVO.CUcisVO;
import com.example.internalship.vo.photo.foto;

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

    public int eliminar_Cirugia_TIPO(String id, String tipo){
        SQLiteDatabase db = this.getWritableDatabase();

        switch (tipo){
            case "OBS":
                return db.delete(TABLE_PACIENTES_CIRUGIA_OBS, "id_pac_obs = ?", new String[]{id});
            case "CHCIRUGIA":
                return db.delete(TABLE_PACIENTES_CIRUGIA_HCIRUGIA, "id_pac_hcirugia = ?", new String[]{id});
            case "UCI":
                return db.delete(TABLE_PACIENTES_CIRUGIA_UCI, "id_pac_uci = ?", new String[]{id});
            case "TSHOCK":
                return db.delete(TABLE_PACIENTES_CIRUGIA_TSHOCK, "id_pac_tshock = ?", new String[]{id});
            default:
                return -1;
        }
    }

    public List<ObjetoVO> list_Obtener(String cama, String tipo){

        List<ObjetoVO> listdatos = new ArrayList<>();

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;

        switch (tipo){
            case "OBS":
                cursor = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES_CIRUGIA_OBS + " WHERE cama = ?", new String[]{cama});
                break;
            case "CHCIRUGIA":
                cursor = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES_CIRUGIA_HCIRUGIA + " WHERE cama = ?", new String[]{cama});
                break;
            case "UCI":
                cursor = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES_CIRUGIA_UCI + " WHERE cama = ?", new String[]{cama});
                break;
            case "TSHOCK":
                cursor = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES_CIRUGIA_TSHOCK + " WHERE cama = ?", new String[]{cama});
                break;
        }

        try {

            if (cursor.moveToFirst()){
                do {

                    ObjetoVO datos = new ObjetoVO();
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

    public int actualizar_Paciente_Cirugia(ObjetoVO obs, String tipo) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = getContentValues(obs);

        switch (tipo){
            case "OBS":
                return db.update(TABLE_PACIENTES_CIRUGIA_OBS, contentValues, "id_pac_obs = ?", new String[]{String.valueOf(obs.getId())});
            case "CHCIRUGIA":
                return db.update(TABLE_PACIENTES_CIRUGIA_HCIRUGIA, contentValues, "id_pac_hcirugia = ?", new String[]{String.valueOf(obs.getId())});
            case "UCI":
                return db.update(TABLE_PACIENTES_CIRUGIA_UCI, contentValues, "id_pac_uci = ?", new String[]{String.valueOf(obs.getId())});
            case "TSHOCK":
                return db.update(TABLE_PACIENTES_CIRUGIA_TSHOCK, contentValues, "id_pac_tshock = ?", new String[]{String.valueOf(obs.getId())});
            default:
                return 0;
        }

    }

    public long insertar_Cirugia_TIPOTABLA(ObjetoVO obs, String tipo) {

        long id = 0;
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = getContentValues(obs);

            switch (tipo){
                case "OBS":

                    id = db.insert(TABLE_PACIENTES_CIRUGIA_OBS, null, contentValues);
                    return id;

                case "CHCIRUGIA":

                    id = db.insert(TABLE_PACIENTES_CIRUGIA_HCIRUGIA, null, contentValues);
                    return id;

                case "UCI":

                    id = db.insert(TABLE_PACIENTES_CIRUGIA_UCI, null, contentValues);
                    return id;

                case "TSHOCK":

                    id = db.insert(TABLE_PACIENTES_CIRUGIA_TSHOCK, null, contentValues);
                    return id;

                default:
                    return 0;
            }


        } catch (Exception e){
            e.toString();
        }
        return id;
    }
    @NonNull
    private static ContentValues getContentValues(ObjetoVO obs) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("cama", obs.getCama());
        contentValues.put("dia", obs.getDia());
        contentValues.put("efisico", obs.getEfisico());
        contentValues.put("evolucion", obs.getEvolucion());
        contentValues.put("dx", obs.getDx());
        contentValues.put("plann", obs.getPlan());
        contentValues.put("tratamiento", obs.getTratamiento());
        contentValues.put("resLab", obs.getResLab());
        contentValues.put("resImagen", obs.getResImagen());
        contentValues.put("procedimiento", obs.getProcedimiento());
        contentValues.put("horaingreso", obs.getHoraingreso());
        contentValues.put("primeringreso", obs.getPrimeringreso());
        return contentValues;
    }


    public int actualizar_Paciente_Cirugia_OBS(CObservacionesVO obs) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = getContentValues_OBS(obs);

        return db.update(TABLE_PACIENTES_CIRUGIA_OBS, contentValues, "id_pac_obs = ?", new String[]{String.valueOf(obs.getId())});
    }

    @NonNull
    private static ContentValues getContentValues_OBS(CObservacionesVO obs) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("cama", obs.getCama());
        contentValues.put("dia", obs.getDia());
        contentValues.put("efisico", obs.getEfisico());
        contentValues.put("evolucion", obs.getEvolucion());
        contentValues.put("dx", obs.getDx());
        contentValues.put("plann", obs.getPlan());
        contentValues.put("tratamiento", obs.getTratamiento());
        contentValues.put("resLab", obs.getResLab());
        contentValues.put("resImagen", obs.getResImagen());
        contentValues.put("procedimiento", obs.getProcedimiento());
        contentValues.put("horaingreso", obs.getHoraingreso());
        contentValues.put("primeringreso", obs.getPrimeringreso());
        return contentValues;
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

    public int eliminar_Cirugia_OBS(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_PACIENTES_CIRUGIA_OBS, "id_pac_obs = ?", new String[]{id});
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
                    paciente.setObservaciones(list_ObtenerOBS(camaslect));
                    paciente.setShock(list_TSHOCK(camaslect));

                    datos.add(paciente);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return datos;
    }

    public CPacienteVO buscar_Cirugia_Paciente(String id, String cama) {
        DbHelper dbHelper = new DbHelper(context);
        CPacienteVO paciente = new CPacienteVO();
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PACIENTES_CIRUGIA + " WHERE id_pac = ? AND cama = ?", new String[]{id,cama});

        try {

            if (cursor.moveToFirst()){
                do {

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
                    paciente.setObservaciones(list_ObtenerOBS(camaslect));
                    paciente.setShock(list_TSHOCK(camaslect));

                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return paciente;
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

    public List<CObservacionesVO> list_ObtenerOBS(String cama){

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

    public List<foto> list_Fotos(String idPac, String tipoFoto){
        List<foto> listdatos = new ArrayList<>();

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_IMAGENES + " WHERE idPaciente = ? AND tipoFoto = ?", new String[]{idPac, tipoFoto});

            if (cursor.moveToFirst()){
                do {

                    foto datos = new foto();
                    datos.setId(cursor.getInt(0));
                    datos.setTitulo(cursor.getString(1));
                    datos.setDescripcion(cursor.getString(2));
                    datos.setUrl(cursor.getString(3));
                    datos.setIdPaciente(cursor.getInt(4));
                    datos.setTipoFoto(cursor.getString(5));

                    listdatos.add(datos);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return listdatos;
    }

    public int eliminar_Foto(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_IMAGENES, "id = ?", new String[]{id});
    }

    public long insertar_Cirugia_Foto(String titulo, String descripcion,
                                     String url, String idPac, String tipoFoto){

        long id = 0;
        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("titulo", titulo);
            contentValues.put("descripcion", descripcion);
            contentValues.put("url", url);
            contentValues.put("idPaciente", idPac);
            contentValues.put("tipoFoto", tipoFoto);

            id = db.insert(TABLE_IMAGENES, null, contentValues);

        } catch (Exception e){
            e.toString();
        }
        return id;

    }

}

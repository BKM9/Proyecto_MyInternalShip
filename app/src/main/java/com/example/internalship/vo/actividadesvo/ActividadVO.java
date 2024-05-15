package com.example.internalship.vo.actividadesvo;

import android.os.Parcel;
import android.os.Parcelable;

public class ActividadVO implements Parcelable {
    private int id;
    private String asunto;
    private String fecha;
    private String hora;
    private String descripcion;

    protected ActividadVO(Parcel in) {
        id = in.readInt();
        asunto = in.readString();
        fecha = in.readString();
        hora = in.readString();
        descripcion = in.readString();
    }

    public static final Creator<ActividadVO> CREATOR = new Creator<ActividadVO>() {
        @Override
        public ActividadVO createFromParcel(Parcel in) {
            return new ActividadVO(in);
        }

        @Override
        public ActividadVO[] newArray(int size) {
            return new ActividadVO[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ActividadVO(int id, String asunto, String fecha, String hora, String descripcion) {
        this.id = id;
        this.asunto = asunto;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    public ActividadVO() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeString(asunto);
        dest.writeString(fecha);
        dest.writeString(hora);
        dest.writeString(descripcion);
    }
}

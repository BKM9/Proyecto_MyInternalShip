package com.example.internalship.vo.medicinaVO;

import android.os.Parcel;
import android.os.Parcelable;

public class UcisVO implements Parcelable {
    private String cama;
    private String dia;
    private String efisico;
    private String evolucion;
    private String dx;
    private String plan;
    private String tratamiento;
    private String resLab;
    private String resImagen;
    private String procedimiento;
    private String horaingreso;
    private String primeringreso;

    public UcisVO(String cama, String dia, String efisico, String evolucion, String dx, String plan, String tratamiento, String resLab, String resImagen, String procedimiento, String horaingreso, String primeringreso) {
        this.cama = cama;
        this.dia = dia;
        this.efisico = efisico;
        this.evolucion = evolucion;
        this.dx = dx;
        this.plan = plan;
        this.tratamiento = tratamiento;
        this.resLab = resLab;
        this.resImagen = resImagen;
        this.procedimiento = procedimiento;
        this.horaingreso = horaingreso;
        this.primeringreso = primeringreso;
    }

    public UcisVO() {
    }

    protected UcisVO(Parcel in) {
        cama = in.readString();
        dia = in.readString();
        efisico = in.readString();
        evolucion = in.readString();
        dx = in.readString();
        plan = in.readString();
        tratamiento = in.readString();
        resLab = in.readString();
        resImagen = in.readString();
        procedimiento = in.readString();
        horaingreso = in.readString();
        primeringreso = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cama);
        dest.writeString(dia);
        dest.writeString(efisico);
        dest.writeString(evolucion);
        dest.writeString(dx);
        dest.writeString(plan);
        dest.writeString(tratamiento);
        dest.writeString(resLab);
        dest.writeString(resImagen);
        dest.writeString(procedimiento);
        dest.writeString(horaingreso);
        dest.writeString(primeringreso);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UcisVO> CREATOR = new Creator<UcisVO>() {
        @Override
        public UcisVO createFromParcel(Parcel in) {
            return new UcisVO(in);
        }

        @Override
        public UcisVO[] newArray(int size) {
            return new UcisVO[size];
        }
    };

    public String getHoraingreso() {
        return horaingreso;
    }

    public void setHoraingreso(String horaingreso) {
        this.horaingreso = horaingreso;
    }

    public String getPrimeringreso() {
        return primeringreso;
    }

    public void setPrimeringreso(String primeringreso) {
        this.primeringreso = primeringreso;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getEfisico() {
        return efisico;
    }

    public void setEfisico(String efisico) {
        this.efisico = efisico;
    }

    public String getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(String evolucion) {
        this.evolucion = evolucion;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getResLab() {
        return resLab;
    }

    public void setResLab(String resLab) {
        this.resLab = resLab;
    }

    public String getResImagen() {
        return resImagen;
    }

    public void setResImagen(String resImagen) {
        this.resImagen = resImagen;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }
}

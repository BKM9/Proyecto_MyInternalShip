package com.example.internalship.vo.cirugiaVO;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CPacienteVO implements Parcelable {
    public String nombre;
    public String cama;
    public String hc;
    public String fi;
    public String horaingreso;
    public String edad;
    public String anamnesis;
    public String antecedentes_qx_personales_alergias;
    public String dx;
    public String tto;
    public String te;
    public String plan;
    public List<CHCirugiaVO> hcirugia;
    public List<CUcisVO> uci;
    public List<CObservacionesVO> observaciones;
    public List<CShockVO> shock;
    public String reevaluacion;
    public int id;


    public CPacienteVO(String nombre,
                       String cama, String hc,
                       String fi, String horaingreso,
                       String edad, String anamnesis, String antecedentes_qx_personales_alergias, String dx, String tto, String te, String plan, List<CObservacionesVO> observaciones, List<CUcisVO> ucis, List<CShockVO> shoc, List<CHCirugiaVO> hmed, String reevaluacion, int id) {
        this.nombre = nombre;
        this.cama = cama;
        this.hc = hc;
        this.horaingreso = horaingreso;
        this.fi = fi;
        this.edad = edad;
        this.anamnesis = anamnesis;
        this.antecedentes_qx_personales_alergias = antecedentes_qx_personales_alergias;
        this.dx = dx;
        this.tto = tto;
        this.te = te;
        this.plan = plan;
        this.observaciones = observaciones;
        this.uci = ucis;
        this.shock = shoc;
        this.hcirugia = hmed;
        this.reevaluacion = reevaluacion;
        this.id = id;
    }

    public CPacienteVO() {

    }

    protected CPacienteVO(Parcel in) {
        nombre = in.readString();
        cama = in.readString();
        hc = in.readString();
        fi = in.readString();
        horaingreso = in.readString();
        edad = in.readString();
        anamnesis = in.readString();
        antecedentes_qx_personales_alergias = in.readString();
        dx = in.readString();
        tto = in.readString();
        te = in.readString();
        plan = in.readString();
        observaciones = in.createTypedArrayList(CObservacionesVO.CREATOR);
        uci = in.createTypedArrayList(CUcisVO.CREATOR);
        shock = in.createTypedArrayList(CShockVO.CREATOR);
        hcirugia = in.createTypedArrayList(CHCirugiaVO.CREATOR);
        reevaluacion = in.readString();
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(cama);
        dest.writeString(hc);
        dest.writeString(fi);
        dest.writeString(horaingreso);
        dest.writeString(edad);
        dest.writeString(anamnesis);
        dest.writeString(antecedentes_qx_personales_alergias);
        dest.writeString(dx);
        dest.writeString(tto);
        dest.writeString(te);
        dest.writeString(plan);
        dest.writeTypedList(observaciones);
        dest.writeTypedList(uci);
        dest.writeTypedList(shock);
        dest.writeTypedList(hcirugia);
        dest.writeString(reevaluacion);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CPacienteVO> CREATOR = new Creator<CPacienteVO>() {
        @Override
        public CPacienteVO createFromParcel(Parcel in) {
            return new CPacienteVO(in);
        }

        @Override
        public CPacienteVO[] newArray(int size) {
            return new CPacienteVO[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReevaluacion() {
        return reevaluacion;
    }

    public void setReevaluacion(String reevaluacion) {
        this.reevaluacion = reevaluacion;
    }

    public List<CShockVO> getShock() {
        return shock;
    }

    public void setShock(List<CShockVO> shock) {
        this.shock = shock;
    }

    public List<CHCirugiaVO> getHcirugia() {
        return hcirugia;
    }

    public void setHcirugia(List<CHCirugiaVO> hcirugia) {
        this.hcirugia = hcirugia;
    }

    public List<CUcisVO> getUci() {
        return uci;
    }

    public void setUci(List<CUcisVO> uci) {
        this.uci = uci;
    }

    public List<CObservacionesVO> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<CObservacionesVO> observaciones) {
        this.observaciones = observaciones;
    }

    public String getHoraingreso() {
        return horaingreso;
    }

    public void setHoraingreso(String horaingreso) {
        this.horaingreso = horaingreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc;
    }

    public String getFi() {
        return fi;
    }

    public void setFi(String fi) {
        this.fi = fi;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public String getAntecedentes_qx_personales_alergias() {
        return antecedentes_qx_personales_alergias;
    }

    public void setAntecedentes_qx_personales_alergias(String antecedentes_qx_personales_alergias) {
        this.antecedentes_qx_personales_alergias = antecedentes_qx_personales_alergias;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public String getTto() {
        return tto;
    }

    public void setTto(String tto) {
        this.tto = tto;
    }

    public String getTe() {
        return te;
    }

    public void setTe(String te) {
        this.te = te;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void ordenarObservacionesPorFecha() {
        Collections.sort(observaciones, new Comparator<CObservacionesVO>() {
            @Override
            public int compare(CObservacionesVO o1, CObservacionesVO o2) {
                DateFormat formato = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                try {
                    Date fecha1 = formato.parse(o1.getDia());
                    Date fecha2 = formato.parse(o2.getDia());
                    return fecha1.compareTo(fecha2);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
    }

    public void ordenarUCIPorFecha() {
        // Utilizar un comparador personalizado para ordenar las observaciones por fecha
        Collections.sort(uci, new Comparator<CUcisVO>() {
            @Override
            public int compare(CUcisVO o1, CUcisVO o2) {
                // Convertir las fechas de String a Date para compararlas
                DateFormat formato = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                try {
                    Date fecha1 = formato.parse(o1.getDia());
                    Date fecha2 = formato.parse(o2.getDia());
                    return fecha1.compareTo(fecha2);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Manejar la excepción si ocurre un error al convertir las fechas
                    return 0;
                }
            }
        });
    }

    public void ordenarUCINPorFecha() {
        // Utilizar un comparador personalizado para ordenar las observaciones por fecha
        Collections.sort(shock, new Comparator<CShockVO>() {
            @Override
            public int compare(CShockVO o1, CShockVO o2) {
                // Convertir las fechas de String a Date para compararlas
                DateFormat formato = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                try {
                    Date fecha1 = formato.parse(o1.getDia());
                    Date fecha2 = formato.parse(o2.getDia());
                    return fecha1.compareTo(fecha2);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Manejar la excepción si ocurre un error al convertir las fechas
                    return 0;
                }
            }
        });
    }

    public void ordenarHmedPorFecha() {
        // Utilizar un comparador personalizado para ordenar las observaciones por fecha
        Collections.sort(hcirugia, new Comparator<CHCirugiaVO>() {
            @Override
            public int compare(CHCirugiaVO o1, CHCirugiaVO o2) {
                // Convertir las fechas de String a Date para compararlas
                DateFormat formato = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                try {
                    Date fecha1 = formato.parse(o1.getDia());
                    Date fecha2 = formato.parse(o2.getDia());
                    return fecha1.compareTo(fecha2);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Manejar la excepción si ocurre un error al convertir las fechas
                    return 0;
                }
            }
        });
    }
}

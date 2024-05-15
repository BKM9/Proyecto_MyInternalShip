package com.example.internalship.vo.medicinaVO;

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

public class PacienteVO implements Parcelable {
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
    public List<ObservacionesVO> observaciones;
    public List<UcisVO> ucis;
    public List<UcinsVO> ucins;
    public List<HmedicinaVO> hmed;
    public String reevaluacion;
    public List<UtsVO> uts;
    public String id;


    public PacienteVO(String nombre, String cama, String hc, String fi,String horaingreso, String edad, String anamnesis, String antecedentes_qx_personales_alergias, String dx, String tto, String te, String plan, List<ObservacionesVO> observaciones,List<UcisVO> ucis,List<UcinsVO> ucins,List<HmedicinaVO> hmed,String reevaluacion,List<UtsVO> uts, String id) {
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
        this.ucis = ucis;
        this.ucins = ucins;
        this.hmed = hmed;
        this.reevaluacion = reevaluacion;
        this.uts = uts;
        this.id = id;
    }

    public PacienteVO() {

    }

    protected PacienteVO(Parcel in) {
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
        observaciones = in.createTypedArrayList(ObservacionesVO.CREATOR);
        ucis = in.createTypedArrayList(UcisVO.CREATOR);
        ucins = in.createTypedArrayList(UcinsVO.CREATOR);
        hmed = in.createTypedArrayList(HmedicinaVO.CREATOR);
        reevaluacion = in.readString();
        uts = in.createTypedArrayList(UtsVO.CREATOR);
        id = in.readString();
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
        dest.writeTypedList(ucis);
        dest.writeTypedList(ucins);
        dest.writeTypedList(hmed);
        dest.writeString(reevaluacion);
        dest.writeTypedList(uts);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PacienteVO> CREATOR = new Creator<PacienteVO>() {
        @Override
        public PacienteVO createFromParcel(Parcel in) {
            return new PacienteVO(in);
        }

        @Override
        public PacienteVO[] newArray(int size) {
            return new PacienteVO[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<UtsVO> getUts() {
        return uts;
    }

    public void setUts(List<UtsVO> uts) {
        this.uts = uts;
    }

    public String getReevaluacion() {
        return reevaluacion;
    }

    public void setReevaluacion(String reevaluacion) {
        this.reevaluacion = reevaluacion;
    }

    public List<UcinsVO> getUcins() {
        return ucins;
    }

    public void setUcins(List<UcinsVO> ucins) {
        this.ucins = ucins;
    }

    public List<HmedicinaVO> getHmed() {
        return hmed;
    }

    public void setHmed(List<HmedicinaVO> hmed) {
        this.hmed = hmed;
    }

    public List<UcisVO> getUcis() {
        return ucis;
    }

    public void setUcis(List<UcisVO> ucis) {
        this.ucis = ucis;
    }

    public List<ObservacionesVO> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<ObservacionesVO> observaciones) {
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
        Collections.sort(observaciones, new Comparator<ObservacionesVO>() {
            @Override
            public int compare(ObservacionesVO o1, ObservacionesVO o2) {
                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
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

    public void ordenarUtsPorFecha() {
        // Utilizar un comparador personalizado para ordenar las observaciones por fecha
        Collections.sort(uts, new Comparator<UtsVO>() {
            @Override
            public int compare(UtsVO o1, UtsVO o2) {
                // Convertir las fechas de String a Date para compararlas
                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
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


    public void ordenarUCIPorFecha() {
        // Utilizar un comparador personalizado para ordenar las observaciones por fecha
        Collections.sort(ucis, new Comparator<UcisVO>() {
            @Override
            public int compare(UcisVO o1, UcisVO o2) {
                // Convertir las fechas de String a Date para compararlas
                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
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
        Collections.sort(ucins, new Comparator<UcinsVO>() {
            @Override
            public int compare(UcinsVO o1, UcinsVO o2) {
                // Convertir las fechas de String a Date para compararlas
                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
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
        Collections.sort(hmed, new Comparator<HmedicinaVO>() {
            @Override
            public int compare(HmedicinaVO o1, HmedicinaVO o2) {
                // Convertir las fechas de String a Date para compararlas
                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
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

package com.example.internalship.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.internalship.vo.cirugiaVO.CHCirugiaVO;
import com.example.internalship.vo.cirugiaVO.CObservacionesVO;
import com.example.internalship.vo.cirugiaVO.CPacienteVO;
import com.example.internalship.vo.cirugiaVO.CUcisVO;
import com.example.internalship.vo.medicinaVO.HmedicinaVO;
import com.example.internalship.vo.medicinaVO.ObservacionesVO;
import com.example.internalship.vo.medicinaVO.PacienteVO;
import com.example.internalship.vo.medicinaVO.UcinsVO;
import com.example.internalship.vo.medicinaVO.UcisVO;
import com.example.internalship.vo.medicinaVO.UtsVO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {
    public static Boolean esNulo(Context context, String... lista) {
        for (String x : lista) {
            if (x.trim().length() == 0) {
                Toast.makeText(context, "Falta Completar", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

    public static Boolean existefechaendatosalmacenadosOBS(List<ObservacionesVO> lista, String valorbuscado) {
        for (ObservacionesVO obs : lista) {
            if (obs.getDia().equals(valorbuscado)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean existefechaendatosalmacenadosOBS_Cirugia(List<CObservacionesVO> lista, String valorbuscado) {
        for (CObservacionesVO obs : lista) {
            if (obs.getDia().equals(valorbuscado)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean existefechaendatosalmacenadosUTS(List<UtsVO> lista, String valorbuscado) {
        for (UtsVO u : lista) {
            if (u.getDia().equals(valorbuscado)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean existefechaendatosalmacenadosUCI(List<UcisVO> lista, String valorbuscado) {
        for (UcisVO U : lista) {
            if (U.getDia().equals(valorbuscado)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean existefechaendatosalmacenadosUCI_Urgencia(List<CUcisVO> lista, String valorbuscado) {
        for (CUcisVO U : lista) {
            if (U.getDia().equals(valorbuscado)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean existefechaendatosalmacenadosUCIN(List<UcinsVO> lista, String valorbuscado) {
        for (UcinsVO U : lista) {
            if (U.getDia().equals(valorbuscado)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean existefechaendatosalmacenadosHMED(List<HmedicinaVO> lista, String valorbuscado) {
        for (HmedicinaVO U : lista) {
            if (U.getDia().equals(valorbuscado)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean existefechaendatosalmacenadosHMED_Cirugia(List<CHCirugiaVO> lista, String valorbuscado) {
        for (CHCirugiaVO U : lista) {
            if (U.getDia().equals(valorbuscado)) {
                return true;
            }
        }
        return false;
    }

    public static long obtenerMaxNumDiasObs(List<ObservacionesVO> listaObjetos) {
        String fechaInicioStr = listaObjetos.get(0).getDia();

        String fechaFinStr = listaObjetos.get(listaObjetos.size() - 1).getDia();

        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaInicio = formato.parse(fechaInicioStr);
            Date fechaFin = formato.parse(fechaFinStr);

            long diferenciaEnMs = fechaFin.getTime() - fechaInicio.getTime();

            long dias = diferenciaEnMs / (1000 * 60 * 60 * 24);

            System.out.println("La cantidad de días entre las fechas es: " + dias);

            return dias;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public static long obtenerMaxNumDiasObs_Cirugia(List<CObservacionesVO> listaObjetos) {
        String fechaInicioStr = listaObjetos.get(0).getDia();

        String fechaFinStr = listaObjetos.get(listaObjetos.size() - 1).getDia();

        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaInicio = formato.parse(fechaInicioStr);
            Date fechaFin = formato.parse(fechaFinStr);

            long diferenciaEnMs = fechaFin.getTime() - fechaInicio.getTime();

            long dias = diferenciaEnMs / (1000 * 60 * 60 * 24);

            System.out.println("La cantidad de días entre las fechas es: " + dias);

            return dias;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public static <T> boolean esFechaMayorAladeIngreso(List<T> listaObjetos, String fechaComparar, Context context) {

        if (listaObjetos != null && !listaObjetos.isEmpty() && listaObjetos.get(0) instanceof ObservacionesVO) {

            if (listaObjetos.size() >= 1) {  // se agrego el igual

                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaingreso = formato.parse(((ObservacionesVO) listaObjetos.get(listaObjetos.size() - 1)).getDia());
                    Date fechaultima = formato.parse(fechaComparar);

                    if (fechaingreso.compareTo(fechaultima) < 0) {
                        return true;
                    } else if (fechaingreso.compareTo(fechaultima) > 0 ) {
                        Toast.makeText(context, "Fecha es Menor", Toast.LENGTH_LONG).show();
                        return false;
                    } else {
                        Toast.makeText(context, "La fecha ya existe", Toast.LENGTH_LONG).show();
                        return false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else {
                return true;
            }

        } else if (listaObjetos != null && !listaObjetos.isEmpty() && listaObjetos.get(0) instanceof UcisVO) {

            if (listaObjetos.size() >= 1) {

                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaingreso = formato.parse(((UcisVO) listaObjetos.get(listaObjetos.size() - 1)).getDia());
                    Date fechaultima = formato.parse(fechaComparar);

                    if (fechaingreso.compareTo(fechaultima) < 0) {
                        return true;
                    } else if (fechaingreso.compareTo(fechaultima) > 0) {
                        Toast.makeText(context, "Fecha es Menor", Toast.LENGTH_LONG).show();
                        return false;
                    } else {
                        Toast.makeText(context, "La fecha ya existe", Toast.LENGTH_LONG).show();
                        return false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else {
                return true;
            }

        } else if (listaObjetos != null && !listaObjetos.isEmpty() && listaObjetos.get(0) instanceof UcinsVO) {

            if (listaObjetos.size() >= 1) {

                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaingreso = formato.parse(((UcinsVO) listaObjetos.get(listaObjetos.size() - 1)).getDia());
                    Date fechaultima = formato.parse(fechaComparar);

                    if (fechaingreso.compareTo(fechaultima) < 0) {
                        return true;
                    } else if (fechaingreso.compareTo(fechaultima) > 0) {
                        Toast.makeText(context, "Fecha es Menor", Toast.LENGTH_LONG).show();
                        return false;
                    } else {
                        Toast.makeText(context, "La fecha ya existe", Toast.LENGTH_LONG).show();
                        return false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else {
                return true;
            }

        } else if (listaObjetos != null && !listaObjetos.isEmpty() && listaObjetos.get(0) instanceof HmedicinaVO) {

            if (listaObjetos.size() >= 1) {

                DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaingreso = formato.parse(((HmedicinaVO) listaObjetos.get(listaObjetos.size() - 1)).getDia());
                    Date fechaultima = formato.parse(fechaComparar);

                    if (fechaingreso.compareTo(fechaultima) < 0) {
                        return true;
                    } else if (fechaingreso.compareTo(fechaultima) > 0) {
                        Toast.makeText(context, "Fecha es Menor", Toast.LENGTH_LONG).show();
                        return false;
                    } else {
                        Toast.makeText(context, "La fecha ya existe", Toast.LENGTH_LONG).show();
                        return false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else {
                return true;
            }

        } else {
            System.out.println("La lista contiene objetos de una clase desconocida");
        }
        return true;
    }

    public static <T> Boolean existefechaprimerregistro(List<T> listaObjetos, Context context) {
        if (listaObjetos != null && !listaObjetos.isEmpty() && listaObjetos.get(0) instanceof ObservacionesVO) {
            List<ObservacionesVO> listaObservaciones = (List<ObservacionesVO>) listaObjetos;

            for (ObservacionesVO obs : listaObservaciones) {
                if (obs.getPrimeringreso().equals("1")) {
                    Toast.makeText(context, "Ya registro Primer Ingreso", Toast.LENGTH_LONG).show();
                    return true;
                }
            }
            return false;
        } else if (listaObjetos != null && !listaObjetos.isEmpty() && listaObjetos.get(0) instanceof UcisVO) {
            List<UcisVO> listaUCI = (List<UcisVO>) listaObjetos;

            for (UcisVO U : listaUCI) {
                if (U.getPrimeringreso().equals("1")) {
                    Toast.makeText(context, "Ya registro Primer Ingreso", Toast.LENGTH_LONG).show();
                    return true;
                }
            }
            return false;
        } else if (listaObjetos != null && !listaObjetos.isEmpty() && listaObjetos.get(0) instanceof UcinsVO) {
            List<UcinsVO> listaUCIN = (List<UcinsVO>) listaObjetos;

            for (UcinsVO U : listaUCIN) {
                if (U.getPrimeringreso().equals("1")) {
                    Toast.makeText(context, "Ya registro Primer Ingreso", Toast.LENGTH_LONG).show();
                    return true;
                }
            }
            return false;
        } else if (listaObjetos != null && !listaObjetos.isEmpty() && listaObjetos.get(0) instanceof HmedicinaVO) {
            List<HmedicinaVO> listahmed = (List<HmedicinaVO>) listaObjetos;

            for (HmedicinaVO U : listahmed) {
                if (U.getPrimeringreso().equals("1")) {
                    Toast.makeText(context, "Ya registro Primer Ingreso", Toast.LENGTH_LONG).show();
                    return true;
                }
            }
            return false;
        } else if (listaObjetos != null && !listaObjetos.isEmpty() && listaObjetos.get(0) instanceof UtsVO) {
            List<UtsVO> listahmed = (List<UtsVO>) listaObjetos;

            for (UtsVO U : listahmed) {
                if (U.getPrimeringreso().equals("1")) {
                    Toast.makeText(context, "Ya registro Primer Ingreso", Toast.LENGTH_LONG).show();
                    return true;
                }
            }
            return false;
        }else if (listaObjetos != null && !listaObjetos.isEmpty() && listaObjetos.get(0) instanceof CObservacionesVO) {
            List<CObservacionesVO> listaObservaciones = (List<CObservacionesVO>) listaObjetos;

            for (CObservacionesVO obs : listaObservaciones) {
                if (obs.getPrimeringreso().equals("1")) {
                    Toast.makeText(context, "Ya registro Primer Ingreso", Toast.LENGTH_LONG).show();
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static String obtenerdiasenestadoporlistaObs_Cirugia(List<CObservacionesVO> objetoPaciente) {
        if (!objetoPaciente.isEmpty()) {
            if (objetoPaciente.size() > 1) {
                String cantidaddias = String.valueOf(Util.obtenerMaxNumDiasObs_Cirugia(objetoPaciente));
                return cantidaddias;
            } else {
                return "1";
            }
        } else {
            return "No info";
        }
    }

    public static String obtenerdiasenestadoporlistaObs(CPacienteVO objetoPaciente) {
        if (!objetoPaciente.getObservaciones().isEmpty()) {
            if (objetoPaciente.getObservaciones().size() > 1) {
                String cantidaddias = String.valueOf(Util.obtenerMaxNumDiasObs_Cirugia(objetoPaciente.getObservaciones()));
                return cantidaddias;
            } else {
                return "1";
            }
        } else {
            return "No info";
        }
    }

    public static long obtenerMaxNumDiasUCI(List<UcisVO> listaObjetos) {
        String fechaInicioStr = listaObjetos.get(0).getDia();

        String fechaFinStr = listaObjetos.get(listaObjetos.size() - 1).getDia();

        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaInicio = formato.parse(fechaInicioStr);
            Date fechaFin = formato.parse(fechaFinStr);

            long diferenciaEnMs = fechaFin.getTime() - fechaInicio.getTime();

            long dias = diferenciaEnMs / (1000 * 60 * 60 * 24);

            System.out.println("La cantidad de días entre las fechas es: " + dias);

            return dias;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public static long obtenerMaxNumDiasUCI_Urgencia(List<CUcisVO> listaObjetos) {
        String fechaInicioStr = listaObjetos.get(0).getDia();

        String fechaFinStr = listaObjetos.get(listaObjetos.size() - 1).getDia();

        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaInicio = formato.parse(fechaInicioStr);
            Date fechaFin = formato.parse(fechaFinStr);

            long diferenciaEnMs = fechaFin.getTime() - fechaInicio.getTime();

            long dias = diferenciaEnMs / (1000 * 60 * 60 * 24);

            System.out.println("La cantidad de días entre las fechas es: " + dias);

            return dias;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public static long obtenerMaxNumDiasUCIN(List<UcinsVO> listaObjetos) {
        String fechaInicioStr = listaObjetos.get(0).getDia();

        String fechaFinStr = listaObjetos.get(listaObjetos.size() - 1).getDia();

        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaInicio = formato.parse(fechaInicioStr);
            Date fechaFin = formato.parse(fechaFinStr);

            long diferenciaEnMs = fechaFin.getTime() - fechaInicio.getTime();

            long dias = diferenciaEnMs / (1000 * 60 * 60 * 24);

            System.out.println("La cantidad de días entre las fechas es: " + dias);

            return dias;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public static long obtenerMaxNumDiasHMED_Cirugia(List<CHCirugiaVO> listaObjetos) {
        String fechaInicioStr = listaObjetos.get(0).getDia();

        String fechaFinStr = listaObjetos.get(listaObjetos.size() - 1).getDia();

        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaInicio = formato.parse(fechaInicioStr);
            Date fechaFin = formato.parse(fechaFinStr);

            long diferenciaEnMs = fechaFin.getTime() - fechaInicio.getTime();

            long dias = diferenciaEnMs / (1000 * 60 * 60 * 24);

            System.out.println("La cantidad de días entre las fechas es: " + dias);

            return dias;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public static long obtenerMaxNumDiasHMED(List<HmedicinaVO> listaObjetos) {
        String fechaInicioStr = listaObjetos.get(0).getDia();

        String fechaFinStr = listaObjetos.get(listaObjetos.size() - 1).getDia();

        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaInicio = formato.parse(fechaInicioStr);
            Date fechaFin = formato.parse(fechaFinStr);

            long diferenciaEnMs = fechaFin.getTime() - fechaInicio.getTime();

            long dias = diferenciaEnMs / (1000 * 60 * 60 * 24);

            System.out.println("La cantidad de días entre las fechas es: " + dias);

            return dias;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public static long obtenerMaxNumDiasUTS(List<UtsVO> listaObjetos) {
        String fechaInicioStr = listaObjetos.get(0).getDia();

        String fechaFinStr = listaObjetos.get(listaObjetos.size() - 1).getDia();

        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaInicio = formato.parse(fechaInicioStr);
            Date fechaFin = formato.parse(fechaFinStr);

            long diferenciaEnMs = fechaFin.getTime() - fechaInicio.getTime();

            long dias = diferenciaEnMs / (1000 * 60 * 60 * 24);

            System.out.println("La cantidad de días entre las fechas es: " + dias);

            return dias;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 1;
    }
    public static String obtenerdiasenestadoporlistaUCI(PacienteVO objetoPaciente) {
        if (!objetoPaciente.getUcis().isEmpty()) {
            if (objetoPaciente.getUcis().size() > 1) {
                String cantidaddias = String.valueOf(Util.obtenerMaxNumDiasUCI(objetoPaciente.getUcis()));
                return cantidaddias;
            } else {
                return "1";
            }
        } else {
            return "No info";
        }
    }

    public static String obtenerdiasenestadoporlistaUCI(CPacienteVO objetoPaciente) {
        if (!objetoPaciente.getUci().isEmpty()) {
            if (objetoPaciente.getUci().size() > 1) {
                String cantidaddias = String.valueOf(Util.obtenerMaxNumDiasUCI_Urgencia(objetoPaciente.getUci()));
                return cantidaddias;
            } else {
                return "1";
            }
        } else {
            return "No info";
        }
    }

    public static String obtenerdiasenestadoporlistaUCIN(PacienteVO objetoPaciente) {
        if (!objetoPaciente.getUcins().isEmpty()) {
            if (objetoPaciente.getUcins().size() > 1) {
                String cantidaddias = String.valueOf(Util.obtenerMaxNumDiasUCIN(objetoPaciente.getUcins()));
                return cantidaddias;
            } else {
                return "1";
            }
        } else {
            return "No info";
        }
    }

    public static String obtenerdiasenestadoporlistaHMED(PacienteVO objetoPaciente) {
        if (!objetoPaciente.getHmed().isEmpty()) {
            if (objetoPaciente.getHmed().size() > 1) {
                String cantidaddias = String.valueOf(Util.obtenerMaxNumDiasHMED(objetoPaciente.getHmed()));
                return cantidaddias;
            } else {
                return "1";
            }
        } else {
            return "No info";
        }
    }

    public static String obtenerdiasenestadoporlistaHMED_Cirugia(CPacienteVO objetoPaciente) {
        if (!objetoPaciente.getHcirugia().isEmpty()) {
            if (objetoPaciente.getHcirugia().size() > 1) {
                String cantidaddias = String.valueOf(Util.obtenerMaxNumDiasHMED_Cirugia(objetoPaciente.getHcirugia()));
                return cantidaddias;
            } else {
                return "1";
            }
        } else {
            return "No info";
        }
    }

    public static String obtenerdiasenestadoporlistaUts(PacienteVO objetoPaciente) {
        if (!objetoPaciente.getUts().isEmpty()) {
            if (objetoPaciente.getUts().size() > 1) {
                String cantidaddias = String.valueOf(Util.obtenerMaxNumDiasUTS(objetoPaciente.getUts()));
                return cantidaddias;
            } else {
                return "1";
            }
        } else {
            return "No info";
        }
    }

    public static String fechaVal(int numFecha) {
        String txtFecha = String.valueOf(numFecha);
        if (txtFecha.length() < 2) {
            return "0".concat(String.valueOf(numFecha));
        } else {
            return String.valueOf(numFecha);
        }
    }
}

package com.example.internalship.utils;

import com.example.principal.models.GoogleSheetsResponse;
import com.example.principal.models.IGoogleSheets;

public class Common {
    public static String SPREAD_SHEET_ID = "1EqFoP9YNUjsZfD3JJSF9sf2kJpDoTpIxCihU82V6ga0";
    public static String TABLE_PACIENTE = "Paciente";
    public static String TABLE_OBSERVACION = "Observacion";
    public static String TABLE_UCI = "UCI";
    public static String TABLE_UCIN = "UCIN";
    public static String TABLE_HMED = "HMedicina";
    public static String TABLE_UTS = "UTS";
    public static String TABLE_UTSII = "UTSII";
    public static String BASE_URL_INSERTAR_ANY = "https://script.google.com/macros/s/AKfycbwZRNcZnkjqBpXBW3dFBIWufCsrr8lxAjvvi0SN6htX_j1o3h-LpgB8YKoo4I9B-Sva/";
    public static String BASE_URL_DELETE_PAC = "https://script.google.com/macros/s/AKfycbwGGBHTbKy6Vr_VCdoKgHbeAOYlzMs5u_WIAPYWds8R3Tnoa95jGysl2Y6mPlh_XB0GdA/";
    public static String CONSULTA_TODO = "https://script.google.com/macros/s/AKfycbxTHYDO4wPMjhqaPV89DHkDmtXjDoChdcsNyAi8msFJcy4D3wGjlrfYr3Qsey6E4r_n/";
    public static String BASE_URL_UPDATE_OBS_MANY = "https://script.google.com/macros/s/AKfycbwcAWHbWkZScy98kK_AB3456pXAvE7d0Jq9sAA2tl7rGB7uvKEgOqAiMdJvay8g-i0S/";
    public static String BASE_URL_UPDATE_UCI_MANY = "https://script.google.com/macros/s/AKfycbxZX835zF6mU6oAJwbDdqavNSfyXIDl928E5yEBPWFl7gNYUAHbjikx84PEw-Ml9y8E/";
    public static String UPDATE_PACIENTE_BY_ID = "https://script.google.com/macros/s/AKfycbzK_xEejAUPPKgrQ9XkkpUcc8461ACiqIQU4jri8xrUASn8FYp8npoAtZ46FfsK1RAm/";
    public static String UPDATE_CAMA_PAC = "https://script.google.com/macros/s/AKfycby4sTQnxA92c92U9MdlWVSkP7pVAardJGc0k8eVLxtR9MnM84duVV_8xH6lfPhmlx436w/";
    public static IGoogleSheets iGSGetMethodPaciente(String baseUrl){
        return GoogleSheetsResponse.getPacienteMethod(baseUrl)
                .create(IGoogleSheets.class);
    }
}

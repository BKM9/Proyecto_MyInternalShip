package com.example.internalship.vo.photo;

public class foto {
    private int id;
    private String titulo;
    private String descripcion;
    private String url;
    private int idPaciente;
    private String tipoFoto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getTipoFoto() {
        return tipoFoto;
    }

    public void setTipoFoto(String tipoFoto) {
        this.tipoFoto = tipoFoto;
    }

    public foto(int id, String titulo, String descripcion, String url, int idPaciente, String tipoFoto) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.idPaciente = idPaciente;
        this.tipoFoto = tipoFoto;
    }

    public foto() {
    }

}

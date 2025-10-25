package com.example.FrankySabado.modelos.dtos;

public class ProyectoDTO {

    private String titulo;
    private String descripcion;
    private String url_proyecto;
    private String tecnologias;
    private Long idPerfilEstudiante;

    public ProyectoDTO() {
    }

    public ProyectoDTO(String titulo, String descripcion, String url_proyecto, String tecnologias, Long idPerfilEstudiante) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url_proyecto = url_proyecto;
        this.tecnologias = tecnologias;
        this.idPerfilEstudiante = idPerfilEstudiante;
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

    public String getUrl_proyecto() {
        return url_proyecto;
    }

    public void setUrl_proyecto(String url_proyecto) {
        this.url_proyecto = url_proyecto;
    }

    public String getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(String tecnologias) {
        this.tecnologias = tecnologias;
    }

    public Long getIdPerfilEstudiante() {
        return idPerfilEstudiante;
    }

    public void setIdPerfilEstudiante(Long idPerfilEstudiante) {
        this.idPerfilEstudiante = idPerfilEstudiante;
    }
}
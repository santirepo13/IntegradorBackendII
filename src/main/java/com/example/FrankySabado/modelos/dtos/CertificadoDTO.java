package com.example.FrankySabado.modelos.dtos;

import java.time.LocalDateTime;

public class CertificadoDTO {

    private Integer id;
    private String nombre;
    private String institucion;
    private LocalDateTime fecha;
    private String url_archivo;
    private Long idPerfilEstudiante;

    public CertificadoDTO() {
    }

    public CertificadoDTO(Integer id, String nombre, String institucion, LocalDateTime fecha, String url_archivo, Long idPerfilEstudiante) {
        this.id = id;
        this.nombre = nombre;
        this.institucion = institucion;
        this.fecha = fecha;
        this.url_archivo = url_archivo;
        this.idPerfilEstudiante = idPerfilEstudiante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getUrl_archivo() {
        return url_archivo;
    }

    public void setUrl_archivo(String url_archivo) {
        this.url_archivo = url_archivo;
    }

    public Long getIdPerfilEstudiante() {
        return idPerfilEstudiante;
    }

    public void setIdPerfilEstudiante(Long idPerfilEstudiante) {
        this.idPerfilEstudiante = idPerfilEstudiante;
    }
}

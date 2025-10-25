package com.example.FrankySabado.modelos.dtos;

public class HabilidadDTO {

    private Long id;
    private String nombre;
    private Integer nivel;
    private String tipoHabilidad;
    private Long idPerfilEstudiante;

    public HabilidadDTO() {
    }

    public HabilidadDTO(Long id, String nombre, Integer nivel, String tipoHabilidad, Long idPerfilEstudiante) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipoHabilidad = tipoHabilidad;
        this.idPerfilEstudiante = idPerfilEstudiante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getTipoHabilidad() {
        return tipoHabilidad;
    }

    public void setTipoHabilidad(String tipoHabilidad) {
        this.tipoHabilidad = tipoHabilidad;
    }

    public Long getIdPerfilEstudiante() {
        return idPerfilEstudiante;
    }

    public void setIdPerfilEstudiante(Long idPerfilEstudiante) {
        this.idPerfilEstudiante = idPerfilEstudiante;
    }
}
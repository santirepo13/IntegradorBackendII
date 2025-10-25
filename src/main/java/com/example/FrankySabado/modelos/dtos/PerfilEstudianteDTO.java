package com.example.FrankySabado.modelos.dtos;

/**
 * DTO para transferir datos públicos del perfil de un estudiante.
 * Incluye únicamente campos no sensibles y sin relaciones a otras entidades.
 */
public class PerfilEstudianteDTO {

    private String resumen;
    private String intereses;
    private String proyectos;
    private String habilidades;

    public PerfilEstudianteDTO() {
    }

    public PerfilEstudianteDTO(String resumen, String intereses, String proyectos, String habilidades) {
        this.resumen = resumen;
        this.intereses = intereses;
        this.proyectos = proyectos;
        this.habilidades = habilidades;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getIntereses() {
        return intereses;
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    public String getProyectos() {
        return proyectos;
    }

    public void setProyectos(String proyectos) {
        this.proyectos = proyectos;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }
}

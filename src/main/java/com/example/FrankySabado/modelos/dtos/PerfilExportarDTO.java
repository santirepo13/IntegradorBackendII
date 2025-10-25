package com.example.FrankySabado.modelos.dtos;

public class PerfilExportarDTO {

    private Long perfilId;
    private Long estudianteId;
    private String textoPlano;
    private String resumen;
    private String intereses;
    private String proyectos;
    private String habilidades;

    public PerfilExportarDTO() {
    }

    public PerfilExportarDTO(Long perfilId, Long estudianteId, String textoPlano, String resumen, String intereses, String proyectos, String habilidades) {
        this.perfilId = perfilId;
        this.estudianteId = estudianteId;
        this.textoPlano = textoPlano;
        this.resumen = resumen;
        this.intereses = intereses;
        this.proyectos = proyectos;
        this.habilidades = habilidades;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getTextoPlano() {
        return textoPlano;
    }

    public void setTextoPlano(String textoPlano) {
        this.textoPlano = textoPlano;
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

package com.example.FrankySabado.modelos;

import com.example.FrankySabado.ayudas.Intereses;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "Perfil Estudiante")
public class PerfilEstudiante {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Resumen", nullable = false)
    private String resumen;

    @Column(name = "Intereses", nullable = false)
    @Enumerated(EnumType.STRING)
    private Intereses intereses;

    @Column(name = "Experiencia", nullable = false)
    private Integer experiencia;

    @Column(name = "Proyectos", nullable = false)
    private String proyectos;

    @OneToOne
    @JoinColumn(name = "Fk_Estudiante", referencedColumnName = "id")
    @JsonManagedReference(value = "Pestudiante_Estudiante")
    private Estudiante estudiante;

    @OneToMany(mappedBy = "perfilEstudiante")
    @JsonManagedReference(value = "habilidad-perfil")
    private ArrayList<Habilidad> habilidad;

    @OneToMany(mappedBy = "perfilEstudiante")
    @JsonManagedReference(value = "proyecto-perfil")
    private ArrayList<Proyecto> proyecto;

    @OneToMany(mappedBy = "perfilEstudiante")
    @JsonManagedReference(value = "certificado-perfil")
    private ArrayList<Certificado> certificado;

    public PerfilEstudiante() {
    }

        public PerfilEstudiante(Long id, String resumen, Intereses intereses, Integer experiencia, String proyectos, Estudiante estudiante, ArrayList<Habilidad> habilidad, ArrayList<Proyecto> proyecto) {
        this.id = id;
        this.resumen = resumen;
        this.intereses = intereses;
        this.experiencia = experiencia;
        this.proyectos = proyectos;
        this.estudiante = estudiante;
        this.habilidad = habilidad;
        this.proyecto = proyecto;
    }

        public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Intereses getIntereses() {
        return intereses;
    }

    public void setIntereses(Intereses intereses) {
        this.intereses = intereses;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    public String getProyectos() {
        return proyectos;
    }

    public void setProyectos(String proyectos) {
        this.proyectos = proyectos;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public ArrayList<Habilidad> getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(ArrayList<Habilidad> habilidad) {
        this.habilidad = habilidad;
    }

    public ArrayList<Proyecto> getProyecto() {
        return proyecto;
    }

    public void setProyecto(ArrayList<Proyecto> proyecto) {
        this.proyecto = proyecto;
    }
}

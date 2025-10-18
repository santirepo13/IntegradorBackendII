package com.example.FrankySabado.modelos;

import com.example.FrankySabado.ayudas.Tecnologias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Proyectos")
public class Proyecto {

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Titulo", nullable = false)
    private String titulo;

    @Column(name = "Descripcion", nullable = false)
    private String descripcion;

    @Column(name = "url_proyecto", nullable = false, unique = true, length = 2083)
    private String url_proyecto;

    @Column(name = "Tecnologias", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tecnologias tecnologias;

    @ManyToOne
    @JoinColumn(name = "fk_perfilestudiante", referencedColumnName = "id")
    @JsonBackReference(value = "proyecto-perfil")
    private PerfilEstudiante perfilEstudiante;

    public Proyecto() {
    }

        public Proyecto(Long id, String titulo, String descripcion, String url_proyecto, Tecnologias tecnologias) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url_proyecto = url_proyecto;
        this.tecnologias = tecnologias;
    }

        public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getUrl_proyecto() {
        return url_proyecto;
    }

    public void setUrl_proyecto(String url_proyecto) {
        this.url_proyecto = url_proyecto;
    }

    public Tecnologias getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(Tecnologias tecnologias) {
        this.tecnologias = tecnologias;
    }

    public PerfilEstudiante getPerfilEstudiante() {
        return perfilEstudiante;
    }

    public void setPerfilEstudiante(PerfilEstudiante perfilEstudiante) {
        this.perfilEstudiante = perfilEstudiante;
    }
}

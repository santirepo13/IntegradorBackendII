package com.example.FrankySabado.modelos;

import com.example.FrankySabado.ayudas.Institucion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Certificados")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre",length = 70,nullable = false,unique = false)
    private String nombre;

    @Column(name = "institucion",nullable = false,unique = true)
    @Enumerated(EnumType.STRING)
    private Institucion institucion;

    @Column(name = "fecha",nullable = false)
    @Timestamp
    private LocalDateTime fecha;

    @Column(name = "url_archivo",nullable = false,unique = true, length = 2083)
    private String url_archivo;


    @ManyToOne
    @JoinColumn(name = "fk_perfilestudiante", referencedColumnName = "id")
    @JsonBackReference(value = "certificado-perfil")
    private PerfilEstudiante perfilEstudiante;




    public Certificado() {
    }

    public Certificado(String nombre, Institucion institucion, LocalDateTime fecha, String url_archivo) {
        this.nombre = nombre;
        this.institucion = institucion;
        this.fecha = fecha;
        this.url_archivo = url_archivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
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
}

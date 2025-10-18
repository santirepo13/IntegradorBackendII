package com.example.FrankySabado.modelos;

import com.example.FrankySabado.ayudas.EstadosAsistencia;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="asistencias")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fecha", nullable = false, unique = false)
    private LocalDate fecha;
    @Column(name = "observacion", nullable = true, unique = false, length = 100)
    private String observacion;
    @Column(name = "estado", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private EstadosAsistencia estado;

    @ManyToOne
    @JoinColumn(name = "fk_estudiante", referencedColumnName = "id")
    @JsonBackReference(value = "relacionestudianteasistencia")
    private Estudiante estudiante;

    public Asistencia() {
    }

    public Asistencia(Integer id, LocalDate fecha, String observacion, EstadosAsistencia estado) {
        this.id = id;
        this.fecha = fecha;
        this.observacion = observacion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public EstadosAsistencia getEstado() {
        return estado;
    }

    public void setEstado(EstadosAsistencia estado) {
        this.estado = estado;
    }
}

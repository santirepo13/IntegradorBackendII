package com.example.FrankySabado.modelos;

import com.example.FrankySabado.ayudas.TipoEvaluacion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name="valor", nullable = false, unique = false)
    private Double valor;

    @Enumerated(EnumType.STRING)
    private TipoEvaluacion tipo;

    @Column(name = "fecha", nullable = true, unique = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "fk_estudiante", referencedColumnName = "id")
    @JsonBackReference(value="relacionestudiantenota")
    private Estudiante estudiante;

    public Nota() {
    }

    public Nota(Integer id, Double valor, TipoEvaluacion tipo, LocalDate fecha) {
        Id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoEvaluacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvaluacion tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

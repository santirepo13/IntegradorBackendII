package com.example.FrankySabado.modelos;

import com.example.FrankySabado.ayudas.Departamentos;
import com.example.FrankySabado.ayudas.SectorEmpresa;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name="empresarios")
public class Empresario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name="nombreEmpresa")
    private String nombreEmpresa;
    @Enumerated(EnumType.STRING)
    private SectorEmpresa sector;
    @Enumerated(EnumType.STRING)
    private Departamentos departamento;

    @OneToOne
    @JoinColumn(name="fk_usuario", referencedColumnName = "id")
    @JsonManagedReference(value="relacionempresariousuario")
    private Usuario usuario;

    public Empresario() {
    }

    public Empresario(Integer id, String nombreEmpresa, SectorEmpresa sector, Departamentos departamento) {
        Id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.sector = sector;
        this.departamento = departamento;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public SectorEmpresa getSector() {
        return sector;
    }

    public void setSector(SectorEmpresa sector) {
        this.sector = sector;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }
}

package com.example.FrankySabado.modelos;

import com.example.FrankySabado.ayudas.TipoHabilidad;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;



@Entity
@Table(name="Habilidades")



public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name= "nombre" , nullable = false)
    private String nombre;
    @Column(name="Nivel", nullable = false)
    private Integer nivel;
    @Column(name="Tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoHabilidad  tipoHabilidad;

@ManyToOne
@JoinColumn(name = "fk_perfilestudiante", referencedColumnName = "id")
@JsonBackReference(value = "habilidad-perfil")
private PerfilEstudiante perfilEstudiante;

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

public TipoHabilidad getTipoHabilidad() {
    return tipoHabilidad;
}

public void setTipoHabilidad(TipoHabilidad tipoHabilidad) {
    this.tipoHabilidad = tipoHabilidad;
}

public PerfilEstudiante getPerfilEstudiante() {
    return perfilEstudiante;
}

public void setPerfilEstudiante(PerfilEstudiante perfilEstudiante) {
    this.perfilEstudiante = perfilEstudiante;
}

}

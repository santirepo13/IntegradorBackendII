package com.example.FrankySabado.modelos;

import com.example.FrankySabado.ayudas.TipoHabilidad;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;

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



}

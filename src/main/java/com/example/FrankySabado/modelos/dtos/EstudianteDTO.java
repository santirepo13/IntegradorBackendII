package com.example.FrankySabado.modelos.dtos;

import com.example.FrankySabado.ayudas.Roles;

import java.time.LocalDate;

public class EstudianteDTO {

    private String nombre;
    private Roles rol;

    private double promedio;
    private LocalDate fechanacimiento;

    public EstudianteDTO() {
    }

    public EstudianteDTO(String nombre, Roles rol, double promedio, LocalDate fechanacimiento) {
        this.nombre = nombre;
        this.rol = rol;
        this.promedio = promedio;
        this.fechanacimiento = fechanacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
}

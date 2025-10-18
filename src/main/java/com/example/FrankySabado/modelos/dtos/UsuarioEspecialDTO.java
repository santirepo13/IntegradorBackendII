package com.example.FrankySabado.modelos.dtos;

import com.example.FrankySabado.ayudas.Estados;

public class UsuarioEspecialDTO {

    private Integer id;
    private String nombre;
    private String contraseña;
    private String rol;
    private Estados estado;

    public UsuarioEspecialDTO() {
    }

    public UsuarioEspecialDTO(Integer id, String nombre, String contraseña, String rol, Estados estado) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rol = rol;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }
}

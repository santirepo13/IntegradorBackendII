package com.example.FrankySabado.ayudas;

public enum MensajeError {

    USUARIO_NO_ECONTRADO("El usuario no existe"),
    ERROR_GENERAL_API("Error de API")
    ;

    private final String descripcion;

    MensajeError(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

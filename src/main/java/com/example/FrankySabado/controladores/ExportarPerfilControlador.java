package com.example.FrankySabado.controladores;

import com.example.FrankySabado.servicios.ExportarPerfilServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exportar")
public class ExportarPerfilControlador {

    @Autowired
    private ExportarPerfilServicios servicio;

    @GetMapping("/{idPerfil}")
    public ResponseEntity<?> exportarPerfil(@PathVariable Long idPerfil) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.generarBorradorPerfil(idPerfil));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}

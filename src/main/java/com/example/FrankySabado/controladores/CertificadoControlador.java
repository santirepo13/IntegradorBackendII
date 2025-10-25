package com.example.FrankySabado.controladores;

import com.example.FrankySabado.modelos.dtos.CertificadoDTO;
import com.example.FrankySabado.servicios.CertificadoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/certificados")
public class CertificadoControlador {

    @Autowired
    private CertificadoServicios servicio;

    @PostMapping
    public ResponseEntity<?> crearCertificado(@RequestBody CertificadoDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.crear(dto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCertificadoPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorId(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/perfil/{idPerfilEstudiante}")
    public ResponseEntity<?> obtenerPorPerfilEstudiante(@PathVariable Long idPerfilEstudiante) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorPerfilEstudiante(idPerfilEstudiante));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}

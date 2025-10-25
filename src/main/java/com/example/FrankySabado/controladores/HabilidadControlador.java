package com.example.FrankySabado.controladores;

import com.example.FrankySabado.modelos.dtos.HabilidadDTO;
import com.example.FrankySabado.servicios.HabilidadServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/habilidades")
public class HabilidadControlador {

    @Autowired
    private HabilidadServicios servicio;

    @PostMapping
    public ResponseEntity<?> crearHabilidad(@RequestBody HabilidadDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.crear(dto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerHabilidadPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorId(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarTodos());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/perfil/{idPerfil}")
    public ResponseEntity<?> obtenerPorPerfilEstudiante(@PathVariable Long idPerfil) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorPerfilEstudiante(idPerfil));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHabilidad(@PathVariable Long id) {
        try {
            servicio.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Habilidad eliminada");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
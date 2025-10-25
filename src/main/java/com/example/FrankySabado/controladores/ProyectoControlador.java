package com.example.FrankySabado.controladores;

import com.example.FrankySabado.modelos.dtos.ProyectoDTO;
import com.example.FrankySabado.servicios.ProyectoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/proyectos")
public class ProyectoControlador {

    @Autowired
    private ProyectoServicios servicio;

    @PostMapping
    public ResponseEntity<?> crearProyecto(@RequestBody ProyectoDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.crear(dto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProyectoPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorId(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarTodos());
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

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProyecto(@PathVariable Long id, @RequestBody ProyectoDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.actualizar(id, dto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProyecto(@PathVariable Long id) {
        try {
            servicio.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Proyecto eliminado");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
package com.example.FrankySabado.controladores;

import com.example.FrankySabado.modelos.PerfilEstudiante;
import com.example.FrankySabado.modelos.dtos.PerfilEstudianteDTO;
import com.example.FrankySabado.servicios.PerfilEstudianteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/perfiles")
public class PerfilEstudianteControlador {

    @Autowired
    private PerfilEstudianteServicios servicio;

    @PostMapping
    public ResponseEntity<?> crearPerfil(@RequestBody PerfilEstudiante perfil) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.guardarPerfil(perfil));
        } catch (PerfilEstudianteServicios.PerfilExistenteException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPerfilPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPerfilPorId(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos(Pageable pageable, @RequestParam(required = false) String programa, @RequestParam(required = false) Integer semestre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarConFiltros(programa, semestre, pageable));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPerfil(@PathVariable Long id, @RequestBody PerfilEstudiante perfil) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.actualizarPerfil(id, perfil));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPerfil(@PathVariable Long id) {
        try {
            servicio.eliminarPerfil(id);
            return ResponseEntity.status(HttpStatus.OK).body("Perfil eliminado");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<?> obtenerPorEstudianteId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorEstudianteId(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/busqueda")
    public ResponseEntity<?> buscarPorPalabraClave(@RequestParam String query) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorPalabraClave(query));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/autenticado")
    public ResponseEntity<?> crearPerfilAutenticado(@RequestBody PerfilEstudianteDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.crearConEstudianteAutenticado(dto));
        } catch (PerfilEstudianteServicios.PerfilExistenteException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}

package com.example.FrankySabado.controladores;
import com.example.FrankySabado.servicios.EstudianteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.FrankySabado.modelos.Estudiante;

@RestController
@RequestMapping("/estudiantes")

public class EstudianteControlador {

    @Autowired
    EstudianteServicios servicio;
@PostMapping
    public ResponseEntity<?>activarGuardadoEstudiante(@RequestBody Estudiante datos){
        try{return ResponseEntity
                .status(HttpStatus.OK)
                .body(servicio.guardarEstudiante(datos));
    }catch(Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<?>activarBuscarTodosEstudiantes(){
        try{return ResponseEntity
                .status(HttpStatus.OK)
                .body(servicio.buscarTodosLosEstudiantes());
        }catch(Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}

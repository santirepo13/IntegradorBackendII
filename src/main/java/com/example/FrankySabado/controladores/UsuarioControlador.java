package com.example.FrankySabado.controladores;
import com.example.FrankySabado.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.FrankySabado.servicios.UsuarioServicios;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
@Autowired
private UsuarioServicios servicio;
    @PostMapping
    public ResponseEntity<?> activarPeticionGuardar(@RequestBody Usuario datos) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.guardarUsuarioGenerico(datos));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }

    }
@GetMapping
    public ResponseEntity<?>activarPeticionBuscarTodos(){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarTodosLosUsuarios());
        } catch (Exception error) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }


    }

    //buscar usuario por id
@GetMapping("/{id}")
    public ResponseEntity<?>activarPeticionBuscarPorId(@PathVariable Integer id){
        try {

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarUsuarioPorId(id));


        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());

        }

    }
}

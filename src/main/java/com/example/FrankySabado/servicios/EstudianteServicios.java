package com.example.FrankySabado.servicios;

import com.example.FrankySabado.ayudas.MensajeError;
import com.example.FrankySabado.modelos.Estudiante;
import com.example.FrankySabado.modelos.dtos.EstudianteDTO;
import com.example.FrankySabado.modelos.mapas.IMapaEstudiante;
import com.example.FrankySabado.repositorios.IEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicios {

    @Autowired
    private IEstudianteRepositorio repositorio;
    @Autowired
    private IMapaEstudiante mapa;

    // 1. Servicio para guardar un estudiante
    public EstudianteDTO guardarEstudiante(Estudiante datosEstudiante) throws Exception {
        try {
            return this.mapa.toDTO(this.repositorio.save(datosEstudiante));
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage(), ex);
        }
    }

        // 2. Servicio para buscar un estudiante por ID
    public EstudianteDTO buscarEstudiantePorId(Long idEstudianteABuscar) throws Exception {
        try {
            Optional<Estudiante> estudianteEncontrado = this.repositorio.findById(idEstudianteABuscar);
            if (estudianteEncontrado.isPresent()) {
                return this.mapa.toDTO(estudianteEncontrado.get());
            } else {
                throw new Exception(MensajeError.USUARIO_NO_ECONTRADO.getDescripcion());
            }
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage(), ex);
        }
    }


    // 3. Servicio para buscar todos los estudiantes
    public List<EstudianteDTO> buscarTodosLosEstudiantes() throws Exception {
        try {
            return this.mapa.toDTO(this.repositorio.findAll());
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage(), ex);
        }
    }

    // 4. Servicio para buscar estudiantes por promedio exacto
    public List<EstudianteDTO> buscarEstudiantesPorPromedio(double promedio) throws Exception {
        try {
            return this.mapa.toDTO(this.repositorio.findByPromedio(promedio));
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage(), ex);
        }
    }
}

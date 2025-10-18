package com.example.FrankySabado.servicios;

import com.example.FrankySabado.ayudas.MensajeError;
import com.example.FrankySabado.modelos.PerfilEstudiante;
import com.example.FrankySabado.repositorios.IPerfilEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilEstudianteServicios {

    @Autowired
    private IPerfilEstudianteRepositorio repositorio;

    // Guardar un perfil
    public PerfilEstudiante guardarPerfil(PerfilEstudiante perfil) throws Exception {
        try {
            return this.repositorio.save(perfil);
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage(), ex);
        }
    }

    // Buscar perfil por id
    public PerfilEstudiante buscarPerfilPorId(Long idPerfil) throws Exception {
        try {
            Optional<PerfilEstudiante> encontrado = this.repositorio.findById(idPerfil);
            if (encontrado.isPresent()) {
                return encontrado.get();
            } else {
                throw new Exception(MensajeError.USUARIO_NO_ECONTRADO.getDescripcion());
            }
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage(), ex);
        }
    }

    // Buscar todos los perfiles
    public List<PerfilEstudiante> buscarTodosLosPerfiles() throws Exception {
        try {
            return this.repositorio.findAll();
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage(), ex);
        }
    }

    // Actualizar perfil
    public PerfilEstudiante actualizarPerfil(Long idPerfil, PerfilEstudiante datosActualizados) throws Exception {
        try {
            Optional<PerfilEstudiante> encontrado = this.repositorio.findById(idPerfil);
            if (encontrado.isPresent()) {
                PerfilEstudiante perfil = encontrado.get();

                perfil.setResumen(datosActualizados.getResumen());
                perfil.setIntereses(datosActualizados.getIntereses());
                perfil.setExperiencia(datosActualizados.getExperiencia());
                perfil.setProyectos(datosActualizados.getProyectos());
                perfil.setEstudiante(datosActualizados.getEstudiante());
                perfil.setHabilidad(datosActualizados.getHabilidad());
                perfil.setProyecto(datosActualizados.getProyecto());

                return this.repositorio.save(perfil);
            } else {
                throw new Exception(MensajeError.USUARIO_NO_ECONTRADO.getDescripcion());
            }
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage(), ex);
        }
    }

    // Eliminar perfil
    public void eliminarPerfil(Long idPerfil) throws Exception {
        try {
            if (this.repositorio.existsById(idPerfil)) {
                this.repositorio.deleteById(idPerfil);
            } else {
                throw new Exception(MensajeError.USUARIO_NO_ECONTRADO.getDescripcion());
            }
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage(), ex);
        }
    }

    // Buscar perfil por estudiante id
    public PerfilEstudiante buscarPorEstudianteId(Long idEstudiante) throws Exception {
        try {
            Optional<PerfilEstudiante> encontrado = this.repositorio.findByEstudianteId(idEstudiante);
            if (encontrado.isPresent()) {
                return encontrado.get();
            } else {
                throw new Exception(MensajeError.USUARIO_NO_ECONTRADO.getDescripcion());
            }
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage(), ex);
        }
    }
}

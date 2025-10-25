package com.example.FrankySabado.servicios;

import com.example.FrankySabado.ayudas.MensajeError;
import com.example.FrankySabado.ayudas.TipoHabilidad;
import com.example.FrankySabado.modelos.Habilidad;
import com.example.FrankySabado.modelos.PerfilEstudiante;
import com.example.FrankySabado.modelos.dtos.HabilidadDTO;
import com.example.FrankySabado.repositorios.IHabilidadRepositorio;
import com.example.FrankySabado.repositorios.IPerfilEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HabilidadServicios {

    @Autowired
    private IHabilidadRepositorio repositorio;

    @Autowired
    private IPerfilEstudianteRepositorio perfilEstudianteRepositorio;

    public static class HabilidadNoEncontradaException extends Exception {
        public HabilidadNoEncontradaException(String mensaje) {
            super(mensaje);
        }
    }

    public HabilidadDTO crear(HabilidadDTO dto) throws Exception {
        try {
            if (dto.getNombre() == null || dto.getNombre().isEmpty()) {
                throw new Exception("El nombre de la habilidad es obligatorio");
            }
            if (dto.getNivel() == null || dto.getNivel() < 1 || dto.getNivel() > 10) {
                throw new Exception("El nivel de la habilidad debe estar entre 1 y 10");
            }
            if (dto.getTipoHabilidad() == null || dto.getTipoHabilidad().isEmpty()) {
                throw new Exception("El tipo de habilidad es obligatorio");
            }
            if (dto.getIdPerfilEstudiante() == null) {
                throw new Exception("El ID del perfil de estudiante es obligatorio");
            }

            Optional<PerfilEstudiante> perfilOpt = perfilEstudianteRepositorio.findById(dto.getIdPerfilEstudiante());
            if (perfilOpt.isEmpty()) {
                throw new Exception("No se encontró el perfil de estudiante con ID: " + dto.getIdPerfilEstudiante());
            }

            Habilidad entidad = new Habilidad();
            entidad.setNombre(dto.getNombre());
            entidad.setNivel(dto.getNivel());
            
            try {
                entidad.setTipoHabilidad(TipoHabilidad.valueOf(dto.getTipoHabilidad()));
            } catch (IllegalArgumentException iae) {
                throw new Exception("Valor de tipoHabilidad inválido: " + dto.getTipoHabilidad(), iae);
            }
            
            entidad.setPerfilEstudiante(perfilOpt.get());
            
            Habilidad guardado = repositorio.save(entidad);

            HabilidadDTO resultado = new HabilidadDTO();
            resultado.setId(guardado.getId());
            resultado.setNombre(guardado.getNombre());
            resultado.setNivel(guardado.getNivel());
            resultado.setTipoHabilidad(guardado.getTipoHabilidad().name());
            resultado.setIdPerfilEstudiante(guardado.getPerfilEstudiante().getId());
            
            return resultado;
        } catch (Exception ex) {
            if (ex instanceof HabilidadNoEncontradaException) {
                throw (HabilidadNoEncontradaException) ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - crear: " + ex.getMessage(), ex);
        }
    }

    public HabilidadDTO buscarPorId(Long id) throws Exception {
        try {
            Optional<Habilidad> encontrado = repositorio.findById(id);
            if (encontrado.isEmpty()) {
                throw new HabilidadNoEncontradaException("Habilidad no encontrada con id: " + id);
            }
            Habilidad habilidad = encontrado.get();
            
            HabilidadDTO dto = new HabilidadDTO();
            dto.setId(habilidad.getId());
            dto.setNombre(habilidad.getNombre());
            dto.setNivel(habilidad.getNivel());
            dto.setTipoHabilidad(habilidad.getTipoHabilidad().name());
            dto.setIdPerfilEstudiante(habilidad.getPerfilEstudiante().getId());
            
            return dto;
        } catch (Exception ex) {
            if (ex instanceof HabilidadNoEncontradaException) {
                throw (HabilidadNoEncontradaException) ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - buscar: " + ex.getMessage(), ex);
        }
    }

    public List<HabilidadDTO> buscarPorPerfilEstudiante(Long idPerfilEstudiante) throws Exception {
        try {
            List<Habilidad> habilidades = repositorio.findByPerfilEstudiante_Id(idPerfilEstudiante);
            List<HabilidadDTO> dtos = new ArrayList<>();
            
            for (Habilidad habilidad : habilidades) {
                HabilidadDTO dto = new HabilidadDTO();
                dto.setId(habilidad.getId());
                dto.setNombre(habilidad.getNombre());
                dto.setNivel(habilidad.getNivel());
                dto.setTipoHabilidad(habilidad.getTipoHabilidad().name());
                dto.setIdPerfilEstudiante(habilidad.getPerfilEstudiante().getId());
                dtos.add(dto);
            }
            
            return dtos;
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - buscarPorPerfilEstudiante: " + ex.getMessage(), ex);
        }
    }

    public List<HabilidadDTO> buscarTodos() throws Exception {
        try {
            List<Habilidad> habilidades = repositorio.findAll();
            List<HabilidadDTO> dtos = new ArrayList<>();
            
            for (Habilidad habilidad : habilidades) {
                HabilidadDTO dto = new HabilidadDTO();
                dto.setId(habilidad.getId());
                dto.setNombre(habilidad.getNombre());
                dto.setNivel(habilidad.getNivel());
                dto.setTipoHabilidad(habilidad.getTipoHabilidad().name());
                dto.setIdPerfilEstudiante(habilidad.getPerfilEstudiante().getId());
                dtos.add(dto);
            }
            
            return dtos;
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - buscarTodos: " + ex.getMessage(), ex);
        }
    }

        public void eliminar(Long id) throws Exception {
        try {
            Optional<Habilidad> encontrado = repositorio.findById(id);
            if (encontrado.isEmpty()) {
                throw new HabilidadNoEncontradaException("Habilidad no encontrada con id: " + id);
            }

            Habilidad habilidad = encontrado.get();

            // Validar que la habilidad pertenece a un perfil de estudiante
            if (habilidad.getPerfilEstudiante() == null || habilidad.getPerfilEstudiante().getId() == null) {
                throw new Exception("La habilidad no pertenece a ningún perfil de estudiante o no tiene propietario");
            }

            repositorio.deleteById(id);
        } catch (Exception ex) {
            if (ex instanceof HabilidadNoEncontradaException) {
                throw (HabilidadNoEncontradaException) ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - eliminar: " + ex.getMessage(), ex);
        }
    }
}
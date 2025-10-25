package com.example.FrankySabado.servicios;

import com.example.FrankySabado.ayudas.MensajeError;
import com.example.FrankySabado.ayudas.Tecnologias;
import com.example.FrankySabado.modelos.Proyecto;
import com.example.FrankySabado.modelos.PerfilEstudiante;
import com.example.FrankySabado.modelos.dtos.ProyectoDTO;
import com.example.FrankySabado.repositorios.IProyectoRepositorio;
import com.example.FrankySabado.repositorios.IPerfilEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class ProyectoServicios {

    @Autowired
    private IProyectoRepositorio repositorio;

    @Autowired
    private IPerfilEstudianteRepositorio perfilEstudianteRepositorio;

    public static class ProyectoNoEncontradoException extends Exception {
        public ProyectoNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    public static class PerfilEstudianteNoEncontradoException extends Exception {
        public PerfilEstudianteNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    public ProyectoDTO crear(ProyectoDTO dto) throws Exception {
        try {
            Optional<PerfilEstudiante> perfil = perfilEstudianteRepositorio.findById(dto.getIdPerfilEstudiante());
            if (perfil.isEmpty()) {
                throw new PerfilEstudianteNoEncontradoException("Perfil de estudiante no encontrado con id: " + dto.getIdPerfilEstudiante());
            }

            Proyecto proyecto = new Proyecto();
            proyecto.setTitulo(dto.getTitulo());
            proyecto.setDescripcion(dto.getDescripcion());
            proyecto.setUrl_proyecto(dto.getUrl_proyecto());
            
            if (dto.getTecnologias() != null) {
                try {
                    proyecto.setTecnologias(Tecnologias.valueOf(dto.getTecnologias()));
                } catch (IllegalArgumentException iae) {
                    throw new Exception("Valor de tecnologías inválido: " + dto.getTecnologias(), iae);
                }
            }
            
            proyecto.setPerfilEstudiante(perfil.get());
            
            Proyecto guardado = repositorio.save(proyecto);

            ProyectoDTO resultado = new ProyectoDTO();
            resultado.setTitulo(guardado.getTitulo());
            resultado.setDescripcion(guardado.getDescripcion());
            resultado.setUrl_proyecto(guardado.getUrl_proyecto());
            resultado.setTecnologias(guardado.getTecnologias() != null ? guardado.getTecnologias().name() : null);
            resultado.setIdPerfilEstudiante(guardado.getPerfilEstudiante().getId());
            
            return resultado;
        } catch (Exception ex) {
            if (ex instanceof ProyectoNoEncontradoException || ex instanceof PerfilEstudianteNoEncontradoException) {
                throw ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - crear: " + ex.getMessage(), ex);
        }
    }

    public ProyectoDTO actualizar(Long id, ProyectoDTO dto) throws Exception {
        try {
            Optional<Proyecto> encontrado = repositorio.findById(id);
            if (encontrado.isEmpty()) {
                throw new ProyectoNoEncontradoException("Proyecto no encontrado con id: " + id);
            }

            Optional<PerfilEstudiante> perfil = perfilEstudianteRepositorio.findById(dto.getIdPerfilEstudiante());
            if (perfil.isEmpty()) {
                throw new PerfilEstudianteNoEncontradoException("Perfil de estudiante no encontrado con id: " + dto.getIdPerfilEstudiante());
            }

            Proyecto proyecto = encontrado.get();
            proyecto.setTitulo(dto.getTitulo());
            proyecto.setDescripcion(dto.getDescripcion());
            proyecto.setUrl_proyecto(dto.getUrl_proyecto());
            
            if (dto.getTecnologias() != null) {
                try {
                    proyecto.setTecnologias(Tecnologias.valueOf(dto.getTecnologias()));
                } catch (IllegalArgumentException iae) {
                    throw new Exception("Valor de tecnologías inválido: " + dto.getTecnologias(), iae);
                }
            }
            
            proyecto.setPerfilEstudiante(perfil.get());

            Proyecto guardado = repositorio.save(proyecto);

            ProyectoDTO resultado = new ProyectoDTO();
            resultado.setTitulo(guardado.getTitulo());
            resultado.setDescripcion(guardado.getDescripcion());
            resultado.setUrl_proyecto(guardado.getUrl_proyecto());
            resultado.setTecnologias(guardado.getTecnologias() != null ? guardado.getTecnologias().name() : null);
            resultado.setIdPerfilEstudiante(guardado.getPerfilEstudiante().getId());
            
            return resultado;
        } catch (Exception ex) {
            if (ex instanceof ProyectoNoEncontradoException || ex instanceof PerfilEstudianteNoEncontradoException) {
                throw ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - actualizar: " + ex.getMessage(), ex);
        }
    }

    public ProyectoDTO buscarPorId(Long id) throws Exception {
        try {
            Optional<Proyecto> encontrado = repositorio.findById(id);
            if (encontrado.isEmpty()) {
                throw new ProyectoNoEncontradoException("Proyecto no encontrado con id: " + id);
            }
            
            Proyecto proyecto = encontrado.get();
            ProyectoDTO dto = new ProyectoDTO();
            dto.setTitulo(proyecto.getTitulo());
            dto.setDescripcion(proyecto.getDescripcion());
            dto.setUrl_proyecto(proyecto.getUrl_proyecto());
            dto.setTecnologias(proyecto.getTecnologias() != null ? proyecto.getTecnologias().name() : null);
            dto.setIdPerfilEstudiante(proyecto.getPerfilEstudiante().getId());
            
            return dto;
        } catch (Exception ex) {
            if (ex instanceof ProyectoNoEncontradoException) {
                throw ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - buscar: " + ex.getMessage(), ex);
        }
    }

    public List<ProyectoDTO> buscarPorPerfilEstudiante(Long idPerfilEstudiante) throws Exception {
        try {
            List<Proyecto> proyectos = repositorio.findByPerfilEstudiante_Id(idPerfilEstudiante);
            List<ProyectoDTO> dtos = new ArrayList<>();
            
            for (Proyecto proyecto : proyectos) {
                ProyectoDTO dto = new ProyectoDTO();
                dto.setTitulo(proyecto.getTitulo());
                dto.setDescripcion(proyecto.getDescripcion());
                dto.setUrl_proyecto(proyecto.getUrl_proyecto());
                dto.setTecnologias(proyecto.getTecnologias() != null ? proyecto.getTecnologias().name() : null);
                dto.setIdPerfilEstudiante(proyecto.getPerfilEstudiante().getId());
                dtos.add(dto);
            }
            
            return dtos;
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - buscar por perfil: " + ex.getMessage(), ex);
        }
    }

    public List<ProyectoDTO> buscarTodos() throws Exception {
        try {
            List<Proyecto> proyectos = repositorio.findAll();
            List<ProyectoDTO> dtos = new ArrayList<>();
            
            for (Proyecto proyecto : proyectos) {
                ProyectoDTO dto = new ProyectoDTO();
                dto.setTitulo(proyecto.getTitulo());
                dto.setDescripcion(proyecto.getDescripcion());
                dto.setUrl_proyecto(proyecto.getUrl_proyecto());
                dto.setTecnologias(proyecto.getTecnologias() != null ? proyecto.getTecnologias().name() : null);
                dto.setIdPerfilEstudiante(proyecto.getPerfilEstudiante().getId());
                dtos.add(dto);
            }
            
            return dtos;
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - buscar todos: " + ex.getMessage(), ex);
        }
    }

    public void eliminar(Long id) throws Exception {
        try {
            if (!repositorio.existsById(id)) {
                throw new ProyectoNoEncontradoException("Proyecto no encontrado con id: " + id);
            }
            repositorio.deleteById(id);
        } catch (Exception ex) {
            if (ex instanceof ProyectoNoEncontradoException) {
                throw ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - eliminar: " + ex.getMessage(), ex);
        }
    }
}
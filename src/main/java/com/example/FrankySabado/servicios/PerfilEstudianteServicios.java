package com.example.FrankySabado.servicios;

import com.example.FrankySabado.ayudas.Intereses;
import com.example.FrankySabado.ayudas.MensajeError;
import com.example.FrankySabado.modelos.PerfilEstudiante;
import com.example.FrankySabado.modelos.dtos.PerfilEstudianteDTO;
import com.example.FrankySabado.modelos.Estudiante;
import com.example.FrankySabado.repositorios.IPerfilEstudianteRepositorio;
import com.example.FrankySabado.repositorios.IEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Service
public class PerfilEstudianteServicios {

    @Autowired
    private IPerfilEstudianteRepositorio repositorio;

    @Autowired
    private IEstudianteRepositorio estudianteRepositorio;


    public static class PerfilNoEncontradoException extends Exception {
        public PerfilNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    public static class PerfilExistenteException extends Exception {
        public PerfilExistenteException(String mensaje) {
            super(mensaje);
        }
    }

    public PerfilEstudianteDTO crear(PerfilEstudianteDTO dto) throws Exception {
        try {
            PerfilEstudiante entidad = new PerfilEstudiante();
            entidad.setResumen(dto.getResumen());
            if (dto.getIntereses() != null) {
                try {
                    entidad.setIntereses(Intereses.valueOf(dto.getIntereses()));
                } catch (IllegalArgumentException iae) {
                    throw new Exception("Valor de intereses inválido: " + dto.getIntereses(), iae);
                }
            }
            entidad.setProyectos(dto.getProyectos());
            // relaciones/habilidades se omiten en DTO
            PerfilEstudiante guardado = repositorio.save(entidad);

            PerfilEstudianteDTO resultado = new PerfilEstudianteDTO();
            resultado.setResumen(guardado.getResumen());
            resultado.setIntereses(guardado.getIntereses() != null ? guardado.getIntereses().name() : null);
            resultado.setProyectos(guardado.getProyectos());
            resultado.setHabilidades("[]");
            return resultado;
        } catch (Exception ex) {
            if (ex instanceof PerfilNoEncontradoException) {
                throw (PerfilNoEncontradoException) ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - crear: " + ex.getMessage(), ex);
        }
    }

    public PerfilEstudianteDTO actualizar(Long id, PerfilEstudianteDTO dto) throws Exception {
        try {
            Optional<PerfilEstudiante> encontrado = repositorio.findById(id);
            if (encontrado.isEmpty()) {
                throw new PerfilNoEncontradoException("Perfil no encontrado con id: " + id);
            }
            PerfilEstudiante perfil = encontrado.get();
            perfil.setResumen(dto.getResumen());
            perfil.setProyectos(dto.getProyectos());
            if (dto.getIntereses() != null) {
                try {
                    perfil.setIntereses(Intereses.valueOf(dto.getIntereses()));
                } catch (IllegalArgumentException iae) {
                    throw new Exception("Valor de intereses inválido: " + dto.getIntereses(), iae);
                }
            }

            PerfilEstudiante guardado = repositorio.save(perfil);

            PerfilEstudianteDTO res = new PerfilEstudianteDTO(guardado.getResumen(), guardado.getIntereses() != null ? guardado.getIntereses().name() : null, guardado.getProyectos(), "[]");
            return res;
        } catch (Exception ex) {
            if (ex instanceof PerfilNoEncontradoException) {
                throw (PerfilNoEncontradoException) ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - actualizar: " + ex.getMessage(), ex);
        }
    }

    public PerfilEstudianteDTO buscarPorEstudiante(Long idEstudiante) throws Exception {
        try {
            Optional<PerfilEstudiante> encontrado = repositorio.findByEstudiante_Id(idEstudiante);
            if (encontrado.isEmpty()) {
                throw new PerfilNoEncontradoException("Perfil no encontrado para el estudiante: " + idEstudiante);
            }
            PerfilEstudiante perfil = encontrado.get();
            return new PerfilEstudianteDTO(perfil.getResumen(), perfil.getIntereses() != null ? perfil.getIntereses().name() : null, perfil.getProyectos(), "[]");
        } catch (Exception ex) {
            if (ex instanceof PerfilNoEncontradoException) {
                throw (PerfilNoEncontradoException) ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - buscar: " + ex.getMessage(), ex);
        }
    }

    public void eliminar(Long id) throws Exception {
        try {
            if (!repositorio.existsById(id)) {
                throw new PerfilNoEncontradoException("Perfil no encontrado con id: " + id);
            }
            repositorio.deleteById(id);
        } catch (Exception ex) {
            if (ex instanceof PerfilNoEncontradoException) {
                throw (PerfilNoEncontradoException) ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - eliminar: " + ex.getMessage(), ex);
        }
    }

    // Nuevas wrappers para alinear con PerfilEstudianteControlador

    public PerfilEstudianteDTO guardarPerfil(PerfilEstudiante perfil) throws Exception {
        try {
            if (perfil.getEstudiante() != null && perfil.getEstudiante().getId() != null) {
                Optional<PerfilEstudiante> existente = repositorio.findByEstudiante_Id(perfil.getEstudiante().getId());
                if (existente.isPresent()) {
                    throw new PerfilExistenteException("El estudiante ya tiene un perfil con id: " + perfil.getEstudiante().getId());
                }
            }

            PerfilEstudiante guardado = repositorio.save(perfil);
            PerfilEstudianteDTO dto = new PerfilEstudianteDTO(
                guardado.getResumen(),
                guardado.getIntereses() != null ? guardado.getIntereses().name() : null,
                guardado.getProyectos(),
                "[]"
            );
            return dto;
        } catch (Exception ex) {
            if (ex instanceof PerfilExistenteException) {
                throw (PerfilExistenteException) ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - guardarPerfil: " + ex.getMessage(), ex);
        }
    }

    public PerfilEstudianteDTO buscarPerfilPorId(Long id) throws Exception {
        Optional<PerfilEstudiante> encontrado = repositorio.findById(id);
        if (encontrado.isEmpty()) {
            throw new PerfilNoEncontradoException("Perfil no encontrado con id: " + id);
        }
        PerfilEstudiante p = encontrado.get();
        return new PerfilEstudianteDTO(p.getResumen(), p.getIntereses() != null ? p.getIntereses().name() : null, p.getProyectos(), "[]");
    }

    public List<PerfilEstudianteDTO> buscarTodosLosPerfiles() throws Exception {
        List<PerfilEstudiante> lista = repositorio.findAll();
        List<PerfilEstudianteDTO> dtos = new ArrayList<>();
        for (PerfilEstudiante p : lista) {
            dtos.add(new PerfilEstudianteDTO(p.getResumen(), p.getIntereses() != null ? p.getIntereses().name() : null, p.getProyectos(), "[]"));
        }
        return dtos;
    }

    public PerfilEstudianteDTO actualizarPerfil(Long id, PerfilEstudiante perfil) throws Exception {
        PerfilEstudianteDTO dto = new PerfilEstudianteDTO(
            perfil.getResumen(),
            perfil.getIntereses() != null ? perfil.getIntereses().name() : null,
            perfil.getProyectos(),
            "[]"
        );
        return actualizar(id, dto);
    }

    public void eliminarPerfil(Long id) throws Exception {
        eliminar(id);
    }

    public PerfilEstudianteDTO buscarPorEstudianteId(Long idEstudiante) throws Exception {
        return buscarPorEstudiante(idEstudiante);
    }

    public List<PerfilEstudianteDTO> buscarPorPalabraClave(String query) throws Exception {
        try {
            List<PerfilEstudiante> perfiles = repositorio.buscarPorPalabraClave(query);
            List<PerfilEstudianteDTO> dtos = new ArrayList<>();
            for (PerfilEstudiante p : perfiles) {
                dtos.add(new PerfilEstudianteDTO(p.getResumen(), p.getIntereses() != null ? p.getIntereses().name() : null, p.getProyectos(), "[]"));
            }
            return dtos;
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - buscarPorPalabraClave: " + ex.getMessage(), ex);
        }
    }

    public PerfilEstudianteDTO crearConEstudianteAutenticado(PerfilEstudianteDTO dto) throws Exception {
        try {
            // Simulación de ID de estudiante autenticado (sin JWT por ahora)
            Long idEstudianteAutenticado = 1L; // ID hardcodeado para simulación
            
            Optional<Estudiante> estudianteOpt = estudianteRepositorio.findById(idEstudianteAutenticado);
            if (estudianteOpt.isEmpty()) {
                throw new Exception("Estudiante autenticado no encontrado con ID: " + idEstudianteAutenticado);
            }
            
            PerfilEstudiante entidad = new PerfilEstudiante();
            entidad.setResumen(dto.getResumen());
            if (dto.getIntereses() != null) {
                try {
                    entidad.setIntereses(Intereses.valueOf(dto.getIntereses()));
                } catch (IllegalArgumentException iae) {
                    throw new Exception("Valor de intereses inválido: " + dto.getIntereses(), iae);
                }
            }
            entidad.setProyectos(dto.getProyectos());
            entidad.setEstudiante(estudianteOpt.get());
            
          
            Optional<PerfilEstudiante> existente = repositorio.findByEstudiante_Id(estudianteOpt.get().getId());
            if (existente.isPresent()) {
                throw new PerfilExistenteException("El estudiante ya tiene un perfil con id: " + estudianteOpt.get().getId());
            }

            PerfilEstudiante guardado = repositorio.save(entidad);

            PerfilEstudianteDTO resultado = new PerfilEstudianteDTO();
            resultado.setResumen(guardado.getResumen());
            resultado.setIntereses(guardado.getIntereses() != null ? guardado.getIntereses().name() : null);
            resultado.setProyectos(guardado.getProyectos());
            resultado.setHabilidades("[]");
            return resultado;
        } catch (Exception ex) {
            if (ex instanceof PerfilNoEncontradoException) {
                throw (PerfilNoEncontradoException) ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - crearConEstudianteAutenticado: " + ex.getMessage(), ex);
        }
    }

    public Page<PerfilEstudianteDTO> buscarConFiltros(String programa, Integer semestre, Pageable pageable) throws Exception {
        try {
            Page<PerfilEstudiante> pagina = repositorio.buscarConFiltros(programa, semestre, pageable);
            return pagina.map(perfil -> new PerfilEstudianteDTO(
                perfil.getResumen(),
                perfil.getIntereses() != null ? perfil.getIntereses().name() : null,
                perfil.getProyectos(),
                "[]"
            ));
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - buscarConFiltros: " + ex.getMessage(), ex);
        }
    }
}

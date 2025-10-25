package com.example.FrankySabado.servicios;

import com.example.FrankySabado.ayudas.MensajeError;
import com.example.FrankySabado.ayudas.Institucion;
import com.example.FrankySabado.modelos.Certificado;
import com.example.FrankySabado.modelos.PerfilEstudiante;
import com.example.FrankySabado.modelos.dtos.CertificadoDTO;
import com.example.FrankySabado.repositorios.ICertificadoRepositorio;
import com.example.FrankySabado.repositorios.IPerfilEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CertificadoServicios {

    @Autowired
    private ICertificadoRepositorio repositorio;

    @Autowired
    private IPerfilEstudianteRepositorio perfilEstudianteRepositorio;

    public static class CertificadoNoEncontradoException extends Exception {
        public CertificadoNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    public static class PerfilEstudianteNoEncontradoException extends Exception {
        public PerfilEstudianteNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    public CertificadoDTO crear(CertificadoDTO dto) throws Exception {
        try {
            Optional<PerfilEstudiante> perfil = perfilEstudianteRepositorio.findById(dto.getIdPerfilEstudiante());
            if (perfil.isEmpty()) {
                throw new PerfilEstudianteNoEncontradoException("Perfil de estudiante no encontrado con id: " + dto.getIdPerfilEstudiante());
            }

            Certificado certificado = new Certificado();
            certificado.setNombre(dto.getNombre());
            certificado.setUrl_archivo(dto.getUrl_archivo());
            if (dto.getInstitucion() != null) {
                try {
                    certificado.setInstitucion(Institucion.valueOf(dto.getInstitucion()));
                } catch (IllegalArgumentException iae) {
                    throw new Exception("Valor de institución inválido: " + dto.getInstitucion(), iae);
                }
            }
            certificado.setFecha(dto.getFecha());

            certificado.setPerfilEstudiante(perfil.get());

            Certificado guardado = repositorio.save(certificado);

            CertificadoDTO resultado = new CertificadoDTO();
            resultado.setId(guardado.getId());
            resultado.setNombre(guardado.getNombre());
            resultado.setInstitucion(guardado.getInstitucion() != null ? guardado.getInstitucion().name() : null);
            resultado.setFecha(guardado.getFecha());
            resultado.setUrl_archivo(guardado.getUrl_archivo());
            resultado.setIdPerfilEstudiante(guardado.getPerfilEstudiante().getId());

            return resultado;
        } catch (Exception ex) {
            if (ex instanceof CertificadoNoEncontradoException || ex instanceof PerfilEstudianteNoEncontradoException) {
                throw ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - crear: " + ex.getMessage(), ex);
        }
    }

    public CertificadoDTO buscarPorId(Integer id) throws Exception {
        try {
            Optional<Certificado> encontrado = repositorio.findById(id);
            if (encontrado.isEmpty()) {
                throw new CertificadoNoEncontradoException("Certificado no encontrado con id: " + id);
            }

            Certificado certificado = encontrado.get();
            CertificadoDTO dto = new CertificadoDTO();
            dto.setId(certificado.getId());
            dto.setNombre(certificado.getNombre());
            dto.setInstitucion(certificado.getInstitucion() != null ? certificado.getInstitucion().name() : null);
            dto.setFecha(certificado.getFecha());
            dto.setUrl_archivo(certificado.getUrl_archivo());
            dto.setIdPerfilEstudiante(certificado.getPerfilEstudiante().getId());

            return dto;
        } catch (Exception ex) {
            if (ex instanceof CertificadoNoEncontradoException) {
                throw ex;
            }
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - buscar: " + ex.getMessage(), ex);
        }
    }

    public List<CertificadoDTO> buscarPorPerfilEstudiante(Long idPerfilEstudiante) throws Exception {
        try {
            List<Certificado> certificados = repositorio.findByPerfilEstudiante_Id(idPerfilEstudiante);
            List<CertificadoDTO> dtos = new ArrayList<>();

            for (Certificado certificado : certificados) {
                CertificadoDTO dto = new CertificadoDTO();
                dto.setId(certificado.getId());
                dto.setNombre(certificado.getNombre());
                dto.setInstitucion(certificado.getInstitucion() != null ? certificado.getInstitucion().name() : null);
                dto.setFecha(certificado.getFecha());
                dto.setUrl_archivo(certificado.getUrl_archivo());
                dto.setIdPerfilEstudiante(certificado.getPerfilEstudiante().getId());
                dtos.add(dto);
            }

            return dtos;
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - buscar por perfil: " + ex.getMessage(), ex);
        }
    }

}

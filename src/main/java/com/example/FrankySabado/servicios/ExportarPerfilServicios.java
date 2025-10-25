package com.example.FrankySabado.servicios;

import com.example.FrankySabado.ayudas.MensajeError;
import com.example.FrankySabado.modelos.PerfilEstudiante;
import com.example.FrankySabado.modelos.dtos.PerfilExportarDTO;
import com.example.FrankySabado.repositorios.IPerfilEstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExportarPerfilServicios {

    @Autowired
    private IPerfilEstudianteRepositorio repositorio;

    public PerfilExportarDTO generarBorradorPerfil(Long idPerfil) throws Exception {
        try {
            Optional<PerfilEstudiante> encontrado = repositorio.findById(idPerfil);
            if (encontrado.isEmpty()) {
                throw new Exception("Perfil no encontrado con id: " + idPerfil);
            }

            PerfilEstudiante perfil = encontrado.get();

            // Simulación de texto plano para hoja de vida
            StringBuilder sb = new StringBuilder();
            sb.append("RESUMEN:\n");
            sb.append(perfil.getResumen() != null ? perfil.getResumen() : "");
            sb.append("\n\nINTERESES:\n");
            sb.append(perfil.getIntereses() != null ? perfil.getIntereses().name() : "");
            sb.append("\n\nPROYECTOS:\n");
            sb.append(perfil.getProyectos() != null ? perfil.getProyectos() : "");
            sb.append("\n\nHABILIDADES:\n");
            sb.append(perfil.getHabilidad() != null ? perfil.getHabilidad().toString() : "");

            PerfilExportarDTO dto = new PerfilExportarDTO();
            dto.setPerfilId(perfil.getId());
            dto.setEstudianteId(perfil.getEstudiante() != null ? perfil.getEstudiante().getId() : null);
            dto.setResumen(perfil.getResumen());
            dto.setIntereses(perfil.getIntereses() != null ? perfil.getIntereses().name() : null);
            dto.setProyectos(perfil.getProyectos());
            dto.setHabilidades(perfil.getHabilidad() != null ? perfil.getHabilidad().toString() : null);
            dto.setTextoPlano(sb.toString());

            return dto;
        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + " - generarBorradorPerfil: " + ex.getMessage(), ex);
        }
    }
}

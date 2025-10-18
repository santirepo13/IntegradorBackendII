package com.example.FrankySabado.modelos.mapas;

import com.example.FrankySabado.modelos.Estudiante;
import com.example.FrankySabado.modelos.dtos.EstudianteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface IMapaEstudiante {

    @Mapping(source ="usuario.nombre", target = "nombre")
    @Mapping(source ="usuario.rol", target = "rol")
    EstudianteDTO toDTO(Estudiante estudiante);

    //Mapeo de listas

    List<EstudianteDTO> toDTO(List<Estudiante> listaEstudiante);


}

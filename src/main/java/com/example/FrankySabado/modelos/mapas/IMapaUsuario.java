package com.example.FrankySabado.modelos.mapas;


import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.UsuarioGenericoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaUsuario {

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "rol", target = "rol")

    UsuarioGenericoDTO convertir_a_dto(Usuario usuario);
    List<UsuarioGenericoDTO> convertir_lista_a_dto (List<Usuario> lista);

}

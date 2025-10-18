package com.example.FrankySabado.servicios;


import com.example.FrankySabado.ayudas.MensajeError;
import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.UsuarioGenericoDTO;
import com.example.FrankySabado.modelos.mapas.IMapaUsuario;
import com.example.FrankySabado.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicios {

    @Autowired
    private IUsuarioRepositorio repositorio;
    @Autowired
    private IMapaUsuario mapa;

    public UsuarioGenericoDTO guardarUsuarioGenerico(Usuario datosUsuario) throws Exception {
        try {
            return this.mapa.convertir_a_dto(this.repositorio.save(datosUsuario));

        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage());
        }
    }

    //2. Servicio para buscar un usuario por ID

    public UsuarioGenericoDTO buscarUsuarioPorId(Integer idUsuarioABuscar) throws Exception {
        try {

            Optional<Usuario> usuarioEncontrado = this.repositorio.findById(idUsuarioABuscar);

            if (usuarioEncontrado.isPresent()) {

                return this.mapa.convertir_a_dto(usuarioEncontrado.get());

            } else {
                throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + idUsuarioABuscar);
            }

        } catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage());
        }
    }
        //2. Servicio para buscar un usuario por correo
        //
        public UsuarioGenericoDTO buscarUsuarioPorCorreo(String correoABuscar) throws Exception {
            try {
                Optional<Usuario> usuarioEncontrado = this.repositorio.findByCorreo(correoABuscar);

                if (usuarioEncontrado.isPresent()) {
                    return this.mapa.convertir_a_dto(usuarioEncontrado.get());
                } else {
                    throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + correoABuscar);
                }
            } catch (Exception ex) {
                throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage(), ex);
            }
        }

        //4. servicio para buscar todos los registros de la tabla

    public List<UsuarioGenericoDTO> buscarTodosLosUsuarios () throws Exception {
        try {
            return this.mapa.convertir_lista_a_dto(this.repositorio.findAll());
        }
        catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage());
        }
    }


    // 5. servicio para buscar todos los usuarios que tengan un nombre específico
    public List<UsuarioGenericoDTO> buscarUsuariosPorNombre(String nombre) throws Exception {
        try {

            return this.mapa.convertir_lista_a_dto(this.repositorio.findByNombre(nombre));


        }
        catch (Exception ex) {
            throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage());

        }
    }


    }

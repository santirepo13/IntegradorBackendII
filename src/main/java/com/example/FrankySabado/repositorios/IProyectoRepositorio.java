package com.example.FrankySabado.repositorios;

import com.example.FrankySabado.modelos.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProyectoRepositorio extends JpaRepository<Proyecto, Long> {

    List<Proyecto> findByPerfilEstudiante_Id(Long idPerfilEstudiante);

    Optional<Proyecto> findByPerfilEstudiante_IdAndId(Long idPerfilEstudiante, Long idProyecto);
}
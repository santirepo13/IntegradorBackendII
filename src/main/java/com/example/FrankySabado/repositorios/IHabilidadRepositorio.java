package com.example.FrankySabado.repositorios;

import com.example.FrankySabado.modelos.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHabilidadRepositorio extends JpaRepository<Habilidad, Long> {

    List<Habilidad> findByPerfilEstudiante_Id(Long idPerfilEstudiante);
}
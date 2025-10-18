package com.example.FrankySabado.repositorios;

import com.example.FrankySabado.modelos.PerfilEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPerfilEstudianteRepositorio extends JpaRepository<PerfilEstudiante, Long> {

    Optional<PerfilEstudiante> findByEstudianteId(Long id);
}

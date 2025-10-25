package com.example.FrankySabado.repositorios;

import com.example.FrankySabado.modelos.PerfilEstudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPerfilEstudianteRepositorio extends JpaRepository<PerfilEstudiante, Long> {

    Optional<PerfilEstudiante> findByEstudiante_Id(Long id);
    
    @Query("SELECT p FROM PerfilEstudiante p WHERE LOWER(p.resumen) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.intereses) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<PerfilEstudiante> buscarPorPalabraClave(@Param("query") String query);
    
    @Query("SELECT p FROM PerfilEstudiante p WHERE (:programa IS NULL OR p.estudiante.programa = :programa) AND (:semestre IS NULL OR p.estudiante.semestre = :semestre)")
    Page<PerfilEstudiante> buscarConFiltros(@Param("programa") String programa, @Param("semestre") Integer semestre, Pageable pageable);
}

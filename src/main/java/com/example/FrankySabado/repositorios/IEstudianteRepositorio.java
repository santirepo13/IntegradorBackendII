package com.example.FrankySabado.repositorios;

import com.example.FrankySabado.modelos.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEstudianteRepositorio extends JpaRepository<Estudiante,Long> {

    List<Estudiante>findByPromedio(double promedio);
}

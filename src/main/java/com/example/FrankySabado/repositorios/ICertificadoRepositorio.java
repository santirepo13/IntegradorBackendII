package com.example.FrankySabado.repositorios;

import com.example.FrankySabado.modelos.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICertificadoRepositorio extends JpaRepository<Certificado, Integer> {

    List<Certificado> findByPerfilEstudiante_Id(Long idPerfilEstudiante);
}

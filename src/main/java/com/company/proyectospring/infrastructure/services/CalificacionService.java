package com.company.proyectospring.infrastructure.services;

import com.company.proyectospring.domain.model.Calificacion;

import java.util.List;
import java.util.Optional;

public interface CalificacionService {

    void save(Calificacion calificacion);

    void update(Calificacion calificacion);

    void deleteById(Integer id);

    Optional<Calificacion> findCalificacionById(int id);

    List<Calificacion> getAllCalificaciones();
}

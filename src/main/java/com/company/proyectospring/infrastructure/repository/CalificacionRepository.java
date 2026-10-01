package com.company.proyectospring.infrastructure.repository;

import com.company.proyectospring.domain.model.Calificacion;
import org.springframework.data.repository.CrudRepository;

public interface CalificacionRepository extends CrudRepository<Calificacion, Integer> {
}

package com.company.proyectospring.infrastructure.services;

import com.company.proyectospring.domain.model.Calificacion;
import com.company.proyectospring.infrastructure.repository.CalificacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CalificacionServiceImpl implements CalificacionService {
    private final CalificacionRepository calificacionRepository;

    @Override
    public void save(Calificacion calificacion) {
        calificacionRepository.save(calificacion);
    }

    @Override
    public void update(Calificacion calificacion) {
        calificacionRepository.save(calificacion);
    }

    @Override
    public void deleteById(Integer id) {
        calificacionRepository.deleteById(id);
    }

    @Override
    public Optional<Calificacion> findCalificacionById(int id) {
        return calificacionRepository.findById(id);
    }

    @Override
    public List<Calificacion> getAllCalificaciones() {
        return (List<Calificacion>) calificacionRepository.findAll();
    }
}

package com.company.proyectospring.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_calificaciones")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Calificacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;

    @NotBlank(message = "El estudiante es obligatorio")
    @Column(nullable = false, length = 100)
    private String estudiante;

    @NotBlank(message = "El docente es obligatorio")
    @Column(nullable = false, length = 100)
    private String docente;

    @NotBlank(message = "La asignatura es obligatoria")
    @Column(nullable = false, length = 100)
    private String asignatura;

    @NotBlank(message = "La carrera es obligatoria")
    @Column(nullable = false, length = 100)
    private String carrera;

    @NotBlank(message = "La universidad es obligatoria")
    @Column(nullable = false, length = 100)
    private String universidad;

    @NotBlank(message = "El periodo es obligatorio")
    @Column(nullable = false, length = 20)
    private String periodo;

    @NotBlank(message = "La actividad evaluada es obligatoria")
    @Column(nullable = false, length = 100)
    private String actividadEvaluada;

    @Column(nullable = false, columnDefinition = "DECIMAL(2,1)")
    private float nota;

    @CurrentTimestamp
    private LocalDate fecha;
}

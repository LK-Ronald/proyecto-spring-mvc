package com.company.proyectospring.infrastructure.services.dto;

import com.company.proyectospring.domain.enums.Rol;
import jakarta.validation.constraints.NotBlank;

public record DtoUsuarioActualizar(
        @NotBlank
        String cedula,
        @NotBlank
        String nombre,
        @NotBlank
        String correo,
        @NotBlank
        Rol rol) {
}

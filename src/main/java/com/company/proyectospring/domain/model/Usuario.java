package com.company.proyectospring.domain.model;

import lombok.Data;

@Data
public class Usuario {
    private String cedula;
    private String nombre;
    private String correo;
    private String password;
    private String rol;
}

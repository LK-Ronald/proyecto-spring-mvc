package com.company.proyectospring.domain.model;

import com.company.proyectospring.domain.enums.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tb_usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    @Id
    @NotBlank
    @Column(length = 12, nullable = false)
    private String cedula;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(unique = true, length = 150, nullable = false)
    @Email(message = "El correo ingresado es invalido")
    private String correo;

    @NotBlank(message = "La password es oblicatoria")
    @Column(length = 255, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;
}

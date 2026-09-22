package com.company.proyectospring.domain.enums;

public enum Rol {
    USUARIO,
    ADMINISTRADOR;

    public static Rol fromString(final String value) {
        for (Rol rol : Rol.values()) {
            if (rol.name().equalsIgnoreCase(value)) {
                return rol;
            }
        }
        throw new IllegalArgumentException("No existe el rol " + value);
    }
}

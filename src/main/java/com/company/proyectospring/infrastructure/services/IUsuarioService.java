package com.company.proyectospring.infrastructure.services;

import com.company.proyectospring.domain.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    void save(Usuario usuario);

    void update(Usuario usuario);

    boolean updatePassword(String cedula, String oldPassword, String newPassword);

    void deleteById(String cedula);

    Usuario findByCedula(String cedula);

    List<Usuario> getAllUsuarios();
}

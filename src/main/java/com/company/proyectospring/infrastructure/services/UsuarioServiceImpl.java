package com.company.proyectospring.infrastructure.services;

import com.company.proyectospring.domain.model.Usuario;
import com.company.proyectospring.infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void save(Usuario usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());

        Usuario usuarioFinal = new Usuario();
        usuarioFinal.setCedula(usuario.getCedula());
        usuarioFinal.setNombre(usuario.getNombre());
        usuarioFinal.setCorreo(usuario.getCorreo());
        usuarioFinal.setPassword(encodedPassword);
        usuarioFinal.setRol(usuario.getRol());

        usuarioRepository.save(usuarioFinal);
    }

    @Override
    @Transactional
    public void update(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public boolean updatePassword(String cedula, String oldPassword, String newPassword) {
        Usuario usuario = usuarioRepository.findById(cedula).orElse(null);
        if (usuario == null) {
            return false;
        }
        if (!passwordEncoder.matches(oldPassword, usuario.getPassword())) {
            return false;
        }
        String encodedPassword = passwordEncoder.encode(newPassword);
        usuario.setPassword(encodedPassword);
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    @Transactional
    public void deleteById(String cedula) {
        usuarioRepository.deleteById(cedula);
    }

    @Override
    public Usuario findByCedula(String cedula) {
        return usuarioRepository.findById(cedula).orElse(null);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }
}

package com.company.proyectospring.infrastructure.services;

import com.company.proyectospring.domain.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioDetailsService implements UserDetailsService {
    private final IUsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByCedula(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return User.builder()
                .username(usuario.getCedula())
                .password(usuario.getPassword())
                .roles(usuario.getRol().name())
                .build();
    }
}

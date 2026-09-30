package com.company.proyectospring.infrastructure.config;

import com.company.proyectospring.domain.enums.Rol;
import com.company.proyectospring.domain.model.Usuario;
import com.company.proyectospring.infrastructure.services.IUsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(IUsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        return args -> {

            Usuario admin = usuarioService.findByCedula("00000000");
            if (admin == null) {
                admin = new Usuario();
                admin.setCedula("00000000");
                admin.setNombre("Administrador");
                admin.setCorreo("admin@admin.com");
                admin.setPassword("admin");
                admin.setRol(Rol.ADMINISTRADOR);

                usuarioService.save(admin);
            }
        };
    }

}

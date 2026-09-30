package com.company.proyectospring.controllers;

import com.company.proyectospring.domain.model.Usuario;
import com.company.proyectospring.infrastructure.services.IUsuarioService;
import com.company.proyectospring.infrastructure.services.dto.DtoUsuarioActualizar;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private final IUsuarioService usuarioService;

    @GetMapping("/agregar")
    public String formularioAgregar(Model model) {
        if (model.getAttribute("usuario") == null) {
            model.addAttribute("usuario", new Usuario());
        }
        return "usuarios/agregar";
    }

    @PostMapping("/agregar")
    public String agregarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult bindingResult,
                                 RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "usuarios/agregar";
        }
        usuarioService.save(usuario);
        redirect.addFlashAttribute("mensaje", "Usuario agregado exitosamente");
        return "redirect:/usuarios/agregar";
    }

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/listar";
    }

    @GetMapping("/actualizar/{cedula}")
    public String formularioActualizar(@PathVariable("cedula") String cedula, Model model) {
        Usuario usuario = usuarioService.findByCedula(cedula);
        if (usuario == null) {
            model.addAttribute("mensaje", "Usuario no encontrado");
            return "redirect:/usuarios/buscar";
        }
        model.addAttribute("usuario", usuario);
        return "usuarios/actualizar";
    }

    @PostMapping("/actualizar/{cedula}")
    public String actualizarUsuario(@PathVariable String cedula, @Valid @ModelAttribute DtoUsuarioActualizar dtoUsuario,
                                    BindingResult bindingResult, RedirectAttributes redirect) {
        if (cedula.isBlank()) {
            redirect.addFlashAttribute("mensaje", "Debe de ingresar una cedula");
            return "redirect:/usuarios/buscar";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/usuarios/actualizar/" + cedula;
        }
        Usuario usuario = usuarioService.findByCedula(cedula);
        if (usuario == null) {
            redirect.addFlashAttribute("mensaje", "Usuario no encontrado");
            return "redirect:/usuarios/buscar";
        }
        Usuario usuarioActualizado = new Usuario(cedula, dtoUsuario.nombre(), dtoUsuario.correo(), usuario.getPassword(), dtoUsuario.rol());
        usuarioService.update(usuarioActualizado);
        redirect.addFlashAttribute("mensaje", "Usuario actualizado exitosamente");
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/buscar")
    public String formularioBuscar() {
        return "usuarios/buscar";
    }

    @PostMapping("/buscar")
    public String buscarUsuario(@RequestParam("cedula") String cedula, Model model) {
        if (cedula == null || cedula.isBlank()) {
            model.addAttribute("mensaje", "Debe de ingresar una cedula");
            return "/usuarios/buscar";
        }
        Usuario encontrado = usuarioService.findByCedula(cedula);
        if (encontrado == null) {
            model.addAttribute("mensaje", "Usuario no encontrado con la cedula: " + cedula);
            return "/usuarios/buscar";
        }
        model.addAttribute("usuario", encontrado);
        return "usuarios/buscar";
    }

    @PostMapping("/eliminar/{cedula}")
    public String eliminarUsuario(@NotBlank @PathVariable String cedula, RedirectAttributes redirect) {
        usuarioService.deleteById(cedula);
        redirect.addFlashAttribute("mensaje", "Usuario eliminado exitosamente");
        return "redirect:/";
    }


    @GetMapping("/cambiar-password")
    public String formularioCambiarPassword() {
        return "usuarios/cambiar-password";
    }

    @PostMapping("/cambiar-password")
    public String cambiarPassword(@RequestParam("oldPassword") String oldPassword,
                                  @RequestParam("newPassword") String newPassword,
                                  Authentication auth,
                                  RedirectAttributes redirect,
                                  HttpSession session) {
        if (auth == null || !auth.isAuthenticated()) {
            redirect.addFlashAttribute("mensaje", "Usuario no autenticado");
            return "redirect:/auth/login";
        }
        String cedula = auth.getName();
        log.info("Cedula del usuario autenticado: {}", cedula);
        if (oldPassword.isBlank() || newPassword.isBlank()) {
            redirect.addFlashAttribute("mensaje", "La password es obligatoria");
            return "redirect:/usuarios/cambiar-password";
        }
        if (oldPassword.equals(newPassword)) {
            redirect.addFlashAttribute("mensaje", "La nueva password no puede ser igual a la anterior");
            return "redirect:/usuarios/cambiar-password";
        }
        boolean estado = usuarioService.updatePassword(cedula, oldPassword, newPassword);
        if (!estado) {
            redirect.addFlashAttribute("mensaje", "La password actual no es correcta");
            return "redirect:/usuarios/cambiar-password";
        }
        session.invalidate();
        SecurityContextHolder.clearContext();
        return "redirect:/auth/login";
    }

    @GetMapping("/perfil")
    public String perfil(Model model, Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/auth/login";
        }
        String cedula = auth.getName();
        Usuario usuario = usuarioService.findByCedula(cedula);
        if (usuario == null) {
            return "redirect:/auth/login";
        }
        model.addAttribute("usuario", usuario);
        return "usuarios/perfil";
    }

}

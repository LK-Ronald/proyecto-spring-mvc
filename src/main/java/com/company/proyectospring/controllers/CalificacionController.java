package com.company.proyectospring.controllers;

import com.company.proyectospring.domain.model.Calificacion;
import com.company.proyectospring.infrastructure.services.CalificacionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/calificacion")
@AllArgsConstructor
public class CalificacionController {
    private final CalificacionService calificacionService;

    @GetMapping("/agregar")
    public String formularioAgregar(Model model) {
        if (model.getAttribute("calificacion") == null) {
            model.addAttribute("calificacion", new Calificacion());
        }
        return "calificacion/agregar";
    }

    @PostMapping("/agregar")
    public String agregarCalificacion(@ModelAttribute Calificacion calificacion, RedirectAttributes redirect) {
        calificacionService.save(calificacion);
        redirect.addFlashAttribute("mensaje", "Calificacion agregada exitosamente");
        return "redirect:/calificacion/agregar";
    }

    @GetMapping("/listar")
    public String listarCalificaciones(Model model) {
        List<Calificacion> calificaciones = calificacionService.getAllCalificaciones();
        model.addAttribute("calificaciones", calificaciones);
        return "calificacion/listar";
    }

    @GetMapping("/actualizar/{id}")
    public String formularioActualizar(@PathVariable("id") int id, Model model) {
        Calificacion calificacion = calificacionService.findCalificacionById(id).orElse(null);
        if (calificacion == null) {
            model.addAttribute("mensaje", "Calificacion no encontrada");
            return "redirect:/calificacion/listar";
        }
        model.addAttribute("calificacion", calificacion);
        return "calificacion/actualizar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCalificacion(@PathVariable("id") int id,
                                         @ModelAttribute Calificacion calificacion,
                                         RedirectAttributes redirect) {
        if (id <= 0) {
            redirect.addAttribute("mensaje", "El id ingresado es invalido");
            return "redirect:/calificacion/buscar";
        }
        calificacion.setCid(id);
        calificacionService.update(calificacion);
        redirect.addFlashAttribute("mensaje", "Calificacion actualizada exitosamente");
        return "redirect:/calificacion/listar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCalificacion(@PathVariable("id") int id, RedirectAttributes redirect) {
        if (id <= 0) {
            redirect.addAttribute("mensaje", "El id ingresado es invalido");
            return "redirect:/calificacion/buscar";
        }
        calificacionService.deleteById(id);
        redirect.addFlashAttribute("mensaje", "Calificacion eliminada exitosamente");
        return "redirect:/calificacion/listar";
    }

    @GetMapping("/buscar")
    public String formularioBuscar() {
        return "calificacion/buscar";
    }

    @PostMapping("/buscar")
    public String buscarCalificacion(@RequestParam("id") int id, Model model, RedirectAttributes redirect) {
        if (id <= 0) {
            redirect.addAttribute("mensaje", "El id ingresado es invalido");
            return "redirect:/calificacion/buscar";
        }
        Calificacion encontrada = calificacionService.findCalificacionById(id).orElse(null);
        if (encontrada == null) {
            model.addAttribute("mensaje", "Calificacion no encontrada con el ID: " + id);
            return "calificacion/buscar";
        }
        model.addAttribute("calificacion", encontrada);
        return "calificacion/buscar";
    }
}

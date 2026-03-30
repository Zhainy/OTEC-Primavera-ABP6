package com.otec.primavera.controllers;

import com.otec.primavera.models.Curso;
import com.otec.primavera.services.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin") // Todas las rutas aquí empezarán con /admin
@RequiredArgsConstructor
public class AdminController {

    private final CursoService cursoService;

    // Muestra la vista principal del administrador
    @GetMapping("/dashboard")
    public String verDashboard(Model model) {
        // Le pasamos la lista de cursos a la vista HTML
        model.addAttribute("cursos", cursoService.obtenerCursosActivos());
        // Pasamos un objeto vacío para que el formulario sepa dónde guardar los datos nuevos
        model.addAttribute("nuevoCurso", new Curso());

        return "admin/dashboard"; // Esto le dice a Spring que busque el archivo templates/admin/dashboard.html
    }

    // Recibe los datos del formulario y los guarda
    @PostMapping("/cursos/guardar")
    public String guardarCurso(@ModelAttribute Curso curso) {
        cursoService.guardarCurso(curso);
        // Después de guardar, redirigimos de vuelta al panel para ver la tabla actualizada
        return "redirect:/admin/dashboard";
    }
    // --- NUEVAS RUTAS CRUD ---

    // 1. Mostrar formulario de edición
    @GetMapping("/cursos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("curso", cursoService.obtenerPorId(id));
        return "admin/editar-curso"; // Crearemos este HTML ahora
    }

    // 2. Guardar los cambios editados
    @PostMapping("/cursos/actualizar/{id}")
    public String actualizarCurso(@PathVariable Long id, @ModelAttribute Curso curso) {
        curso.setId(id); // Aseguramos que se actualice el registro existente, no crear uno nuevo
        // Por defecto, al editar desde este form, asumimos que sigue activo
        curso.setActivo(true);
        cursoService.guardarCurso(curso);
        return "redirect:/admin/dashboard";
    }

    // 3. Eliminar (Borrado lógico: lo pasamos a inactivo)
    @GetMapping("/cursos/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        Curso curso = cursoService.obtenerPorId(id);
        curso.setActivo(false); // No lo borramos de la BD por historial, solo lo ocultamos
        cursoService.guardarCurso(curso);
        return "redirect:/admin/dashboard";
    }
}
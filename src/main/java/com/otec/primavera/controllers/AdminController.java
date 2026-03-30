package com.otec.primavera.controllers;

import com.otec.primavera.dto.AdminAlumnoMatriculaForm;
import com.otec.primavera.models.Curso;
import com.otec.primavera.models.Matricula;
import com.otec.primavera.models.Usuario;
import com.otec.primavera.services.CursoService;
import com.otec.primavera.services.MatriculaService;
import com.otec.primavera.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin") // Todas las rutas aquí empezarán con /admin
@RequiredArgsConstructor
public class AdminController {

    private final CursoService cursoService;
    private final MatriculaService matriculaService;
    private final UsuarioService usuarioService;

    // Muestra la vista principal del administrador
    @GetMapping("/dashboard")
    public String verDashboard(Model model, Principal principal) {
        long alumnosInscritos = matriculaService.contarAlumnosInscritos();
        long alumnosNoAsignados = Math.max(0, usuarioService.contarAlumnos() - alumnosInscritos);

        // Le pasamos la lista de cursos a la vista HTML
        model.addAttribute("cursos", cursoService.obtenerCursosActivos());
        // Pasamos un objeto vacío para que el formulario sepa dónde guardar los datos nuevos
        model.addAttribute("nuevoCurso", new Curso());
        model.addAttribute("cursosActivos", cursoService.contarCursosActivos());
        model.addAttribute("alumnosInscritos", alumnosInscritos);
        model.addAttribute("alumnosNoAsignados", alumnosNoAsignados);
        model.addAttribute("tasaExito", matriculaService.calcularTasaExito());
        model.addAttribute("progresoPromedio", matriculaService.calcularProgresoPromedioGeneral());
        model.addAttribute("totalEnCurso", matriculaService.contarPorEstado("EN_CURSO"));
        model.addAttribute("totalAprobado", matriculaService.contarPorEstado("APROBADO"));
        model.addAttribute("totalReprobado", matriculaService.contarPorEstado("REPROBADO"));
        model.addAttribute("adminNombre", principal != null ? principal.getName() : "Administrador");

        return "admin/dashboard"; // Esto le dice a Spring que busque el archivo templates/admin/dashboard.html
    }

    @GetMapping("/alumnos")
    public String verAdministrarAlumnos(Model model, Principal principal) {
        String filtro = null;
        Map<Long, Matricula> matriculasPorAlumno = new LinkedHashMap<>();
        java.util.List<Usuario> alumnosAMostrar = usuarioService.obtenerAlumnos();

        // Aplicar filtro si existe
        if ("sin_asignacion".equals(model.getAttribute("filtro"))) {
            filtro = "sin_asignacion";
            alumnosAMostrar = alumnosAMostrar.stream()
                    .filter(alumno -> !matriculaService.obtenerUltimaMatricula(alumno).isPresent())
                    .collect(Collectors.toList());
        }

        for (Usuario alumno : alumnosAMostrar) {
            matriculaService.obtenerUltimaMatricula(alumno)
                    .ifPresent(matricula -> matriculasPorAlumno.put(alumno.getId(), matricula));
        }

        model.addAttribute("nuevoAlumno", new Usuario());
        model.addAttribute("alumnos", alumnosAMostrar);
        model.addAttribute("matriculasPorAlumno", matriculasPorAlumno);
        model.addAttribute("filtroActivo", filtro);
        model.addAttribute("adminNombre", principal != null ? principal.getName() : "Administrador");
        return "admin/alumnos";
    }

    @GetMapping(value = "/alumnos", params = "filtro=sin_asignacion")
    public String verAdministrarAlumnosFiltrado(@RequestParam String filtro, Model model, Principal principal) {
        Map<Long, Matricula> matriculasPorAlumno = new LinkedHashMap<>();
        java.util.List<Usuario> alumnosAMostrar = usuarioService.obtenerAlumnos().stream()
                .filter(alumno -> !matriculaService.obtenerUltimaMatricula(alumno).isPresent())
                .collect(Collectors.toList());

        for (Usuario alumno : alumnosAMostrar) {
            matriculaService.obtenerUltimaMatricula(alumno)
                    .ifPresent(matricula -> matriculasPorAlumno.put(alumno.getId(), matricula));
        }

        model.addAttribute("nuevoAlumno", new Usuario());
        model.addAttribute("alumnos", alumnosAMostrar);
        model.addAttribute("matriculasPorAlumno", matriculasPorAlumno);
        model.addAttribute("filtroActivo", "sin_asignacion");
        model.addAttribute("adminNombre", principal != null ? principal.getName() : "Administrador");
        return "admin/alumnos";
    }

    @GetMapping("/alumnos/editar/{id}")
    public String verEditarAlumno(@PathVariable Long id, Model model, Principal principal) {
        Usuario alumno = usuarioService.obtenerPorId(id);
        Matricula matriculaActual = matriculaService.obtenerUltimaMatricula(alumno).orElse(null);

        AdminAlumnoMatriculaForm form = new AdminAlumnoMatriculaForm();
        if (matriculaActual != null) {
            form.setCursoId(matriculaActual.getCurso().getId());
            form.setEstado(matriculaActual.getEstado());
            form.setPorcentajeProgreso(matriculaActual.getPorcentajeProgreso());
        } else {
            form.setEstado("EN_CURSO");
            form.setPorcentajeProgreso(0.0);
        }

        model.addAttribute("alumno", alumno);
        model.addAttribute("matriculaActual", matriculaActual);
        model.addAttribute("cursos", cursoService.obtenerCursosActivos());
        model.addAttribute("formMatricula", form);
        model.addAttribute("adminNombre", principal != null ? principal.getName() : "Administrador");
        return "admin/editar-alumno";
    }

    @PostMapping("/alumnos/actualizar/{id}")
    public String actualizarAlumno(@PathVariable Long id,
                                   @ModelAttribute("formMatricula") AdminAlumnoMatriculaForm form,
                                   RedirectAttributes redirectAttributes) {
        try {
            Usuario alumno = usuarioService.obtenerPorId(id);
            Curso curso = cursoService.obtenerPorId(form.getCursoId());
            matriculaService.crearOActualizarMatriculaAdmin(alumno, curso, form.getEstado(), form.getPorcentajeProgreso());
            redirectAttributes.addFlashAttribute("msgType", "success");
            redirectAttributes.addFlashAttribute("msgTitle", "Alumno actualizado");
            redirectAttributes.addFlashAttribute("msgMessage", "Se asigno correctamente el curso y estado del alumno.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msgType", "danger");
            redirectAttributes.addFlashAttribute("msgTitle", "No se pudo actualizar");
            redirectAttributes.addFlashAttribute("msgMessage", "Ocurrio un error al actualizar la asignacion del alumno.");
        }

        return "redirect:/admin/alumnos";
    }

    @PostMapping("/alumnos/guardar")
    public String guardarAlumno(@ModelAttribute Usuario nuevoAlumno, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.registrarNuevoAlumno(nuevoAlumno);
            redirectAttributes.addFlashAttribute("msgType", "success");
            redirectAttributes.addFlashAttribute("msgTitle", "Alumno registrado");
            redirectAttributes.addFlashAttribute("msgMessage", "El alumno fue creado correctamente y ya puede iniciar sesion.");
        } catch (IllegalStateException ex) {
            redirectAttributes.addFlashAttribute("msgType", "danger");
            redirectAttributes.addFlashAttribute("msgTitle", "Correo ya registrado");
            redirectAttributes.addFlashAttribute("msgMessage", ex.getMessage());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msgType", "danger");
            redirectAttributes.addFlashAttribute("msgTitle", "No se pudo registrar");
            redirectAttributes.addFlashAttribute("msgMessage", "Ocurrio un error al crear el alumno.");
        }

        return "redirect:/admin/alumnos";
    }

    // Recibe los datos del formulario y los guarda
    @PostMapping("/cursos/guardar")
    public String guardarCurso(@ModelAttribute Curso curso, RedirectAttributes redirectAttributes) {
        try {
            cursoService.guardarCurso(curso);
            redirectAttributes.addFlashAttribute("msgType", "success");
            redirectAttributes.addFlashAttribute("msgTitle", "Programa guardado");
            redirectAttributes.addFlashAttribute("msgMessage", "El programa se agrego correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msgType", "danger");
            redirectAttributes.addFlashAttribute("msgTitle", "No se pudo guardar");
            redirectAttributes.addFlashAttribute("msgMessage", "Ocurrio un error al crear el programa.");
        }

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
    public String actualizarCurso(@PathVariable Long id, @ModelAttribute Curso curso, RedirectAttributes redirectAttributes) {
        try {
            curso.setId(id); // Aseguramos que se actualice el registro existente, no crear uno nuevo
            // Por defecto, al editar desde este form, asumimos que sigue activo
            curso.setActivo(true);
            cursoService.guardarCurso(curso);
            redirectAttributes.addFlashAttribute("msgType", "success");
            redirectAttributes.addFlashAttribute("msgTitle", "Programa actualizado");
            redirectAttributes.addFlashAttribute("msgMessage", "Los cambios se guardaron correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msgType", "danger");
            redirectAttributes.addFlashAttribute("msgTitle", "No se pudo actualizar");
            redirectAttributes.addFlashAttribute("msgMessage", "No se pudieron guardar los cambios del programa.");
        }

        return "redirect:/admin/dashboard";
    }

    // 3. Eliminar (Borrado lógico: lo pasamos a inactivo)
    @PostMapping("/cursos/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Curso curso = cursoService.obtenerPorId(id);
            curso.setActivo(false); // No lo borramos de la BD por historial, solo lo ocultamos
            cursoService.guardarCurso(curso);
            redirectAttributes.addFlashAttribute("msgType", "success");
            redirectAttributes.addFlashAttribute("msgTitle", "Programa eliminado");
            redirectAttributes.addFlashAttribute("msgMessage", "El programa fue desactivado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msgType", "danger");
            redirectAttributes.addFlashAttribute("msgTitle", "No se pudo eliminar");
            redirectAttributes.addFlashAttribute("msgMessage", "No fue posible desactivar el programa solicitado.");
        }

        return "redirect:/admin/dashboard";
    }
}
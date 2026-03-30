package com.otec.primavera.controllers;

import com.otec.primavera.models.Usuario;
import com.otec.primavera.services.MatriculaService;
import com.otec.primavera.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/alumno")
@RequiredArgsConstructor
public class AlumnoController {

    private final MatriculaService matriculaService;
    private final UsuarioService usuarioService;

    @GetMapping("/dashboard")
    public String verMiProgreso(Model model, Principal principal) {
        Usuario alumno = usuarioService.buscarPorEmail(principal.getName());
        model.addAttribute("nombreAlumno", alumno.getNombre());
        model.addAttribute("matriculaActiva", matriculaService.obtenerMatriculaActiva(alumno).orElse(null));

        return "alumno/dashboard"; // Buscará templates/alumno/dashboard.html
    }
}

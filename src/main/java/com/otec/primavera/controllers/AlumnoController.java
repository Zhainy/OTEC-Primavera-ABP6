package com.otec.primavera.controllers;

import com.otec.primavera.models.Matricula;
import com.otec.primavera.models.Usuario;
import com.otec.primavera.services.MatriculaService;
import com.otec.primavera.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/alumno")
@RequiredArgsConstructor
public class AlumnoController {

    private final MatriculaService matriculaService;
    private final UsuarioService usuarioService;

    @GetMapping("/dashboard")
    public String verMiProgreso(Model model, Principal principal) {
        /* * NOTA: 'Principal' es un objeto de Spring Security que nos dará el email del
         * usuario que inició sesión. Lo dejaremos comentado hasta el Paso 7 (Seguridad).
         * * Usuario alumno = usuarioService.buscarPorEmail(principal.getName());
         * List<Matricula> misMatriculas = matriculaService.obtenerMatriculasPorAlumno(alumno);
         * model.addAttribute("matriculas", misMatriculas);
         */

        return "alumno/dashboard"; // Buscará templates/alumno/dashboard.html
    }
}

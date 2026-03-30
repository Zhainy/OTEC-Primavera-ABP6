package com.otec.primavera.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio() {
        // Redirige automáticamente a la pantalla de inicio de sesión
        return "redirect:/login";
    }
}
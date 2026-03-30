package com.otec.primavera.controllers.api;

import com.otec.primavera.models.Curso;
import com.otec.primavera.services.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController le dice a Spring: "No busques plantillas HTML, devuelve los datos puros (JSON)"
@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
public class CursoRestController {

    private final CursoService cursoService;

    // Endpoint 1: Obtener todos los cursos activos
    // Se accederá mediante GET a http://localhost:8081/api/v1/cursos
    @GetMapping
    public ResponseEntity<List<Curso>> obtenerTodosLosCursos() {
        List<Curso> cursos = cursoService.obtenerCursosActivos();

        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve un 204 No Content si está vacío
        }
        return ResponseEntity.ok(cursos); // Devuelve un 200 OK con la lista en JSON
    }

    // Endpoint 2: Obtener un curso específico por su ID
    // Se accederá mediante GET a http://localhost:8081/api/v1/cursos/1
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id) {
        try {
            Curso curso = cursoService.obtenerPorId(id);
            return ResponseEntity.ok(curso); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Devuelve un 404 Not Found si el ID no existe
        }
    }
}
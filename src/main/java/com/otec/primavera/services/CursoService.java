package com.otec.primavera.services;

import com.otec.primavera.models.Curso;
import com.otec.primavera.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    // Spring inyecta el repositorio automáticamente gracias a @RequiredArgsConstructor
    private final CursoRepository cursoRepository;

    public List<Curso> obtenerCursosActivos() {
        return cursoRepository.findByActivoTrue();
    }

    public long contarCursosActivos() {
        return cursoRepository.countByActivoTrue();
    }

    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
    }
}
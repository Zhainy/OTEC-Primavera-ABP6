package com.otec.primavera.services;

import com.otec.primavera.models.Curso;
import com.otec.primavera.models.Matricula;
import com.otec.primavera.models.Usuario;
import com.otec.primavera.repositories.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

    @Transactional // Asegura que toda la operación se complete o se cancele si hay error
    public Matricula matricularAlumno(Usuario alumno, Curso curso) {

        // REGLA DE NEGOCIO: Verificar si ya tiene un curso activo
        boolean yaEstaEstudiando = matriculaRepository.existsByAlumnoAndEstado(alumno, "EN_CURSO");

        if (yaEstaEstudiando) {
            // Lanzamos una excepción que luego capturaremos para mostrar un mensaje en la vista
            throw new IllegalStateException("El alumno ya se encuentra cursando un programa actualmente.");
        }

        Matricula nuevaMatricula = new Matricula();
        nuevaMatricula.setAlumno(alumno);
        nuevaMatricula.setCurso(curso);
        nuevaMatricula.setFechaInscripcion(LocalDate.now());
        nuevaMatricula.setPorcentajeProgreso(0.0);
        nuevaMatricula.setEstado("EN_CURSO");

        return matriculaRepository.save(nuevaMatricula);
    }

    public List<Matricula> obtenerMatriculasPorAlumno(Usuario alumno) {
        return matriculaRepository.findByAlumno(alumno);
    }
}
package com.otec.primavera.services;

import com.otec.primavera.models.Curso;
import com.otec.primavera.models.Matricula;
import com.otec.primavera.models.Usuario;
import com.otec.primavera.repositories.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private static final String ESTADO_EN_CURSO = "EN_CURSO";
    private static final String ESTADO_APROBADO = "APROBADO";
    private static final String ESTADO_REPROBADO = "REPROBADO";

    private final MatriculaRepository matriculaRepository;

    @Transactional // Asegura que toda la operación se complete o se cancele si hay error
    public Matricula matricularAlumno(Usuario alumno, Curso curso) {

        // REGLA DE NEGOCIO: Verificar si ya tiene un curso activo
        boolean yaEstaEstudiando = matriculaRepository.existsByAlumnoAndEstado(alumno, ESTADO_EN_CURSO);

        if (yaEstaEstudiando) {
            // Lanzamos una excepción que luego capturaremos para mostrar un mensaje en la vista
            throw new IllegalStateException("El alumno ya se encuentra cursando un programa actualmente.");
        }

        Matricula nuevaMatricula = new Matricula();
        nuevaMatricula.setAlumno(alumno);
        nuevaMatricula.setCurso(curso);
        nuevaMatricula.setFechaInscripcion(LocalDate.now());
        nuevaMatricula.setPorcentajeProgreso(0.0);
        nuevaMatricula.setEstado(ESTADO_EN_CURSO);

        return matriculaRepository.save(nuevaMatricula);
    }

    public List<Matricula> obtenerMatriculasPorAlumno(Usuario alumno) {
        return matriculaRepository.findByAlumno(alumno);
    }

    public Optional<Matricula> obtenerMatriculaActiva(Usuario alumno) {
        return matriculaRepository.findFirstByAlumnoAndEstadoOrderByFechaInscripcionDesc(alumno, ESTADO_EN_CURSO);
    }

    public Optional<Matricula> obtenerUltimaMatricula(Usuario alumno) {
        return matriculaRepository.findTopByAlumnoOrderByFechaInscripcionDescIdDesc(alumno);
    }

    public long contarAlumnosInscritos() {
        return matriculaRepository.countAlumnosInscritos();
    }

    public double calcularTasaExito() {
        long finalizados = matriculaRepository.countByEstadoIn(Arrays.asList(ESTADO_APROBADO, ESTADO_REPROBADO));
        if (finalizados == 0) {
            return 0.0;
        }

        long aprobados = matriculaRepository.countByEstado(ESTADO_APROBADO);
        return (aprobados * 100.0) / finalizados;
    }

    public double calcularProgresoPromedioGeneral() {
        return matriculaRepository.calcularPromedioProgreso();
    }

    public long contarPorEstado(String estado) {
        return matriculaRepository.countByEstado(estado);
    }

    @Transactional
    public Matricula crearOActualizarMatriculaAdmin(Usuario alumno, Curso curso, String estado, Double porcentajeProgreso) {
        if (!ESTADO_EN_CURSO.equals(estado) && !ESTADO_APROBADO.equals(estado) && !ESTADO_REPROBADO.equals(estado)) {
            throw new IllegalArgumentException("Estado de matrícula no válido.");
        }

        double progresoNormalizado = porcentajeProgreso == null ? 0.0 : Math.max(0.0, Math.min(100.0, porcentajeProgreso));
        if (ESTADO_APROBADO.equals(estado)) {
            progresoNormalizado = 100.0;
        }

        Matricula matricula = obtenerUltimaMatricula(alumno).orElseGet(Matricula::new);
        if (matricula.getId() == null) {
            matricula.setAlumno(alumno);
            matricula.setFechaInscripcion(LocalDate.now());
        }

        matricula.setCurso(curso);
        matricula.setEstado(estado);
        matricula.setPorcentajeProgreso(progresoNormalizado);
        return matriculaRepository.save(matricula);
    }
}
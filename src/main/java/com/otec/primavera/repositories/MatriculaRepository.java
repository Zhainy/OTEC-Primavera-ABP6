package com.otec.primavera.repositories;

import com.otec.primavera.models.Matricula;
import com.otec.primavera.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    // Para que el alumno vea su historial en su portal de autogestión
    List<Matricula> findByAlumno(Usuario alumno);

    Optional<Matricula> findTopByAlumnoOrderByFechaInscripcionDescIdDesc(Usuario alumno);

    Optional<Matricula> findFirstByAlumnoAndEstadoOrderByFechaInscripcionDesc(Usuario alumno, String estado);

    // Este metodo nos dirá si el alumno ya tiene un curso con estado "EN_CURSO"
    boolean existsByAlumnoAndEstado(Usuario alumno, String estado);

    long countByEstadoIn(List<String> estados);

    long countByEstado(String estado);

    @Query("SELECT COALESCE(AVG(COALESCE(m.porcentajeProgreso, 0)), 0) FROM Matricula m")
    double calcularPromedioProgreso();

    @Query("SELECT COUNT(DISTINCT m.alumno.id) FROM Matricula m")
    long countAlumnosInscritos();
}
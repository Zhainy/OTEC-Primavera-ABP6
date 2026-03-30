package com.otec.primavera.repositories;

import com.otec.primavera.models.Matricula;
import com.otec.primavera.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    // Para que el alumno vea su historial en su portal de autogestión
    List<Matricula> findByAlumno(Usuario alumno);

    // Este metodo nos dirá si el alumno ya tiene un curso con estado "EN_CURSO"
    boolean existsByAlumnoAndEstado(Usuario alumno, String estado);
}
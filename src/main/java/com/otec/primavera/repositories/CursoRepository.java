package com.otec.primavera.repositories;

import com.otec.primavera.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Nos devuelve solo los cursos que no han sido dados de baja
    List<Curso> findByActivoTrue();
}
package com.otec.primavera.repositories;

import com.otec.primavera.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Spring crea la consulta SQL automáticamente solo con leer el nombre del metodo
    Optional<Rol> findByNombre(String nombre);
}
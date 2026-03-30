package com.otec.primavera.repositories;

import com.otec.primavera.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Fundamental para el Login con Spring Security
    Optional<Usuario> findByEmail(String email);
}
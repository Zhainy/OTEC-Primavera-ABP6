package com.otec.primavera.services;

import com.otec.primavera.models.Rol;
import com.otec.primavera.models.Usuario;
import com.otec.primavera.repositories.RolRepository;
import com.otec.primavera.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private static final String ROL_ALUMNO = "ROLE_ALUMNO";

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario registrarNuevoAlumno(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalStateException("Ya existe un alumno registrado con ese correo.");
        }

        // Buscamos el rol de ALUMNO en la base de datos
        Rol rolAlumno = rolRepository.findByNombre(ROL_ALUMNO)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado en la base de datos"));

        usuario.setRol(rolAlumno);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerAlumnos() {
        return usuarioRepository.findByRol_NombreOrderByNombreAsc(ROL_ALUMNO);
    }

    public long contarAlumnos() {
        return usuarioRepository.countByRol_Nombre(ROL_ALUMNO);
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado"));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
}
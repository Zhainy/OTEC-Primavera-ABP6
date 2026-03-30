package com.otec.primavera.services;

import com.otec.primavera.models.Rol;
import com.otec.primavera.models.Usuario;
import com.otec.primavera.repositories.RolRepository;
import com.otec.primavera.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public Usuario registrarNuevoAlumno(Usuario usuario) {
        // Buscamos el rol de ALUMNO en la base de datos
        Rol rolAlumno = rolRepository.findByNombre("ROLE_ALUMNO")
                .orElseThrow(() -> new RuntimeException("Rol no encontrado en la base de datos"));

        usuario.setRol(rolAlumno);

        // TODO: En el Paso 7 de Seguridad, aquí encriptaremos la contraseña antes de guardar
        // usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
}
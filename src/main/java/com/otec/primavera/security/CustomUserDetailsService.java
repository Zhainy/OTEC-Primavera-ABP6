package com.otec.primavera.security;

import com.otec.primavera.models.Usuario;
import com.otec.primavera.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Buscamos al usuario en nuestra base de datos por su email
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        // 2. Transformamos nuestro 'Usuario' al objeto 'UserDetails' que entiende Spring Security
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword()) // La contraseña ya debe venir encriptada de la BD
                .authorities(usuario.getRol().getNombre()) // Le pasamos el rol (ej: "ROLE_ADMIN")
                .build();
    }
}
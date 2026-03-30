package com.otec.primavera.security;

import com.otec.primavera.models.Curso;
import com.otec.primavera.models.Matricula;
import com.otec.primavera.models.Rol;
import com.otec.primavera.models.Usuario;
import com.otec.primavera.repositories.CursoRepository;
import com.otec.primavera.repositories.MatriculaRepository;
import com.otec.primavera.repositories.RolRepository;
import com.otec.primavera.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final CursoRepository cursoRepository;
    private final MatriculaRepository matriculaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // 1. Crear los roles si la tabla está vacía
        if (rolRepository.count() == 0) {
            rolRepository.save(new Rol(null, "ROLE_ADMIN"));
            rolRepository.save(new Rol(null, "ROLE_ALUMNO"));
            System.out.println("✅ Roles iniciales creados en la BD.");
        }

        Rol rolAdmin = rolRepository.findByNombre("ROLE_ADMIN")
                .orElseThrow(() -> new IllegalStateException("No existe ROLE_ADMIN"));
        Rol rolAlumno = rolRepository.findByNombre("ROLE_ALUMNO")
                .orElseThrow(() -> new IllegalStateException("No existe ROLE_ALUMNO"));

        // 2. Usuarios base de prueba
        Usuario admin = crearUsuarioSiNoExiste("Administrador Principal", "admin@otec.cl", "admin123", rolAdmin);
        Usuario alumnoPrincipal = crearUsuarioSiNoExiste("Estudiante de Prueba", "alumno@otec.cl", "alumno123", rolAlumno);
        Usuario alumno2 = crearUsuarioSiNoExiste("Ana Torres", "ana@otec.cl", "ana123", rolAlumno);
        Usuario alumno3 = crearUsuarioSiNoExiste("Luis Perez", "luis@otec.cl", "luis123", rolAlumno);
        Usuario alumno4 = crearUsuarioSiNoExiste("Camila Rojas", "camila@otec.cl", "camila123", rolAlumno);

        // 3. Cursos demo
        Curso cursoJava = crearCursoSiNoExiste(
                "Bootcamp Back-end Java",
                "Programa intensivo de Java, Spring Boot y APIs REST.");
        Curso cursoFrontend = crearCursoSiNoExiste(
                "Frontend con React",
                "Fundamentos de React, componentes y consumo de APIs.");
        Curso cursoDatos = crearCursoSiNoExiste(
                "Analisis de Datos",
                "Python, SQL y visualizacion para proyectos de analitica.");

        // 4. Matriculas demo para poblar dashboards
        crearMatriculaSiNoExiste(alumnoPrincipal, cursoJava, "EN_CURSO", 45.0);
        crearMatriculaSiNoExiste(alumno2, cursoFrontend, "APROBADO", 100.0);
        crearMatriculaSiNoExiste(alumno3, cursoDatos, "REPROBADO", 62.0);
        crearMatriculaSiNoExiste(alumno4, cursoJava, "EN_CURSO", 28.0);
    }

    private Usuario crearUsuarioSiNoExiste(String nombre, String email, String passwordPlano, Rol rol) {
        return usuarioRepository.findByEmail(email).orElseGet(() -> {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(passwordEncoder.encode(passwordPlano));
            usuario.setRol(rol);
            Usuario creado = usuarioRepository.save(usuario);
            System.out.println("✅ Usuario creado: " + email + " / " + passwordPlano);
            return creado;
        });
    }

    private Curso crearCursoSiNoExiste(String nombre, String descripcion) {
        return cursoRepository.findByNombre(nombre).orElseGet(() -> {
            Curso curso = new Curso();
            curso.setNombre(nombre);
            curso.setDescripcion(descripcion);
            curso.setActivo(true);
            Curso creado = cursoRepository.save(curso);
            System.out.println("✅ Curso demo creado: " + nombre);
            return creado;
        });
    }

    private void crearMatriculaSiNoExiste(Usuario alumno, Curso curso, String estado, double progreso) {
        boolean existe = matriculaRepository.existsByAlumnoAndEstado(alumno, estado);
        if (existe) {
            return;
        }

        Matricula matricula = new Matricula();
        matricula.setAlumno(alumno);
        matricula.setCurso(curso);
        matricula.setFechaInscripcion(LocalDate.now().minusDays(7));
        matricula.setPorcentajeProgreso(progreso);
        matricula.setEstado(estado);
        matriculaRepository.save(matricula);
        System.out.println("✅ Matricula demo creada para " + alumno.getEmail() + " en " + curso.getNombre() + " (" + estado + ")");
    }
}
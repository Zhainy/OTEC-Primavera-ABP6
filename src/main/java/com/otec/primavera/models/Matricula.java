package com.otec.primavera.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "matriculas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación: Una matrícula pertenece a un solo alumno
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario alumno;

    // Relación: Una matrícula corresponde a un solo curso
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    private LocalDate fechaInscripcion;

    // Aquí registramos los "hitos" o el avance
    private Double porcentajeProgreso; // Ej: 0.0 a 100.0

    // Ej: "EN_CURSO", "APROBADO", "REPROBADO"
    private String estado;
}
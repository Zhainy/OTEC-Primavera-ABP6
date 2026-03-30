package com.otec.primavera.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aquí guardaremos valores como "ROLE_ADMIN" o "ROLE_ALUMNO"
    @Column(unique = true, nullable = false)
    private String nombre;
}
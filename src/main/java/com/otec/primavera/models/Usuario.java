package com.otec.primavera.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // Aquí guardaremos la contraseña encriptada

    // Relación: Muchos usuarios pueden tener un mismo rol
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private Rol rol;
}
package com.otec.primavera.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminAlumnoMatriculaForm {

    private Long cursoId;
    private String estado;
    private Double porcentajeProgreso;
}


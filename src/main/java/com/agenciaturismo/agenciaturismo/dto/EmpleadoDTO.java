package com.agenciaturismo.agenciaturismo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EmpleadoDTO {
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private LocalDate fecha_nac;
    private String nacionalidad;
    private String celular;
    private String email;
    private String cargo;
    private Double sueldo;
}

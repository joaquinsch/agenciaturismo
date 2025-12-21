package com.agenciaturismo.agenciaturismo.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@Getter @Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Usuario {
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private LocalDate fecha_nac;
    private String nacionalidad;
    private String celular;
    private String email;

}

package com.agenciaturismo.agenciaturismo.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@Getter @Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class Usuario {

    public enum Estado { ACTIVO, ELIMINADO }
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private LocalDate fecha_nac;
    private String nacionalidad;
    private String celular;
    @Email(message = "El email ingresado es inválido")
    private String email;
    @Enumerated(EnumType.STRING)
    private Estado estado;

}

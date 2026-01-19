package com.agenciaturismo.agenciaturismo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
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
    @NotNull(message = "Debe ingresar un nombre válido")
    @NotBlank(message = "Debe ingresar un nombre")
    @Pattern(
            regexp = "^[\\p{L} ]{1,50}$",
            message = "El nombre es inválido"
    )
    private String nombre;
    @NotNull(message = "Debe ingresar un apellido válido")
    @NotBlank(message = "Debe ingresar un apellido")
    @Pattern(
            regexp = "^[\\p{L} ]{1,50}$",
            message = "El apellido es inválido"
    )
    private String apellido;
    @NotNull(message = "Debe ingresar una dirección válida")
    @NotBlank(message = "Debe ingresar una dirección")
    @Pattern(regexp = "^[\\p{L}0-9 .-]{1,50}$",
             message = "La dirección es inválida"
    )
    private String direccion;
    @NotNull(message = "Debe ingresar un DNI válido")
    @NotBlank(message = "Debe ingresar un DNI")
    @Pattern(
            regexp = "^[0-9]{8}$",
            message = "El DNI debe tener exactamente 8 números"
    )
    private String dni;
    @NotNull(message = "Debe ingresar una fecha de nacimiento")
    @PastOrPresent(message = "La fecha de nacimiento es inválida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_nac;
    @NotNull(message = "Debe ingresar una nacionalidad válida")
    @NotBlank(message = "Debe ingresar una nacionalidad")
    @Pattern(regexp = "^[\\p{L}0-9 ]{1,20}$",
            message = "La dirección es inválida"
    )
    private String nacionalidad;
    @NotNull(message = "Debe ingresar un celular válido")
    @NotBlank(message = "Debe ingresar un celular")
    @Pattern(regexp = "^\\+?[0-9 ]{8,50}$",
            message = "El celular es inválido"
    )
    private String celular;
    @Email(message = "El email ingresado es inválido")
    private String email;
    @Enumerated(EnumType.STRING)
    private Estado estado;

}

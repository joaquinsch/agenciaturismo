package com.agenciaturismo.agenciaturismo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
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
    @NotNull(message = "Debes ingresar el cargo válido")
    @NotBlank(message = "Debes ingresar el cargo")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{1,20}$",
            message = "El cargo es inválido"
    )
    private String cargo;
    @NotNull(message = "Debes ingresar un sueldo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El sueldo debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El sueldo puede tener hasta 8 dígitos enteros y 2 decimales")
    private Double sueldo;
}

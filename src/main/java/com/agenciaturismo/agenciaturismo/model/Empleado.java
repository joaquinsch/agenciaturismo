package com.agenciaturismo.agenciaturismo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Empleado extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;
    @NotNull(message = "Debes ingresar el cargo válido")
    @NotBlank(message = "Debes ingresar el cargo")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{1,25}$",
            message = "El cargo es inválido"
    )
    private String cargo;
    @NotNull(message = "Debes ingresar un sueldo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El sueldo debe ser mayor a 0")
    private Double sueldo;
    @JsonIgnore
    @OneToMany(mappedBy = "empleado")
    private List<Venta> ventas;
}

package com.agenciaturismo.agenciaturismo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "servicios_turisticos")
@NoArgsConstructor
public class ServicioTuristico extends ProductoTuristico {
    @NotNull(message = "Debe ingresar un nombre")
    @NotBlank(message = "Debe ingresar un nombre válido")
    @Pattern(
            regexp = "^[\\p{L} ]{1,50}$",
            message = "El nombre es inválido"
    )
    private String nombre;
    @NotNull(message = "Debe ingresar una descripción")
    @NotBlank(message = "Debe ingresar una descripción válida")
    @Pattern(
            regexp = "^[\\p{L} ]{1,50}$",
            message = "La descripción es inválida"
    )
    private String descripcion_breve;
    @NotNull(message = "Debe ingresar un destino")
    @NotBlank(message = "Debe ingresar un destino válido")
    @Pattern(
            regexp = "^[\\p{L} ]{1,50}$",
            message = "El destino es inválido"
    )
    private String destino_servicio;
    @NotNull(message = "Debe ingresar la fecha del destino")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_servicio;
    @NotNull(message = "Debes ingresar el costo del servicio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor a 0")
    @DecimalMax(value = "1000001.0", inclusive = false, message = "El costo es inválido")
    private Double costo_servicio;
    @ManyToMany(mappedBy = "lista_servicios_incluidos")
    @JsonIgnore
    private List<PaqueteTuristico> paquetes;
}
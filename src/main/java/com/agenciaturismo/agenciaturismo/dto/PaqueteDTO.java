package com.agenciaturismo.agenciaturismo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaqueteDTO {

    private List<Long> lista_ids_servicios_incluidos;
    @NotNull(message = "Debes ingresar el costo del paquete")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor a 0")
    @Digits(integer = 20, fraction = 2, message = "El costo puede tener hasta 20 dígitos enteros y 2 decimales")
    private Double costo_paquete;

}

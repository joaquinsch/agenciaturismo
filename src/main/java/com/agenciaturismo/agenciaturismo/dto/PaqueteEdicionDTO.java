package com.agenciaturismo.agenciaturismo.dto;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
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
public class PaqueteEdicionDTO {

    private Long codigo_producto;
    private List<Long> lista_ids_servicios_incluidos;
    @NotNull(message = "Debes ingresar el costo del paquete")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor a 0")
    @DecimalMax(value = "1000001.0", inclusive = false, message = "El costo es inválido")
    private Double costo_paquete;

}

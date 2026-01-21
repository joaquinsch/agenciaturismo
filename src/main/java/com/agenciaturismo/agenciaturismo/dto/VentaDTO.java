package com.agenciaturismo.agenciaturismo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDTO {
    @NotNull(message = "Debe ingresar la fecha de la venta")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_venta;
    @NotNull(message = "Debe ingresar un medio de pago")
    @NotBlank(message = "Debe ingresar un medio de pago válido")
    @Pattern(
            regexp = "^[\\p{L} ]{1,50}$",
            message = "El medio de pago es inválido"
    )
    private String medio_pago;
    private Long id_cliente;
    private Long id_empleado;
    private Long codigo_producto;

}

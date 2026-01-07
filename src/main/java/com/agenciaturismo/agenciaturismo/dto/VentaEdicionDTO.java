package com.agenciaturismo.agenciaturismo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaEdicionDTO {
    private Long num_venta;
    private LocalDate fecha_venta;
    private String medio_pago;
    private Long id_cliente;
    private Long id_empleado;
    private Long codigo_producto;
}

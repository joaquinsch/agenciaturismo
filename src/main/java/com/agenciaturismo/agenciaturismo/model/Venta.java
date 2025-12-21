package com.agenciaturismo.agenciaturismo.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Venta {
    private final Long num_venta;
    private final LocalDate fecha_venta;
    private final String medio_pago;
    private final Cliente cliente;
    private final Empleado empleado;
    private final ProductoTuristico producto_turistico;

}

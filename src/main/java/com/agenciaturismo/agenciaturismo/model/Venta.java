package com.agenciaturismo.agenciaturismo.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Venta {
    private final Long num_venta;
    private final LocalDate fecha_venta;
    private final String medio_pago;
    private final Cliente cliente;

    public Venta(Long num_venta, LocalDate fecha_venta, String medio_pago, Cliente cliente) {
        this.num_venta = num_venta;
        this.fecha_venta = fecha_venta;
        this.medio_pago = medio_pago;
        this.cliente = cliente;
    }
}

package com.agenciaturismo.agenciaturismo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class PaqueteTuristico extends ProductoTuristico {
    private Long codigo_producto;
    private List<ServicioTuristico> lista_servicios_incluidos;
    private Double costo_paquete;

    protected PaqueteTuristico(PaqueteTuristicoBuilder<?, ?> b) {
        super(b);
        this.costo_paquete = b.costo_paquete;
        if (this.costo_paquete < 0) {
            throw new IllegalArgumentException("El costo es inválido");
        }
        this.codigo_producto = b.codigo_producto;
        this.lista_servicios_incluidos = b.lista_servicios_incluidos;

    }

}

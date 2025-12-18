package com.agenciaturismo.agenciaturismo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ServicioTuristico {
    private Long codigo_producto;
    private String nombre;
    private String descripcion_breve;
    private String destino_servicio;
    private LocalDate fecha_servicio;
    private Double costo_servicio;

    public ServicioTuristico(Long codigo_producto, String nombre,
                             String descripcion_breve,
                             String destino_servicio, LocalDate fecha_servicio,
                             Double costo_servicio) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.descripcion_breve = descripcion_breve;
        this.destino_servicio = destino_servicio;
        this.fecha_servicio = fecha_servicio;
        this.costo_servicio = costo_servicio;

        if (costo_servicio < 0) throw new IllegalArgumentException();
    }
}
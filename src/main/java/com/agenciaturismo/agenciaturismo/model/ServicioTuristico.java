package com.agenciaturismo.agenciaturismo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ServicioTuristico {
    private Long codigo_servicio;
    private String nombre;
    private String descripcion_breve;
    private String destino_servicio;
    private LocalDate fecha_servicio;
    private Double costo_servicio;
}
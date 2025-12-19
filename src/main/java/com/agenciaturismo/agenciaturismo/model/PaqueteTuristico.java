package com.agenciaturismo.agenciaturismo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaqueteTuristico extends ProductoTuristico {
    private Long codigo_producto;
    private List<ServicioTuristico> lista_servicios_incluidos;
    private Double costo_paquete;

}

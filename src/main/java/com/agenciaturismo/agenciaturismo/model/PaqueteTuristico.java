package com.agenciaturismo.agenciaturismo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PaqueteTuristico extends ProductoTuristico {
    private Long codigo_producto;
    private List<ServicioTuristico> lista_servicios_incluidos;
    private Double costo_paquete;

    public PaqueteTuristico(Long codigo_producto,
                            List<ServicioTuristico> lista_servicios_incluidos,
                            Double costo_paquete) {
        this.codigo_producto = codigo_producto;
        this.lista_servicios_incluidos = lista_servicios_incluidos;
        this.costo_paquete = costo_paquete;

        if (lista_servicios_incluidos.isEmpty()) throw new IllegalArgumentException();
    }

}

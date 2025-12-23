package com.agenciaturismo.agenciaturismo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@Table(name = "paquetes_turisticos")
@Entity
public class PaqueteTuristico extends ProductoTuristico {

    @ManyToMany
    @JoinTable(name = "paquetes_servicios",
               joinColumns = @JoinColumn(name = "codigo_paquete"),
               inverseJoinColumns = @JoinColumn(name = "codigo_servicio")
    )
    private List<ServicioTuristico> lista_servicios_incluidos;
    private Double costo_paquete;
    public static final double DESCUENTO = 0.1;

    protected PaqueteTuristico(PaqueteTuristicoBuilder<?, ?> b) {
        super(b);
        this.costo_paquete = b.costo_paquete;
        this.lista_servicios_incluidos = b.lista_servicios_incluidos;
        if (lista_servicios_incluidos == null) {
            throw new IllegalArgumentException("El paquete no tiene servicios asociados");
        }
        if (this.costo_paquete < 0) {
            throw new IllegalArgumentException("El costo es inválido");
        } else if (!validarCostoDePaquete()) {
            throw new IllegalArgumentException("El costo del paquete no coincide con la suma de los servicios menos 10%");
        }



    }

    /*
        Valida que el costo del paquete asignado sea igual
        a la suma de los servicios menos un 10%
     */
    private boolean validarCostoDePaquete() {
        double sumaDeCostosDeServicios = 0.0;
        for (ServicioTuristico servicio : this.lista_servicios_incluidos) {
            sumaDeCostosDeServicios += servicio.getCosto_servicio();
        }
        double costo_final = sumaDeCostosDeServicios - sumaDeCostosDeServicios * DESCUENTO;
        return this.costo_paquete == costo_final;
    }

}

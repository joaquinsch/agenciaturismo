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

    protected PaqueteTuristico(PaqueteTuristicoBuilder<?, ?> b) {
        super(b);
        this.costo_paquete = b.costo_paquete;
        if (this.costo_paquete < 0) {
            throw new IllegalArgumentException("El costo es inválido");
        }

        this.lista_servicios_incluidos = b.lista_servicios_incluidos;

    }

}

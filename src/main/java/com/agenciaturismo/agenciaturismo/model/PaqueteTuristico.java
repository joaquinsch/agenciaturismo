package com.agenciaturismo.agenciaturismo.model;

import com.agenciaturismo.agenciaturismo.exceptions.CostoInvalidoError;
import com.agenciaturismo.agenciaturismo.exceptions.PaqueteInvalidoError;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@Table(name = "paquetes_turisticos")
@Entity
@NoArgsConstructor
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
            throw new PaqueteInvalidoError("El paquete no tiene servicios asociados");
        } else if (lista_servicios_incluidos.size() < 2) {
            throw new PaqueteInvalidoError("El paquete debe tener mas de un servicio asociado");
        }
        if (!validarCostoDePaquete()) {
            throw new CostoInvalidoError("El costo del paquete no coincide con la suma de los servicios menos 10%");
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

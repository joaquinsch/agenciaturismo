package com.agenciaturismo.agenciaturismo.model;

import com.agenciaturismo.agenciaturismo.exceptions.CostoInvalidoError;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "servicios_turisticos")
@NoArgsConstructor
public class ServicioTuristico extends ProductoTuristico {
    private String nombre;
    private String descripcion_breve;
    private String destino_servicio;
    private LocalDate fecha_servicio;
    private Double costo_servicio;
    @ManyToMany(mappedBy = "lista_servicios_incluidos")
    private List<PaqueteTuristico> paquetes;

    protected ServicioTuristico(ServicioTuristicoBuilder<?, ?> b) {
        super(b);
        this.costo_servicio = b.costo_servicio;
        if (this.costo_servicio == null || this.costo_servicio < 0) {
            throw new CostoInvalidoError("El costo es inválido");
        }

        this.nombre = b.nombre;
        this.descripcion_breve = b.descripcion_breve;
        this.destino_servicio = b.destino_servicio;
        this.fecha_servicio = b.fecha_servicio;
    }
}
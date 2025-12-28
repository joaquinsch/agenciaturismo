package com.agenciaturismo.agenciaturismo.model;

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
}
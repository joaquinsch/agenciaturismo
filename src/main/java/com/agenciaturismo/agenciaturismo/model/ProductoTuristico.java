package com.agenciaturismo.agenciaturismo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@Entity
@NoArgsConstructor
public class ProductoTuristico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_producto;
    private String tipo_producto;
    @OneToOne
    @JoinColumn(name = "num_venta")
    private Venta venta;
}

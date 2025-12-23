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
@Table(name = "productos_turisticos")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductoTuristico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_producto;
    private String tipo_producto;
    @OneToOne(mappedBy = "producto_turistico")
    private Venta venta;
}

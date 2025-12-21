package com.agenciaturismo.agenciaturismo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ProductoTuristico {
    private Long codigo_producto;
    private String tipo_producto;
}

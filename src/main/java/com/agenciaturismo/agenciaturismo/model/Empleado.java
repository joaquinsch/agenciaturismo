package com.agenciaturismo.agenciaturismo.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Empleado extends Usuario {
    private final Long id_empleado;
    private final String cargo;
    private final Double sueldo;
}

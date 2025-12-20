package com.agenciaturismo.agenciaturismo.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Empleado extends Usuario {
    private final Long id_empleado;
    private final String cargo;
    private final Double sueldo;

    public Empleado(Long id_empleado, String cargo, Double sueldo,
                    String nombre, String apellido, String direccion,
                    String dni, LocalDate fecha_nac, String nacionalidad,
                    String celular, String email
                    ) {
        super(nombre, apellido, direccion, dni, fecha_nac, nacionalidad, celular, email);
        this.id_empleado = id_empleado;
        this.cargo = cargo;
        this.sueldo = sueldo;
    }
}

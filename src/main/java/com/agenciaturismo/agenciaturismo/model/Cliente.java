package com.agenciaturismo.agenciaturismo.model;


import lombok.Getter;

import java.time.LocalDate;
@Getter
public class Cliente extends Usuario {

    private final Long id_cliente;

    public Cliente(Long id_cliente, String nombre, String apellido,
                   String direccion, String dni,
                   LocalDate fecha_nac, String nacionalidad, String celular, String email) {
        super(nombre, apellido, direccion, dni, fecha_nac, nacionalidad, celular, email);
        this.id_cliente = id_cliente;
    }
}
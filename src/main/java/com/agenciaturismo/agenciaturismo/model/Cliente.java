package com.agenciaturismo.agenciaturismo.model;


import java.time.LocalDate;

public class Cliente extends Usuario {

    public Cliente(String nombre, String apellido, String direccion, String dni,
                   LocalDate fecha_nac, String nacionalidad, String celular, String email) {
        super(nombre, apellido, direccion, dni, fecha_nac, nacionalidad, celular, email);
    }
}
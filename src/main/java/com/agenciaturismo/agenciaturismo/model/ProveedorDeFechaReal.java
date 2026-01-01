package com.agenciaturismo.agenciaturismo.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProveedorDeFechaReal implements IProveedorDeFecha {

    @Override
    public boolean esFechaPasada(LocalDate fecha) {
        return fecha.isBefore(LocalDate.now());
    }
}

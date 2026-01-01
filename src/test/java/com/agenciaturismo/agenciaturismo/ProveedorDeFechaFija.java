package com.agenciaturismo.agenciaturismo;

import com.agenciaturismo.agenciaturismo.model.IProveedorDeFecha;

import java.time.LocalDate;

public class ProveedorDeFechaFija implements IProveedorDeFecha {

    private final LocalDate fecha;

    public ProveedorDeFechaFija(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean esFechaPasada(LocalDate fecha) {
        return fecha.isBefore(this.fecha);
    }
}

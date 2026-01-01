package com.agenciaturismo.agenciaturismo.model;

import java.time.LocalDate;

public interface IProveedorDeFecha {

    boolean esFechaPasada(LocalDate fecha);
}

package com.agenciaturismo.agenciaturismo.exceptions;

public class VentaInexistenteError extends RuntimeException {
    public VentaInexistenteError(String message) {
        super(message);
    }
}

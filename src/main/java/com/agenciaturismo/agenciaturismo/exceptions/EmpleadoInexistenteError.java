package com.agenciaturismo.agenciaturismo.exceptions;

public class EmpleadoInexistenteError extends RuntimeException {
    public EmpleadoInexistenteError(String message) {
        super(message);
    }
}

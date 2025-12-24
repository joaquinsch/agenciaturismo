package com.agenciaturismo.agenciaturismo.exceptions;

public class ServicioInexistenteError extends RuntimeException {
    public ServicioInexistenteError(String message) {
        super(message);
    }
}

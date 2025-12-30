package com.agenciaturismo.agenciaturismo.exceptions;

public class PaqueteInexistenteError extends RuntimeException {
    public PaqueteInexistenteError(String message) {
        super(message);
    }
}

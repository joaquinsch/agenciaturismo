package com.agenciaturismo.agenciaturismo.exceptions;

public class ClienteInexistenteError extends RuntimeException {
    public ClienteInexistenteError(String message) {
        super(message);
    }
}

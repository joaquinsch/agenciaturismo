package com.agenciaturismo.agenciaturismo.exceptions;

public class ProductoInexistenteError extends RuntimeException {
    public ProductoInexistenteError(String message) {
        super(message);
    }
}

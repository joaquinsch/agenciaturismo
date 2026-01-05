package com.agenciaturismo.agenciaturismo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ServicioInexistenteError.class})
    public ResponseEntity<ApiError> handleServicioInexistenteError(ServicioInexistenteError e) {
        ApiError apiError = new ApiError(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CostoInvalidoError.class})
    public ResponseEntity<ApiError> handleCostoInvalidoErrorError(CostoInvalidoError e) {
        ApiError apiError = new ApiError(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PaqueteInvalidoError.class})
    public ResponseEntity<ApiError> handlePaqueteInvalidoError(PaqueteInvalidoError e) {
        ApiError apiError = new ApiError(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PaqueteInexistenteError.class})
    public ResponseEntity<ApiError> handlePaqueteInexistenteError(PaqueteInexistenteError e) {
        ApiError apiError = new ApiError(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {FechaInvalidaError.class})
    public ResponseEntity<ApiError> handleFechaInvalidaError(FechaInvalidaError e) {
        ApiError apiError = new ApiError(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ClienteInexistenteError.class})
    public ResponseEntity<ApiError> handleClienteInexistenteError(ClienteInexistenteError e) {
        ApiError apiError = new ApiError(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EmpleadoInexistenteError.class})
    public ResponseEntity<ApiError> handleEmpleadoInexistenteError(EmpleadoInexistenteError e) {
        ApiError apiError = new ApiError(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ProductoInexistenteError.class})
    public ResponseEntity<ApiError> handleProductoInexistenteError(ProductoInexistenteError e) {
        ApiError apiError = new ApiError(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {VentaInexistenteError.class})
    public ResponseEntity<ApiError> handleVentaInexistenteError(VentaInexistenteError e) {
        ApiError apiError = new ApiError(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}

package com.agenciaturismo.agenciaturismo.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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

    @ExceptionHandler(value = {EdicionInvalidaError.class})
    public ResponseEntity<ApiError> handleEdicionInvalidaError(EdicionInvalidaError e) {
        ApiError apiError = new ApiError(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleMethodArgumentNotValidExceptionError(MethodArgumentNotValidException e) {
        String mensaje = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        // otra forma de obtener el mensaje que tira el @Email
        /*String mensaje = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .findFirst()
                .orElse("Datos inválidos");*/
        ApiError apiError = new ApiError(mensaje, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadableError(
            HttpMessageNotReadableException e) {
        String mensaje;

        if (e.getMessage() != null && (e.getMessage().contains("LocalDate") ||
                e.getMessage().contains("birthDate") ||
                e.getMessage().contains("DateTimeParseException"))) {
            mensaje = "La fecha de nacimiento ingresada es inválida. Use: yyyy-MM-dd (ejemplo: 1999-09-29)";
        } else {
            // Error genérico de JSON mal formado
            mensaje = "El JSON enviado tiene un formato inválido. Revise la sintaxis (comillas, comas, llaves, etc.)";
        }

        ApiError apiError = new ApiError(mensaje, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(
            ConstraintViolationException e) {

        String mensaje = e.getConstraintViolations()
                .iterator()
                .next()
                .getMessage();

        ApiError apiError = new ApiError(mensaje, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    // VALIDACION DE URLS
    /*
        Si ingresa un parámetro inválido, por ej: GET /api/clientes/asd
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleNoMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ApiError apiError = new ApiError("El parámetro ingresado es inválido", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    /*
        Si ingresa un verbo inválido, por ej: POST /api/clientes/3
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleNoHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ApiError apiError = new ApiError("La acción es inválida", HttpStatus.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(apiError, HttpStatus.METHOD_NOT_ALLOWED);
    }
    /*
        Si ingresa una url inválida, por ej: POST /asd
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> handleNoResourceFoundException(NoResourceFoundException e) {
        ApiError apiError = new ApiError("No se encontró el recurso ingresado", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
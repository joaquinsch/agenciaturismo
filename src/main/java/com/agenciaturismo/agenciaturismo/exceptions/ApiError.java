package com.agenciaturismo.agenciaturismo.exceptions;

import org.springframework.http.HttpStatus;


public record ApiError(String mensaje, HttpStatus httpStatus) {
}

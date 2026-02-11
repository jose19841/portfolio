package com.josepereyra.portfolio.shared.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<ValidationError> errors;

    // Constructor usado cuando no se envía lista de errores
    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errors = null;
    }

    // Constructor usado cuando se envía lista de errores de validación
    public ErrorResponse(
            int status,
            String error,
            String message,
            String path,
            List<ValidationError> errors) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    @Getter
    @AllArgsConstructor
    public static class ValidationError {
        private String field;
        private String message;
    }
}

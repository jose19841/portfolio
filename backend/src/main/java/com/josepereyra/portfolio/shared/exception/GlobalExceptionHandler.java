package com.josepereyra.portfolio.shared.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.josepereyra.portfolio.about.domain.exception.AboutMeNotFoundException;
import com.josepereyra.portfolio.contact.domain.exception.InvalidEmailException;
import com.josepereyra.portfolio.home.domain.exception.HomeInfoNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidEmail(
        InvalidEmailException ex, 
        HttpServletRequest request) {
        return new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(), 
        "Email invalido",
        ex.getMessage(),
        request.getRequestURI()
        );  
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationErrors(
        MethodArgumentNotValidException ex, 
        HttpServletRequest request) {
        List<ErrorResponse.ValidationError> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> new ErrorResponse.ValidationError(
            error.getField(),
             error.getDefaultMessage()
            ))
        .collect(Collectors.toList());

        return new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(), 
            "Errores de validacion",
            "Errores de validacion en los datos enviados",
            request.getRequestURI(),
            errors
            );  
    }

  @ExceptionHandler(IllegalArgumentException.class) 
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleIllegalArgumentException(
    IllegalArgumentException ex, 
    HttpServletRequest request) {

        return new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(), 
            "Argumento invalido",
            ex.getMessage(),
            request.getRequestURI()
            );
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(
        Exception ex, 
        HttpServletRequest request) {
            
        return new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Error interno del servidor",
            ex.getMessage(),
            request.getRequestURI()
            );
    }
    @ExceptionHandler(HomeInfoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleHomeInfoNotFound(
        HomeInfoNotFoundException ex,
        HttpServletRequest request) {

        return new ErrorResponse(
            HttpStatus.NOT_FOUND.value(), 
            "Informacion de inicio no encontrada",
            ex.getMessage(),
            request.getRequestURI()
            );
    }
    @ExceptionHandler(AboutMeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAboutMeNotFound(
        AboutMeNotFoundException ex,
        HttpServletRequest request) {

        return new ErrorResponse(
            HttpStatus.NOT_FOUND.value(), 
            "No encontrado",
            ex.getMessage(),
            request.getRequestURI() 
            );
    }
        
}
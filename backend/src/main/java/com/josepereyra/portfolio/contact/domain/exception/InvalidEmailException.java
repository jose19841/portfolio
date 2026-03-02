package com.josepereyra.portfolio.contact.domain.exception;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String message) {
        super(message);
    }
}

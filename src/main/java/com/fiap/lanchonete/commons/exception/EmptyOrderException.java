package com.fiap.lanchonete.commons.exception;

public class EmptyOrderException extends RuntimeException {
    public EmptyOrderException(String message) {
        super(message);
    }
}

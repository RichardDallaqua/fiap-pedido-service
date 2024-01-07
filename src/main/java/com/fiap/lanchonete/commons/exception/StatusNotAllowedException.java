package com.fiap.lanchonete.commons.exception;

public class StatusNotAllowedException extends RuntimeException {
    public StatusNotAllowedException(String message) {
        super(message);
    }
}

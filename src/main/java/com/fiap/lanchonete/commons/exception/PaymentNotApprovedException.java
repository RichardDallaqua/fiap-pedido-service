package com.fiap.lanchonete.commons.exception;

public class PaymentNotApprovedException extends RuntimeException {

    public PaymentNotApprovedException(String message) {
        super(message);
    }
}

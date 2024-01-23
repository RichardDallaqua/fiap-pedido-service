package com.fiap.lanchonete.controller;

import com.fiap.lanchonete.commons.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fiap.lanchonete.controller.dto.ErrorResponseDTO;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBindException(BindException bindException) {
        StringBuilder sb = new StringBuilder();
        bindException.getAllErrors().forEach(error -> sb.append(error.getDefaultMessage()).append("; "));
        return ErrorResponseDTO.builder().message(sb.toString()).build();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFoundException(NotFoundException notFoundException) {
        return ErrorResponseDTO.builder().message(notFoundException.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler(InvalidTypeException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ErrorResponseDTO handleInvalidTypeException(InvalidTypeException invalidTypeException) {
        return ErrorResponseDTO.builder().message(invalidTypeException.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleHttpMessageNotReadableException(
            HttpMessageNotReadableException httpMessageNotReadableException) {
        return ErrorResponseDTO.builder().message(httpMessageNotReadableException.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler(PaymentNotApprovedException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public ErrorResponseDTO handlePaymentNotApprovedException(PaymentNotApprovedException paymentNotApproved) {
        return ErrorResponseDTO.builder().message(paymentNotApproved.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler(StatusNotAllowedException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ErrorResponseDTO handleStatusNotAllowedException(StatusNotAllowedException statusNotAllowedException) {
        return ErrorResponseDTO.builder().message(statusNotAllowedException.getMessage()).build();
    }

    @ResponseBody
    @ExceptionHandler(EmptyOrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleEmptyOrderException(EmptyOrderException emptyOrderException) {
        return ErrorResponseDTO.builder().message(emptyOrderException.getMessage()).build();
    }
}

package com.met.transaction.controller.advice;

import com.met.transaction.exception.ErrorCode;
import com.met.transaction.exception.ErrorResponse;
import com.met.transaction.exception.TransactionServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class TransactionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {TransactionServiceException.class})
    protected ResponseEntity<ErrorResponse> handleTransactionException(
            TransactionServiceException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class})
    protected ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.ILLEGAL_ARGUMENT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value
            = {ValidationException.class})
    protected ResponseEntity<ErrorResponse> handleValidation(
            ValidationException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.VALIDATION_EXCEPTION, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<ErrorResponse> handleGeneral(
            Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.GENERAL_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

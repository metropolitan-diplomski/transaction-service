package com.met.transaction.exception;

public class TransactionServiceException extends RuntimeException {

    private ErrorCode errorCode;

    public TransactionServiceException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}

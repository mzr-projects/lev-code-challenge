package com.challenge.codechallenge.exceptions;

public class NoTransactionFoundException extends LeoVegasException {

    public NoTransactionFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public NoTransactionFoundException() {
        super(LeoVegasErrorMessage.NO_TRANSACTION_FOUND.getMessage(),
                LeoVegasErrorMessage.NO_TRANSACTION_FOUND.getCode());
    }
}

package com.challenge.codechallenge.exceptions;

public class NotEnoughCreditException extends LeoVegasException{

    public NotEnoughCreditException(String message, String errorCode) {
        super(message, errorCode);
    }

    public NotEnoughCreditException() {
        super(LeoVegasErrorMessage.NOT_ENOUGH_CREDIT.getMessage(),
                LeoVegasErrorMessage.NOT_ENOUGH_CREDIT.getCode());
    }
}

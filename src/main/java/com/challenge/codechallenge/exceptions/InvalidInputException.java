package com.challenge.codechallenge.exceptions;

public class InvalidInputException extends LeoVegasException {

    public InvalidInputException(String message, String errorCode) {
        super(message, errorCode);
    }

}

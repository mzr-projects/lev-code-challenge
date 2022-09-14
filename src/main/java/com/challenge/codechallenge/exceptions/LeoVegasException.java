package com.challenge.codechallenge.exceptions;

import lombok.Data;

@Data
public class LeoVegasException extends RuntimeException {

    private String message;
    private String errorCode;

    public LeoVegasException(String  message, String errorCode){
        this.message = message;
        this.errorCode = errorCode;
    }
}

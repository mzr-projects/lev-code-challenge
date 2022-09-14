package com.challenge.codechallenge.exceptions;

public class PlayerNotFoundException extends LeoVegasException{

    public PlayerNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public PlayerNotFoundException() {
        super(LeoVegasErrorMessage.PLAYER_NOT_FOUND.getMessage(),
                LeoVegasErrorMessage.PLAYER_NOT_FOUND.getCode());
    }
}

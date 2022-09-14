package com.challenge.codechallenge.exceptions;

public class NonUniqueTransactionIdException extends LeoVegasException {
	public NonUniqueTransactionIdException(String message, String errorCode) {
		super(message, errorCode);
	}

	public NonUniqueTransactionIdException() {
		super(LeoVegasErrorMessage.NON_UNIQUE_TRANSACTION_ID.getMessage(),
				LeoVegasErrorMessage.NON_UNIQUE_TRANSACTION_ID.getCode());
	}
}

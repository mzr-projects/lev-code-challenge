package com.challenge.codechallenge.exceptions;

import lombok.Getter;

@Getter
public enum LeoVegasErrorMessage {

	INVALID_INPUT_DATA("100", "Invalid input data."),
	NOT_ENOUGH_CREDIT("101", "Not enough credit."),
	PLAYER_NOT_FOUND("102", "Player not found"),
	NO_TRANSACTION_FOUND("103", "No transaction found"),
	NON_UNIQUE_TRANSACTION_ID("104", "Transaction ID must be unique");

	private static final String LEO_VEGAS_PREFIX = "LV-";
	private final String code;
	private final String message;

	LeoVegasErrorMessage(String code, String message) {
		this.code = LEO_VEGAS_PREFIX + code;
		this.message = message;
	}


}

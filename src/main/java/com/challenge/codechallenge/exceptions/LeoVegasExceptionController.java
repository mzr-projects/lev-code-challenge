package com.challenge.codechallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LeoVegasExceptionController {

	@ExceptionHandler(value = {InvalidInputException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected @ResponseBody LeoVegasExceptionResponse handleInvalidInputFormat(InvalidInputException ex,
	                                                                           WebRequest request) {
		return new LeoVegasExceptionResponse(ex.getMessage(), ex.getErrorCode());
	}

	@ExceptionHandler(value = {PlayerNotFoundException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected @ResponseBody LeoVegasExceptionResponse playerNotFound(PlayerNotFoundException ex,
	                                                                 WebRequest request) {
		return new LeoVegasExceptionResponse(ex.getMessage(), ex.getErrorCode());
	}

	@ExceptionHandler(value = {NotEnoughCreditException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected @ResponseBody LeoVegasExceptionResponse notEnoughCredit(NotEnoughCreditException ex,
	                                                                  WebRequest request) {
		return new LeoVegasExceptionResponse(ex.getMessage(), ex.getErrorCode());
	}

	@ExceptionHandler(value = {NumberFormatException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected @ResponseBody LeoVegasExceptionResponse handleInvalidInputData(NumberFormatException ex,
	                                                                         WebRequest request) {
		return new LeoVegasExceptionResponse(LeoVegasErrorMessage.INVALID_INPUT_DATA.getMessage(),
				LeoVegasErrorMessage.INVALID_INPUT_DATA.getCode());
	}

	@ExceptionHandler(value = {NonUniqueTransactionIdException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected @ResponseBody LeoVegasExceptionResponse nonUniqueTransactionIdException(NonUniqueTransactionIdException ex,
	                                                                                  WebRequest request) {
		return new LeoVegasExceptionResponse(LeoVegasErrorMessage.NON_UNIQUE_TRANSACTION_ID.getMessage(),
				LeoVegasErrorMessage.NON_UNIQUE_TRANSACTION_ID.getCode());
	}

	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	                                                              WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}

package com.challenge.codechallenge.payloads.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreditRequest {

	@NotEmpty(message = "Email must not be empty")
	private String email;

	@NotNull(message = "Amount must not be empty")
	private BigDecimal amount;

	@NotNull(message = "Transaction Id must not be empty")
	private Long transactionId;
}

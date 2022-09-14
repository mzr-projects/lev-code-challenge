package com.challenge.codechallenge.payloads.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LeoVegasRequest {

    @NotEmpty(message = "Email must not be empty")
    private String email;
}

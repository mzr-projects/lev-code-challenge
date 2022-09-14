package com.challenge.codechallenge.payloads.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreditReport {
    private BigDecimal credit;
}

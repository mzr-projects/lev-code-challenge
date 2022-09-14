package com.challenge.codechallenge.payloads.responses;

import com.challenge.codechallenge.entities.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TransactionsHistoryReport extends LeoVegasResponse{

    private BigDecimal transactionAmount;

    private Date transactionDate;

    private TransactionType transactionType;
}

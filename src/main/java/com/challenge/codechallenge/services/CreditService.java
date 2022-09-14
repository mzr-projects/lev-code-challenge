package com.challenge.codechallenge.services;

import com.challenge.codechallenge.payloads.requests.CreditRequest;
import com.challenge.codechallenge.payloads.responses.CreditReport;
import com.challenge.codechallenge.payloads.responses.PlayerCreditResponse;
import com.challenge.codechallenge.payloads.responses.TransactionsHistoryReport;

import java.util.List;
import java.util.UUID;

public interface CreditService<T> {

    PlayerCreditResponse addCredit(T creditRequest);

    PlayerCreditResponse withDraw(T creditRequest);
}

package com.challenge.codechallenge.services;

import com.challenge.codechallenge.payloads.responses.CreditReport;
import com.challenge.codechallenge.payloads.responses.TransactionsHistoryReport;

import java.util.List;

public interface TransactionService<T> {
	List<TransactionsHistoryReport> reportTransactions(T email);

	CreditReport getCreditReport(T email);
}

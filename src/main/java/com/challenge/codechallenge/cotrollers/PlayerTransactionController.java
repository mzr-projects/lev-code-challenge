package com.challenge.codechallenge.cotrollers;

import com.challenge.codechallenge.payloads.requests.CreditInquiryRequest;
import com.challenge.codechallenge.payloads.requests.CreditRequest;
import com.challenge.codechallenge.payloads.requests.ReportTransactionRequest;
import com.challenge.codechallenge.payloads.responses.CreditReport;
import com.challenge.codechallenge.payloads.responses.PlayerCreditResponse;
import com.challenge.codechallenge.payloads.responses.TransactionsHistoryReport;
import com.challenge.codechallenge.services.CreditService;
import com.challenge.codechallenge.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leo-vegas")
@RequiredArgsConstructor
public class PlayerTransactionController {

	private final CreditService<CreditRequest> creditService;
	private final TransactionService<String> transactionService;

	@PostMapping("/add-credit")
	public ResponseEntity<PlayerCreditResponse> addCredit(@Valid @RequestBody CreditRequest creditRequest) {
		PlayerCreditResponse playerCreditResponse = creditService.addCredit(creditRequest);
		return ResponseEntity.ok().body(playerCreditResponse);
	}

	@PostMapping("/withdraw-credit")
	public ResponseEntity<PlayerCreditResponse> withDraw(@Valid @RequestBody CreditRequest creditRequest) {
		PlayerCreditResponse playerCreditResponse = creditService.withDraw(creditRequest);
		return ResponseEntity.ok().body(playerCreditResponse);
	}

	@GetMapping("/transaction-history")
	public ResponseEntity<List<TransactionsHistoryReport>> reportTransactions(@Valid @RequestBody ReportTransactionRequest reportTransactionRequest) {
		return ResponseEntity.ok().body(transactionService.reportTransactions(reportTransactionRequest.getEmail()));
	}

	@GetMapping("/credit-inquiry")
	public ResponseEntity<CreditReport> creditInquiry(@Valid @RequestBody CreditInquiryRequest creditInquiryRequest) {
		return ResponseEntity.ok().body(transactionService.getCreditReport(creditInquiryRequest.getEmail()));
	}
}

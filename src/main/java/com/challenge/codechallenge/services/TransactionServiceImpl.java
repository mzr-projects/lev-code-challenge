package com.challenge.codechallenge.services;

import com.challenge.codechallenge.entities.Player;
import com.challenge.codechallenge.entities.PlayerTransaction;
import com.challenge.codechallenge.exceptions.LeoVegasException;
import com.challenge.codechallenge.exceptions.NoTransactionFoundException;
import com.challenge.codechallenge.payloads.responses.CreditReport;
import com.challenge.codechallenge.payloads.responses.TransactionsHistoryReport;
import com.challenge.codechallenge.repositories.PlayerRepository;
import com.challenge.codechallenge.utils.PlayerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = LeoVegasException.class)
@RequiredArgsConstructor
@Slf4j
class TransactionServiceImpl implements TransactionService<String> {

	private final PlayerRepository playerRepository;

	/**
	 * Create report of transactions history
	 *
	 * @param email Email of the player
	 *
	 * @return Transactions history
	 */
	public List<TransactionsHistoryReport> reportTransactions(String email) {
		Player player = playerRepository.findPlayerByEmail(email);
		PlayerUtils.checkPlayerIsPresent(player);

		Set<PlayerTransaction> playerTransactions = player.getPlayerTransactionSet();

		if (playerTransactions == null) {
			throw new NoTransactionFoundException();
		}

		List<TransactionsHistoryReport> transactionsHistoryReportList = new ArrayList<>();

		for (PlayerTransaction playerTransaction : playerTransactions) {
			TransactionsHistoryReport transactionsHistoryReport = new TransactionsHistoryReport();
			transactionsHistoryReport.setTransactionId(playerTransaction.getTransactionId());
			transactionsHistoryReport.setTransactionAmount(playerTransaction.getTransactionAmount());
			transactionsHistoryReport.setTransactionDate(playerTransaction.getCreatedData());
			transactionsHistoryReport.setTransactionType(playerTransaction.getTransactionType());
			transactionsHistoryReportList.add(transactionsHistoryReport);
		}

		log.info("History reported successfully.");

		return transactionsHistoryReportList;
	}

	/**
	 * Report credit of a player
	 *
	 * @param email Email of the player
	 *
	 * @return Credit report
	 */
	@Override
	public CreditReport getCreditReport(String email) {
		Player player = playerRepository.findPlayerByEmail(email);
		PlayerUtils.checkPlayerIsPresent(player);

		CreditReport creditReport = new CreditReport();
		creditReport.setCredit(player.getCredit());

		return creditReport;
	}
}

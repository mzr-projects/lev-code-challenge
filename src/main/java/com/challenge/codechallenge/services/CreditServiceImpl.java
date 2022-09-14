package com.challenge.codechallenge.services;

import com.challenge.codechallenge.entities.Player;
import com.challenge.codechallenge.entities.PlayerTransaction;
import com.challenge.codechallenge.entities.TransactionType;
import com.challenge.codechallenge.exceptions.LeoVegasException;
import com.challenge.codechallenge.exceptions.NonUniqueTransactionIdException;
import com.challenge.codechallenge.exceptions.NotEnoughCreditException;
import com.challenge.codechallenge.payloads.requests.CreditRequest;
import com.challenge.codechallenge.payloads.responses.PlayerCreditResponse;
import com.challenge.codechallenge.repositories.PlayerRepository;
import com.challenge.codechallenge.repositories.PlayerTransactionRepository;
import com.challenge.codechallenge.utils.PlayerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional(rollbackFor = LeoVegasException.class)
@Service
@RequiredArgsConstructor
@Slf4j
class CreditServiceImpl implements CreditService<CreditRequest> {

	private final PlayerRepository playerRepository;
	private final PlayerTransactionRepository playerTransactionRepository;

	/**
	 * Add credit to players account
	 *
	 * @param creditRequest
	 *
	 * @return
	 */
	public PlayerCreditResponse addCredit(CreditRequest creditRequest) {
		Player player = playerRepository.findPlayerByEmail(creditRequest.getEmail());

		PlayerUtils.checkPlayerIsPresent(player);
		checkTransactionIdUniqueness(creditRequest);

		BigDecimal amount = player.getCredit().add(creditRequest.getAmount());

		PlayerTransaction playerTransaction = new PlayerTransaction(player);
		playerTransaction.setTransactionAmount(creditRequest.getAmount());
		playerTransaction.setTransactionType(TransactionType.ADD);
		playerTransaction.setTransactionId(creditRequest.getTransactionId());

		player.setPlayerTransaction(playerTransaction);
		player.setCredit(amount);

		PlayerCreditResponse playerCreditResponse = new PlayerCreditResponse();
		playerCreditResponse.setTransactionId(creditRequest.getTransactionId());
		playerCreditResponse.setMessage("Credit added to the account successfully.");

		log.info("Credit added successfully.");
		return playerCreditResponse;
	}

	/**
	 * Withdraw credit from the players account
	 *
	 * @param creditRequest
	 *
	 * @return
	 */
	public PlayerCreditResponse withDraw(CreditRequest creditRequest) {
		Player player = playerRepository.findPlayerByEmail(creditRequest.getEmail());

		PlayerUtils.checkPlayerIsPresent(player);
		checkTransactionIdUniqueness(creditRequest);

		BigDecimal amount = player.getCredit().subtract(creditRequest.getAmount());
		if (amount.compareTo(new BigDecimal(0)) >= 0) {
			PlayerTransaction playerTransaction = new PlayerTransaction(player);
			playerTransaction.setTransactionAmount(creditRequest.getAmount());
			playerTransaction.setTransactionId(creditRequest.getTransactionId());
			playerTransaction.setTransactionType(TransactionType.WITHDRAW);

			player.setCredit(amount);
			player.setPlayerTransaction(playerTransaction);
		} else {
			throw new NotEnoughCreditException();
		}

		PlayerCreditResponse playerCreditResponse = new PlayerCreditResponse();
		playerCreditResponse.setTransactionId(creditRequest.getTransactionId());
		playerCreditResponse.setMessage("Credit withdrew from the account successfully.");

		log.info("Credit withdrew successfully.");
		return playerCreditResponse;
	}

	/**
	 * Check if transactionId is unique
	 *
	 * @param creditRequest
	 */
	private void checkTransactionIdUniqueness(CreditRequest creditRequest) {
		PlayerTransaction playerTransaction =
				playerTransactionRepository.findPlayerTransactionByTransactionId(creditRequest.getTransactionId());
		if (playerTransaction != null) {
			throw new NonUniqueTransactionIdException();
		}
	}
}

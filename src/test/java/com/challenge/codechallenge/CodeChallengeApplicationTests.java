package com.challenge.codechallenge;

import com.challenge.codechallenge.entities.Player;
import com.challenge.codechallenge.payloads.requests.CreditRequest;
import com.challenge.codechallenge.payloads.responses.PlayerCreditResponse;
import com.challenge.codechallenge.repositories.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CodeChallengeApplicationTests {

	@LocalServerPort
	int randomServerPort;

	@Autowired
	private PlayerRepository playerRepository;

	@Test
	void testAddCreditToAccount() {
		String addCreditUrl = "http://localhost:" + randomServerPort + "/leo-vegas/add-credit";

		TestRestTemplate testRestTemplate = new TestRestTemplate();

		CreditRequest creditRequest = new CreditRequest();
		creditRequest.setAmount(new BigDecimal(200));
		creditRequest.setEmail("jk@gmail.com");
		creditRequest.setTransactionId(1234L);

		ResponseEntity<PlayerCreditResponse> response =
				testRestTemplate.postForEntity(addCreditUrl, creditRequest, PlayerCreditResponse.class);

		Player player = playerRepository.findPlayerByEmail(creditRequest.getEmail());

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(0, player.getCredit().compareTo(new BigDecimal(4800)));
	}

	@Test
	void testNonUniquenessTransactionId() {
		String addCreditUrl = "http://localhost:" + randomServerPort + "/leo-vegas/add-credit";

		TestRestTemplate testRestTemplate = new TestRestTemplate();

		CreditRequest creditRequest1 = new CreditRequest();
		creditRequest1.setAmount(new BigDecimal(200));
		creditRequest1.setEmail("jk@gmail.com");
		creditRequest1.setTransactionId(1234L);

		ResponseEntity<PlayerCreditResponse> response1 =
				testRestTemplate.postForEntity(addCreditUrl, creditRequest1, PlayerCreditResponse.class);

		CreditRequest creditRequest2 = new CreditRequest();
		creditRequest2.setAmount(new BigDecimal(300));
		creditRequest2.setEmail("sm@gmail.com");
		creditRequest2.setTransactionId(1234L);

		ResponseEntity<PlayerCreditResponse> response2 =
				testRestTemplate.postForEntity(addCreditUrl, creditRequest2, PlayerCreditResponse.class);

		Assertions.assertEquals(HttpStatus.OK, response1.getStatusCode());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
	}

	@Test
	void testWithdrawCreditToAccount() {
		String withdrawCreditUrl = "http://localhost:" + randomServerPort + "/leo-vegas/withdraw-credit";
		TestRestTemplate testRestTemplate = new TestRestTemplate();

		CreditRequest creditRequest = new CreditRequest();
		creditRequest.setAmount(new BigDecimal(400));
		creditRequest.setEmail("jk@gmail.com");
		creditRequest.setTransactionId(3214L);

		ResponseEntity<PlayerCreditResponse> response =
				testRestTemplate.postForEntity(withdrawCreditUrl, creditRequest, PlayerCreditResponse.class);

		Player player = playerRepository.findPlayerByEmail(creditRequest.getEmail());

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(0, player.getCredit().compareTo(new BigDecimal(4200)));
	}
}

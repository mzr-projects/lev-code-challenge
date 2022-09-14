package com.challenge.codechallenge.repositories;

import com.challenge.codechallenge.entities.PlayerTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlayerTransactionRepository extends CrudRepository<PlayerTransaction, Long> {

    PlayerTransaction findPlayerTransactionByTransactionId(Long email);
}

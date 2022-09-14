package com.challenge.codechallenge.repositories;

import com.challenge.codechallenge.entities.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Player findPlayerByEmail(String email);
}

package com.challenge.codechallenge.utils;

import com.challenge.codechallenge.entities.Player;
import com.challenge.codechallenge.exceptions.PlayerNotFoundException;

public class PlayerUtils {

	public static void checkPlayerIsPresent(Player player) {
		if (player == null) {
			throw new PlayerNotFoundException();
		}
	}
}

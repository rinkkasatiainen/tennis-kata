package com.louhekon.examples.tennis;

import java.util.HashMap;
import java.util.Map;

public class SetScorer implements Scorer {

	private Map<Player, Integer> games = new HashMap<Player, Integer>();

	public SetScorer(Player player1, Player player2) {
		games.put(player1, 0);
		games.put(player2, 0);
	}

	public void addPointFor(Player player) {
		games.put(player, games.get(player) + 1);
	}

	public int pointsFor(Player player) {
		return games.get(player);
	}

	public boolean wonBy(Player player) {
		return winWithSixGames(player) || winWithSevenGames(player);
	}

	private boolean winWithSevenGames(Player player) {
		return games.get(player) == 7;
	}

	private boolean winWithSixGames(Player player) {
		return games.get(player) == 6 && games.get(opponentOf(player)) < 5;
	}

	private Player opponentOf(Player player) {
		for (Player p : games.keySet()) {
			if (!p.equals(player))
				return p;
		}
		throw new IllegalStateException("Should have more than one Player");
	}
}

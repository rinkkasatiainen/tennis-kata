package com.louhekon.examples.tennis;

import java.util.HashMap;
import java.util.Map;

public class Game {

	private Map<Player, Point> score = new HashMap<Player, Point>();
	
	public Game(Player player1, Player player2) {
		score.put(player1, Point.forName("Love"));
		score.put(player2, Point.forName("Love"));
	}

	public Point pointsFor(Player player) {
		return score.get(player);
	}

	public void addPointFor(Player player) {
		score.put(player, score.get(player).nextPoint());
	}
	
	
	
}

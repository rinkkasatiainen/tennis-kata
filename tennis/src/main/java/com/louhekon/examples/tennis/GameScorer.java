package com.louhekon.examples.tennis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class GameScorer implements Scorer<Point> {

	private Map<Player, Point> score = new HashMap<Player, Point>();

	public GameScorer(Player player1, Player player2) {
		score.put(player1, Point.Love);
		score.put(player2, Point.Love);
	}

	public void addPointFor(Player player) {
		if (bothPlayersHaving(Point.Deuce) ){
			score.put(opponentOf(player), Point.Fourty);
		}
		score.put(player, pointsFor(player).nextPoint());
		if (bothPlayersHaving(Point.Fourty) || bothPlayersHaving(Point.A)) {
			score.put(player, Point.Deuce);
			score.put(opponentOf(player), Point.Deuce);
		}
	}

	private boolean bothPlayersHaving(Point point) {
		Iterator<Entry<Player, Point>> pointIterator = score.entrySet().iterator();
		return pointIterator.next().getValue().equals(point)
				&& pointIterator.next().getValue().equals(point);
	}

	private Player opponentOf(Player player) {
		for (Player p : score.keySet()) {
			if (!p.equals(player))
				return p;
		}
		throw new IllegalStateException("Should have more than one Player");
	}	

	public Point pointsFor(Player player) {
		return score.get(player);
	}

	public boolean wonBy(Player player) {
		return (pointsFor(player).equals(Point.A) 
			&& ! (pointsFor(opponentOf(player)).equals(Point.Deuce) 
				  || pointsFor(opponentOf(player)).equals(Point.Fourty)))
		|| pointsFor(player).equals(Point.Game);
	}


}

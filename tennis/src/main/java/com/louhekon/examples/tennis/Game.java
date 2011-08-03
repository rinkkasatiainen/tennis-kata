package com.louhekon.examples.tennis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Game {

	private Map<Player, Point> score = new HashMap<Player, Point>();
	private final List<GameStatusObserver> observers = new ArrayList<GameStatusObserver>();

	public Game(Player player1, Player player2) {
		score.put(player1, Point.forName("Love"));
		score.put(player2, Point.forName("Love"));
	}

	public Point pointsFor(Player player) {
		return score.get(player);
	}

	public void addPointFor(Player player) {
		if (bothPlayersHaving(Point.Deuce) ){
			score.put(opponentOf(player), Point.Fourty);
		}
		score.put(player, scoreFor(player).nextPoint());
		if (bothPlayersHaving(Point.Fourty) || bothPlayersHaving(Point.A)) {
			score.put(player, Point.Deuce);
			score.put(opponentOf(player), Point.Deuce);
		}
		if (isGameWonBy(player)) {
			notifyObserversForWinningAGameBy(player);
		}
	}

	private boolean bothPlayersHaving(Point point) {
		Iterator<Entry<Player, Point>> pointIterator = score.entrySet().iterator();
		return pointIterator.next().getValue().equals(point)
				&& pointIterator.next().getValue().equals(point);
	}

	private Point scoreFor(Player player) {
		return score.get(player);
	}

	private Player opponentOf(Player player) {
		for (Player p : score.keySet()) {
			if (!p.equals(player))
				return p;
		}
		throw new IllegalStateException("Should have more than one Player");
	}

	private boolean isGameWonBy(Player player) {
		return (scoreFor(player).equals(Point.A) 
				&& ! (scoreFor(opponentOf(player)).equals(Point.Deuce) 
					  || scoreFor(opponentOf(player)).equals(Point.Fourty)))
			|| scoreFor(player).equals(Point.Game);
	}

	private void notifyObserversForWinningAGameBy(Player player) {
		for (GameStatusObserver observer : observers) {
			observer.gameBy(player);
		}
	}

	public void registerObserver(GameStatusObserver observer) {
		this.observers.add(observer);
	}

}

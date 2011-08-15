package com.louhekon.examples.tennis;

import java.util.ArrayList;
import java.util.List;

public class Game<T> {

	private final List<GameStatusObserver> observers = new ArrayList<GameStatusObserver>();
	private final Scorer<T> scorer;

	public Game(Scorer<T> scorer) {
		this.scorer = scorer;
	}

	public T pointsFor(Player player) {
		return scorer.pointsFor(player);
	}

	public void addPointFor(Player player) {
		scorer.addPointFor(player);
		if (scorer.wonBy(player)) {
			notifyObserversForWinningAGameBy(player);
		}
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

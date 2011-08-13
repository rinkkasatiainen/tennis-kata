package com.louhekon.examples.tennis;

import java.util.ArrayList;
import java.util.List;

public class TennisSet implements GameStatusObserver {

	private final Scorer scorer;
	private Game currentGame;
	private List<SetStatusObserver> observers = new ArrayList<SetStatusObserver>();
	private final GameFactory factory;

	public TennisSet(Scorer scorer, GameFactory factory) {
		this.scorer = scorer;
		this.factory = factory;
		this.currentGame = factory.newGame();
	}

	public int gamesFor(Player player) {
		return scorer.pointsFor(player);
	}

	public void gameBy(Player player) {
		scorer.addPointFor(player);
		if (scorer.wonBy(player)) {
			notifyObserversForWinningASet(player);
		} else {
			this.currentGame = factory.newGame();
		}
	}

	private void notifyObserversForWinningASet(Player player) {
		for (SetStatusObserver observer : observers) {
			observer.setBy(player);
		}
	}

	public void registerObserver(SetStatusObserver observer) {
		observers.add(observer);
	}

	public void addPointFor(Player player) {
		currentGame.addPointFor(player);
	}
}

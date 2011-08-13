package com.louhekon.examples.tennis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TennisSet implements GameStatusObserver{

	private final Player player_1;
	private final Player player_2;
	private Map<Player, Integer> games = new HashMap<Player, Integer>();
	private Game currentGame;
	private List<SetStatusObserver> observers = new ArrayList<SetStatusObserver>();
	private final GameFactory factory;
	
	public TennisSet(Player player_1, Player player_2, GameFactory factory) {
		this.player_1 = player_1;
		this.player_2 = player_2;
		this.factory = factory;
		this.currentGame = factory.newGameFor(player_1, player_2);
		games.put(player_1, 0);
		games.put(player_2, 0);
	}

	public int gamesFor(Player player) {
		return games.get(player);
	}

	public void gameBy(Player player) {
		games.put(player, games.get(player) + 1);
		if(isSetWonBy(player)){
			notifyObserversForWinningASet(player);
		}
		this.currentGame = factory.newGameFor(player_1, player_2);
	}

	protected Game createNewGame() {
		return new Game(player_1, player_2);
	}

	private boolean isSetWonBy(Player player) {
		return games.get(player).equals(6);
	}

	private void notifyObserversForWinningASet(Player player) {
		for(SetStatusObserver observer : observers){
			observer.setBy(player);
		}
	}

	public void registerObserver(SetStatusObserver observer){
		observers.add(observer);
	}

	public void addPointFor(Player player) {
		currentGame.addPointFor(player);
	}
	

}

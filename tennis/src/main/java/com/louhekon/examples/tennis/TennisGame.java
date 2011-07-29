package com.louhekon.examples.tennis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class TennisGame {

	Logger logger = Logger.getLogger(TennisGame.class);
	private final List<Player> players = new ArrayList<Player>();
	private final List<Map<Player, Integer>> set1 = new ArrayList<Map<Player, Integer>>();
	private Game game;

	public TennisGame(Player player1, Player player2) {
		players.add(player1);
		players.add(player2);
		createNewSet(player1, player2);
		game = new Game(player1, player2);
	}

	private void createNewSet(Player player1, Player player2) {
		set1.add( withPlayer(player1) );
		set1.add( withPlayer(player2) );
	}

	private Map<Player, Integer> withPlayer(Player player) {
		Map<Player, Integer> gamesForPlayer = new HashMap<Player, Integer>();
		gamesForPlayer.put(player, Integer.valueOf(0));
		return gamesForPlayer;
	}

	@SuppressWarnings("unused")
	public int setsFor(Player player) {
		return 0;
	}

	@SuppressWarnings("unused")
	public List<Map<Player, Integer>> getGamesForSet(int i) {
		logger.info("GamesForSet: " + set1.toString());
		return set1;
	}

	public Map<Player, Point> pointsForCurrentGame() {
		Game currentGame = this.game;
		Map<Player, Point> scoreBoard = new HashMap<Player, Point>();
		for(Player p : players){
			scoreBoard.put(p, currentGame.pointsFor(p));
		}
		return scoreBoard;
	}
}

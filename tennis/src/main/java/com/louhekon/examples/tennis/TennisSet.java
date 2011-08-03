package com.louhekon.examples.tennis;

public class TennisSet {

	private final Player player_1;
	private final Player player_2;

	public TennisSet(Player player_1, Player player_2) {
		this.player_1 = player_1;
		this.player_2 = player_2;
	}

	@SuppressWarnings("unused")
	public int gamesFor(Player player) {
		return 0;
	}

}

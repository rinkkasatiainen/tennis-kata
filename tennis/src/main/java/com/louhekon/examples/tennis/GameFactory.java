package com.louhekon.examples.tennis;

public class GameFactory {

	private final Player player1;
	private final Player player2;
	private final SetScorer scorer;

	public GameFactory(Player p1, Player p2, SetScorer scorer) {
		this.player1 = p1;
		this.player2 = p2;
		this.scorer = scorer;
	}

	public Game<?> newGame() {
		if (isNextGameATieBreak())
			return new Game<Integer>(new TieBreakScorer(player1, player2));
		return new Game<Point>(new GameScorer(player1, player2));
	}

	private boolean isNextGameATieBreak() {
		return scorer.pointsFor(player1) == 6 && scorer.pointsFor(player2) == 6;
	}

}

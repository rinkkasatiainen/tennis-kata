package com.louhekon.examples.tennis;

import java.util.HashMap;
import java.util.Map;

public class MatchScorer implements Scorer<Integer> {

	private Map<Player, Integer> sets = new HashMap<Player, Integer>();
	
	public MatchScorer(Player p1, Player p2) {
		sets.put(p1, 0);
		sets.put(p2, 0);
	}

	public void addPointFor(Player player) {
		sets.put(player, pointsFor(player) + 1);
	}

	public Integer pointsFor(Player player) {
		return sets.get(player);
	}

	public boolean wonBy(Player player) {
		return pointsFor(player) == 3;
	}

}

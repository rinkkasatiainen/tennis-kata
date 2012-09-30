package fi.rinkkasatiainen.examples.tennis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TieBreakScorer implements Scorer<Integer> {

	Map<Player, Integer> score = new HashMap<Player, Integer>();

	public TieBreakScorer(Player player1, Player player2) {
		score.put(player1, 0);
		score.put(player2, 0);
	}

	public void addPointFor(Player player) {
		score.put(player, pointsFor(player) + 1);
	}

	public Integer pointsFor(Player player) {
		return score.get(player);
	}

	public boolean isWonBy(Player player) {
		if (bothPlayersHavingMoreThanFivePoints())
			return playerHavingTwoPointsMoreThanTheOpponent(player);
		return pointsFor(player) == 7;
	}

	private boolean playerHavingTwoPointsMoreThanTheOpponent(Player player) {
		return pointsFor(player) == 2 + pointsFor(opponentOf(player));
	}

	private boolean bothPlayersHavingMoreThanFivePoints() {
		Iterator<Entry<Player, Integer>> iter = score.entrySet().iterator();
		return iter.next().getValue() > 5 && iter.next().getValue() > 5;
	}

	// TODO AS: This is duplicate to GameScorer & SetScorer - that is. There
	// should be a container object to give this information
	private Player opponentOf(Player player) {
		for (Player p : score.keySet()) {
			if (!p.equals(player))
				return p;
		}
		throw new IllegalStateException("Should have more than one Player");
	}
}

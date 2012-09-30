package fi.rinkkasatiainen.examples.tennis;

import java.util.HashMap;
import java.util.Map;

import fi.rinkkasatiainen.examples.tennis.Rule.BestOf;

public class MatchScorer implements Scorer<Integer> {

	private Map<Player, Integer> sets = new HashMap<Player, Integer>();
	private final BestOf rule;
	
	public MatchScorer(Player p1, Player p2, Rule.BestOf rule) {
		this.rule = rule;
		sets.put(p1, 0);
		sets.put(p2, 0);
	}

	public void addPointFor(Player player) {
		sets.put(player, pointsFor(player) + 1);
	}

	public Integer pointsFor(Player player) {
		return sets.get(player);
	}

	public boolean isWonBy(Player player) {
		return pointsFor(player) > rule.sets() / 2;
	}

}

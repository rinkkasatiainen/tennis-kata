package fi.rinkkasatiainen.examples.tennis;

import org.apache.log4j.Logger;

public class TennisMatch implements SetStatusObserver {

	Logger logger = Logger.getLogger(TennisMatch.class);
	private final MatchScorer scorer;

	public TennisMatch(MatchScorer scorer) {
		this.scorer = scorer;
	}

	public int setsFor(Player player) {
		return scorer.pointsFor(player);
	}

	public void setBy(Player player) {
		scorer.addPointFor(player);
	}
}

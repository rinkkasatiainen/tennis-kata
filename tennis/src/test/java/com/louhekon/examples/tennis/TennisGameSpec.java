package com.louhekon.examples.tennis;

import java.util.Map;
import java.util.Map.Entry;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class TennisGameSpec extends Specification<TennisGame> {
	Logger logger = Logger.getLogger(TennisGameSpec.class);
	private final Player PLAYER_1 = new Player("Kalle Kehveli");
	private final Player PLAYER_2 = new Player("Mikko Mallikas");

	public class WithANewGame {

		public TennisGame create() {
			return new TennisGame(PLAYER_1, PLAYER_2);
		}

		// Sets --> Games --> Points
		public void shouldHaveZeroSetsWhenStartingAGame() {
			specify(context.setsFor(PLAYER_1), should.equal(0));
			specify(context.setsFor(PLAYER_2), should.equal(0));
		}

		public void shouldHaveFirstSetWithZeroGames(){
			for( Map<Player, Integer> set : context.getGamesForSet(1)){
				logger.info("games in set 1 for Player: " + set.toString());
				specify(set.containsValue(0));
			}
		}
		
		public void shouldHaveZeroPointsForCurrentGame() {
			Map<Player, Point> score = context.pointsForCurrentGame();
			for (Entry<Player, Point> e : score.entrySet()){
				specify(e.getValue(), should.equal(Point.Love));
			}
		}
	}
	
//	public class WithFirstSet {
//		
//		public TennisGame create() {
//			return new TennisGame(PLAYER_1, PLAYER_2);
//		}
//		
//		public void shouldHave() {
//			TennisGame.pointTo(PLAYER_1);
//			
//		}
//		
//	}
}

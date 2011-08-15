package com.louhekon.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.apache.log4j.Logger;
import org.jmock.Expectations;
import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class TennisMatchSpec extends Specification<TennisMatch> {
	Logger logger = Logger.getLogger(TennisMatchSpec.class);
	private final Player PLAYER_1 = mock(Player.class, "p1");
	private final Player PLAYER_2 = mock(Player.class, "p2");
	
	public class WithANewGame {

		public TennisMatch create() {
			return new TennisMatch(new MatchScorer(PLAYER_1, PLAYER_2, Rule.BestOf.Three));
		}

		public void shouldStartWithZeroPoints(){
			specify(context.setsFor(PLAYER_1), should.equal(0));
			specify(context.setsFor(PLAYER_2), should.equal(0));
		}
	}
	
	public class WhenWinningSets{	
		private final MatchScorer scorer = mock(MatchScorer.class);

		public TennisMatch create() {
			return new TennisMatch(scorer);
		}
		
		public void shouldAddAPoint(){
			checking(new Expectations(){
				{
					one(scorer).addPointFor(PLAYER_1);
				}
			});
			context.setBy(PLAYER_1);
		}
	}	
}

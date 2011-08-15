package com.louhekon.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class GameScorerSpec extends Specification<GameScorer> {

	private Player player1 = mock(Player.class, "p1");
	private Player player2 = mock(Player.class, "p2");

	public class WithGameScorer {

		public GameScorer create() {
			return new GameScorer(player1, player2);
		}

		public void withStartWithNoPoints() {
			specify(context.pointsFor(player1), should.equal(Point.Love));
			specify(context.pointsFor(player2), should.equal(Point.Love));
		}

		public void fromLoveToFifteen() {
			context.addPointFor(player1);
			specify(context.pointsFor(player1), should.equal(Point.Fifteen));
		}

		public void fromFifteenToThirty() {
			context.addPointFor(player1);
			context.addPointFor(player1);
			specify(context.pointsFor(player1), should.equal(Point.Thirty));
		}

		public void fromThirtyToFourty() {
			context.addPointFor(player1);
			context.addPointFor(player1);
			context.addPointFor(player1);
			specify(context.pointsFor(player1), should.equal(Point.Fourty));
		}

		public void shouldWinAfterFourty() {
			context.addPointFor(player1);
			context.addPointFor(player1);
			context.addPointFor(player1);
			context.addPointFor(player1);
			specify(context.wonBy(player1), should.equal(true));
		}

		public void bothHavingDeuce() {
			context.addPointFor(player1);
			context.addPointFor(player1);
			context.addPointFor(player1);
			context.addPointFor(player2);
			context.addPointFor(player2);
			context.addPointFor(player2);
			specify(context.pointsFor(player1), should.equal(Point.Deuce));
			specify(context.pointsFor(player2), should.equal(Point.Deuce));
		}
	}

	public class withGameScorerHavingDeuce {

		public GameScorer create() {
			GameScorer scorer = new GameScorer(player1, player2);
			scorer.addPointFor(player1);
			scorer.addPointFor(player1);
			scorer.addPointFor(player1);
			scorer.addPointFor(player2);
			scorer.addPointFor(player2);
			scorer.addPointFor(player2);
			return scorer;
		}

		public void shouldHaveAdvantageWhenWinningAPoint() {
			context.addPointFor(player1);
			specify(context.pointsFor(player1), should.equal(Point.A));
			specify(context.pointsFor(player2), should.equal(Point.Fourty));
		}

		public void shouldWinFromAdvantage() {
			context.addPointFor(player1);
			context.addPointFor(player1);
			specify(context.wonBy(player1), should.equal(true));
		}
	}
}

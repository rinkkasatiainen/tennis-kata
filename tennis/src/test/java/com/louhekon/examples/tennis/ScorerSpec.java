package com.louhekon.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class ScorerSpec extends Specification<Scorer> {

	
	public class WithANewSet{
		
		Player player1 = mock(Player.class, "p1");
		Player player2 = mock(Player.class, "p2");
		
		public Scorer create(){
			return new SetScorer(player1, player2);
		}
		
		public void shouldHaveZeroPoints(){
			specify(context.pointsFor(player1), should.equal(0));
			specify(context.pointsFor(player2), should.equal(0));
		}
		
		public void shouldAddAPointWhenWinningAGame(){
			context.addPointFor(player1);
			specify(context.pointsFor(player1), should.equal(1));
		}
		
		public void shouldWinWith6Points(){
			withSixPointsFor(player1);
			specify(context.pointsFor(player1), should.equal(6));
			specify(context.wonBy(player1), should.equal(true));
			specify(context.wonBy(player2), should.equal(false));
		}
		
		public void shouldNotWinWhenSixAgainstFive(){
			withFivePointsFor(player2);
			withSixPointsFor(player1);
			specify(context.pointsFor(player1), should.equal(6));
			specify(context.pointsFor(player2), should.equal(5));
			specify(context.wonBy(player1), should.equal(false));
			specify(context.wonBy(player2), should.equal(false));
		}

		public void shouldWinWith7To5(){
			withFivePointsFor(player2);
			withSevenPointsFor(player1);
			specify(context.wonBy(player1), should.equal(true));
			specify(context.wonBy(player2), should.equal(false));
		}
		
		public void shouldWinAfterATieBreak(){
			withSixPointsFor(player2);
			withSevenPointsFor(player1);			
			specify(context.wonBy(player1), should.equal(true));
			specify(context.wonBy(player2), should.equal(false));
		}
		
		private void withSevenPointsFor(Player player) {
			withSixPointsFor(player);
			context.addPointFor(player);
		}

		private void withSixPointsFor(Player player) {
			withFivePointsFor(player);
			context.addPointFor(player);
		}

		private void withFivePointsFor(Player player) {
			context.addPointFor(player);
			context.addPointFor(player);
			context.addPointFor(player);
			context.addPointFor(player);
			context.addPointFor(player);
		}
	}
}

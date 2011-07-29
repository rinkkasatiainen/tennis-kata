package com.louhekon.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class GameSpec extends Specification<Game> {

	public class WithAny {
		
		private final Player PLAYER_1 = new Player("Kalle Kehveli");
		private final Player PLAYER_2 = new Player("Mikko Mallikas");
		
		public Game create() {
			return new Game(PLAYER_1, PLAYER_2);
		}
	
		public void shouldHaveZeroPointsWhenStarting() {
			specify(context.pointsFor(PLAYER_1), should.equal(Point.Love));
			specify(context.pointsFor(PLAYER_2), should.equal(Point.Love));
		}
		
		public void shouldHaveFifteenPointAfterFirstPoint(){
			context.addPointFor(PLAYER_1);
			specify(context.pointsFor(PLAYER_1), should.equal(Point.Fifteen));
			specify(context.pointsFor(PLAYER_2), should.equal(Point.Love));
		}
		
		public void shouldHaveThirtyAndFourtyPoints(){
			context.addPointFor(PLAYER_1);
			specify(context.pointsFor(PLAYER_1), should.equal(Point.Fifteen));
			context.addPointFor(PLAYER_1);
			specify(context.pointsFor(PLAYER_1), should.equal(Point.Thirty));
			context.addPointFor(PLAYER_1);
			specify(context.pointsFor(PLAYER_1), should.equal(Point.Fourty));
			specify(context.pointsFor(PLAYER_2), should.equal(Point.Love));
		}
		
		public void shouldWinPoint() {
			withFourtyPoints(PLAYER_1);
			context.addPointFor(PLAYER_1);
		}

		private void withFourtyPoints(Player player) {
			int[] numbers = {1,2,3};
			for(@SuppressWarnings("unused") int i : numbers) {
				context.addPointFor(player);
			}
			specify(context.pointsFor(PLAYER_1), should.equal(Point.Fourty));
		}
	}
}

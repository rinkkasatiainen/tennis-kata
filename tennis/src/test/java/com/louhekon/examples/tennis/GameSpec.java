package com.louhekon.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.jmock.Expectations;
import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class GameSpec extends Specification<Game> {
	private final Player PLAYER_1 = new Player("Kalle Kehveli");
	private final Player PLAYER_2 = new Player("Mikko Mallikas");
	private final GameStatusObserver observer = mock(GameStatusObserver.class);

	public class WithAny {

		public Game create() {
			return createGameContext();
		}

		public void shouldHaveZeroPointsWhenStarting() {
			assertPlayerWithPoints(PLAYER_1, Point.Love);
			assertPlayerWithPoints(PLAYER_2, Point.Love);
		}

		public void shouldHaveFifteenPointAfterFirstPoint() {
			assertPlayerWinningAPoint(PLAYER_1, Point.Fifteen);
			assertPlayerWithPoints(PLAYER_2, Point.Love);
		}

		public void shouldHaveCorrectPoints() {
			assertPlayerWinningAPoint(PLAYER_1, Point.Fifteen);
			assertPlayerWinningAPoint(PLAYER_1, Point.Thirty);
			assertPlayerWinningAPoint(PLAYER_1, Point.Fourty);
			assertPlayerWithPoints(PLAYER_2, Point.Love);
		}
	}
	
	public class withDeuce{
		
		public Game create(){
			return contextWithDeuce();
		}

		public void shouldHaveDeuce(){
			assertPlayerWithPoints(PLAYER_1, Point.Deuce);
			assertPlayerWithPoints(PLAYER_2, Point.Deuce);
		}
		
		public void shouldHaveAdvantageAndBackToDeuce(){
			assertPlayerWinningAPoint(PLAYER_1, Point.A);
			assertPlayerWithPoints(PLAYER_2, Point.Fourty);
			context.addPointFor(PLAYER_2);
			assertPlayerWithPoints(PLAYER_1, Point.Deuce);
			assertPlayerWithPoints(PLAYER_2, Point.Deuce);
		}

		public void shouldWinFromAdvantage() {
			assertPlayerWinningAPoint(PLAYER_1, Point.A);
			
			checking(new Expectations() {
				{
					one(observer).gameBy(PLAYER_1);
				}
			});
			context.addPointFor(PLAYER_1);
		}
		
		private Game contextWithDeuce() {
			Game game = createGameContext();
			game.addPointFor(PLAYER_1);
			game.addPointFor(PLAYER_1);
			game.addPointFor(PLAYER_1);
			game.addPointFor(PLAYER_2);
			game.addPointFor(PLAYER_2);
			game.addPointFor(PLAYER_2);
			return game;
		}
	}

	public class WhenWinningGame {

		public Game create() {
			return createGameContext();
		}

		public void shouldWinPoint() {
			withFourtyPointsFor(PLAYER_1);
			
			checking(new Expectations() {
				{
					one(observer).gameBy(PLAYER_1);
				}
			});
			context.addPointFor(PLAYER_1);
		}
	}

	private void assertPlayerWithPoints(Player player, Point point) {
		specify(context.pointsFor(player), should.equal(point));
	}

	private void assertPlayerWinningAPoint(Player player, Point point) {
		context.addPointFor(player);
		specify(context.pointsFor(player), should.equal(point));
	}

	private void withFourtyPointsFor(Player player) {
		int[] numbers = { 1, 2, 3 };
		for (@SuppressWarnings("unused") int i : numbers) {
			context.addPointFor(player);
		}
		specify(context.pointsFor(player), should.equal(Point.Fourty));
	}

	private Game createGameContext() {
		Game game = new Game(PLAYER_1, PLAYER_2);
		game.registerObserver(observer);
		return game;
	}
}

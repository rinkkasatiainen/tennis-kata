package com.louhekon.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.jmock.Expectations;
import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class GameSpec extends Specification<Game<?>> {
	private final Player PLAYER_1 = new Player("Kalle Kehveli");
	@SuppressWarnings("unused")
	private final Player PLAYER_2 = new Player("Mikko Mallikas");
	private final GameStatusObserver observer = mock(GameStatusObserver.class);
	
	public class WithAGame {

		private Scorer<Point> scorer = mock(GameScorer.class);
		
		public Game<Point> create() {
			Game<Point> game = new Game<Point>(scorer);
			game.registerObserver(observer);
			return game;
		}

		public void shouldCalculateScore(){
			checking(new Expectations(){
				{
					one(scorer).addPointFor(PLAYER_1);
					one(scorer).wonBy(PLAYER_1);
					will(returnValue(false));
				}
			});
			context.addPointFor(PLAYER_1);
		}
		
		public void shouldInformObserversWhenWinningAPoint() {
			checking(new Expectations() {
				{
					one(scorer).addPointFor(PLAYER_1);
					one(scorer).wonBy(PLAYER_1);
					will(returnValue(true));
					one(observer).gameBy(PLAYER_1);
				}
			});
			context.addPointFor(PLAYER_1);
		}

		public void shouldBeAbleToRetrievePoints(){
			checking(new Expectations(){
				{
					one(scorer).pointsFor(PLAYER_1);
					will(returnValue(Point.Love));
				}
			});
			specify(context.pointsFor(PLAYER_1), should.equal(Point.Love));
		}
	}

	public class WithATieBreaker{
		
		private TieBreakScorer scorer = mock(TieBreakScorer.class);

		public Game<Integer> create(){
			Game<Integer> game = new Game<Integer>(scorer);
			game.registerObserver(observer);
			return game;
		}
		
		public void shouldBeAbleToRetrievePoints(){
			checking(new Expectations(){
				{
					one(scorer).pointsFor(PLAYER_1);
					will(returnValue(5));
				}
			});
			specify(context.pointsFor(PLAYER_1), should.equal(5));
		}
	}
}

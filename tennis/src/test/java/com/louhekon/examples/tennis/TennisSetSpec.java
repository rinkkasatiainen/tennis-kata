package com.louhekon.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.jmock.Expectations;
import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class TennisSetSpec extends Specification<TennisSet> {
	
	private final Player PLAYER_1 = PlayerSpec.PLAYER_1; 
	private final Player PLAYER_2 = PlayerSpec.PLAYER_2; 
	private final SetStatusObserver observer = mock(SetStatusObserver.class);
	private Scorer scorer;
	private final GameFactory factory = mock(GameFactory.class);
	private final Game game = mock(Game.class);
	
	public class WithAny{
		
		private final Game gameNo2 = mock(Game.class, "AfterWinningOneGame");

		public void shouldHaveZeroGamesForPlayers(){
			specify(context.gamesFor(PLAYER_1), should.equal(0));
			specify(context.gamesFor(PLAYER_2), should.equal(0));
		}
		
		public TennisSet create(){
			scorer = new SetScorer(PLAYER_1, PLAYER_2);
			checking(new Expectations(){
				{
					one(factory).newGame();
					will(returnValue(game));
				}
			});
			TennisSet set = new TennisSet(scorer, factory);
			set.registerObserver(observer);
			return set;
		}
		
		public void shouldCreateANewGameWhenStartingANewSet(){
			//Empty - already tested in initialization
		}
		
		public void shouldMarkPointToCorrectPlayerForTheCurrentGame(){
			checking(new Expectations(){
				{
					one(game).addPointFor(PLAYER_1);
				}
			});
			context.addPointFor(PLAYER_1);
		}

		public void shouldCreateNewGameWhenAPlayerWinsOne(){
			checking(new Expectations(){
				{
					one(factory).newGame();
					will(returnValue(gameNo2));
					one(gameNo2).addPointFor(PLAYER_1);
				}
			});
			context.gameBy(PLAYER_1);
			context.addPointFor(PLAYER_1);
		}
	}
	
	public class WhenWinningGames{
		
		public TennisSet create(){
			scorer = mock(SetScorer.class);
			return createTennisSet();
		}
		
		public void shouldCalculatePointsWhenWinningOne(){
			checking(new Expectations(){
				{
					one(scorer).addPointFor(PLAYER_1);
					one(scorer).wonBy(PLAYER_1);
					will(returnValue(false));
				}
			});
			context.gameBy(PLAYER_1);
		}
		
		public void shouldInformObserversForWinningASet(){			
			checking( new Expectations(){
				{
					one(scorer).addPointFor(PLAYER_1);
					one(scorer).wonBy(PLAYER_1);
					will(returnValue(true));
					one(observer).setBy(PLAYER_1);
				}
			});
			context.gameBy(PLAYER_1);
		}

		private TennisSet createTennisSet() {
			withGameFactory();
			TennisSet set = new TennisSet(scorer, factory);
			set.registerObserver(observer);
			return set;
		}

		private void withGameFactory() {
			checking(new Expectations(){
				{
					allowing(factory).newGame();
				}
			});
		}
	}
}

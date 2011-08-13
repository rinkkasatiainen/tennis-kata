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
	private final GameFactory factory = mock(GameFactory.class);
	private final Game game = mock(Game.class);

	public class WithAny {

		private final Game gameNo2 = mock(Game.class, "AfterWinningOneGame");

		public void shouldHaveZeroGamesForPlayers() {
			specify(context.gamesFor(PLAYER_1), should.equal(0));
			specify(context.gamesFor(PLAYER_2), should.equal(0));
		}

		public TennisSet create() {
			checking(new Expectations() {
				{
					one(factory).newGameFor(PLAYER_1, PLAYER_2);
					will(returnValue(game));
				}
			});
			TennisSet set = new TennisSet(PLAYER_1, PLAYER_2, factory);
			set.registerObserver(observer);
			return set;
		}

		public void shouldCreateANewGameWhenStartingANewSet() {
			// Empty - already tested in initialization
		}

		public void shouldMarkPointToCorrectPlayer() {
			checking(new Expectations() {
				{
					one(game).addPointFor(PLAYER_1);
				}
			});
			context.addPointFor(PLAYER_1);
		}

		public void shouldCreateNewGameWhenAPlayerWinsOne() {
			checking(new Expectations() {
				{
					one(factory).newGameFor(PLAYER_1, PLAYER_2);
					will(returnValue(gameNo2));
					one(gameNo2).addPointFor(PLAYER_1);
				}
			});
			context.gameBy(PLAYER_1);
			context.addPointFor(PLAYER_1);
		}
	}

	public class WhenWinningGames {

		public TennisSet create() {
			return createTennisSet();
		}

		public void shouldKnowWhenAGameIsWonByAPlayer() {
			context.gameBy(PLAYER_1);
			specify(context.gamesFor(PLAYER_1), should.equal(1));
		}

		public void shouldWinSetWith6_0() {
			assertPlayerWinningAGame(PLAYER_1, 1);
			assertPlayerWinningAGame(PLAYER_1, 2);
			assertPlayerWinningAGame(PLAYER_1, 3);
			assertPlayerWinningAGame(PLAYER_1, 4);
			assertPlayerWinningAGame(PLAYER_1, 5);

			checking(new Expectations() {
				{
					one(observer).setBy(PLAYER_1);
				}
			});
			assertPlayerWinningAGame(PLAYER_1, 6);
		}
	}

	private void assertPlayerWinningAGame(Player player, int expectedGames) {
		context.gameBy(player);
		specify(context.gamesFor(player), should.equal(expectedGames));
	}

	// TODO AS: Use real factory instead of a mock.
	private TennisSet createTennisSet() {
		withGameFactory();
		TennisSet set = new TennisSet(PLAYER_1, PLAYER_2, factory);
		set.registerObserver(observer);
		return set;
	}

	private void withGameFactory() {
		checking(new Expectations() {
			{
				allowing(factory).newGameFor(PLAYER_1, PLAYER_2);
			}
		});
	}
}

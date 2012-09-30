package fi.rinkkasatiainen.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.jmock.Expectations;
import org.junit.runner.RunWith;

import fi.rinkkasatiainen.examples.tennis.Game;
import fi.rinkkasatiainen.examples.tennis.GameFactory;
import fi.rinkkasatiainen.examples.tennis.Player;
import fi.rinkkasatiainen.examples.tennis.Point;
import fi.rinkkasatiainen.examples.tennis.SetScorer;

@RunWith(JDaveRunner.class)
public class GameFactorySpec extends Specification<GameFactory> {

	public class WithANormalSet{
		
		private final Player p1 = mock(Player.class, "p1");
		private final Player p2 = mock(Player.class, "p2");
		private final SetScorer scorer = mock(SetScorer.class);
		
		public GameFactory create(){
			return new GameFactory(p1, p2, scorer);
		}

		public void shouldCreateANormalGame(){
			checking(new Expectations(){
				{
					allowing(scorer).pointsFor(with(any(Player.class)));
					will(returnValue(0));
				}
			});
			Game<?> game = context.newGame();
			specify(game.pointsFor(p1), should.equal(Point.Love));
			specify(game.pointsFor(p2), should.equal(Point.Love));
		}
		
		public void shouldCreateATieBreak(){
			checking(new Expectations(){
				{
					exactly(2).of(scorer).pointsFor(with(any(Player.class)));
					will(returnValue(6));
				}
			});
			Game<?> game = context.newGame();
			specify(game.pointsFor(p1), should.equal(0));
			specify(game.pointsFor(p2), should.equal(0));
		}
	}
}

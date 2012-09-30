package fi.rinkkasatiainen.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.junit.runner.RunWith;

import fi.rinkkasatiainen.examples.tennis.Player;
import fi.rinkkasatiainen.examples.tennis.TieBreakScorer;

@RunWith(JDaveRunner.class)
public class TieBreakScorerSpec extends Specification<TieBreakScorer>{

	public class WithAny{
		Player player1 = mock(Player.class, "p1");
		Player player2 = mock(Player.class, "p2");

		public TieBreakScorer create(){
			return new TieBreakScorer(player1, player2);
		}
		
		public void shouldStartWithZeroPoints(){
			specify(context.pointsFor(player1), should.equal(0));
			specify(context.pointsFor(player2), should.equal(0));
		}
		
		public void shouldAddAPoint(){
			context.addPointFor(player1);
			specify(context.pointsFor(player1), should.equal(1));
		}
		
		public void shouldWinAfterSevenPoints(){
			playerWithXPoints(player1, 7);
			specify(context.pointsFor(player1), should.equal(7));
			specify(context.isWonBy(player1), should.equal(true));
		}

		private void playerWithXPoints(Player player, int points) {
			for (int i = 0; i < points ; i++)
				context.addPointFor(player);
		}
		
		public void shouldHaveAtLeastTwoPointsMoreThanOpponentToWin(){
			playerWithXPoints(player2, 6);
			playerWithXPoints(player1, 7);
			specify(context.isWonBy(player1), should.equal(false));
			context.addPointFor(player1);
			specify(context.isWonBy(player1), should.equal(true));
		}
	}
}

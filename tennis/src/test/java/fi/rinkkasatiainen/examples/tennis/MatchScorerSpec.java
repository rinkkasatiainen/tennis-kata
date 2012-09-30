package fi.rinkkasatiainen.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.junit.runner.RunWith;

import fi.rinkkasatiainen.examples.tennis.MatchScorer;
import fi.rinkkasatiainen.examples.tennis.Player;
import fi.rinkkasatiainen.examples.tennis.Rule.BestOf;

@RunWith(JDaveRunner.class)
public class MatchScorerSpec extends Specification<MatchScorer> {

	private Player p1 = mock(Player.class, "p1");
	private Player p2 = mock(Player.class, "p2");
	
	public class WithANewMatch{
		
		public MatchScorer create(){
			return new MatchScorer(p1, p2, BestOf.Five);
		}
		
		public void shouldStartWithZeroSets(){
			specify(context.pointsFor(p1), should.equal(0));
			specify(context.pointsFor(p2), should.equal(0));
		}
		
		public void shouldCalculateSets(){
			context.addPointFor(p1);
			specify(context.pointsFor(p1), should.equal(1));
		}
	}
	
	public class WinningAMatch{
		
		public MatchScorer create(){
			return new MatchScorer(p1, p2, BestOf.Five);
		}

		//TODO AS: Why best of 5? 
		public void shouldWinAfter3Sets(){
			context.addPointFor(p1);
			context.addPointFor(p1);
			context.addPointFor(p1);
			specify(context.isWonBy(p1), should.equal(true));
		}
	}
}

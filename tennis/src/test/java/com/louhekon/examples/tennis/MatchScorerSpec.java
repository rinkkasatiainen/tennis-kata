package com.louhekon.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class MatchScorerSpec extends Specification<MatchScorer> {

	public class WithANewMatch{
		
		public Player p1 = mock(Player.class, "p1");
		public Player p2 = mock(Player.class, "p2");
		
		public MatchScorer create(){
			return new MatchScorer(p1, p2);
		}
		
		public void shouldStartWithZeroSets(){
			specify(context.pointsFor(p1), should.equal(0));
			specify(context.pointsFor(p2), should.equal(0));
		}
		
		public void shouldCalculateSets(){
			context.addPointFor(p1);
			specify(context.pointsFor(p1), should.equal(1));
		}
		
		//TODO AS: Why best of 5? 
		public void shouldWinAfter3Sets(){
			context.addPointFor(p1);
			context.addPointFor(p1);
			context.addPointFor(p1);
			specify(context.wonBy(p1), should.equal(true));
		}
	}
}

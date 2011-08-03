package com.louhekon.examples.tennis;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class TennisSetSpec extends Specification<TennisSet> {
	
	public class WithAny{
		
		private final Player PLAYER_1 = PlayerSpec.PLAYER_1; 
		private final Player PLAYER_2 = PlayerSpec.PLAYER_2; 
		
		public TennisSet create(){
			return new TennisSet(PLAYER_1, PLAYER_2);
		}
		
		public void shouldHaveZeroGamesForPlayers(){
			specify(context.gamesFor(PLAYER_1), should.equal(0));
			specify(context.gamesFor(PLAYER_2), should.equal(0));
		}
	}
}

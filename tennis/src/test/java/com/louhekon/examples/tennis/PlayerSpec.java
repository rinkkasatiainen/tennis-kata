package com.louhekon.examples.tennis;

import org.junit.runner.RunWith;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

@RunWith(JDaveRunner.class)
public class PlayerSpec extends Specification<Player> {

	public class WithAny{
		
		private static final String PLAYER_NAME = "Kalle Kehveli";

		public Player create(){
			return new Player(PLAYER_NAME);
		}
		
		public void ShouldHaveAName(){
			specify(context.getName(), should.equal(PLAYER_NAME));
		}
	}
}

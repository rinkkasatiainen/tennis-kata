package fi.rinkkasatiainen.examples.tennis;

import org.junit.runner.RunWith;

import fi.rinkkasatiainen.examples.tennis.Player;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

@RunWith(JDaveRunner.class)
public class PlayerSpec extends Specification<Player> {

	public static final Player PLAYER_1 = new Player("Kalle Kehveli");
	public static final Player PLAYER_2 = new Player("Mikko Mallikas");

	public class WithAny{
		
		public Player create(){
			return new Player("Foo");
		}
		
		public void ShouldHaveAName(){
			specify(context.getName(), should.equal("Foo"));
		}
	}
}

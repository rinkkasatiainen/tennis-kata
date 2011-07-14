package com.louhekon.examples;

import org.junit.runner.RunWith;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

@RunWith(JDaveRunner.class)
public class TennisGameSpec extends Specification<TennisGame> {

	public class WithAny {
		
		public void ShouldFail(){
			fail("Foobar");
			
		}
		
	}
	
}

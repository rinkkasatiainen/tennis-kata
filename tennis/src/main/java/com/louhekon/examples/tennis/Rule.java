package com.louhekon.examples.tennis;

public class Rule {

	enum BestOf{
		Five(5), 
		Three(3);
		
		private final int numberOfSets;
		
		private BestOf(int sets){
			this.numberOfSets = sets;
		}
		
		public int sets(){
			return numberOfSets;
		}
	}
}

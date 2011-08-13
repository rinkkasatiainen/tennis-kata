package com.louhekon.examples.tennis;

public interface Scorer {

	public void addPointFor(Player player);

	public int pointsFor(Player player);

	public boolean wonBy(Player player);

}

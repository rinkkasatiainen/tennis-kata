package com.louhekon.examples.tennis;

public interface Scorer<T> {

	public void addPointFor(Player player);

	public T pointsFor(Player player);

	public boolean wonBy(Player player);

}

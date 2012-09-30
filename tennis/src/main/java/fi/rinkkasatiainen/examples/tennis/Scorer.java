package fi.rinkkasatiainen.examples.tennis;

public interface Scorer<T> {

	public void addPointFor(Player player);

	public T pointsFor(Player player);

	public boolean isWonBy(Player player);

}

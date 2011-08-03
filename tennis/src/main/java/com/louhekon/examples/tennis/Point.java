package com.louhekon.examples.tennis;

public enum Point {

	Game("Game", "Game"),
	A("A", "Advantage", Game),
	Deuce("D", "Deuce", A),
	Fourty(40, "fourty", A),
	Thirty(30, "thirty", Fourty), 
	Fifteen(15, "fifteen", Thirty), 
	Love(0, "love", Fifteen);

	private final String score;
	private final String name;
	private final Point nextInChain;

	private Point(Integer points, String name, Point nextInChain) {
		this.nextInChain = nextInChain;
		this.score = points.toString();
		this.name = name;
	}

	private Point(String points, String name) {
		this.nextInChain = null;
		this.score = points;
		this.name = name;
	}

	private Point(String points, String name, Point nextInChain) {
		this.nextInChain = nextInChain;
		this.score = points;
		this.name = name;
	}

	public static Point forName(String name) {
		for (Point point : Point.values()) {
			if (point.name.equals(name.toLowerCase()))
				return point;
		}
		throw new java.lang.IllegalArgumentException("No point for name: " + name);
	}
	
	public Point nextPoint(){
		return this.nextInChain;
	}

}

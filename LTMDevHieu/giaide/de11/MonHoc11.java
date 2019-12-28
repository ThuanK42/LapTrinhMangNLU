package de11;

public class MonHoc11 {
	private String name;
	private double score;

	public MonHoc11(String name, double score) {
		super();
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return "MonHoc11 [name=" + name + ", score=" + score + "]";
	}

	public String getName() {
		return name;
	}

	public double getScore() {
		return score;
	}

}

package de12;

public class MonHoc12 {
	private String name;
	private double score;

	public MonHoc12(String name, double score) {
		super();
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return "MonHoc12 [name=" + name + ", score=" + score + "]";
	}

	public String getName() {
		return name;
	}

	public double getScore() {
		return score;
	}

}

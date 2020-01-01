package Tuan_8.Bai_21;



public class Student {
	private String name;
	private int age;
	private double score;

	public Student(String name, int age, double score) {
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getScore() {
		return score;
	}

	public String toString() {
		return name + "\t" + age + "\t" + score;
	}
}

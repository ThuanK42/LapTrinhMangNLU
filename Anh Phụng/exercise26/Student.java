package exercise26;

import java.util.ArrayList;


public class Student {
	private String name;
	private double score;
	private int age;

	public Student(String name, double score, int age) {
		super();
		this.name = name;
		this.score = score;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static ArrayList<Student> loadDataBase() {
		ArrayList<Student> lstSt = new ArrayList<Student>();

		lstSt.add(new Student("A", 8.9, 20));
		lstSt.add(new Student("B", 8.7, 20));
		lstSt.add(new Student("C", 7.5, 20));
		lstSt.add(new Student("D", 7.3, 21));
		lstSt.add(new Student("E", 7.2, 21));

		return lstSt;
	}
}

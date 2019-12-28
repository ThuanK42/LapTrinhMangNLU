package serverSide.model;

public class Student {
	private String id;
	private String name;
	private double grade;
	private int birthYear;

	public Student() {
	}

	public Student(String id, String name, double grade, int birthYear) {
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.birthYear = birthYear;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", grade=" + grade + ", birthYear=" + birthYear + "]";
	}

	public boolean hasID(String id) {
		return this.id.equalsIgnoreCase(id);
	}

	public boolean hasNameEndWith(String name) {
		return this.name.toUpperCase().endsWith(name.toUpperCase());
	}

	public boolean hasScore(double score) {
		return Double.compare(score, grade) == 0;
	}

}

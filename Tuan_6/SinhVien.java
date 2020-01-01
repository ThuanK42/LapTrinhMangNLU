package Tuan_6;

public class SinhVien {
	String id;
	String name;
	double grade;

	public SinhVien(String id, String name, double grade) {
		this.id = id;
		this.name = name;
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return id + " " + name + " " + grade;
	}

}

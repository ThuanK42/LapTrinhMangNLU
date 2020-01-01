package Tuan_4.Bai12;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Student {
	private int id;
	private String name;
	private List<Grade> grades;

	public Student(int id, String name) {
		this.id = id;
		this.name = name;
		this.grades = new ArrayList<>(5);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", grades=" + grades + "]";
	}

	public boolean addGrade(String name, double score) {
		return this.grades.add(new Grade(name, score));
	}

	public boolean addGrade(Grade grade) {
		return this.grades.add(grade);
	}

	public static void save(Student student, DataOutputStream outputStream) throws IOException {
		outputStream.writeInt(student.id);
		outputStream.writeUTF(student.name);
		outputStream.writeInt(student.grades.size());
		for (Grade grade : student.grades) {
			Grade.save(grade, outputStream);
		}
	}

	public static Student load(DataInputStream inputStream) throws IOException {
		Student result = new Student(inputStream.readInt(), inputStream.readUTF());
		int numGrade = inputStream.readInt();
		for (int i = 0; i < numGrade; i++) {
			result.addGrade(Grade.load(inputStream));
		}
		return result;
	}
}

package Tuan_4.Bai13;

import java.io.DataInput;
import java.io.DataOutput;
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

	public Student() {
		this.grades = new ArrayList<>(5);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", grades=" + grades + "]";
	}

	public boolean addGrade(String name, double score) {
		return this.grades.add(new Grade(name, score));
	}

	public void save(DataOutput dataOutput) throws IOException {
		dataOutput.writeInt(this.id);
		dataOutput.writeUTF(this.name);
		dataOutput.writeInt(this.grades.size());
		for (Grade grade : this.grades) {
			grade.save(dataOutput);
		}

	}

	public void load(DataInput dataInput) throws IOException {
		this.id = dataInput.readInt();
		this.name = dataInput.readUTF();
		int numGrade = dataInput.readInt();
		Grade gr;
		for (int i = 0; i < numGrade; i++) {
			gr = new Grade();
			gr.load(dataInput);
			this.grades.add(gr);
		}
	}
}

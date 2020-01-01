package Tuan_4.Bai12;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentManagement {
	private static void save(List<Student> students, String address) throws IOException {
		DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(address));
		outputStream.writeInt(students.size());
		for (Student student : students) {
			Student.save(student, outputStream);
		}
		outputStream.close();
	}

	private static List<Student> load(String address) throws IOException {
		DataInputStream inputStream = new DataInputStream(new FileInputStream(address));
		int numStudent = inputStream.readInt();
		List<Student> loStudent = new ArrayList<>(numStudent);
		for (int i = 0; i < numStudent; i++) {
			loStudent.add(Student.load(inputStream));
		}
		return loStudent;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<Student> students = new ArrayList<>();
		Student student1 = new Student(123, "Võ Thị Hộ");
		Student student2 = new Student(456, "Lê Huỳnh Ngô");
		Student student3 = new Student(789, "Trần Khải Hoàn");
		students.add(student1);
		students.add(student2);
		students.add(student3);
		student2.addGrade("LTCB", 9.2);
		student3.addGrade("LTCB", 5);
		student3.addGrade("MMTCB", 7.5);
		student3.addGrade("CTDL", 10);

		//save(students, "src\\Tuan_4.Bai13\\students.dat");
		List<Student> data = load("src\\Tuan_4.Bai13\\students.dat");
		for (Student student : data) {
			System.out.println(student);
		}
	}
}

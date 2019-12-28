package serverSide.dao;

import java.util.ArrayList;
import java.util.List;

import serverSide.model.Student;

public class StudentDAO {
	public static List<Student> students = new ArrayList<>();

	static {
		students.add(new Student("123", "Nguyễn Thị Mỹ Hương", 4.2, 1998));
		students.add(new Student("456", "Long Văn Hật", 7.6, 1999));
		students.add(new Student("789", "Huỳnh Trọng Mật", 4.2, 1997));
	}

	public static ArrayList<Student> findByID(String id) {
		ArrayList<Student> filteredList = new ArrayList<>();
		for (Student student : students) {
			if(student.hasID(id))
				filteredList.add(student);
		}
		return filteredList;

	}

	public static ArrayList<Student> findByName(String name) {
		ArrayList<Student> filteredList = new ArrayList<>();
		for (Student student : students) {
			if(student.hasNameEndWith(name))
				filteredList.add(student);
		}
		return filteredList;
	}

	public static ArrayList<Student> findByScore(double score) {
		ArrayList<Student> filteredList = new ArrayList<>();
		for (Student student : students) {
			if(student.hasScore(score))
				filteredList.add(student);
		}
		return filteredList;
	}
}

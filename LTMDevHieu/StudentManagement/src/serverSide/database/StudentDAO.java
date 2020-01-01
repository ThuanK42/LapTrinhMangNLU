package serverSide.database;

import java.sql.ResultSet;
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
		String sql = "select * from students where id = '" + id + "'";
		ResultSet rs;
		try {
			rs = DatabaseConnection.excuteQuery(sql);

			Student st;
			while (rs.next()) {
				st = new Student(rs.getNString("ID"), rs.getNString("NAME"), rs.getDouble("GRADE"),
						rs.getInt("BIRTHYEAR"));
				filteredList.add(st);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filteredList;
	}

	public static ArrayList<Student> findByName(String name) {
		ArrayList<Student> filteredList = new ArrayList<>();
		String sql = "select * from students where name like '%" + name + "'";
		ResultSet rs;
		try {
			rs = DatabaseConnection.excuteQuery(sql);

			Student st;
			while (rs.next()) {
				st = new Student(rs.getNString("ID"), rs.getNString("NAME"), rs.getDouble("GRADE"),
						rs.getInt("BIRTHYEAR"));
				filteredList.add(st);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filteredList;
	}

	public static ArrayList<Student> findByScore(double score) {
		ArrayList<Student> filteredList = new ArrayList<>();
		String sql = "select * from students where score = '" + score + "'";
		ResultSet rs;
		try {
			rs = DatabaseConnection.excuteQuery(sql);

			Student st;
			while (rs.next()) {
				st = new Student(rs.getNString("ID"), rs.getNString("NAME"), rs.getDouble("GRADE"),
						rs.getInt("BIRTHYEAR"));
				filteredList.add(st);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filteredList;
	}
}

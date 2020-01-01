package giaiDe;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static void bai1(List<SinhVien> students, String address) throws IOException {
		PrintWriter pw = new PrintWriter(address, "UTF-8");
		for (SinhVien student : students) {
			pw.println(student.toSave());
		}
		pw.close();
	}

	private static List<SinhVien> bai2(String address) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(address), "UTF-8"));
		List<SinhVien> loStudent = new ArrayList<>();
		SinhVien stu;
		String line;
		while ((line = br.readLine()) != null) {
			stu = new SinhVien();
			stu.load(line.split("\t+"));
			loStudent.add(stu);
		}
		br.close();
		for (SinhVien sinhVien : loStudent) {
			sinhVien.inTrungBinh();
		}
		return loStudent;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<SinhVien> students = new ArrayList<>();
		SinhVien student1 = new SinhVien(123, "Võ Thị Hộ");

		SinhVien student2 = new SinhVien(456, "Lê Huỳnh Ngô");
		student2.addGrade("LTCB", 9.2);

		SinhVien student3 = new SinhVien(789, "Trần Khải Hoàn");
		student3.addGrade("LTCB", 5);
		student3.addGrade("MMTCB", 7.5);
		student3.addGrade("CTDL", 10);

		students.add(student1);
		students.add(student2);
		students.add(student3);

		bai1(students, "D:\\students.txt");
		List<SinhVien> data = bai2("D:\\students.txt");
		System.out.println("\nDanh sách sinh viên load được");
		for (SinhVien sinhVien : data) {
			System.out.println(sinhVien);
		}
	}
}

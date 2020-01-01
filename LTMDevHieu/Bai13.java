import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import myIO.Bai12.Student;

public class Bai13 {
	public static void save(List<Student> students, String address) throws IOException {
		RandomAccessFile outputStream = new RandomAccessFile(address, "rw");
		outputStream.writeInt(students.size());

		for (int i = 0; i < students.size(); i++) {
			outputStream.writeLong(0);
		}

		long currPointer;
		for (int i = 0; i < students.size(); i++) {
			currPointer = outputStream.getFilePointer();
			outputStream.seek(4 + i * 8);
			outputStream.writeLong(currPointer);
			outputStream.seek(currPointer);
			students.get(i).save(outputStream);
		}

		outputStream.close();
	}

	public static Student get(String address, int index) throws IOException {
		RandomAccessFile inputStream = new RandomAccessFile(address, "r");
		int size = inputStream.readInt();
		if (index < -1 || index >= size) {
			inputStream.close();
			return null;
		}

		Student stu = new Student();
		inputStream.seek(4 + index * 8);
		inputStream.seek(inputStream.readLong());
		stu.load(inputStream);
		inputStream.close();
		return stu;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<Student> students = new ArrayList<>();
		Student student1 = new Student(123, "Võ Thị Hộ");

		Student student2 = new Student(456, "Lê Huỳnh Ngô");
		student2.addGrade("LTCB", 9.2);

		Student student3 = new Student(789, "Trần Khải Hoàn");
		student3.addGrade("LTCB", 5);
		student3.addGrade("MMTCB", 7.5);
		student3.addGrade("CTDL", 10);

		students.add(student1);
		students.add(student2);
		students.add(student3);

		save(students, "D:\\students.dat");
		System.out.println(get("D:\\students.dat", 2));
	}
}

package Tuan_4.Bai13;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class WriteReadST_RAF {
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
		Student sv = new Student(1, "Gia Cat Luong");
		Student sv2 = new Student(2, "Chu Du");
		Student sv3 = new Student(3, "Quach Phung Hieu");
		Student sv4 = new Student(4, "Bang Thong");
		Student sv5 = new Student(5, "Tu Ma Y");

		sv.addGrade("Ton Tu Binh Phap", 9.0);
		sv.addGrade("Binh Thu Yeu Luoc", 9.0);
		sv.addGrade("Xuan Thu Chien Phap", 9.0);

		sv2.addGrade("Ton Tu Binh Phap", 9.0);
		sv2.addGrade("Binh Thu Yeu Luoc", 9.0);

		sv3.addGrade("Ton Tu Binh Phap", 9.0);
		sv3.addGrade("Binh Thu Yeu Luoc", 9.0);

		sv4.addGrade("Ton Tu Binh Phap", 9.0);

		sv5.addGrade("Binh Thu Yeu Luoc", 9.0);

		ArrayList<Student> los = new ArrayList<>();
		los.add(sv);
		los.add(sv2);
		los.add(sv3);
		los.add(sv4);
		los.add(sv5);

		String dFile = "src\\Tuan_4.Bai13\\students.txt";

		//save(los, dFile);
		System.out.println(get(dFile, 1));

	}

}

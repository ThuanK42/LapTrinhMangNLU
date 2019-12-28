package Tuan_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Bai16 {

	public void exportFileCSV(String dFile) throws IOException {

		ArrayList<Student> list = new ArrayList<Student>();

		list.add(new Student("ST001", "Le Vo Dong Hoi", "DH16DT", 8.9));
		list.add(new Student("ST002", "Tran Viet Son", "DH17DT", 8.9));
		list.add(new Student("ST003", "Nguyen Van Quang", "DH18DT", 8.9));
		list.add(new Student("ST004", "Le Hoang", "DH19DT", 8.9));
		list.add(new Student("ST005", "Le Van Thuan", "DH20DT", 8.9));

		if (list.isEmpty()) {
			System.out.println("Khong co data");
		} else {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dFile, Charset.forName("UTF-8")));
			String header = "ID_ST, NAME_ST,CLASS_ST,SCORE_ST";
			bufferedWriter.write(header);
			bufferedWriter.write("\n");
			for (Student student : list) {
				bufferedWriter.write(student.getId());
				bufferedWriter.write(",");
				bufferedWriter.write(student.getName());
				bufferedWriter.write(",");
				bufferedWriter.write(student.getLop());
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(student.getDiem()));
				bufferedWriter.write("\n");
			}
			bufferedWriter.close();
			System.out.println("successful");
		}
	}

	public static void importFile(String dFile) throws IOException {
		File file = new File(dFile);
		if (!file.exists()) {
			System.out.println("File khong ton tai");
		} else {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file, Charset.forName("UTF-8")));
			String[] header = bufferedReader.readLine().split(",");
			System.out.println(header[0] + "\t" + header[1] + "\t" + header[2] + "\t" + header[3]);
			String line = "";
			Student student = null;

			while ((line = bufferedReader.readLine()) != null) {
				String[] str = line.split(",");
				student = new Student(str[0], str[1], str[2], Double.parseDouble(str[3]));
				System.out.println(student.toString());
			}

			bufferedReader.close();
			System.out.println("read successful");
		}

	}

	public static void main(String[] args) throws IOException {
		Bai16 b16 = new Bai16();
		 b16.exportFileCSV("src\\Tuan_7\\StudentTest.csv");
		b16.importFile("src\\Tuan_7\\StudentTest.csv");
	}
}

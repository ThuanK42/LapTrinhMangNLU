import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ImportExportExcel {
	static ArrayList<Student> lstSt = new ArrayList<Student>();

	public static void inportExcel(String path, String charset)
			throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), charset));

		String line = input.readLine();
		String[] hearder = line.split(",");
		System.out.println(hearder[0] + "\t" + hearder[1] + "\t" + hearder[2]);
		Student st = null;

		while ((line = input.readLine()) != null) {
			String[] stline = line.split(",");
			String mssv = stline[0];
			String name = stline[1];
			int score = Integer.valueOf(stline[2]);

			st = new Student(mssv, name, score);
			lstSt.add(st);
		}
		input.close();
		for (Student student : lstSt) {
			System.out.println(student.toString());
		}

	}

	public static void exportExcel(String path, String charset)
			throws IOException {
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(path), charset));

		output.write("ID");
		output.write(",");
		output.write("NAME");
		output.write(",");
		output.write("SCORE");
		output.write('\n');

		for (Student student : lstSt) {
			output.write(student.getMssv());
			output.write(",");
			output.write(student.getName());
			output.write(",");
			output.write(student.getScore());
			output.write('\n');
		}
		output.close();
	}
	public static void main(String[] args) throws IOException {
		inportExcel("D:\\import.csv", "UTF-8");
		exportExcel("D:\\export.csv", "UTF-8");
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ImportExportFile {
	static ArrayList<Student> lstSt = new ArrayList<Student>();

	public static void inportFile(String path, String charset)
			throws IOException {

		BufferedReader input = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), charset));

		String line = "";
		Student st = null;
		while ((line = input.readLine()) != null) {
			String[] stline = line.split("" + '\t');
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

	public static void exportFile(String path, String charset)
			throws IOException {
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(path), charset));

		for (Student student : lstSt) {
			output.write(student.getMssv());
			output.write('\t');
			output.write(student.getName());
			output.write('\t');
			output.write(student.getScore());
			output.write('\n');
		}
		output.close();
	}

	public static void main(String[] args) throws IOException {
		inportFile("D:\\import.txt", "UTF-8");
		exportFile("D:\\export.txt", "UTF-8");
	}

}

package Tuan_6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ExportImportFileCSV {
	public static void export(String dFile) throws IOException {
		ArrayList<SinhVien> list = new ArrayList<>();
		list.add(new SinhVien("123", "Vương Xuân Thành", 7.0));
		list.add(new SinhVien("124", "Hà Bảo Lộc", 9.0));
		list.add(new SinhVien("125", "Lê Anh Kỳ", 8));
		if (list.isEmpty()) {
			System.out.println("null");
		} else {
			OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(dFile), Charset.forName("UTF-8"));
			String header = "ID,NAME,Grade";
			os.write(header);
			os.write("\n");
			for (SinhVien sinhVien : list) {
				os.write(sinhVien.getId());
				os.write(",");
				os.write(sinhVien.getName());
				os.write(",");
				os.write(String.valueOf(sinhVien.getGrade()));
				os.write("\n");
			}
			os.close();
			System.out.println("success...........");
		}

	}

	public static void importFile(String sf) throws IOException {
		File f = new File(sf);
		if (!f.exists()) {
			System.out.println("not Exists");
		} else {
			BufferedReader is = new BufferedReader(new FileReader(f));
			String[] s = is.readLine().split(",");
			System.out.println(s[0] + "\t" + s[1] + "\t" + s[2]);
			SinhVien sv = null;
			String st = "";
			while ((st = is.readLine()) != null) {
				String arr[] = st.split(",");
				sv = new SinhVien(arr[0], arr[1], Double.parseDouble(arr[2]));
				System.out.println(sv.toString());
			}
			is.close();
			System.out.println("success");
		}

	}

	public static void main(String[] args) throws IOException {
		String a = "src\\Tuan_8\\test.csv";

		export(a);

		importFile(a);
	}

}

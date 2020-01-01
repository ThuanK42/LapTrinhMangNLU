package de11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveLoad_PrintBuffer {
	public static void main(String[] args) throws Exception {
		// tao ds sinh vine
		SinhVien11 s1 = new SinhVien11(16130692, "Nguyễn Hoàng Việt");
		SinhVien11 s2 = new SinhVien11(16130342, "Nguyễn Thị Mén");
		SinhVien11 s3 = new SinhVien11(16130145, "Võ Tuấn Kiệt");
		s1.add(new MonHoc11("Lý Thuyết Đồ Thị", 8.0));
		s1.add(new MonHoc11("LTM", 10.0));
		s2.add(new MonHoc11("Lý Thuyết Đồ Thị", 5.0));
		s2.add(new MonHoc11("LTM", 6.0));
		s3.add(new MonHoc11("Lý Thuyết Đồ Thị", 10.0));
		s3.add(new MonHoc11("LTM", 9.0));
		s3.add(new MonHoc11("GTNM", 8.0));
		ArrayList<SinhVien11> los = new ArrayList<>();
		los.add(s1);
		los.add(s2);
		los.add(s3);
		
		// test cau 1
		save(los, "C:\\Users\\Administrator\\Desktop\\LTM\\c.txt");
		
		// test cau 2
		ArrayList<SinhVien11> loadlos = load("C:\\Users\\Administrator\\Desktop\\LTM\\c.txt");
		printlos(los);
	}
	
	// cau 1: luu xuong file
	public static void save(ArrayList<SinhVien11> los, String dest) throws Exception {
		File fd = new File(dest);
		if(!fd.exists() || fd.isDirectory()) return;
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fd), "UTF-8"));
		for(SinhVien11 s:los) {
			pw.println(s.line("\t"));
		}
		pw.close();
	}
	
	// cau 2: load len 
	public static ArrayList<SinhVien11> load(String source) throws Exception {
		ArrayList<SinhVien11> los = new ArrayList<>();
		File fs = new File(source);
		if(!fs.exists() || fs.isDirectory()) return null;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fs), "UTF-8"));
		String line;
		SinhVien11 sv;
		while((line = br.readLine()) != null ) {
			String[] lineSub = line.split("\t");
			sv = new SinhVien11(Integer.parseInt(lineSub[0]), lineSub[1]);
			if(lineSub.length > 2) {
				for(int i = 2 ; i < lineSub.length ; i+=2) {
					sv.add(new MonHoc11(lineSub[i], Double.parseDouble(lineSub[i+1])));
				}
			}
			los.add(sv);
		}
		br.close();
		return los;
	}
	
	// cau 2: tinh diem trung binh
	public static double avr(SinhVien11 st) {
		double res =0;
		for(MonHoc11 m:st.getLom()) {
			res += m.getScore();
		}
		return res/st.getLom().size();
	}
	
	// cau 2: in ra theo yeu cau de bai
	public static void printlos(ArrayList<SinhVien11> los) {
		for(SinhVien11 s:los) {
			System.out.println(s.getId() + "\t" + s.getName() + "\t" + avr(s));
		}
	}
}

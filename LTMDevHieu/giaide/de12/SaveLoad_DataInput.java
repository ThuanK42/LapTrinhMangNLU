package de12;

import java.io.*;
import java.util.ArrayList;

/*
 * LÀM KHÔNG THEO CHUẨN HƯỚNG ĐỐI TƯỢNG
 */
public class SaveLoad_DataInput {

	public static void main(String[] args) throws Exception {
		// tao danh sach sinh vien
		SinhVien12 s1 = new SinhVien12(16130692, "Nguyễn Hoàng Việt");
		SinhVien12 s2 = new SinhVien12(16130342, "Nguyễn Thị Mén");
		SinhVien12 s3 = new SinhVien12(16130145, "Võ Tuấn Kiệt");
		s1.add(new MonHoc12("Lý Thuyết Đồ Thị", 8.0));
		s1.add(new MonHoc12("LTM", 10.0));
		s2.add(new MonHoc12("Lý Thuyết Đồ Thị", 5.0));
		s2.add(new MonHoc12("LTM", 6.0));
		s3.add(new MonHoc12("Lý Thuyết Đồ Thị", 10.0));
		s3.add(new MonHoc12("LTM", 9.0));
		s3.add(new MonHoc12("GTNM", 8.0));
		
		ArrayList<SinhVien12> los = new ArrayList<>();
		los.add(s1);
		los.add(s2);
		los.add(s3);
		
		// test cau1
		save(los, "C:\\Users\\Administrator\\Desktop\\LTM\\b.txt");
		
		// test cau2
		ArrayList<SinhVien12> losv = load("C:\\Users\\Administrator\\Desktop\\LTM\\b.txt");
		printsv(losv);
	}

	// cau 1: luu ds sinh vien xuong file
	public static void save(ArrayList<SinhVien12> los, String dest) throws Exception {
		File df = new File(dest);
		if(!df.exists() || df.isDirectory()) return;
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(df));
		dos.writeInt(los.size());
		for (SinhVien12 s : los) {
			dos.writeInt(s.getId());
			dos.writeUTF(s.getName());
			dos.writeInt(s.getLog().size());
			for (MonHoc12 m : s.getLog()) {
				dos.writeUTF(m.getName());
				dos.writeDouble(m.getScore());
			}
		}
	}

	// cau 2: load danh sach sinh vien len
	public static ArrayList<SinhVien12> load(String source) throws Exception {
		ArrayList<SinhVien12> los = new ArrayList<>();
		File fs = new File(source);
		if(!fs.exists() || fs.isDirectory()) return null;
		DataInputStream dis = new DataInputStream(new FileInputStream(fs));
		int sizeSV = dis.readInt();
		SinhVien12 sv;
		for (int i = 0; i < sizeSV; i++) {
			sv = new SinhVien12(dis.readInt(), dis.readUTF());
			int sizeMH = dis.readInt();
			for (int k = 0; k < sizeMH; k++) {
				sv.add(new MonHoc12(dis.readUTF(), dis.readDouble()));
			}
			los.add(sv);
		}
		dis.close();
		return los;
	}

	// cau 2: tinh diem trung binh
	public static double avr(SinhVien12 s) {
		double res = 0;
		for(MonHoc12 m:s.getLog()) {
			res += m.getScore();
		}
		return res/s.getLog().size();
	}
	
	// cau 2: im ra mang hinh
	public static void printsv(ArrayList<SinhVien12> los) {
		for(SinhVien12 s:los) {
			System.out.println(s.getId() + "\t" + s.getName() + "\t" + avr(s));
		}
	}
}

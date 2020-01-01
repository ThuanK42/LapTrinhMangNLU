package giaiDe;

import java.util.ArrayList;
import java.util.List;

public class SinhVien {
	private String hoTen;
	private int mssv;
	private List<MonHoc> dsMon;

	public SinhVien(int mssv, String hoTen) {
		this.mssv = mssv;
		this.hoTen = hoTen;
		this.dsMon = new ArrayList<>(5);
	}

	public SinhVien() {
		this.dsMon = new ArrayList<>(5);
	}

	@Override
	public String toString() {
		String result = "Họ và tên: " + hoTen + ", MSSV: " + mssv;
		if (this.dsMon.size() == 0)
			return result + ", chưa có môn học";
		result += ", DS môn học là \"";
		for (int i = 0; i < dsMon.size() - 1; i++) {
			result += dsMon.get(i).toString() + ", ";
		}
		return result + dsMon.get(dsMon.size() - 1).toString() + "\"";
	}

	public boolean addGrade(String name, double score) {
		return this.dsMon.add(new MonHoc(name, score));
	}

	public String toSave() {
		String result = this.mssv + "\t" + this.hoTen;
		for (MonHoc monHoc : dsMon) {
			result += "\t" + monHoc.toSave();
		}
		return result;
	}

	public void load(String[] elements) {
		this.mssv = Integer.parseInt(elements[0]);
		this.hoTen = elements[1];
		MonHoc mh;
		for (int i = 2; i < elements.length; i += 2) {
			mh = new MonHoc();
			mh.load(elements, i);
			this.dsMon.add(mh);
		}
	}

	public void inTrungBinh() {
		System.out.println(this.mssv + ", " + this.hoTen + ", " + this.tinhTrungBinh());
	}

	private double tinhTrungBinh() {
		if (dsMon.size() == 0)
			return 0;
		double sum = 0;
		for (MonHoc monHoc : dsMon) {
			sum += monHoc.getDiem();
		}
		return sum / dsMon.size();
	}
}

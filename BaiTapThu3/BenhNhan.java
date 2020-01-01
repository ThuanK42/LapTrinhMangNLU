package BaiTapThu3;

public class BenhNhan {
	private String ten, loaiBenh;
	private double vienPhi;

	public BenhNhan(String ten, String loaiBenh, double vienPhi) {
		super();
		this.ten = ten;
		this.loaiBenh = loaiBenh;
		this.vienPhi = vienPhi;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getLoaiBenh() {
		return loaiBenh;
	}

	public void setLoaiBenh(String loaiBenh) {
		this.loaiBenh = loaiBenh;
	}

	public double getVienPhi() {
		return vienPhi;
	}

	public void setVienPhi(double vienPhi) {
		this.vienPhi = vienPhi;
	}

	@Override
	public String toString() {
		return "BenhNhan [ten=" + ten + ", loaiBenh=" + loaiBenh + ", vienPhi=" + vienPhi + "]";
	}

}

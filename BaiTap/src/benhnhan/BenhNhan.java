package benhnhan;

public class BenhNhan {
	String ten;
	String loaibenh;
	double vienphi;
	public BenhNhan(String ten, String loaibenh, double vienphi) {
		super();
		this.ten = ten;
		this.loaibenh = loaibenh;
		this.vienphi = vienphi;
	}
	@Override
	public String toString() {
		return "BenhNhan [ten=" + ten + ", loaibenh=" + loaibenh + ", vienphi=" + vienphi + "]";
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getLoaibenh() {
		return loaibenh;
	}
	public void setLoaibenh(String loaibenh) {
		this.loaibenh = loaibenh;
	}
	public double getVienphi() {
		return vienphi;
	}
	public void setVienphi(double vienphi) {
		this.vienphi = vienphi;
	}
	

}

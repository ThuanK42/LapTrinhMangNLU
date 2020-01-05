package NganHangNLU;

public class User {

	private String ten, matKhau, soTaiKhoan;
	private double soTien;

	public User(String ten, String matKhau, String soTaiKhoan, double soTien) {
		super();
		this.ten = ten;
		this.matKhau = matKhau;
		this.soTaiKhoan = soTaiKhoan;
		this.soTien = soTien;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getSoTaiKhoan() {
		return soTaiKhoan;
	}

	public void setSoTaiKhoan(String soTaiKhoan) {
		this.soTaiKhoan = soTaiKhoan;
	}

	public double getSoTien() {
		return soTien;
	}

	public void setSoTien(double soTien) {
		this.soTien = soTien;
	}

	@Override
	public String toString() {
		return "User [ten=" + ten + ", matKhau=" + matKhau + ", soTaiKhoan=" + soTaiKhoan + ", soTien=" + soTien + "]";
	}

}

package rmi.model;

public class SanPham {
	int idsp;
	String ten_san_pham;
	int so_luong;
	double gia_ban;

	public SanPham(int idsp, String ten_san_pham, int so_luong, double gia_ban) {
		super();
		this.idsp = idsp;
		this.ten_san_pham = ten_san_pham;
		this.so_luong = so_luong;
		this.gia_ban = gia_ban;
	}

	public int getIdsp() {
		return idsp;
	}

	public void setIdsp(int idsp) {
		this.idsp = idsp;
	}

	public String getTen_san_pham() {
		return ten_san_pham;
	}

	public void setTen_san_pham(String ten_san_pham) {
		this.ten_san_pham = ten_san_pham;
	}

	public int getSo_luong() {
		return so_luong;
	}

	public void setSo_luong(int so_luong) {
		this.so_luong = so_luong;
	}

	public double getGia_ban() {
		return gia_ban;
	}

	public void setGia_ban(double gia_ban) {
		this.gia_ban = gia_ban;
	}

	@Override
	public String toString() {
		return "SanPham [idsp=" + idsp + ", ten_san_pham=" + ten_san_pham + ", so_luong=" + so_luong + ", gia_ban="
				+ gia_ban + "]";
	}

}

package quanlyxemphim;

public class Phim {
	private String maPhim, maKhachHang;
	private Date ngayChieu;
	private double donGia;
	private int soCho;

	public Phim(Date ngayChieu, String maPhim, String maKhachHang, double donGia, int soCho) {
		super();
		this.ngayChieu = ngayChieu;
		this.maPhim = maPhim;
		this.maKhachHang = maKhachHang;
		this.donGia = donGia;
		this.soCho = soCho;
	}

	@Override
	public String toString() {
		return "phim [ngayChieu=" + ngayChieu + ", maPhim=" + maPhim + ", maKhachHang=" + maKhachHang + ", donGia="
				+ donGia + ", soCho=" + soCho + "]";
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public Date getNgayChieu() {
		return ngayChieu;
	}

	public void setNgayChieu(Date ngayChieu) {
		this.ngayChieu = ngayChieu;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public int getSoCho() {
		return soCho;
	}

	public void setSoCho(int soCho) {
		this.soCho = soCho;
	}
}

package manSERVER2014_15;

import java.io.Serializable;

public class SanPham implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idsp;
	private String tensp;
	private int sl;
	private double gia;

	public SanPham(String idsp, String tensp, int sl, double gia) {
		super();
		this.idsp = idsp;
		this.tensp = tensp;
		this.sl = sl;
		this.gia = gia;
	}

	public String getIdsp() {
		return idsp;
	}

	public void setIdsp(String idsp) {
		this.idsp = idsp;
	}

	public String getTensp() {
		return tensp;
	}

	public void setTensp(String tensp) {
		this.tensp = tensp;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

	public double getgia() {
		return gia;
	}

	public void setgia(double gia) {
		this.gia = gia;
	}

	public SanPham() {
		super();
	}

	@Override
	public String toString() {
		return "SanPham [idsp=" + idsp + ", tensp=" + tensp + ", sl=" + sl + ", gia=" + gia + "]";
	}

}
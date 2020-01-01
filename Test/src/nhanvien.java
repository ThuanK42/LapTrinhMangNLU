
public class nhanvien {
	private String ms, hoten, ngaysinh, cutru;

	public String getMs() {
		return ms;
	}

	public nhanvien(String ms, String hoten, String ngaysinh, String cutru) {
		super();
		this.ms = ms;
		this.hoten = hoten;
		this.ngaysinh = ngaysinh;
		this.cutru = cutru;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getCutru() {
		return cutru;
	}

	public void setCutru(String cutru) {
		this.cutru = cutru;
	}

	@Override
	public String toString() {
		return "nhanvien [ms=" + ms + ", hoten=" + hoten + ", ngaysinh=" + ngaysinh + ", cutru=" + cutru + "]";
	}

}

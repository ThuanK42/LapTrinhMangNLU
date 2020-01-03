import java.io.Serializable;

public class TreEm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ms, ten, noiCuTru;
	private NgaySinh ngaySinh;

	public TreEm() {
		super();
	}

	public TreEm(String ms, String ten, NgaySinh ngaySinh, String noiCuTru) {
		this.ms = ms;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.noiCuTru = noiCuTru;
	}

	@Override
	public String toString() {
		return "TreEm [ms=" + ms + ", ten=" + ten + ", ngaySinh=" + ngaySinh + ", noiCuTru=" + noiCuTru + "]";
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public NgaySinh getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(NgaySinh ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getNoiCuTru() {
		return noiCuTru;
	}

	public void setNoiCuTru(String noiCuTru) {
		this.noiCuTru = noiCuTru;
	}

}

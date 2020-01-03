import java.io.Serializable;

public class NgaySinh implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ngay, thang, nam;

	public NgaySinh(int ngay, int thang, int nam) {
		super();
		this.ngay = ngay;
		this.thang = thang;
		this.nam = nam;
	}

	public int getNgay() {
		return ngay;
	}

	public void setNgay(int ngay) {
		this.ngay = ngay;
	}

	public NgaySinh() {
		super();
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}

	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}

	@Override
	public String toString() {
		return "NgaySinh [ngay=" + ngay + ", thang=" + thang + ", nam=" + nam + "]";
	}

}

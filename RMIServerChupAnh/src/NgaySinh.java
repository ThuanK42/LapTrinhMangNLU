import java.io.Serializable;

public class NgaySinh implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ngay, thang, nam;

	public NgaySinh(int i, int j, int k) {
		super();
		this.ngay = i;
		this.thang = j;
		this.nam = k;
	}

	public int getNgay() {
		return ngay;
	}

	public void setNgay(int ngay) {
		this.ngay = ngay;
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
		return ngay + "/" + thang + "/" + nam;
	}

}

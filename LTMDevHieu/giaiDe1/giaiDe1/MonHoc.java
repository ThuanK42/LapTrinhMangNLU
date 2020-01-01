package giaiDe;

public class MonHoc {
	private String ten;
	private double diem;

	public MonHoc(String name, double score) {
		this.ten = name;
		this.diem = score;
	}

	public MonHoc() {
	}

	@Override
	public String toString() {
		return ten + ": " + diem;
	}

	public String toSave() {
		return this.ten + "\t" + this.diem;
	}

	public void load(String[] elements, int i) {
		this.ten = elements[i];
		this.diem = Double.parseDouble(elements[i + 1]);
	}

	public double getDiem() {
		return this.diem;
	}

}

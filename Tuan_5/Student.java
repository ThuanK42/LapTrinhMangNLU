package Tuan_5;

public class Student {
	private String id, name, lop;
	private double diem;

	public Student(String id, String name, String lop, double diem) {
		super();
		this.id = id;
		this.name = name;
		this.lop = lop;
		this.diem = diem;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public double getDiem() {
		return diem;
	}

	public void setDiem(double diem) {
		this.diem = diem;
	}

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + lop + "\t \t" + diem;
	}
}

package de11;

import java.util.ArrayList;

public class SinhVien11 {
	private int id;
	private String name;
	private ArrayList<MonHoc11> lom;

	public SinhVien11(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		lom = new ArrayList<>();
	}

	public void add(MonHoc11 m) {
		lom.add(m);
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<MonHoc11> getLom() {
		return lom;
	}

	@Override
	public String toString() {
		return "SinhVien11 [id=" + id + ", name=" + name + ", lom=" + lom + "]";
	}

	public String line(String delimited) {
		String res = id + delimited + name;
		for(MonHoc11 m:lom) {
			res += delimited + m.getName() +  delimited + m.getScore();
		}
		return res;
	}

}

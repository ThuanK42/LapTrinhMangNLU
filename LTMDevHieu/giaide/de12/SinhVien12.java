package de12;

import java.util.ArrayList;

public class SinhVien12 {
	private int id;
	private String name;
	private ArrayList<MonHoc12> log;

	@Override
	public String toString() {
		return "SinhVien12 [id=" + id + ", name=" + name + ", log=" + log + "]";
	}

	public SinhVien12(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		log = new ArrayList<>();
	}

	public SinhVien12() {
		log = new ArrayList<>();
	}

	public void add(MonHoc12 g) {
		log.add(g);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<MonHoc12> getLog() {
		return log;
	}
	
}

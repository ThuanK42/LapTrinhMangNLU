package server;

import java.io.Serializable;

public class SinhVien implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int age;
	private double score;

	public SinhVien(int id, String name, int age, double score) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.score = score;
	}

	@Override
	public String toString() {
		return "SinhVien [id=" + id + ", name=" + name + ", age=" + age + ", score=" + score + "]";
	}

}

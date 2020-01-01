public class Student {

	private String mssv;
	private String name;
	private int score;


	public Student(String mssv, String name, int score) {
		super();
		this.mssv = mssv;
		this.name = name;
		this.score = score;
	}

	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [mssv=" + mssv + ", name=" + name + ", score=" + score
				+ "]";
	}

}

package myIO.Bai12;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Grade {
	private String name;
	private double score;

	public Grade(String name, double score) {
		this.name = name;
		this.score = score;
	}

	public Grade() {
	}

	@Override
	public String toString() {
		return "Grade [name=" + name + ", score=" + score + "]";
	}

	public void save(DataOutput dataOutput) throws IOException {
		dataOutput.writeUTF(this.name);
		dataOutput.writeDouble(this.score);
	}

	public void load(DataInput dataInput) throws IOException {
		this.name = dataInput.readUTF();
		this.score = dataInput.readDouble();
	}

}

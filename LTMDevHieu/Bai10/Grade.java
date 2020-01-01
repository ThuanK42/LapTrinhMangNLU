package myIO.Bai10;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Grade {
	private String name;
	private double score;

	public Grade(String name, double score) {
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Grade [name=" + name + ", score=" + score + "]";
	}

	public static void save(Grade grade, DataOutputStream outputStream) throws IOException {
		outputStream.writeUTF(grade.name);
		outputStream.writeDouble(grade.score);
	}

	public static Grade load(DataInputStream inputStream) throws IOException {
		return new Grade(inputStream.readUTF(), inputStream.readDouble());
	}

}

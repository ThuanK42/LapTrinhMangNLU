package rmi.server;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test  {
	public static void main(String[] args) throws IOException {
		String line = "         3.3  		asdfsadf	 asdf		  1231 123 1123                ";
		StringTokenizer st = new StringTokenizer(line);
		int num = 0;
		int word = 0;
		double next;
		while(st.hasMoreTokens()) {
			try {
				next = Double.parseDouble(st.nextToken());
				num += 1;
			} catch (NumberFormatException e) {
				word += 1;
			}
		}
		System.out.println(num + ", " + word);
	}
}

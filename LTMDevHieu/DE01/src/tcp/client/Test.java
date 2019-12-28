package tcp.client;

import java.io.File;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		File file = new File("c:\\s");
		file.renameTo(new File("c:\\d"));
	}
}

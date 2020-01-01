package tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

public class Server {
	BufferedOutputStream bos = null;
	BufferedInputStream bis = null;
	DataInputStream dis;
	DataOutputStream dos;
	String line;
	boolean result;

	public void process(String line) throws IOException {
		StringTokenizer st = new StringTokenizer(line);
		String command = st.nextToken();
		String sf, df;

		switch (command) {
		case "COPY":
			sf = st.nextToken();
			df = st.nextToken();
			if (copy(sf, df)) {
				dos.writeUTF("Copy success");
			}
			break;

		case "MOVE":
			sf = st.nextToken();
			df = st.nextToken();
			if (move(sf, df)) {
				dos.writeUTF("Move success");
			} else {
				dos.writeUTF("Move fail");
			}
			break;

		case "RENAME":
			sf = st.nextToken();
			df = st.nextToken();
			File sfile = new File(sf);
			File dfile = new File(df);
			if (sfile.renameTo(dfile)) {
				dos.writeUTF("rename success");
			} else {
				dos.writeUTF("rename fail");
			}
			break;
		default:
			break;
		}
		dos.flush();
		dos.close();

	}

	public boolean copy(String sf, String df) throws IOException {
		result = false;
		try {
			try {
				bos = new BufferedOutputStream(new FileOutputStream(df));
			} catch (FileNotFoundException e) {
				dos.writeUTF("Server: Không thể tạo file" + df);
				result = false;
			}

			try {
				bis = new BufferedInputStream(new FileInputStream(sf));
			} catch (FileNotFoundException e) {
				dos.writeUTF("Server: Không thể mở file" + sf);
				result = false;
			}

			byte[] arr = new byte[100 * 1024];
			int data;
			while ((data = bis.read(arr)) != -1) {
				bos.write(arr, 0, data);
			}
			bos.close();
			bis.close();
			result = true;

		} catch (NullPointerException e) {
			result = false;
		} catch (Exception e) {
		}
		return result;

	}

	public boolean move(String sf, String df) throws IOException {
		result = false;
		try {
			File file = new File(sf);
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(df));

			byte[] arr = new byte[100 * 1024];
			int data;
			while ((data = bis.read(arr)) != -1) {
				bos.write(arr, 0, data);
			}

			bis.close();
			bos.close();
			file.delete();
			result = true;
		} catch (FileNotFoundException e) {
			result = false;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		String sf = "E:/Sophomore/Nam3/LTM/src/Test/ádasdas.txt";
		String df = "E:/Sophomore/Nam3/LTM/src/Test/as.txt";
		File s = new File(sf);
		File d = new File(df);
		System.out.println(s.renameTo(d));
	}
}

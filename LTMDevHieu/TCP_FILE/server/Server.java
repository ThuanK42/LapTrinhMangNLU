package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Server extends Thread {
	DataInputStream dis;
	DataOutputStream dos;

	public void process() {
		String line;
		StringTokenizer st;
		String command;
		String sf, df;
		while (true) {
			try {
				line = dis.readUTF();
				st = new StringTokenizer(line);

				if ("QUIT".equalsIgnoreCase(line))
					break;

				command = st.nextToken();
				switch (command) {
				case "COPY":
					sf = st.nextToken();
					df = st.nextToken();
					if (copy(sf, df)) {
						dos.writeUTF("Copy success");
					} else {
					}

					break;
				case "RENAME":
					sf = st.nextToken();
					df = st.nextToken();
					if (copy(sf, df)) {
						dos.writeUTF("Copy success");
					} else {
					}

					break;

				default:
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean copy(String sf, String df) {
		boolean result = false;
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(df));
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf));

			byte[] arr = new byte[100 * 1024];
			int data;
			while ((data = bis.read(arr)) != -1) {
				bos.write(arr, 0, data);
			}
			result = true;
			bos.flush();
			bos.close();
			bis.close();
		} catch (FileNotFoundException e) {
			result = false;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		try {
			ServerSocket sc = new ServerSocket(12345);
			System.out.println("dang cho ket noi");

			Socket client = sc.accept();
			System.out.println("da ket noi");

			System.out.println("Welcome to....");
			new Server().process();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

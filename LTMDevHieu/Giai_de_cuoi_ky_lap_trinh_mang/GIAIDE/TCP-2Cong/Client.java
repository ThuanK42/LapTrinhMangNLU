package tcp2con;

import tcp2con.*;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Aragon on 8/4/2017.
 */
public class Client {
	public static final String source = "E:\\th";
	Socket socket;
	int port = 12345;
	String host = "127.0.0.1";
	BufferedReader in, reader;
	PrintWriter out;

	public Client() throws IOException {
		connect();
		createStream();
		listen();
		String line;
		while (true) {
			receipt();
			while (!sendCommend())
				;
			line = in.readLine();
			if (line.equalsIgnoreCase("Download"))
				download();
		}
	}

	private void download() throws UnknownHostException, IOException {
		Socket socket1 = new Socket("127.0.0.1", 7777);
		System.out.println("Client ok");
		DataOutputStream dos = new DataOutputStream(socket1.getOutputStream());
		DataInputStream din = new DataInputStream(socket1.getInputStream());
		long size = din.readLong();
		String name = din.readUTF();
		System.out.println(size);
		System.out.println(name);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(source + "\\" + name));
		long end = 0;
		int a;
		while (size != end) {
			a = din.read();
			bos.write(a);
			end++;
			if (size - end == 0) {
				break;
			}
		}
		dos.flush();
		bos.close();
		din.close();
		dos.close();
		System.out.println("Đã nhận được fie");
		socket1.close();
	}

	private void sendData() throws IOException {
		while (true) {
			String line = in.readLine();
			if (line.equals("exit"))
				break;
			System.out.println(line);
			out.println(reader.readLine());
			out.flush();
		}
	}

	public void connect() throws IOException {
		socket = new Socket(host, port);
	}

	public void createStream() throws IOException {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public void listen() throws IOException {
		System.out.println(in.readLine());
	}

	public void receipt() throws IOException {
		String line;
		while (true) {
			line = in.readLine();
			if (line.equals("exit"))
				break;
			System.out.println(line);
		}
	}

	public boolean sendCommend() throws IOException {
		String line = reader.readLine();
		out.println(line);
		out.flush();
		System.out.println(in.readLine());
		if (in.readLine().equals("1"))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		try {
			Client c = new Client();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

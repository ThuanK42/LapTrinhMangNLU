package tcp.client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	Socket socket;
	DataInputStream netIn;
	DataOutputStream netOut;
	BufferedReader userIn;
	String sf, df, command;
	StringTokenizer st;
	String folderView;

	public static void main(String[] args) throws IOException {
		Client client = new Client();
		client.start();
	}

	private void start() throws IOException {
		socket = new Socket("127.0.0.1", 55555);
		netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		userIn = new BufferedReader(new InputStreamReader(System.in));

		String getWelcom = netIn.readUTF();
		System.out.println(getWelcom);
		String line;
		while (true) {
			line = userIn.readLine();
			lineprocess(line);
			if ("QUIT".equalsIgnoreCase(line))
				break;
		}
		userIn.close();
		netIn.close();
		netOut.close();
		socket.close();
	}

	private void lineprocess(String line) throws IOException {
		line = line.replace(" ", "");
		st = new StringTokenizer(line, "|");
		command = st.nextToken();
		String path, pathFileName, result = null;
		switch (command) {
		case "QUIT":
			netOut.writeUTF(line);
			result = "QUIT SUCCESS";
			break;
		case "SET_FOLDER":
			folderView = st.nextToken();
			result = "SET_FOLDER SUCCESS";
			break;
		case "VIEW":
			path = st.nextToken();
			File file = new File(path);
			if (file.isDirectory()) {
				processPath(file);
				result = "VIEW SUCCESS";
			}else {
				pathFileName = folderView + File.separator + path;
				File filePath = new File(pathFileName);
				if (filePath.exists()) {
					result = filePath.getAbsolutePath();
				} else {
					result = "It not a folder or a name file in folder defaul";
				}
			}
			break;
		case "COPY":
			sf = st.nextToken();
			df = st.nextToken();
			netOut.writeUTF(command + " " + sf + " " + df);
			netOut.flush();
			result = netIn.readUTF();
			break;
		case "MOVE":
			sf = st.nextToken();
			df = st.nextToken();
			netOut.writeUTF(command + " " + sf + " " + df);
			netOut.flush();
			result = netIn.readUTF();
			break;
		case "RENAME":
			sf = st.nextToken();
			df = st.nextToken();
			netOut.writeUTF(command + " " + sf + " " + df);
			netOut.flush();
			result = netIn.readUTF();
			break;
		default:
			result = "command error";
			break;
		}
		netOut.flush();
		System.out.println(result);
	}

	private void processPath(File file) {
		File[] files = file.listFiles();
		for (File f : files) {
			System.out.println(f.getAbsolutePath());
		}
	}

}

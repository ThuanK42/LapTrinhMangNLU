package tcp.server;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class ServerProcess extends Thread {
	Socket socket;
	DataInputStream netIn;
	DataOutputStream netOut;

	public ServerProcess(Socket socket) throws IOException {
		this.socket = socket;
		netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		netOut.writeUTF("Welcom to File manager");
		netOut.flush();
	}

	@Override
	public void run() {
		String line;
		StringTokenizer st;

		while (true) {
			try {
				line = netIn.readUTF();
				if ("QUIT".equalsIgnoreCase(line))
					break;
				processLine(line);

			} catch (IOException e) {
			}
		}

	}

	private void processLine(String line) throws IOException {
		StringTokenizer st = new StringTokenizer(line);
		String command = st.nextToken();
		String sf, df;
		switch (command) {
		case "COPY":
			sf = st.nextToken();
			df = st.nextToken();
			if (copy(sf, df)) {
				netOut.writeUTF("Copy success");
			} else {
			}
			break;
		case "MOVE":
			sf = st.nextToken();
			df = st.nextToken();
			if (move(sf, df)) {
				netOut.writeUTF("Move success");
			} else {
				netOut.writeUTF("Move fail");
			}
			break;
		case "RENAME":
			sf = st.nextToken();
			df = st.nextToken();
			File sfs = new File(sf);
			File sfd = new File(df);
			if (sfs.renameTo(sfd)) {
				netOut.writeUTF("Rename success");
			} else {
				netOut.writeUTF("Rename fail");
			}
			break;
		default:
			break;
		}
		netOut.flush();

	}

	private boolean move(String sf, String df) {
		try {
			File file = new File(sf);

			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

			byte[] buff = new byte[10 * 1024];
			int data;
			while ((data = bis.read(buff)) != -1) {
				bos.write(buff, 0, data);
			}
			bos.close();
			bis.close();
			file.delete();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private boolean copy(String sf, String df) {
		try {
			BufferedOutputStream bos = null;
			try {
				bos = new BufferedOutputStream(new FileOutputStream(df));
			} catch (FileNotFoundException e) {
				netOut.writeUTF("SERVER: Can not create dest file");
				return false;
			}

			BufferedInputStream bis = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(sf));
			} catch (FileNotFoundException e2) {
				netOut.writeUTF("SERVER: Can not open source file");
				return false;
			}

			byte[] buff = new byte[10 * 1024];
			int data;
			while ((data = bis.read(buff)) != -1) {
				bos.write(buff, 0, data);
			}
			bos.flush();
			bos.close();
			bis.close();
			return true;
		} catch (NullPointerException e) {
			return false;
		}catch (Exception e) {
		}
		return false;
	}
}

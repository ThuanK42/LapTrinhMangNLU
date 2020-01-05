package QuanLySieuThi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Client {
	public static final int PORT = 1212;
	public static final String HOST = "127.0.0.1";
	private BufferedReader userIn; // nhap tu dong lenh
	private DataInputStream netIn; // nhan ket qua tu server
	private DataOutputStream netOut; // gui lenh den server

	public Client() {
	}

	public void request() {
		try {
			// Connect
			Socket socket = new Socket(HOST, PORT);
			System.out.println("Connect Done...");
			userIn = new BufferedReader(new InputStreamReader(System.in));
			netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			String line;
			// Vòng lặp để nhận request
			while (true) {
				try {
					line = userIn.readLine();
					// Phân tích chuỗi
					analysic(line);
					// Nếu như chuỗi line == Quit thì kết thúc
					if ("QUIT111".equalsIgnoreCase(line)) {
						System.out.println("Hẹn gặp lại");
						break;
					}

				} catch (IOException e) {
					e.printStackTrace();
				} catch (NoSuchElementException e) {
					System.out.println(e.getMessage());
				}
			}
			netOut.close();
			netIn.close();
			userIn.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void analysic(String line) throws IOException {
		StringTokenizer st = new StringTokenizer(line, "\t");
		String lenhGuiDi;
		while (st.hasMoreTokens()) {
			String command = st.nextToken();
			switch (command) {
			case "USER": // lam sao de gui lenh di, nhan ket qua tra ve, du lieu nhap tu ban phim da nhan

				lenhGuiDi=command + "\t" + st.nextToken();
				netOut.writeUTF(lenhGuiDi); // lenh gui di
				netOut.flush();
				
				String ressult = netIn.readUTF(); // nhan ket qua tra ve
				System.out.println(ressult);
				break;
			case "PASS":
				netOut.writeUTF(command + "\t" + st.nextToken()); // lenh gui di
				netOut.flush();
				String rs = netIn.readUTF(); // nhan ket qua tra ve
				System.out.println(rs);
				break;
			case "EXIT":
				netOut.writeUTF(line); // lenh gui di
				netOut.flush();
				String rs2 = netIn.readUTF(); // nhan ket qua tra ve
				System.out.println(rs2);
				break;
			case "ADD":
				netOut.writeUTF(line); // lenh gui di
				netOut.flush();
				String rs3 = netIn.readUTF(); // nhan ket qua tra ve
				System.out.println(rs3);
				break;
			case "REMOVE":
				ArrayList<String> listIDDelete = new ArrayList<String>();
				while (st.hasMoreTokens()) {
					listIDDelete.add(st.nextToken() + "\t");
				}
				netOut.writeUTF(command + "\t" + listIDDelete);
				netOut.flush();
				String rs4 = netIn.readUTF(); // nhan ket qua tra ve
				System.out.println(rs4);
				break;
			case "EDIT":
				netOut.writeUTF(line); // lenh gui di
				netOut.flush();
				String rs5 = netIn.readUTF(); // nhan ket qua tra ve
				System.out.println(rs5);
				break;
			case "VIEW":
				netOut.writeUTF(line); // lenh gui di
				netOut.flush();
				String rs6 = netIn.readUTF(); // nhan ket qua tra ve
				System.out.println(rs6);
				break;
			case "QUIT":
				netOut.writeUTF(line);
				break;
			default:
				break;
			}
			// Flush
			netOut.flush();
		}

	}

	public static void main(String[] args) {
		Client cl = new Client();
		cl.request();
	}
}

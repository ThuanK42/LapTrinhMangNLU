package NganHangNLU;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerProcess extends Thread {

	Socket socket;
	DataInputStream dis; // nhan yeu cau tu client
	DataOutputStream dos; // gui ket qua cho client
	String requestFromClient;
	UserDao ud;

	public ServerProcess(Socket socket) throws IOException {
		this.socket = socket;
		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		ud = new UserDao();
	}

	@Override
	public void run() {
		try {
			while (true) {
				while (true) {

					
					requestFromClient = dis.readUTF();

					if ("EXIT".equalsIgnoreCase(requestFromClient)) {
						dos.writeUTF("Cam on ban da dung chuong trinh cua chung toi");
						dos.flush();
						socket.close();
						dis.close();
						dos.close();
						break;
					}
					System.out.println(requestFromClient);

					StringTokenizer token = new StringTokenizer(requestFromClient, "\t");

					if (token.countTokens() != 2) {
						dos.writeUTF("Cu phap sai");
						dos.flush();

					}

					String lenh = token.nextToken();

					System.out.println(lenh);

					if ("TEN".equalsIgnoreCase(lenh)) {
						boolean ketQua = ud.checkUser(token.nextToken());
						if (ketQua == true) {
							dos.writeUTF("OK");
							dos.flush();
						} else {
							dos.writeUTF("Fail");
							dos.flush();
						}
					} else if ("MATKHAU".equalsIgnoreCase(lenh)) {
						boolean ketQua = ud.checkLogin(token.nextToken());
						if (ketQua == true) {
							dos.writeUTF("OK");
							dos.flush();
							break;
						} else {
							dos.writeUTF("Fail");
							dos.flush();
						}
					} else {
						dos.writeUTF("Sai cu phap");
						dos.flush();
						continue;
					}

				}

				while (true) {
					
					System.out.println("asdasjkdhgsadjagdsjah");

					requestFromClient = dis.readUTF();

					if ("QUIT".equalsIgnoreCase(requestFromClient)) {
						dos.writeUTF("Dung dich vu EBanking");
						dos.flush();
						socket.close();
						dis.close();
						dos.close();
						break;
					}

					StringTokenizer token = new StringTokenizer(requestFromClient, "\t");
					String lenh = token.nextToken();

					System.out.println("lenh 222222222: "+lenh);
					
					while (token.hasMoreTokens()) {
						switch (lenh) {
						case "GUI":
							boolean ketQua = ud.guiTien(token.nextToken());
							if (ketQua == true) {
								dos.writeUTF("gui thanh cong");
								dos.flush();
							} else {
								dos.writeUTF("gui that bai");
								dos.flush();
							}
							break;
						case "LAY":
							boolean ketQua2 = ud.layTien(token.nextToken());
							if (ketQua2 == true) {
								dos.writeUTF("lay thanh cong");
								dos.flush();
							} else {
								dos.writeUTF("lay that bai");
								dos.flush();
							}
							break;
						case "CHUYEN":
							boolean ketQua3 = ud.chuyenTien(token.nextToken(), token.nextToken());
							if (ketQua3 == true) {
								dos.writeUTF("CHUYEN thanh cong");
								dos.flush();
							} else {
								dos.writeUTF("CHUYEN that bai");
								dos.flush();
							}
							break;

						default:
							break;
						}
					}

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

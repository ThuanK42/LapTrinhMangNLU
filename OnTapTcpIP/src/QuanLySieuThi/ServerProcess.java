package QuanLySieuThi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ServerProcess extends Thread {
	Socket socket;
	DataInputStream dis; // nhan tu client
	DataOutputStream dos; // gui ket qua cho client
	String message;
	UserDao ud;
	ProductDao pd;
	String requestFromClient;

	public ServerProcess(Socket socket) throws IOException {
		this.socket = socket;
		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		ud = new UserDao();
		pd = new ProductDao();

	}

	@Override
	public void run() {
		try {

			// while thuc thi chuong trinh
			while (true) {

				while (true) { // while dang nhhap
					
					requestFromClient=dis.readUTF();

					if ("EXIT".equalsIgnoreCase(requestFromClient)) {
						dos.writeUTF("Tam biet dong while dang nhap");
						dos.flush();
						break; // dung while chuong trinh
					}

					StringTokenizer token = new StringTokenizer(requestFromClient, "\t");

					if (token.countTokens() < 2) {
						dos.writeUTF("Sai cu phap 1");
						dos.flush();
					}

					String lenh = token.nextToken();
					
					System.out.println(lenh);

					if ("USER".equalsIgnoreCase(lenh)) {

						int i = ud.checkUser(token.nextToken());

						if (i == 1) {
							dos.writeUTF("OK 1");
							dos.flush();
						} else {
							dos.writeUTF("Fail 1");
							dos.flush();
						}

					} else if ("PASS".equalsIgnoreCase(lenh)) {
						int i = ud.checkLogin(token.nextToken());
						if (i == 1) {
							dos.writeUTF("OK 2");
							dos.flush();
							break; // dung while dang nhap
						} else {
							dos.writeUTF("Fail 2");
							dos.flush();
						}
					} else {
						dos.writeUTF("sai cu phap 2");
						dos.flush();
						continue;
					}
				}

				while (true) { // while chuc nang
					
					requestFromClient=dis.readUTF();

					if ("QUIT".equalsIgnoreCase(requestFromClient)) {
						
						dos.writeUTF("Tam biet dong while dang nhap");
						dos.flush();
						break; // dung while chuc nang
					}

					StringTokenizer token = new StringTokenizer(requestFromClient, "\t");

					if (token.countTokens() < 2) {
						dos.writeUTF("Sai cu phap 2");
						dos.flush();
					}

					String lenh = token.nextToken();

					if ("ADD".equalsIgnoreCase(lenh)) {
						int i = pd.addProduct(token.nextToken(), token.nextToken(), token.nextToken(),
								token.nextToken());
						if (i == 1) {
							dos.writeUTF("OK 3");
							dos.flush();
						} else {
							dos.writeUTF("ERROR 3");
							dos.flush();
						}
					} else if ("EDIT".equalsIgnoreCase(lenh)) {
						int i = pd.editProduct(token.nextToken(), token.nextToken(), token.nextToken(),
								token.nextToken());
						if (i == 1) {
							dos.writeUTF("OK 3");
							dos.flush();
						} else {
							dos.writeUTF("cannot update");
							dos.flush();
						}
					} else if ("REMOVE".equalsIgnoreCase(lenh)) {
						ArrayList<String> listID = new ArrayList<String>();
						while (token.hasMoreTokens()) {
							listID.add(token.nextToken());
						}
						int count = pd.deleteListProduct(listID);
						if (count != 0) {
							dos.writeUTF("So san pham duoc xoa la: " + count);
							dos.flush();
						} else {
							dos.writeUTF("Khong san pham nao duoc xoa");
							dos.flush();
						}
					} else if ("VIEW".equalsIgnoreCase(lenh)) {
						List<Product> listProduct = new ArrayList<Product>();
						listProduct = pd.viewInfoProductByName(token.nextToken());
						if (listProduct != null) {
							dos.writeUTF("San pham theo dieu kien loc la: \n" + listProduct.toString());
							dos.flush();
						} else {
							dos.writeUTF("Khong tim thay san pham nao");
							dos.flush();
						}
					} else {
						dos.writeUTF("sai cu phap view");
						dos.flush();
						continue;
					}
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

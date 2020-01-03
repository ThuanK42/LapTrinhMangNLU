import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.StringTokenizer;

import Server.IBank;

public class Client {
	public static void main(String[] args) throws NotBoundException, IOException {
		// lay lenh tu ban phim
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;

		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 12345);
		IBank ud = (IBank) registry.lookup("iBank");

		// while chuong trinh
		abc: while (true) {

			System.out.println(ud.hiClient());
			ud.napDuLieuBanDau();

			// while user
			while (true) {

				// nhan du lieu tu ban phim theo dong
				line = br.readLine();

				// lenh dung chuong trinh tu buoc user
				if ("exit".equalsIgnoreCase(line)) {
					System.out.println("Server dung tai day !");
					break abc;
				}

				// nguoi dung nhap lenh kiem tra option lenh nguoi dung

				StringTokenizer token = new StringTokenizer(line, "\t");

				// 1 lenh gom key:value

				if (token.countTokens() != 2) {
					System.out.println("Lenh nhap sai");
					continue;
				}

				line = token.nextToken();


				if ("TEN".equalsIgnoreCase(line)) {
					int i = ud.checkUser(token.nextToken());
					System.out.println(i);
					if (i == 1) {
						System.out.println("OK");
					} else if (i == 0) {
						System.out.println("Fail");
					} else {
						System.out.println("Lenh dung la: TEN	tentaikhoan");
					}
				} else if ("MATKHAU".equalsIgnoreCase(line)) {
					int i = ud.checkLogin(token.nextToken());
					if (i == 1) {
						System.out.println("OK");
						break; // dung while user
					} else if (i == 0) {
						System.out.println("Failed");
					} else {
						System.out.println("Cau lenh sai");
						continue; // quay ve cau lenh while user
					}
				}

			}

			// while xu ly chuong trinh

			while (true) {

				// nhan du lieu tu ban phim theo dong
				line = br.readLine();

				// lenh dung chuong trinh tu buoc user
				if ("quit".equalsIgnoreCase(line)) {
					System.out.println("Server dung tai day !");
					break abc;
				}

				// nguoi dung nhap lenh kiem tra option lenh nguoi dung

				StringTokenizer token = new StringTokenizer(line, "\t");

				// 1 lenh gom key:value

				if (token.countTokens() < 2) {
					System.out.println("Lenh nhap sai");
					continue;
				}

				line = token.nextToken();

				if ("GUI".equalsIgnoreCase(line)) {
					int i = ud.guiTien(Integer.parseInt(token.nextToken()));
					if (i == 1) {
						System.out.println("Ban da gui tien vao tai khoan thanh cong");
					} else {
						System.out.println("Chuyen tien that bai ????");
					}
				} else if ("LAY".equalsIgnoreCase(line)) {
					int i = ud.layTien(Integer.parseInt(token.nextToken()));
					if (i == 1) {
						System.out.println("Rut tien thanh cong");
					} else {
						System.out.println("Rut tien that bai ????");
					}
				} else if ("CHUYEN".equalsIgnoreCase(line)) {
					int i = ud.chuyenTien(token.nextToken(), Integer.parseInt(token.nextToken()));
					if (i == 1) {
						System.out.println("Chuyen tien thanh cong");
					} else {
						System.out.println("Chuyen tien that bai ????");
					}
				} else {
					System.out.println("Nhap sai lenh");
					continue;
				}

			}

		}
	}
}

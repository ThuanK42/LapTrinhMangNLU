import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.StringTokenizer;

import server.ISanPham;
import server.IUser;


public class Client {
	public static void main(String[] args) throws IOException, NotBoundException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String lines;
		System.out.println("Quản lý siêu thị");
		
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1212);
		
		IUser ud = (IUser)registry.lookup("iu");
		ISanPham spd =  (ISanPham)registry.lookup("isp");

		abc: while (true) {

			// user
			while (true) {
				lines = br.readLine();

				if ("exit".equalsIgnoreCase(lines)) {
					break abc;
				}

				StringTokenizer token = new StringTokenizer(lines, "\t");
				if (token.countTokens() != 2) {
					System.out.println("Sai cu phap 1");
					continue;
				}

				lines = token.nextToken();

				if ("user".equalsIgnoreCase(lines)) {
					int i = ud.checkUser(token.nextToken());
					if (i == 1) {
						System.out.println("OK 1");
					} else if (i == 0) {
						System.out.println("Failed 1");
					} else {
						System.out.println("Sai cu phap 1");
					}
				} else if ("pass".equalsIgnoreCase(lines)) {
					int i = ud.checkLogin(token.nextToken());
					if (i == 1) {
						System.out.println("OK 2");
						break;
					} else if (i == 0) {
						System.out.println("Failed 2");
					} else {
						System.out.println("Sai cu phap 2");
						continue;
					}
				}

			}
			// xu ly chuong trinh chinh
			while (true) {
				lines = br.readLine();
				if ("exit".equalsIgnoreCase(lines)) {
					break abc;
				}
				StringTokenizer token = new StringTokenizer(lines, "\t");
				if (token.countTokens() < 2) {
					System.out.println("Sai cu phap 4");
					continue;
				}

				lines = token.nextToken();

				if ("add".equalsIgnoreCase(lines)) {
					int i = spd.addProduct(Integer.parseInt(token.nextToken()), token.nextToken(),
							Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
					if (i == 1) {
						System.out.println("OK 5");
					} else if (i == 0) {
						System.out.println("Failed 5");
					} else {
						System.out.println("Sai cu phap 5");
					}

				} else if ("edit".equalsIgnoreCase(lines)) {
					int i = spd.editProduct(Integer.parseInt(token.nextToken()), token.nextToken(),
							Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
					if (i == 1) {
						System.out.println("OK 6");
					} else if (i == 0) {
						System.out.println("Failed 6");
					} else {
						System.out.println("Sai cu phap 6");
					}
				} else if ("delete".equalsIgnoreCase(lines)) {
					int i = spd.deleteProduct(Integer.parseInt(token.nextToken()));
					if (i == 1) {
						System.out.println("OK 7");
					} else if (i == 0) {
						System.out.println("Failed 7");
					} else {
						System.out.println("Sai cu phap 7");
					}
				} else if ("view".equalsIgnoreCase(lines)) {

					spd.viewSearch(token.nextToken());
				} else {
					System.out.println("Nhap sai lenh");
					continue;
				}
			}
		}
	}
}

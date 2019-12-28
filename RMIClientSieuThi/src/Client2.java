import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.StringTokenizer;

import server.ISanPham;
import server.IUser;
import server.SanPham;

public class Client2 {
	public static void main(String[] args) throws NotBoundException, IOException {
		// doc lenh tu ban phim
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// dong lenh
		String line;
		System.out.println("Chao mung cac vi den voi phan mem quan ly sieu thi");

		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1212);

		IUser ud = (IUser) registry.lookup("iu");
		ISanPham spd = (ISanPham) registry.lookup("isp");

		abc: while (true) {

			// dang nhap
			while (true) {
				// doc lenh tu ban phim
				line = br.readLine();

				if ("exit".equalsIgnoreCase(line)) {
					// thi dung chuong trinh
					break abc;
				}

				// phan tich lenh cat lenh ra
				StringTokenizer token = new StringTokenizer(line, "\t");

				// dem thu du option cau thanh lenh khong
				if (token.countTokens() != 2) {
					System.out.println("Cau lenh sai");
					continue; // dua no ve dong while 2 lai
				}

				// cat dong lenh ra thi phai co cai gi gan voi tung bo phan cau lenh sau khi cat
				line = token.nextToken(); // lay tung bo phan ra

				if ("user".equalsIgnoreCase(line)) {
					// nexttoken: lay chuoi token tiep theo
					if (ud.checkUser(token.nextToken())) {
						System.out.println("OK");
					} else {
						System.err.println("FALSE");
					}
				} else if ("pass".equalsIgnoreCase(line)) {
					// nhan vao pass
					int i = ud.checkLogin(token.nextToken());
					if (i == 1) {
						System.out.println("OK");
						break; // ket thuc dong while dang nhap
					} else if (i == 0) {
						System.out.println("FALSE");
					} else {
						System.out.println("cau lenh sai roi");
						continue; // quay ve dong lenh 2
					}
				}

			}

			// xu ly cac phuong thuc sau khi dang nhap thanh cong
			while (true) {
				// doc thong tin tu ban phim
				line = br.readLine();
				// quit ra phan xu ly dich vu nguoi dung
				if ("quit".equalsIgnoreCase(line)) {
					break abc;
				}
				// cat lenh nguoi dung theo tab
				StringTokenizer token = new StringTokenizer(line, "\t");

				// cac cau lenh deu cau thanh tu 3 bo phan tro len
				if (token.countTokens() < 2) {
					System.out.println("Cau lenh sai");
					continue;
				}

				line = token.nextToken();
				

				// tao moi san pham
				if ("add".equalsIgnoreCase(line)) {
					int i = spd.addProduct(Integer.parseInt(token.nextToken()), token.nextToken(),
							Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));

					if (i == 1) {
						System.out.println("OK");
					} else if (i == 0) {
						System.out.println("ERROR");
					} else {
						System.out.println("Loi cu phap");
					}
				}
				// xoa danh sach san pham theo id
				else if ("remove".equalsIgnoreCase(line)) {
					int count = 0;
					while (token.hasMoreTokens()) {
						int i = spd.deleteSPById(Integer.parseInt(token.nextToken()));
						if (i == 1) {
							count++;
						} else if (i == 0) {
							System.out.println("xoa loi");
						} else {
							System.out.println("Sai cau lenh xoa");
						}
					}
					System.out.println("So luong san pham xoa thanh cong la: " + count);
				}
				// xu ly cap nhat san pham
				else if ("edit".equalsIgnoreCase(line)) {
					int i = spd.editProduct(Integer.parseInt(token.nextToken()), token.nextToken(),
							Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
					if (i == 1) {
						System.out.println("OK");
					} else if (i == 0) {
						System.out.println("Cannot update");
					} else {
						System.out.println("Loi cu phap");
					}
				}
				// in ra cac san pham tim duoc theo ten
				else if ("view".equalsIgnoreCase(line)) {
					List<SanPham> listSanPhamTimDuoc = spd.searchProductByName(token.nextToken());
					for (SanPham sanPham : listSanPhamTimDuoc) {
						System.out.println(sanPham.toString());
					}
				} else {
					System.out.println("Nhap sai lenh");
					continue;
				}
			}
		}
	}
}

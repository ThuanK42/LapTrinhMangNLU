package SV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

import server.SVDAO;
import server.SinhVien;
import server.UserDAO;

public class TraCuu {

	public TraCuu() throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		String lines;
		System.out.println("Chao mung cac vi den voi phan mem quan ly sinh vien");
		UserDAO ud = new UserDAO();
		SVDAO svDAO = new SVDAO();
		// quan ly 2 thang trong

		abc: while (true) {
			// xu ly dang nhap
			while (true) {
				// doc lenh tu ban phim
				lines = sc.readLine();
				// ngung het vong while
				if ("exit".equalsIgnoreCase(lines))
					break abc;
				// truong hop user
				StringTokenizer token = new StringTokenizer(lines);
				// kiem tra du option cau thanh lenh ko

				if (token.countTokens() != 2) {
					System.out.println("Nhap sai lenh");
					continue;
				}

				lines = token.nextToken();

				if ("user".equalsIgnoreCase(lines)) {
					if (ud.checkUser(token.nextToken())) {
						System.out.println("OK");

					} else {
						System.err.println("failed");
					}
				} else if ("pass".equalsIgnoreCase(lines)) {
					int i = ud.checkLogin(token.nextToken());
					if (i == 0) {
						System.out.println("Dang nhap duoc roi bro");
						break;// ngung dong while dang nhap de vao chuong trinh chinh
					} else if (i == 1) {
						System.out.println("Dang nhap eo duoc ????");
					} else {
						System.out.println("Ban chua nhap user");
					}
				} else {
					System.out.println("Nhap sai lenh!");
					continue;
				}

			}
			// tra cu thong tin sinh vien
			while (true) {
				// doc lenh tu ban phim
				lines = sc.readLine();
				// ngung het vong while
				if ("exit".equalsIgnoreCase(lines))
					break abc;
				// truong hop user
				StringTokenizer token = new StringTokenizer(lines);
				// kiem tra du option cau thanh lenh ko

				if (token.countTokens() != 2) {
					System.out.println("Nhap sai lenh");
					continue;
				}

				lines = token.nextToken();

				if ("findbyname".equalsIgnoreCase(lines)) {
					List<SinhVien> list = svDAO.findByName(token.nextToken());
					for (SinhVien sinhVien : list) {
						System.out.println(sinhVien);
					}
				} else if ("findbyage".equalsIgnoreCase(lines)) {
					List<SinhVien> list = svDAO.findByAge(token.nextToken());
					for (SinhVien sinhVien : list) {
						System.out.println(sinhVien);
					}
				} else if ("findbyscore".equalsIgnoreCase(lines)) {
					List<SinhVien> list = svDAO.findByScore(token.nextToken());
					for (SinhVien sinhVien : list) {
						System.out.println(sinhVien);
					}
				} else {
					System.out.println("Nhap sai lenh");
					continue;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new TraCuu();
	}
}

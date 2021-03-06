package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Dao extends UnicastRemoteObject implements IBank {

	protected Dao() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	User user = null;

	static List<String> list = new ArrayList<>();
	static List<User> listData = new ArrayList<User>();

	public void napDuLieuBanDau() {
		File file = new File("src\\Server\\user.txt");
		BufferedReader br;
		String line;
		if (!file.exists())
			return;
		if (file.isDirectory())
			return;
		if (file.isFile()) {
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				while ((line = br.readLine()) != null) {
					list.add(line);
				}
				list.remove(0);
				for (String string : list) {
					StringTokenizer token = new StringTokenizer(string, "\t");
					User user2 = new User();
					user2.setTen(token.nextToken());
					user2.setMatKhau(token.nextToken());
					user2.setSoTaiKhoan(token.nextToken());
					user2.setSoTien(Double.parseDouble(token.nextToken()));

					listData.add(user2);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int checkUser(String name) {
		
		
		for (User user3 : listData) {
			
			System.out.println(user3.toString());
			
			if (user3.getTen().equalsIgnoreCase(name)) {
				this.user = new User(name, null, null, 0);
				System.out.println("CHECK US");
				return 1;
			}
		}
		this.user = null;
		
		return 0;
	}

	public int checkLogin(String pass) {

		if (user == null) {
			return 2;
		}
		for (User user5 : listData) {
			if (user5.getTen().equalsIgnoreCase(this.user.getTen()) && user5.getMatKhau().contentEquals(pass)) {
				this.user.setMatKhau(pass);
				System.out.println("CHECK LG");
				return 1;
			}
		}
		return 0;
	}

	public int guiTien(int soTien) {
		if (soTien >= 0) {
			for (User userGuiTien : listData) {
				if (userGuiTien.getTen().equalsIgnoreCase(this.user.getTen())
						&& userGuiTien.getMatKhau().equalsIgnoreCase(this.user.getMatKhau())) {
					userGuiTien.setSoTien(userGuiTien.getSoTien() + soTien);
					System.out.println("Tai khoan: " + userGuiTien.getTen()
							+ " gui tien vao tai khoan thanh cong. So tien hien co la: " + userGuiTien.getSoTien());
					return 1;
				}
			}
		}
		return 0;
	}

	public int layTien(int soTien) {
		if (soTien >= 0) {
			for (User userGuiTien : listData) {
				if (userGuiTien.getTen().equalsIgnoreCase(this.user.getTen())
						&& userGuiTien.getMatKhau().equalsIgnoreCase(this.user.getMatKhau())
						&& userGuiTien.getSoTien() >= soTien) {
					userGuiTien.setSoTien(userGuiTien.getSoTien() - soTien);
					System.out.println("Tai khoan: " + userGuiTien.getTen()
							+ " rut tien thanh cong. So tien hien con lai la: " + userGuiTien.getSoTien());
					return 1;
				}
			}
		}
		return 0;
	}

	public int chuyenTien(String stkChuyenDen, int soTien) {

		for (User us2 : listData) {
			if (us2.getTen().equalsIgnoreCase(this.user.getTen())
					&& us2.getMatKhau().equalsIgnoreCase(this.user.getMatKhau()) && us2.getSoTien() >= soTien) {
				us2.setSoTien(us2.getSoTien() - soTien);
				nhanTien(stkChuyenDen, soTien);
				System.out.println("Chuyen tien thanh cong. Tai khoan cua ban con: " + us2.getSoTien());
				return 1;
			}
		}

		return 0;
	}

	public void nhanTien(String stk, int soTien) {
		for (User us : listData) {
			if (us.getSoTaiKhoan().equalsIgnoreCase(stk)) {
				System.out.println("Tai khoan: " + us.getSoTaiKhoan() + " So tien truoc khi nhan: " + us.getSoTien());
				us.setSoTien(us.getSoTien() + soTien);
				System.out.println("Ban moi nhan nhan duoc tien. So tien ban co hien tai la: " + us.getSoTien());
			}
		}
	}

	public boolean isNumber(String number) {
		if (number == null) {
			return false;

		}
		try {
			int money = Integer.parseInt(number);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public String hiClient() {
		return "Chao mung cac ban den voi ngan hang the ky";
	}

}

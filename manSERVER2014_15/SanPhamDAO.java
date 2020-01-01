package manSERVER2014_15;

import java.util.ArrayList;

public class SanPhamDAO {
	public static ArrayList<User> arrUser = arruser();
	public static ArrayList<SanPham> arrSP = arrSP();

	public static ArrayList<User> getArrUser() {
		return arrUser;
	}

	public static void setArrUser(ArrayList<User> arrUser) {
		SanPhamDAO.arrUser = arrUser;
	}

	public static ArrayList<SanPham> getArrSP() {
		return arrSP;
	}

	public static void setArrSP(ArrayList<SanPham> arrSP) {
		SanPhamDAO.arrSP = arrSP;
	}

	public  static ArrayList<User> arruser() {
		ArrayList<User> arr=new ArrayList<>();
		arr.add(new User("man", "1998"));

		return arr;
	}

	public  static ArrayList<SanPham> arrSP() {
		ArrayList<SanPham> arr=new ArrayList<>();
		arr.add(new SanPham("123", "iphone 6", 7, 150000));
		arr.add(new SanPham("144", "iphone 5", 3, 250000));
		return arr;
	}

	public static void main(String[] args) {
		System.out.println(arrSP);
	}

}
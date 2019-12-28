package RMI_POP3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl {

	public static ArrayList<User> arruser = new ArrayList<>();

	static {
		nhap();
	}

	private static void nhap() {
		ResultSet rs;
		try {
			rs = new ConnectDTB().chondulieu("select * from user");
			while (rs.next()) {
				arruser.add(new User(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean checkExistUserName(String username) {
		for (User u : arruser) {
			if (u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public static boolean login(String username, String pass) {
		for (User u : arruser) {
			if (u.getUsername().equals(username) && u.getPass().equals(pass)) {
				return true;
			}

		}
		return false;
	}

	public static User get(String username) {
		for (User u : arruser) {
			if (u.getUsername().equals(username)) {
				return u;
			}

		}
		return null;
	}
	public static void main(String[] args) {
		for (User u : arruser) {
			System.out.println(u);
		}
	}
}
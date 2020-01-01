package serverSide.database;

import java.sql.SQLException;

public class AccountDAO {
	public static boolean isUserExist(String username) {
		String sql = "select * from accounts where username = '" + username + "'";
		try {
			return DatabaseConnection.excuteQuery(sql).next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean login(String lastUsername, String password) {
		String sql = "select * from accounts where username = '" + lastUsername + "' and password = '" + password + "'";
		try {
			return DatabaseConnection.excuteQuery(sql).next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

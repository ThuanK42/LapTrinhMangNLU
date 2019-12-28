package serverSide.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	private static Connection connection = null;

	private DatabaseConnection() {
	}

	static {
		try {
			connection = getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		return DriverManager.getConnection("jdbc:odbc:database", "", "");
	}

	public static ResultSet excuteQuery(String sql) throws Exception {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
}

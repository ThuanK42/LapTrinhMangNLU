package rmi.model;

import java.sql.*;

public class Connect {
	public static Connection getConnect() {
		Connection res = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			res = DriverManager.getConnection("jdbc:odbc:user");
		} catch (Exception e) {
			System.out.println("error connect");
		}
		return res;
	}

	public static void main(String[] args) {
		Connect.getConnect();
	}
}

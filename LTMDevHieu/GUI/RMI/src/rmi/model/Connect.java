package rmi.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public static Connection getConnection() {
		Connection res = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			res = DriverManager.getConnection("jdbc:odbc:sv");
			System.out.println("Success");
		} catch (Exception e) {
			System.out.println("Error connection");
		}
		return res;
	}
	
	public static void main(String[] args) {
		getConnection();
	}
}

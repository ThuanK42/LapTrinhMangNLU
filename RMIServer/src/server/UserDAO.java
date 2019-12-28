package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO extends UnicastRemoteObject implements IUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	String user;
	static Connection con;

	static {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:quanlysinhvien");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkUser(String name) {
		user = name;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from user where username = '" + name + "'");
			int i = 0;
			while (resultSet.next())
				i++;
			if (i == 1)
				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int checkLogin(String nextToken) {
		if (user == null)
			
			return 2; // chua nhap username va pass
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from user where username = '" + user + "' and pass = '" + nextToken + "'");
			int i = 0;
			while (resultSet.next())
				i++;
			if (i == 1)
				return 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

}

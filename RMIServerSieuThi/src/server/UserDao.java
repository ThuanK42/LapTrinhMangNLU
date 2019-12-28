package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao extends UnicastRemoteObject implements IUser {

	private static final long serialVersionUID = 1L;
	static Connection connection;
	String user;

	static {
		try {
			// thiet lap driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// lay ket noi
			// getconnection lay url den database
			connection = DriverManager.getConnection("jdbc:odbc:quanlysieuthi");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected UserDao() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean checkUser(String name) {
// gan bien user o tren = name
		user = name;
		try {
			Statement statement = connection.createStatement();// tao cau sql
			ResultSet rs = statement.executeQuery("select * from user where username = '" + name + "'");
			int i = 0; // so dong tra ve
			while (rs.next()) {
				i++; // tang len
				if (i == 1) {// 1 username tra ve la dung
					return true;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public int checkLogin(String password) {
		if (user == null) {// neu nguoi dung nhap username dung thi no se luu giu username do va khi nhap
							// tiep pass thi no se lay username nhap dung do de di tiep
			return 2;
		}
		int i = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(
					"select * from user where username = '" + user + "' and password = '" + password + "'");
			while (rs.next()) {
				i++;
				if (i == 1) {
					return 1; // dung tra ve 1 (1 user chi 1 pass, 1 useer name)
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // tao cau lenh sql

		return 0;
	}

}

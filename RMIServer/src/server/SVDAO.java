package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SVDAO extends UnicastRemoteObject implements ISinhVien{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SVDAO() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public List<SinhVien> findByName(String name) {
		List<SinhVien> list = new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from sinhvien where name = 'Le Van Thuan'");

			while (resultSet.next())
				list.add(new SinhVien(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getDouble(4)));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<SinhVien> findByAge(String age) {
		List<SinhVien> list = new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from sinhvien where age = " + age + "");

			while (resultSet.next())
				list.add(new SinhVien(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getDouble(4)));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<SinhVien> findByScore(String score) {
		List<SinhVien> list = new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from sinhvien where score = " + score + "");

			while (resultSet.next())
				list.add(new SinhVien(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getDouble(4)));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}

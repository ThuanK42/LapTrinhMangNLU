package RMIServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentImp extends UnicastRemoteObject implements IStudent {
	Connection con;
	String url = "jdbc:odbc:quanlysinhvien";

	protected StudentImp() throws RemoteException {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection(url);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String findByName(String name) throws RemoteException {
		String result = "";
		Statement statement;
		try {
			statement = con.createStatement();
			String sql = "select * from sinhvien where NAME = '" + name + "' ";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				result += "MSSV: " + rs.getInt("ID") + "Name: " + rs.getString("NAME") + "Age: " + rs.getInt("AGE")
						+ "Score: " + rs.getDouble("SCORE") + "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String findByUnderScore(double score) throws RemoteException {
		String result = "";
		Statement statement;
		try {
			statement = con.createStatement();
			String sql = "select * from sinhvien where SCORE < '" + score + "' ";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				result += "MSSV: " + rs.getInt("ID") + "Name: " + rs.getString("NAME") + "Age: " + rs.getInt("AGE")
						+ "Score: " + rs.getDouble("SCORE") + "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String findByUpperScore(double score) throws RemoteException {
		String result = "";
		Statement statement;
		try {
			statement = con.createStatement();
			String sql = "select * from sinhvien where SCORE >= '" + score + "' ";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				result += "MSSV: " + rs.getInt("ID") + "Name: " + rs.getString("NAME") + "Age: " + rs.getInt("AGE")
						+ "Score: " + rs.getDouble("SCORE") + "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String findByUnderAge(int age) throws RemoteException {
		String result = "";
		Statement statement;
		try {
			statement = con.createStatement();
			String sql = "select * from sinhvien where AGE < '" + age + "' ";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				result += "MSSV: " + rs.getInt("ID") + "Name: " + rs.getString("NAME") + "Age: " + rs.getInt("AGE")
						+ "Score: " + rs.getDouble("SCORE") + "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String findByUpperAge(int age) throws RemoteException {
		String result = "";
		Statement statement;
		try {
			statement = con.createStatement();
			String sql = "select * from sinhvien where AGE >= '" + age + "' ";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				result += "MSSV: " + rs.getInt("ID") + "Name: " + rs.getString("NAME") + "Age: " + rs.getInt("AGE")
						+ "Score: " + rs.getDouble("SCORE") + "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}

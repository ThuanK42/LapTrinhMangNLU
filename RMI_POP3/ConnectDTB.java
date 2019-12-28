package RMI_POP3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDTB {
	public static Connection getConnect() {
		Connection con = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String sql = "jdbc:odbc:mang2019";
			try {
				con = DriverManager.getConnection(sql);
				System.out.println("tc");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("false");
		}
		return con;

	}

	public void thucthicaulenh(String s) throws SQLException {
		Connection con = getConnect();
		Statement sta = con.createStatement();
		sta.executeUpdate(s);

	}

	public ResultSet chondulieu(String sql) throws SQLException {
		Connection con = getConnect();
		Statement sta = con.createStatement();
		ResultSet rs = sta.executeQuery(sql);
		return rs;
	}

	public static void main(String[] args) {
		getConnect();
	}
}
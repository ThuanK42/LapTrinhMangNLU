package Chuong3.Bai3_3;

import java.sql.*;

public class GetStudent {
	Connection con;
	Statement st;

	public GetStudent() {
		String path = System.getProperty("user.dir");
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// con =
			// DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="
			// + path + "\\SV.mdb");
			con = DriverManager.getConnection("jdbc:odbc:OdbcTHLTM");
			st = con.createStatement();
		} catch (Exception e) {
			System.out.println("Khong mo duoc CSDL. Chuong trinh se thoat");
			System.exit(0);
		}
	}

	public void Get() {
		try {
			String sql = "select * from Students";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				String age = rs.getString("Age");
				System.out.println(id + "\t" + name + "\t" + age);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (con != null) {
				try {
					st.close();
					con.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public static void main(String[] args) {
		GetStudent O = new GetStudent();
		O.Get();
	}
}

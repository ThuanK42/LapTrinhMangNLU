package Chuong3.Bai3_3;

import java.sql.*;
import java.util.Scanner;

public class AddStudent {
	Connection con;
	Statement st;
	int rs;
	String name, age;

	public AddStudent() {
		String path = System.getProperty("user.dir");
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// con =
			// DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="
			// + path + "\\SV.mdb");
			con = DriverManager.getConnection("jdbc:odbc:OdbcTHLTM");
			st = con.createStatement();
		} catch (Exception e) {
			System.out.println("Khong mo duoc CSDL. Chuong Trinh se thoat");
			System.exit(0);
		}
	}

	public void Add() {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Input Name: ");
			name = input.nextLine();
			System.out.println("Input Age: ");
			age = input.nextLine();
			String sql = "INSERT INTO Students([Name],[Age]) VALUES('" + name
					+ "'," + age + ")";
			rs = st.executeUpdate(sql);
			System.out.println("Insert Successfull: " + name + "\t" + age
					+ " tuoi\n");
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
		AddStudent O = new AddStudent();
		O.Add();
	}
}

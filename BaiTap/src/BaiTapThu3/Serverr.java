package BaiTapThu3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Serverr {

	private ArrayList<BenhNhan> loadData() {
		ArrayList<BenhNhan> list = new ArrayList<BenhNhan>();

		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String db = "src\\BaiTapThu3\\quanlybenhnhan.mdb";
			String dbUrl = "jdbc:ucanaccess://" + db;
			String sql = "select * from benhnhan";

			Connection connection = DriverManager.getConnection(dbUrl);
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareCall(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String ten = rs.getString("ten");
				String loaiBenh = rs.getString("loaibenh");
				double vienPhi = rs.getDouble("vienphi");
				BenhNhan bn = new BenhNhan(ten, loaiBenh, vienPhi);
				list.add(bn);

			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public String xemTienVienPhi(String name) {
		ArrayList<BenhNhan> list = loadData();
		String result = "";
		for (BenhNhan benhNhan : list) {
			if (benhNhan.getTen().trim().equalsIgnoreCase(name)) {
				result += benhNhan.getTen() + " no " + benhNhan.getVienPhi();
			}
		}
		return result;
	}

	public boolean dongTienVienPhi(String name, double tienvienphi) {
		boolean result = false;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String db = "src\\BaiTapThu3\\quanlybenhnhan.mdb";
			String dbUrl = "jdbc:ucanaccess://" + db;

			String sql = " update benhnhan set vienphi = 0.0 where ten = 'tran viet son'";

			Connection connection = DriverManager.getConnection(dbUrl);
			PreparedStatement prepare = connection.prepareStatement(sql);
			prepare.executeUpdate();
			connection.close();

			result = true;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String xemBenh(String name) {
		ArrayList<BenhNhan> list = loadData();
		String result = "";
		for (BenhNhan benhNhan : list) {
			if (benhNhan.getTen().trim().equalsIgnoreCase(name)) {
				result += benhNhan.getTen() + " benh " + benhNhan.getLoaiBenh();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Serverr serverr = new Serverr();
		try {

			ServerSocket serverSocket = new ServerSocket(9999);
			System.out.println("cho doi nguoi dung ket noi");
			Socket socket = serverSocket.accept();
			System.out.println("da ket noi");

			// lay lenh tu nguoi dung
			DataInputStream fromClient = new DataInputStream(socket.getInputStream());
			// gui ket qua cho nguoi dung
			DataOutputStream sendClient = new DataOutputStream(socket.getOutputStream());

			while (true) {

				String caulenh = fromClient.readUTF();
				String[] phantich = caulenh.trim().split("\t");
				System.out.println(caulenh);

				if (phantich.length == 2) {
					String ten = phantich[0].trim();
					String lenh = phantich[1].trim();

					if (lenh.equalsIgnoreCase("xemtienvienphi")) {
						String ketqua = serverr.xemTienVienPhi(ten);
						if (ketqua == "") {
							sendClient.writeUTF("Khong tim thay benh nhan nay");
						} else {
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						}
					} else if (lenh.equalsIgnoreCase("xembenh")) {
						String ketqua = serverr.xemBenh(ten);
						if (ketqua == "") {
							sendClient.writeUTF("Khong tim thay benh nhan nay");
						} else {
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						}
					}
				} else if (phantich.length == 3) {
					String ten = phantich[0].trim();
					String lenh = phantich[1].trim();
					String tien = phantich[2].trim();
					if (lenh.equalsIgnoreCase("dongtienvienphi")) {
						boolean kq = serverr.dongTienVienPhi(ten, Double.parseDouble(tien));
						String ketqua = "" + kq;
						if (ketqua == "") {
							sendClient.writeUTF("Khong tim thay benh nhan nay");
						} else {
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						}
					}
				} else if (phantich.length == 1) {
					String lenh2 = phantich[0];
					if (lenh2.equalsIgnoreCase("EXIT")) {
						sendClient.close();
						serverSocket.close();

					}
				} else {
					System.out.println("câu lệnh sai");
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
package KetNoiDuLieu;

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

public class ServerProcess {

	// load du lieu
	public ArrayList<SinhVien> loadDataAccess() {
		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		// connect
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String db = "src\\KetNoiDuLieu\\quanlysinhvien.mdb";
			String dbUrl = "jdbc:ucanaccess://" + db;
			String sql = "select * from sinhvien";

			Connection connection = DriverManager.getConnection(dbUrl);
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareCall(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				double score = rs.getDouble("score");
				SinhVien sv = new SinhVien(id, name, age, score);
				list.add(sv);

			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	// tim = ten

	public String findByName(String name) {
		String result = "";
		ArrayList<SinhVien> listSV;
		listSV = loadDataAccess();
		for (SinhVien sv : listSV) {
			if (compareName(sv.getName(), name)) {
				result += sv + "\n";

			}
		}
		return result;
	}

	public boolean compareName(String name, String name2) {
		boolean result = false;
		String[] arrName = name.trim().split(" ");
		name2 = name2.trim();
		if (arrName[arrName.length - 1].equalsIgnoreCase(name2))
			result = true;
		return result;
	}

	// tim bang < score
	public String findByUnderScore(double score) {
		String result = "";
		ArrayList<SinhVien> listSV;
		listSV = loadDataAccess();
		for (SinhVien sv : listSV) {
			if (sv.getScore() < score) {
				result += sv + "\n";
			}
		}
		return result;
	}

	// tim bang > score
	public String findByUpperScore(double score) {
		String result = "";
		ArrayList<SinhVien> listSV;
		listSV = loadDataAccess();
		for (SinhVien sv : listSV) {
			if (sv.getScore() > score) {
				result += sv + "\n";
			}
		}
		return result;
	}

	// tim bang < age
	public String findByUnderAge(int age) {
		String result = "";
		ArrayList<SinhVien> listSV;
		listSV = loadDataAccess();
		for (SinhVien sv : listSV) {
			if (sv.getAge() <= age) {
				result += sv + "\n";
			}
		}
		return result;
	}

	// tim bang > age
	public String findByUpperAge(int age) {
		String result = "";
		ArrayList<SinhVien> listSV;
		listSV = loadDataAccess();
		for (SinhVien sv : listSV) {
			if (sv.getAge() > age) {
				result += sv + "\n";
			}
		}
		return result;
	}

	public static void main(String[] args) {
		ServerProcess sp = new ServerProcess();
		try {

			ServerSocket serverSocket = new ServerSocket(9999);// tao cong
			System.out.println("waiting for connection client");
			Socket socket = serverSocket.accept();// cho cac client dung cong nay
			System.out.println("Accept connection from Client");

			// tao luong gui va luong nhan
			// ket qua gui cho client
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			// nhan lenh tu client
			DataInputStream in = new DataInputStream(socket.getInputStream());

			while (true) {
				// doc lenh tu client
				String readClient = in.readUTF();
				// phan tich dong lenh
				String arr[] = readClient.split("\t");
				String comm = arr[0];// lenh
				String value = arr[1];// gia tri

				// xu ly tung yeu cau

				if (comm.equalsIgnoreCase("findByName")) {
					String result = sp.findByName(value);
					if (result == "") {
						out.writeUTF("Khong tim thay ten");
					} else {
						out.writeUTF(result);
						out.flush();
					}
				} else if (comm.equalsIgnoreCase("findByUnderScore")) {
					String result = sp.findByUnderScore(Double.parseDouble(value));
					if (result == "") {
						out.writeUTF("Khong tim thay danh sach");
					} else {
						out.writeUTF(result);
						out.flush();
					}
				} else if (comm.equalsIgnoreCase("findByUpperScore")) {
					String result = sp.findByUpperScore(Double.parseDouble(value));
					if (result == "") {
						out.writeUTF("Khong tim thay danh sach");
					} else {
						out.writeUTF(result);
						out.flush();
					}
				} else if (comm.equalsIgnoreCase("findByUnderAge")) {
					String result = sp.findByUnderAge(Integer.parseInt(value));
					if (result == "") {
						out.writeUTF("Khong tim thay danh sach");
					} else {
						out.writeUTF(result);
						out.flush();
					}
				} else if (comm.equalsIgnoreCase("findByUpperAge")) {
					String result = sp.findByUpperAge(Integer.parseInt(value));
					if (result == "") {
						out.writeUTF("Khong tim thay danh sach");
					} else {
						out.writeUTF(result);
						out.flush();
					}
				}
//					else if (comm.equalsIgnoreCase("EXIT")) {
//					break;
//				} 
				else {
					out.writeUTF("Incorrect syntax");
				}
			}

//			out.close();
//			in.close();
			// serverSocket.close();

		} catch (IOException e) {
			// TODO: handle exception
		}

	}
}

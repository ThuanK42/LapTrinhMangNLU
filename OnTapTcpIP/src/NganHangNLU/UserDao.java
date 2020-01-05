package NganHangNLU;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class UserDao {

	User user = null;
	static List<User> listUser = new ArrayList<>();
	static List<User> listUserAccess = new ArrayList<>();
	String pathFileUser = "src\\NganHangNLU\\user.txt";
	String pathFileHistoryTransaction = "src\\NganHangNLU\\lichsugiaodich.txt";
	String pathFileHistoryLogin = "src\\NganHangNLU\\lichsudangnhap.txt";
	PrintWriter pwTransaction;
	PrintWriter pwLogin;
	static Connection connection;
	static {
		// muon co 1 ket noi can nhung thu gi ?
		// thu nhat link toi file, url, thiet lap ket noi drive, lay ket noi
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); // khai bao
			connection = DriverManager.getConnection("jdbc:odbc:quanlynganhang"); // chuyen no den database

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Date today = new Date(System.currentTimeMillis());
	SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");

	public UserDao() throws IOException {
		//loadDataFromFileAccess();
		loadDataFromTextFile();
		pwTransaction = new PrintWriter(new FileOutputStream(pathFileHistoryTransaction), true);
		pwTransaction.println("Thoi gian \t Tai khoan \t Hoat dong \t So tien \t");
		pwLogin = new PrintWriter(new FileOutputStream(pathFileHistoryLogin), true);
		pwLogin.println("Thoi gian \t Tai khoan \t Mat khau");
	}

	public void loadDataFromTextFile() throws IOException {
		File file = new File(pathFileUser);
		BufferedReader br;
		String line;
		if (!file.exists() || file.isDirectory() || !file.isFile()) {
			return;
		} else {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			line = br.readLine(); // tieu de
			while ((line = br.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(line, "\t");
				listUser.add(new User(token.nextToken(), token.nextToken(), token.nextToken(),
						Double.parseDouble(token.nextToken())));
			}

			br.close();
		}
	}

	public void loadDataFromFileAccess() {
		try {
			String sql = "select * from user";
			Statement statement = connection.createStatement(); // tao ket noi de thuc thi
			ResultSet rs = statement.executeQuery(sql);// thuc thi
			while (rs.next()) {
				listUserAccess.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// check user theo file text
	public boolean checkUser(String name) {

		if (name == null) {
			return false;
		}

		for (User us : listUser) {
			if (us.getTen().equalsIgnoreCase(name)) {
				this.user = new User(name, null, null, 0);
				return true;
			}
		}
		this.user = null;

		return false;
	}

	// check user theo accesss
	public boolean checkUser2(String name) throws SQLException {

		if (name == null) {
			return false;
		}
		String sql = "select * from user where username = '" + name + "'";
		int count = 0;
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			count++;
		}

		if (count == 1) {
			this.user = new User(name, null, null, 0);
			return true;
		}

		return false;
	}

	// check Login theo file text
	public boolean checkLogin(String pass) {
		if (this.user == null) {
			return false;
		}

		if (pass == null) {
			return false;
		}
		for (User us : listUser) {
			if (us.getTen().equalsIgnoreCase(this.user.getTen()) && us.getMatKhau().equalsIgnoreCase(pass)) {
				this.user.setMatKhau(pass);
				this.pwLogin.println(timeFormat.format(today.getTime()) + "\t" + us.getTen() + "\t" + us.getMatKhau());
				this.pwLogin.flush();
				return true;
			}
		}

		return false;
	}

	// check login = accesss
	public boolean checkLogin2(String pass) throws SQLException {
		if (this.user == null) {
			return false;
		}

		if (pass == null) {
			return false;
		}
		int count = 0;
		String sql = "select * from user where username = '" + this.user.getTen() + "'" + " and password = '" + pass
				+ "'";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			this.user.setMatKhau(pass);
			this.pwLogin.println(timeFormat.format(today.getTime()) + "\t" + rs.getString(1) + "\t" + rs.getString(2));
			this.pwLogin.flush();
			count++;
		}
		if (count == 1) {
			return true;
		}
		return false;
	}

	// gui tien dung file text
	public boolean guiTien(String tienGui) {

		if (this.user == null) {
			return false;
		}

		try {

			Double tien = Double.parseDouble(tienGui);

			for (User us : listUser) {
				if (us.getTen().equalsIgnoreCase(this.user.getTen())
						&& us.getMatKhau().equalsIgnoreCase(this.user.getMatKhau()) && tien >= 0) {
					us.setSoTien(us.getSoTien() + tien);
					this.pwTransaction.println(
							timeFormat.format(today.getTime()) + "\t" + us.getTen() + "\t" + "Gui tien" + "\t" + tien);
					this.pwTransaction.flush();
					return true;
				}
			}

		} catch (NumberFormatException e) {
			// TODO: handle exception
		}

		return false;
	}

	// gui tien dung accesss
	public boolean guiTien2(String tienGui) {

		if (this.user == null) {
			return false;
		}
		try {
			Double tien = Double.parseDouble(tienGui);
			for (User us : listUserAccess) {
				if (us.getTen().equalsIgnoreCase(this.user.getTen())
						&& us.getMatKhau().equalsIgnoreCase(this.user.getMatKhau()) && tien >= 0) {
					double tienHienTai = us.getSoTien() + tien;
					String sql = "update user set sotien = " + tienHienTai + " where username ='" + us.getTen()
							+ "' and password = '" + us.getMatKhau() + "'";
					Statement statement = connection.createStatement();
					int rs = statement.executeUpdate(sql);
					if (rs > 0) {
						this.pwTransaction.println(timeFormat.format(today.getTime()) + "\t" + us.getTen() + "\t"
								+ "Gui tien" + "\t" + tien);
						this.pwTransaction.flush();
						return true;
					}
				} else {
					return false;
				}
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// lay tien dung file text
	public boolean layTien(String soTienRut) {

		if (this.user == null) {
			return false;
		}

		try {
			double tien = Double.parseDouble(soTienRut);
			for (User us : listUser) {
				if (us.getTen().equalsIgnoreCase(this.user.getTen())
						&& us.getMatKhau().equalsIgnoreCase(this.user.getMatKhau()) && tien >= 0
						&& us.getSoTien() >= tien) {
					double tienHienTai = us.getSoTien() - tien;
					us.setSoTien(tienHienTai);
					pwTransaction.println(
							timeFormat.format(today.getTime()) + "\t" + us.getTen() + "\t" + "Rut tien" + "\t" + tien);
					this.pwTransaction.flush();
					return true;
				}
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return false;
	}

	// lay tien dung accesss
	public boolean layTien2(String soTienRut) {

		if (this.user == null) {
			return false;
		}

		try {
			double tien = Double.parseDouble(soTienRut);
			for (User us : listUserAccess) {
				if (us.getTen().equalsIgnoreCase(this.user.getTen())
						&& us.getMatKhau().equalsIgnoreCase(this.user.getMatKhau()) && tien >= 0
						&& us.getSoTien() >= tien) {
					double tienHienTai = us.getSoTien() - tien;

					String sql = "update user set sotien = " + tienHienTai + "where username='" + this.user.getTen()
							+ "' and password ='" + this.user.getMatKhau() + "'";

					Statement statement = connection.createStatement();
					int rs = statement.executeUpdate(sql);
					if (rs > 0) {
						pwTransaction.println(timeFormat.format(today.getTime()) + "\t" + us.getTen() + "\t"
								+ "Rut tien" + "\t" + tien);
						pwTransaction.flush();
						return true;
					}

					return true;
				}
			}
		} catch (NumberFormatException e) {
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// chuyen tien = text con acesss tuong tu
	public boolean chuyenTien(String stkchuyenden, String sotien) {

		if (this.user == null) {
			return false;
		}

		if (stkchuyenden == null) {
			return false;
		}

		try {
			Double tien = Double.parseDouble(sotien);
			for (User us : listUser) {
				if (us.getTen().equalsIgnoreCase(this.user.getTen())
						&& us.getMatKhau().equalsIgnoreCase(this.user.getMatKhau()) && us.getSoTien() >= tien) {
					double soTienHienTai = us.getSoTien() - tien;
					us.setSoTien(soTienHienTai);
					setSoTienTaiKhoanChuyenDen(stkchuyenden, sotien);
					pwTransaction.println(timeFormat.format(today.getTime()) + "\t" + us.getTen() + "\t"
							+ "Chuyen tien den tai khoan " + stkchuyenden + "\t" + tien);
					pwTransaction.flush();

					return true;
				}
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return false;
	}

	public void setSoTienTaiKhoanChuyenDen(String stkchuyenden, String sotien) {
		if (stkchuyenden == null) {
			return;
		}
		for (User us : listUser) {
			if (us.getSoTaiKhoan().equalsIgnoreCase(stkchuyenden)) {
				Double tien = Double.parseDouble(sotien);
				double tienHienTai = us.getSoTien() + tien;
				us.setSoTien(tienHienTai);
			}
		}
	}

	public void historyTransaction() throws IOException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File("src\\NganHangNLU\\TienTungTaiKhoanHienTai.txt")));
		pw.println("Thay doi so tien trong cac tai khoan hien tai");
		pw.println("Tên tài khoản \t \t Mật khẩu \t\t Số tài khoản \t \t Số tiền hiện có");
		for (User us : listUser) {
			pw.println(us.getTen() + "\t\t" + us.getMatKhau() + "\t\t" + us.getSoTaiKhoan() + "\t\t" + us.getSoTien());
		}
		pw.close();
	}

//	public static void main(String[] args) throws IOException, SQLException {
//		UserDao ud = new UserDao();
//
//		// check ham dung accesss
////		ud.loadDataFromFileAccess();
////		System.out.println(ud.checkUser2("admin"));
////		System.out.println(ud.checkLogin2("1234"));
////		System.out.println(ud.guiTien2("121212"));
////		System.out.println(ud.guiTien2("123232"));
////		System.out.println(ud.guiTien2("1435345"));
//
//		// check ham dung file
//		ud.loadDataFromTextFile();
//		System.out.println(ud.checkUser("admin"));
//		System.out.println(ud.checkLogin("123"));
//		System.out.println(ud.guiTien("121212.32"));
//		System.out.println(ud.layTien("1212.32"));
//		System.out.println(ud.chuyenTien("4567", "100000"));
//		ud.historyTransaction();
//	}

}

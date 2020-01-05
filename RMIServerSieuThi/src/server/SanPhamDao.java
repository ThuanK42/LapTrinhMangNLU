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

public class SanPhamDao extends UnicastRemoteObject implements ISanPham {

	private static final long serialVersionUID = 1L;
	static Connection connection;

	static {
		try {
			// thiet lap driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// tao ket noi toi url chua database
			connection = DriverManager.getConnection("jdbc:odbc:quanlysieuthi");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected SanPhamDao() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public int addProduct(int id, String name, int soluong, int giaban)throws RemoteException {
		int i = 0;
		try {

			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate(
					"insert into product values (" + id + ",'" + name + "'," + soluong + "," + giaban + ")");
			while (rs>0) {
				i++;
				if (i == 1) {
					return 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public int deleteSPById(int idsp) {
		int i = 0;
		try {

			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate("delete from product where idsp = " + idsp);
			while (rs>0) {
				i++;
				if (i == 1) {
					return 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public int editProduct(int idsp, String tensp, int soluong, int giaban) {
		int i = 0;
		try {

			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate("update product set tensanpham = '" + tensp
					+ "', soluong=" + soluong + ", giaban =" + giaban + " where idsp = " + idsp);
			while (rs>0) {
				i++;
				if (i == 1) {
					return 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public List<SanPham> searchProductByName(String tensp) {
		List<SanPham> listSanPham = new ArrayList<SanPham>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from product where tensanpham like '%" + tensp + "%'");
			while (rs.next()) {
				listSanPham.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listSanPham;
	}
}

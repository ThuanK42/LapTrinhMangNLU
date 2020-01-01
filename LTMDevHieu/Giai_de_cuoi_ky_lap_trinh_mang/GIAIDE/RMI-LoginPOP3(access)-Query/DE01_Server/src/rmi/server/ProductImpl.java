package rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import rmi.model.Connect;
import rmi.model.SanPham;
import rmi.model.User;

public class ProductImpl extends UnicastRemoteObject implements IProduct {
	private static final long serialVersionUID = 1L;
	User user;

	protected ProductImpl() throws RemoteException {
		super();
		user = null;
	}

	@Override
	public String getWelcome() throws RemoteException {
		return "WELCOME TO MANAGE PRODUCT SYSTEM";
	}

	@Override
	public boolean add(int idsp, String ten_san_pham, int so_luong, double gia_ban) throws RemoteException {
		try {
			Connection connect = Connect.getConnect();
			PreparedStatement statement = connect.prepareStatement("insert into sanpham values(?,?,?,?)");
			statement.setInt(1, idsp);
			statement.setString(2, ten_san_pham);
			statement.setInt(3, so_luong);
			statement.setDouble(4, gia_ban);
			statement.executeUpdate();
			statement.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean remove(int idsp) throws RemoteException {
		try {
			Connection connect = Connect.getConnect();
			PreparedStatement statement = connect.prepareStatement("delete from sanpham where idsp = ?");
			statement.setInt(1, idsp);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean edit(int idsp, String ten_san_pham, int so_luong, double gia_ban) throws RemoteException {
		try {
			Connection connect = Connect.getConnect();
			PreparedStatement statement = connect
					.prepareStatement("update sanpham set name = ?, count = ?, price = ? where idsp = ?");
			statement.setString(1, ten_san_pham);
			statement.setInt(2, so_luong);
			statement.setDouble(3, gia_ban);
			statement.setInt(4, idsp);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean checkUserName(String username) throws RemoteException {
		try {
			Connection connect = Connect.getConnect();
			PreparedStatement statement = connect.prepareStatement("select username from user");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				if (result.getString("username").equals(username)) {
					user = new User(username, null);
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkPass(String pass) throws RemoteException {
		if(user == null) {
			throw new RemoteException("enter username first");
		}
		try {
			Connection connect = Connect.getConnect();
			PreparedStatement statement = connect.prepareStatement("select username, password from user");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				if (result.getString("username").equals(user.getUsername())
						&& result.getString("password").equals(pass)) {
					user.setPass(pass);
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String view(String ten_san_pham) throws RemoteException {
		String res = "";
		try {
			Connection connect = Connect.getConnect();
			PreparedStatement statement = connect
					.prepareStatement("select idsp, name, count, price from sanpham where name = ?");
			statement.setString(1, ten_san_pham);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				res += result.getInt("idsp") + ", " + result.getString("name") + ", " + result.getInt("count") + ", "
						+ result.getDouble("price") + "\n";

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static void main(String[] args) throws RemoteException {
		ProductImpl p = new ProductImpl();
		System.out.println(p.view("SNACK"));
	}
}

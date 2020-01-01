package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDao extends UnicastRemoteObject implements ISanPham {

	protected SanPhamDao() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static List<SanPham> listSP;

	static {
		listSP = new ArrayList<SanPham>();
		listSP.add(new SanPham(1, "nokia", 213, 32432423));
		listSP.add(new SanPham(2, "samsung", 213, 32432423));
		listSP.add(new SanPham(3, "mottorola", 213, 32432423));
		listSP.add(new SanPham(4, "iphone", 213, 32432423));
		listSP.add(new SanPham(5, "huawei", 213, 32432423));
		listSP.add(new SanPham(6, "xiaomi", 213, 32432423));
		listSP.add(new SanPham(111, "nokia", 213, 32432423));
		listSP.add(new SanPham(112, "nokia", 213, 32432423));
		listSP.add(new SanPham(1113, "nokia", 213, 32432423));
		listSP.add(new SanPham(1114, "nokia", 213, 32432423));
		listSP.add(new SanPham(1113, "nokia", 213, 32432423));

	}

	public int addProduct(int idsp, String ten_san_pham, int so_luong, int gia_ban) {
		if (idsp > 0 || so_luong >= 0 || gia_ban >= 0) {
			listSP.add(new SanPham(idsp, ten_san_pham, so_luong, gia_ban));
			return 1;
		}
		return 0;
	}

	public int editProduct(int idsp, String ten_san_pham, int so_luong, int gia_ban) {
		if (idsp > 0 || so_luong >= 0 || gia_ban >= 0) {
			for (SanPham sanPham : listSP) {

				if (sanPham.getIdsp() == idsp) {
					sanPham.setTen_san_pham(ten_san_pham);
					sanPham.setSo_luong(so_luong);
					sanPham.setGia_ban(gia_ban);
				}
			}
			return 1;
		}
		return 0;
	}

	public int deleteProduct(int idsp) {
		if (idsp > 0) {
			for (int i = 0; i < listSP.size(); i++) {
				if (listSP.get(i).getIdsp() == idsp) {
					listSP.remove(listSP.get(i).getIdsp());
				}
			}
			return 1;
		}
		return 0;
	}

	public void viewSearch(String name) {
		for (int i = 0; i < listSP.size(); i++) {
			if (listSP.get(i).getTen_san_pham().equalsIgnoreCase(name)) {
				System.out.println(listSP.get(i).toString());
			}
		}
	}

	public static void showList() {
		for (SanPham sanPham : listSP) {
			System.out.println(sanPham.toString());
		}
	}

}

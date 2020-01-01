package quanlyxemphim;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerProcess {
	private static final int port = 9999;

	public ArrayList<Phim> loadData() {
		ArrayList<Phim> list = new ArrayList<Phim>();
		list.add(new Phim(new Date(1, 1, 2000), "111", "121", 12.45, 45));
		list.add(new Phim(new Date(1, 2, 2000), "191", "101", 30.45, 46));
		list.add(new Phim(new Date(1, 3, 2000), "181", "131", 42.45, 40));
		list.add(new Phim(new Date(1, 4, 2000), "171", "141", 52.45, 41));
		list.add(new Phim(new Date(1, 5, 2000), "161", "151", 62.45, 42));
		return list;
	}

	public String xemDonGia(String tenPhim) {
		ArrayList<Phim> list = loadData();
		String result = "";
		for (Phim phim : list) {
			if (phim.getMaPhim().trim().equalsIgnoreCase(tenPhim)) {
				result += "Ma phim: " + phim.getMaPhim() + " don gia: " + phim.getDonGia();
			}
		}
		return result;
	}

	public String xemSoCho(String tenPhim) {
		ArrayList<Phim> list = loadData();
		String result = "";
		for (Phim phim : list) {
			if (phim.getMaPhim().trim().equalsIgnoreCase(tenPhim)) {
				result += "Ma phim: " + phim.getMaPhim() + " so cho da dat: " + phim.getSoCho();
			}
		}
		return result;
	}

	public String xemthanhToan(String maKH) {
		ArrayList<Phim> list = loadData();
		String result = "";
		for (Phim phim : list) {
			if (phim.getMaKhachHang().trim().equalsIgnoreCase(maKH)) {
				result += "Ma khach hang: " + phim.getMaKhachHang() + " so tien thanh toan la: "
						+ phim.getDonGia() * phim.getSoCho();
			}
		}
		return result;

	}

	public String thanhToan(String maKH, double tien) {
		ArrayList<Phim> list = loadData();
		String result = "";
		for (Phim phim : list) {
			if (phim.getMaKhachHang().trim().equalsIgnoreCase(maKH) && tien == phim.getSoCho() * phim.getDonGia()) {
				result += "Ma khach hang: " + phim.getMaKhachHang() + " ma phim: " + phim.getMaPhim() + " so cho dat:"
						+ phim.getSoCho() + " da thanh toan " + phim.getSoCho() * phim.getDonGia();

			}
		}
		return result;

	}

	public String xemPhimChieu(int month) {
		ArrayList<Phim> list = loadData();
		String result = "";
		for (Phim phim : list) {
			if (phim.getNgayChieu().getMonth() == month) {
				result += "ma phim: " + phim.getMaPhim() + phim.getNgayChieu() + " don gia: " + phim.getDonGia()
						+ " so cho :" + phim.getSoCho();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		ServerProcess sp = new ServerProcess();

		try {
			// cho ket noi tu client
			System.out.println("cho ket noi tu client");
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("client da ket noi");
			Socket socket = serverSocket.accept();

			// nhan lenh tu client
			DataInputStream fromClient = new DataInputStream(socket.getInputStream());
			// gui di cho client
			DataOutputStream sendClient = new DataOutputStream(socket.getOutputStream());

			String lenh;
			String giaTri;
			double tien;
			String ketqua;

			while (true) {
				String fromClient2 = fromClient.readUTF();
				String[] phanTichCauLenh = fromClient2.trim().split(" ");

				if (phanTichCauLenh.length == 2) {
					lenh = phanTichCauLenh[0];
					giaTri = phanTichCauLenh[1];
					if (lenh.trim().equalsIgnoreCase("xemphimchieu")) {
						ketqua = sp.xemPhimChieu(Integer.parseInt(giaTri));
						if (ketqua == null) {
							ketqua = "Khong tim thay phim trong thang nay";
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						} else {
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						}

					} else if (lenh.trim().equalsIgnoreCase("xemsocho")) {
						ketqua = sp.xemSoCho(giaTri);
						if (ketqua == null) {
							ketqua = "Khong tim thay phim nay";
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						} else {
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						}
					} else if (lenh.trim().equalsIgnoreCase("xemdongia")) {
						ketqua = sp.xemDonGia(giaTri);
						if (ketqua == null) {
							ketqua = "Khong tim thay phim";
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						} else {
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						}
					} else if (lenh.trim().equalsIgnoreCase("xemthanhtien")) {
						ketqua = sp.xemthanhToan(giaTri);
						if (ketqua == null) {
							ketqua = "Khong tim thay ma khach hang";
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						} else {
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						}
					} else {
						sendClient.writeUTF("cau lenh sai");
						sendClient.flush();
					}

				} else if (phanTichCauLenh.length == 3) {
					lenh = phanTichCauLenh[0];
					giaTri = phanTichCauLenh[1];
					tien = Double.parseDouble(phanTichCauLenh[2]);
					if (lenh.trim().equalsIgnoreCase("thanhtoan")) {
						ketqua = sp.thanhToan(giaTri, tien);
						if (ketqua == null) {
							ketqua = "phim khong tim thay";
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						} else {
							sendClient.writeUTF(ketqua);
							sendClient.flush();
						}

					}
				} else {
					sendClient.writeUTF("cau lenh sai");
					sendClient.flush();
				}
			}

		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

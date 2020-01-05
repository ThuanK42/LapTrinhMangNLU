
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UserDao extends UnicastRemoteObject implements IUser {

	protected UserDao() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	public static String fileName = "src\\Data\\info.dat";
	public static String folderNameImage = "C:\\Users\\ACER\\Desktop\\LapTrinhMangNLU\\RMIServerChupAnh\\src\\Data\\";
	public static List<TreEm> listData = new ArrayList<TreEm>();
	TreEm te = null;

	public String getBanner() {

		return "welcome to Registration";
	}

	public String dangKy(String ten, String ngaySinh, String noiCuTru) throws RemoteException {
		String maSo = null;
		if (ngaySinh != null) {
			int namSinh = Integer.parseInt(ngaySinh.substring(ngaySinh.length() - 4, ngaySinh.length()));
			int tuoi = 2019 - namSinh;
			if (tuoi <= 6) {
				if (ten != null && noiCuTru != null && isNumeric(ten) == false && isNumeric(noiCuTru) == false) {
					StringTokenizer token = new StringTokenizer(ngaySinh, "/");
					// maSo = taoMaSo();
					maSo = "MS00" + listData.size() + 1;
					listData.add(new TreEm(
							maSo, ten, new NgaySinh(Integer.parseInt(token.nextToken()),
									Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken())),
							noiCuTru));

					this.te = new TreEm(maSo, null, null, null);
					return maSo;
				}
			}
			this.te = null;
		}

		return maSo;
	}

	public String guiAnh(String pathFileImage) throws RemoteException {
		String ketQua = null;

		if (this.te == null) {
			return ketQua = "Nguoi gui anh khong dung";
		}

		String xuLyLinkAnh = pathFileImage.substring(4, pathFileImage.length());
		String maSoAnh = xuLyLinkAnh.substring(0, xuLyLinkAnh.length() - 4);

		for (TreEm treEm : listData) {

			// ma so anh = ma so tre em trong danh sach
			// ma so tre em trong danh sach = ma so cua user dang dang ky

			if (treEm.getMs().equalsIgnoreCase(maSoAnh) && treEm.getMs().equalsIgnoreCase(this.te.getMs())) {
				// up load anh ?

				try {
					uploadAnh(pathFileImage, folderNameImage + treEm.getMs() + ".jpg");
					return ketQua = "upload anh thanh cong";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return ketQua;
	}

	public void luuDulieu() throws RemoteException {
		System.out.println(listData.toString());

		File file = new File(fileName);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}

		DataOutputStream dos;
		try {
			dos = new DataOutputStream(new FileOutputStream(file));
			dos.writeInt(listData.size());
			for (TreEm treEm : listData) {
				
				dos.writeUTF(treEm.getMs());
				dos.writeUTF(treEm.getTen());
				String ngaySinh = treEm.getNgaySinh().getNgay() + "/" + treEm.getNgaySinh().getThang() + "/"
						+ treEm.getNgaySinh().getNam();
				dos.writeUTF(ngaySinh);
				dos.writeUTF(treEm.getNoiCuTru());
			}
			dos.flush();
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String viewInfo(String maSo) {
		String ketQua = "khong tim thay";
		for (TreEm treEm : listData) {
			if (treEm.getMs().equalsIgnoreCase(maSo)) {
				ketQua = treEm.toString();
			}
		}
		return ketQua;
	}

	public boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		try {
			int so = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

//	public String taoMaSo() {
//		return "MS00" + listData.size() + 1;
//	}

	public String uploadAnh(String src, String dest) throws IOException {
		
		File fileNguon = new File(src);
		File fileDich = new File(dest);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		if (!fileNguon.exists() || fileNguon.isDirectory()) {
			return "Link anh khong ton tai";
		} else {
			bis = new BufferedInputStream(new FileInputStream(fileNguon));
			bos = new BufferedOutputStream(new FileOutputStream(fileDich));
			byte[] buffer = new byte[10 * 1024];
			int length;
			while ((length = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, length);
			}
			bis.close();
			bos.close();
			return "Upload anh thanh cong";
		}

	}

//	public static void main(String[] args) throws IOException {
//		UserDao ud = new UserDao();
//		System.out.println(ud.dangKy("Le thi le huyen2", "12/01/2014", "nha trang"));
//		System.out.println(ud.dangKy("Le thi le huyen3", "12/01/2014", "nha trang"));
//		System.out.println(ud.dangKy("Le thi le huyen4", "12/01/2014", "nha trang"));
//		System.out.println(ud.dangKy("Le thi le huyen5", "12/01/2014", "nha trang"));
//		System.out.println(ud.dangKy("Le thi le huyen6", "12/01/2014", "nha trang"));
//		ud.luuDulieu();
//
//		// System.out.println(ud.viewInfo("MS0001"));
//		// System.out.println(ud.guiAnh("src\\MS0001.jpg"));
////		System.out.println(ud.uploadAnh("src\\MS0001.jpg", "src\\Data\\MS0001.jpg"));
//
//	}

}

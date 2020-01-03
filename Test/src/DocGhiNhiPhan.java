import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DocGhiNhiPhan {

	public static String file = "src\\info.dat";

	public static List<TreEm> listData = new ArrayList<TreEm>();

	public void ghiFile(List<TreEm> list) throws IOException {

		File file2 = new File(file);
		if (file2.isDirectory())
			return;
		if (!file2.exists())
			return;
		if (file2.isFile() && file2.exists()) {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(file2));
			dos.writeInt(list.size());// ghi so luong bao nhieu
			for (TreEm em : list) {
				dos.writeUTF(em.getMs());
				dos.writeUTF(em.getTen());
				String ngaySinh = em.getNgaySinh().getNgay() + "/" + em.getNgaySinh().getThang() + "/"
						+ em.getNgaySinh().getNam();
				dos.writeUTF(ngaySinh);
				dos.writeUTF(em.getNoiCuTru());
			}
			dos.flush();
			dos.close();
		}

	}

	public void loadData() throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream(new File(file)));
		int soLuongData = dis.readInt();// doi xung ben tren ghi bao nhieu doi tuong
		ArrayList<TreEm> listTreEm = new ArrayList<TreEm>();// load du lieu len cho no vao mang
		for (int i = 0; i < soLuongData; i++) {
			String ms = dis.readUTF();
			String ten = dis.readUTF();
			String ngaySinh = dis.readUTF();
			String noiCuTru = dis.readUTF();
			StringTokenizer token = new StringTokenizer(ngaySinh, "/");
			listTreEm.add(new TreEm(ms, ten, new NgaySinh(Integer.parseInt(token.nextToken()),
					Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken())), noiCuTru));
		}

		for (TreEm treEm : listTreEm) {
			System.out.println(treEm.toString());
		}
		dis.close();

	}

	public static void main(String[] args) throws IOException {
		List<TreEm> listData = new ArrayList<TreEm>();
		listData.add(new TreEm("MS001", "Nguyen Van Quang", new NgaySinh(01, 01, 2013), "Binh Dinh"));
		listData.add(new TreEm("MS002", "Le Thi Le Huyen", new NgaySinh(02, 01, 2013), "Nha Trang"));
		listData.add(new TreEm("MS003", "Ho Thi My Trang", new NgaySinh(03, 01, 2013), "Nha Trang"));
		listData.add(new TreEm("MS004", "Dong Hoi", new NgaySinh(04, 01, 2013), "Bo Tay"));
		listData.add(new TreEm("MS005", "Tran Viet Son", new NgaySinh(05, 01, 2013), "Thanh Hoa"));
		listData.add(new TreEm("MS006", "To Thanh Sang", new NgaySinh(06, 01, 2013), "Phu Yen"));

		DocGhiNhiPhan dg = new DocGhiNhiPhan();
		dg.ghiFile(listData);
		dg.loadData();

	}

}

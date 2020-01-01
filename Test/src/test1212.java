import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class test1212 {

	private ArrayList<nhanvien> initData() {
		ArrayList<nhanvien> listnv = new ArrayList<nhanvien>();
		listnv.add(new nhanvien("ms001", "A", "21/01/1998", "hồ chí minh"));
		listnv.add(new nhanvien("ms002", "A", "21/01/1998", "hồ chí minh"));
		listnv.add(new nhanvien("ms003", "A", "21/01/1998", "hồ chí minh"));
		listnv.add(new nhanvien("ms004", "A", "21/01/1998", "hồ chí minh"));
		listnv.add(new nhanvien("ms005", "A", "21/01/1998", "hồ chí minh"));
		listnv.add(new nhanvien("ms006", "A", "21/01/1998", "hồ chí minh"));
		return listnv;
	}

	public static void createFirstLine(PrintWriter pw, nhanvien nv) {
		// pw.append("Mã số\t \t").append("Họ và tên\t \t").append("Ngày sinh\t
		// \t").append("Cư trú \n");
		pw.println(nv.getMs() + "\t\t" + nv.getHoten() + "\t\t" + nv.getNgaysinh() + "\t\t" + nv.getCutru() + "\n");

	}

	public static void main(String[] args) throws FileNotFoundException {

		test1212 test1212 = new test1212();
		String file = "src\\1211.txt";
//		File f = new File(file);
		PrintWriter pw = new PrintWriter("src\\1211.txt");
		
		pw.println("Mã số\t \t Họ và tên\t \t Ngày sinh\t \t Cư trú \n");
		ArrayList<nhanvien> list = test1212.initData();
		for (int i = 0; i < list.size(); i++) {
			String ms = list.get(i).getMs();
			String tennv = list.get(i).getHoten();
			String ngaysinh = list.get(i).getNgaysinh();
			String cutru = list.get(i).getCutru();
			createFirstLine(pw, new nhanvien(ms, tennv, ngaysinh, cutru));
		}
		pw.close();
	}
}

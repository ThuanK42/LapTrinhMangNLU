package QuanLySieuThi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	static String pathFileProduct = "src\\QuanLySieuThi\\QuanLySieuThi.txt";
	static List<Product> listProduct = new ArrayList<>();
	private PrintWriter printWriter;

	public ProductDao() throws FileNotFoundException {
		printWriter = new PrintWriter(new FileOutputStream(pathFileProduct, true));
	}

	// them san pham
	public int addProduct(String idsp, String tensanpham, String soluong, String giaban) {
		if (checkNumeric(idsp, soluong, giaban) == false || tensanpham == null) {
			return 0;
		} else {
			listProduct.add(new Product(Integer.parseInt(idsp), tensanpham, Integer.parseInt(soluong),
					Integer.parseInt(giaban)));
			this.printWriter.println(idsp + "\t" + tensanpham + "\t" + soluong + "\t" + giaban);
			this.printWriter.flush();
			return 1;
		}
	}

	// chinh sua san pham
	public int editProduct(String idsp, String tensanpham, String soluong, String giaban) {

		if (checkNumeric(idsp, soluong, giaban) == false || tensanpham == null) {
			return 0;
		}

		for (Product product : listProduct) {
			if (product.getIdsp() == Integer.parseInt(idsp)) {
				product.setTen_san_pham(tensanpham);
				product.setSo_luong(Integer.parseInt(soluong));
				product.setGia_ban(Integer.parseInt(giaban));
				return 1;
			}
		}
		return 0;

	}

	// xoa danh sach san pham
	public int deleteListProduct(List<String> listId) {

		System.out.println("ham xoa day nay voi list no nua " + listId);
		int count = 0;
		if (listId.size() <= 0) {
			return count;
		}
		// copy listProduct thanh 1 list khac (abc).
//		for (final String string : listId) {
//			System.out.println("so duoc nhap vao : " + string);
//			if (checkNumeric(string)) {
//				listProduct.removeIf(t -> t.getIdsp() == Integer.parseInt(string)
//				// count++;
//				);
//
//			}
//		}

		return count;
	}

	// xem thong tin san pham

	public List<Product> viewInfoProductByName(String name) {
		List<Product> listProductsFind = new ArrayList<>();
		for (Product product : listProduct) {
			if (product.getTen_san_pham().equalsIgnoreCase(name)) {
				listProductsFind.add(product);
			}
		}
		return listProductsFind;

	}

	// check thu dua vao co phai so khong
	private boolean checkNumeric(String idsp, String soluong, String giaban) {
		return checkNumeric(idsp) && checkNumeric(giaban) && checkNumeric(soluong);
	}

	// hoi ngu =...= tham so truyen vao nen la 1
	private boolean checkNumeric(String idsp) {
		try {
			Integer.parseInt(idsp);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws FileNotFoundException {
		ProductDao pd = new ProductDao();

		System.out.println(pd.addProduct("101", "xiaomi2", "121", "7200000"));
		System.out.println(pd.addProduct("102", "xiaomi3", "121", "1200000"));
		System.out.println(pd.addProduct("103", "xiaomi4", "121", "2200000"));
		System.out.println(pd.addProduct("104", "xiaomi5", "121", "3200000"));
//		pd.writeListProduct();

		ArrayList<String> list = new ArrayList<String>();
		list.add("101");
		System.out.println(pd.deleteListProduct(list));

	}
}

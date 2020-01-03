import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.StringTokenizer;

public class Client {
	public static void main(String[] args) throws NotBoundException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;

		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);

		IUser ud = (IUser) reg.lookup("iu");

		abc: while (true) {

			System.out.println(ud.getBanner());

			// while xu ly
			while (true) {

				// lay du lieu tu dong lenh
				line = br.readLine();

				if ("QUIT".equalsIgnoreCase(line)) {
					ud.luuDulieu();
					System.out.println("Tam biet ban");
					break abc;
				}

				StringTokenizer token = new StringTokenizer(line, "|");

				line = token.nextToken();

				if ("REGISTER".equalsIgnoreCase(line)) {
					String maSo = ud.dangKy(token.nextToken(), token.nextToken(), token.nextToken());
					if (maSo == null) {
						System.out.println("Dang ky that bai");
					} else {
						System.out.println("Dang ky thanh cong voi ms du thi la: " + maSo);
					}
				} else if ("SEND_FOTO".equalsIgnoreCase(line)) {
					String guiAnh = ud.guiAnh(token.nextToken());
					System.out.println(guiAnh);
				} else if ("VIEW_INFO".equalsIgnoreCase(line)) {
					String view = ud.viewInfo(token.nextToken());
					System.out.println(view);
				} else {
					System.out.println("cau lenh sai!");
					continue;
				}
			}
		}
	}
}

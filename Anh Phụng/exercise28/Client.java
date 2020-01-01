package exercise28;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	public static void main(String[] args) throws NotBoundException, IOException {
		String sf = "/home/tiensino/Documents/Net-Program-OutCames-Exercise.docx";
		String df = "/home/tiensino/Documents/upload-rmi-client-1.docx";
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1234);
		IUpload upload =  (IUpload) reg.lookup("upload");
//		FileInputStream fis = new FileInputStream(new File(sf));
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(sf)));
		int id = upload.createDestFile(df);
		int length;
		byte[] data = new byte[1024*16];
		while ((length = bis.read(data)) != -1) {
			upload.writeFile(data, length, id);
		}
		bis.close();
		upload.closeDestFile(id);
	}
}

package exercise27;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client2 {
	public static void main(String[] args) throws NotBoundException, IOException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 12345);
		IDownload d = (IDownload) reg.lookup("download");
		
		String sFile = "/home/tiensino/Documents/eclipse-workspace/SourceFolder/test.docx";
		String dFile = "/home/tiensino/Documents/eclipse-workspace/SourceFolder/test3.docx";
		
		int id = d.openFile(sFile);
		byte[] data = new byte[1024];
		FileOutputStream fos = new FileOutputStream(new File(dFile));
		while ((data = d.readData(id) )!= null) {
			fos.write(data);
		}
		d.closeFile(id);
		fos.close();
	}
}

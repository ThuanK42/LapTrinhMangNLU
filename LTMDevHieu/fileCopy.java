package LTM_T3_456;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class fileCopy {
	public static boolean fileCopy(String sFile, String destFile, boolean moved)
			throws IOException {
		if (moved == false) {
			System.out.println("nothing happen");
			return false;
		} else {
			File sf = new File(sFile);
			File df = new File(destFile);
			if (!sf.exists()) {
				return false;
			} else {
				FileInputStream fis = new FileInputStream(sf);
				FileOutputStream fos = new FileOutputStream(df);

				byte[] data = new byte[1024 * 100];
				int byteread;
				while ((byteread = fis.read(data)) != -1) {
					fos.write(data, 0, byteread);
				}
				fis.close();
				fos.close();
			}
			return true;
		}
	}

	public static boolean folderCopy(String sFile, String destFile,
			boolean moved) throws IOException {
		if (moved == false) {
			System.out.println("nothing happen");
			return false;
		} else {
			File sf = new File(sFile);
			File[] lisf = sf.listFiles();
			File df = new File(destFile);
			File[] lidf = df.listFiles();
			if (sf.exists()) {
				if (sf.isDirectory()) {
					if (!df.exists()) {
						df.mkdirs();
					}
					for (int i = 0; i < lisf.length; i++) {

							File sfsub = new File(sf, lisf[i].getName());
							File dfsub = new File(df, lisf[i].getName());
						folderCopy(sfsub.getAbsolutePath(),
								dfsub.getAbsolutePath(), true);
					}
					
				} else {
					FileInputStream fis = new FileInputStream(sf);
					FileOutputStream fos = new FileOutputStream(df);

					byte[] data = new byte[1024 * 100];
					int byteread;
					while ((byteread = fis.read(data)) != -1) {
						fos.write(data, 0, byteread);
					}
					fis.close();
					fos.close();

				}
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		String sFile = "D:\\BAITAP\\baitap\\DesFile";
		String destFile = "D:\\BAITAP\\baitap\\DesFile_copy";
		System.out.println(folderCopy(sFile, destFile, true));

	}
}

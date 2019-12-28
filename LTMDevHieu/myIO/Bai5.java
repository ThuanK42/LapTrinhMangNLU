package myIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Bai5 {
	public static boolean copyFile(String sFile, String destFile, boolean moved) {
		File sourceFile = new File(sFile);
		if (!sourceFile.exists() || sourceFile.isDirectory())
			return false;
		File destinationFile = new File(destFile);
		if (destinationFile.isDirectory())
			return false;
		destinationFile.getParentFile().mkdirs();

		try {
			InputStream input = new BufferedInputStream(new FileInputStream(sFile));
			OutputStream output = new BufferedOutputStream(new FileOutputStream(destFile));

			byte[] data = new byte[1024 * 4];
			int byteToRead;
			while ((byteToRead = input.read(data)) != -1) {
				output.write(data, 0, byteToRead);
			}

			input.close();
			output.close();
		} catch (IOException e) {
			return false;
		}

		if (moved) {
			return sourceFile.delete();
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		String file = "d:\\a.txt";
		String source = "d:\\b.txt";
		System.out.println(copyFile(file, source, true));
	}
}

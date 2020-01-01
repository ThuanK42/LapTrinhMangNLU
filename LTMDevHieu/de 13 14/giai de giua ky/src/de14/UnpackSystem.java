package de14;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UnpackSystem {

	public static boolean unPack(String sFile, String fileName, String dPath) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(sFile, "rw");
		BufferedOutputStream bos;

		while (true) {
			long pos = raf.readLong();
			long fLength = raf.readLong();
			String fName = raf.readUTF();

			if (fName.equals(fileName)) {
				bos = new BufferedOutputStream(new FileOutputStream(dPath + "\\" + fileName));
				for (long i = 0; i < fLength; i++) {
					bos.write(raf.read());
				}

				bos.close();
				raf.close();
				return true;

			} else {
				raf.seek(pos);
			}

			// file cuoi cung se chua vi tri con tro la 0 (con tro cua file dau tien)
			if (pos == 0) {
				System.out.println("khong co file can tim");
				break;
			}

		}
		raf.close();
		return false;

	}

	public static void main(String[] args) throws IOException {
		String sFile = "E:\\Ronaldo\\Messi\\Kaka\\out.pack";
		String fileName = "fifa.txt";
		String dPath = "E:\\Ronaldo\\Messi\\Kaka";
		boolean result = unPack(sFile, fileName, dPath);
		System.out.println(result);
	}
}

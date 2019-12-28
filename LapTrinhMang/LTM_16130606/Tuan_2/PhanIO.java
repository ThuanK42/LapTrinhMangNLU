package Tuan_2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhanIO {
	/*
	 * Bài 5: Viết CT copy/move file dùng byte array kết hợp với BIS, BOS: boolean
	 * fileCopy(String sFile, String destFile,boolean moved)
	 */

	public static boolean fileCopy(String sFile, String destFile, boolean moved) throws IOException {
		File sf = new File(sFile);
		File df = new File(destFile);

		if (!sf.exists()) {
			System.out.println("Path not exist");
			return moved = false;
		} else {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
			int length;
			byte[] data = new byte[1024 * 100];
			while ((length = bis.read(data)) > -1) {
				bos.write(data, 0, length);
			}
			System.out.println("File is copy successful!");
			bis.close();
			bos.close();
			return moved = true;
		}
	}

	public static boolean fileMove(String sFile, String destFile, boolean moved) throws IOException {
		File sf = new File(sFile);
		File df = new File(destFile);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
		int length;
		byte[] data = new byte[1024 * 100];
		if (!sf.exists()) {
			System.out.println("Path not exist");
			return moved = false;
		} else {
			while ((length = bis.read(data)) > -1) {
				bos.write(data, 0, length);
			}
			System.out.println("File is moved successful!");
			bis.close();
			bos.close();
			sf.delete();
			return moved = true;
		}
	}

	/*
	 * Bài 6: Viết CT copy/move thư mục dùng byte array kết hợp với BIS, BOS:
	 * boolean folderCopy(String sFolder, String destFolder, boolean moved);
	 */

	public static boolean folderCopy(String sFile, String destFile, boolean moved) throws IOException {
		File sf = new File(sFile);
		File df = new File(destFile);
		if (!sf.exists()) {
			System.out.println("Duong dan khong ton tai");
			return moved;
		} else {
			if (sf.isDirectory()) {
				// Thu muc dich chua ton tai thi tao
				if (!df.exists()) {
					df.mkdir();
					System.out.println("Thu muc dich duoc tao: " + df);
				}
				String files[] = sf.list();
				for (String file : files) {
					File srcFile = new File(sf, file);
					File desFile = new File(df, file);
					folderCopy(srcFile.getAbsolutePath(), desFile.getAbsolutePath(), moved);
				}
			} else {
				fileCopy(sf.getAbsolutePath(), df.getAbsolutePath(), false);
			}
			return moved = true;
		}
	}

	public static boolean folderMove(String sFile, String destFile, boolean moved) throws IOException {
		File sf = new File(sFile);
		File df = new File(destFile);
		if (!sf.exists()) {
			System.out.println("Duong dan khong ton tai");
			return moved = false;
		} else {
			if (sf.isDirectory()) {
				// Thu muc dich chua ton tai thi tao
				if (!df.exists()) {
					df.mkdir();
					System.out.println("Thu muc dich duoc tao: " + df);
				}
				String files[] = sf.list();
				for (String file : files) {
					File srcFile = new File(sf, file);
					File desFile = new File(df, file);
					folderMove(srcFile.getAbsolutePath(), desFile.getAbsolutePath(), moved);
				}
			} else {
				fileMove(sf.getAbsolutePath(), df.getAbsolutePath(), false);
			}
			sf.delete();
			return moved = true;
		}
	}

	public static void main(String[] args) throws IOException {
		String nullDir = "";
		String fileDir = "src\\Tuan_2\\File\\fake.jpg";
		String folderDir = "src\\Tuan_2\\CopyFolder";
		String destDir = "src\\Tuan_2\\MoveFolder";

		PhanIO io = new PhanIO();
		// fileCopy(fileDir, "src\\Tuan_2\\File\\fake3.jpg", false);
		// fileMove("src\\Tuan_2\\File\\fake3.jpg","src\\Tuan_2\\File\\fakeMove.jpg",
		// false);
		// folderCopy(folderDir, "src\\Tuan_2\\CopyFolder2", false);
		// folderMove("src\\Tuan_2\\CopyFolder2", "src\\Tuan_2\\MoveFolder", false);

	}
}

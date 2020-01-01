package myIO;

import java.io.File;

import fileClass.Bai1;

public class Bai6 {
	/**
	 * phương thức chưa hoàn thiện và ẩn chứa lỗi ở các trường hợp đặc biệt như chạy
	 * nhiều lần từ thư mục test sang test2, chép vào thư mục con hay chép vào chính
	 * nó
	 */
	public static boolean copyFolder(String sourceFolderPath, String destFolderPath, boolean moved) {
		File sourceFolder = new File(sourceFolderPath);
		File destFolder = new File(destFolderPath);

		if (!sourceFolder.exists())
			return false;
		if (!destFolder.exists())
			destFolder.mkdirs();
		if (!destFolder.isDirectory())
			return false;
		File newFolder = new File(destFolderPath + "\\" + sourceFolder.getName());
		if (!sourceFolder.isDirectory()) {
			// sourceFolderPath và newFolder lúc này đều là file
			return Bai5.copyFile(sourceFolderPath, newFolder.getAbsolutePath(), moved);
		}

		// tới thời điểm này ta đảm bảo nguồn và đích đều là thư mục và đã tồn tại

		if (newFolder.exists()) // xử lý thư mục có sẵn trùng tên trước để tránh xử lý khó hiểu
			copyFolder(newFolder.getAbsolutePath(), newFolder.getAbsolutePath(), moved);
		else
			newFolder.mkdirs();

		File[] subFiles = sourceFolder.listFiles((dir, name) -> !name.equals(newFolder.getName()));
		if (subFiles == null)
			return false;

		boolean isDone = true;
		for (File subFile : subFiles) {
			isDone &= copyFolder(subFile.getAbsolutePath(), newFolder.getAbsolutePath(), moved);
		}

		if (moved)
			return Bai1.delete(sourceFolderPath);

		return isDone;
	}

	public static void main(String[] args) {
		String source = "c:\\";
		String des = "d:\\";
		System.out.println(copyFolder(source, des, true));
	}
}

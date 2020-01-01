package fileClass;

import java.io.File;

public class Bai1 {
	/* xóa tất cả */
	public static boolean delete(String path) {
		File file = new File(path);
		if (!file.exists())
			return false;

		if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			if (!(subFiles == null))
				for (File subFile : subFiles)
					delete(subFile.getAbsolutePath());
		}
		return file.delete();
	}

	/* chỉ xóa file giữ nguyên thư mục */
	public static boolean deleteFile(String path) {
		File file = new File(path);
		if (!file.exists())
			return false;
		if (!file.isDirectory())
			return file.delete();
		File[] subFiles = file.listFiles();
		if (subFiles == null)
			return false;
		boolean result = true;
		for (File subFile : subFiles)
			result &= deleteFile(subFile.getAbsolutePath());
		return result;
	}

	public static void main(String[] args) {
		String address = "d:\\";
		System.out.println(delete(address));
	}
}

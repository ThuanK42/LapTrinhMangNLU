package fileClass;

import java.io.File;

public class Bai2 {
	/* in ra file đầu tiên tìm được */
	public static boolean findFirst(String path, String pattern) {
		File file = new File(path);
		if (!file.exists())
			return false;

		if (file.getName().contains(pattern)) {
			System.out.println(file.getAbsolutePath());
			return true;
		}

		if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			if (subFiles != null)
				for (File subFile : subFiles)
					if (findFirst(subFile.getAbsolutePath(), pattern))
						return true;
		}

		return false;
	}

	/* in ra tất cả file phù hợp */
	public static boolean findList(String path, String pattern) {
		File file = new File(path);
		if (!file.exists())
			return false;

		boolean isFound = false;

		if (file.getName().contains(pattern)) {
			isFound |= true;
			System.out.println(file.getAbsolutePath());
		}

		if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			if (subFiles != null)
				for (File subFile : subFiles)
					isFound |= findList(subFile.getAbsolutePath(), pattern);
		}

		return isFound;
	}

	public static void main(String[] args) {
		String path = "c:\\";
		String pattern = "ini";
		System.out.println(findFirst(path, pattern));
		System.out.println(findList(path, pattern));

	}
}

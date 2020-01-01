package Tuan_3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class BaiTapIO {

	public boolean splitFile(String path, long size) throws IOException {
		File sf = new File(path);
		long fsize = sf.length();
		long count = fsize / size;
		long remain = fsize % size;
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf));
		for (int i = 1; i <= count; i++) {
			String df = sf + "." + extend(count);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
			writeFile(size, bis, bos);
			bos.flush();
			bos.close();
		}
		if (remain > 0) {
			String df = sf + "." + extend(count + 1);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
			writeFile(remain, bis, bos);
			bos.flush();
			bos.close();
		}
		return true;
	}

	private void writeFile(long leng, BufferedInputStream bis, BufferedOutputStream bos) throws IOException {
		byte[] arr = new byte[1024 * 100];
		long remain = leng;
		int byteRead, readSize;
		while (remain > 0) {
			readSize = (int) ((arr.length < remain) ? arr.length : remain);
			byteRead = bis.read(arr, 0, readSize);
			bos.write(arr, 0, byteRead);
			remain -= byteRead;
		}
	}

	private String extend(long count) {
		String s = "" + count;
		while (s.length() < 3) {
			s = "0" + s;
		}
		return s;

	}

	public boolean joinFile(String path) throws IOException {

		File sf = new File(path);
		if (!sf.exists())
			return false;
		String df = path.substring(0, path.length() - 4);
		String ext = path.substring(path.length() - 4, path.length());
		OutputStream ous = new BufferedOutputStream(new FileOutputStream(df + "Join" + ext));
		File[] listFile = sf.getParentFile().listFiles();
		for (File file : listFile) {
			String name = file.getName().substring(0, file.getName().length() - 4);
			if (name.equals(new File(path).getName())) {
				InputStream is = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
				byte[] arr = new byte[1024];
				while ((is.read(arr)) != -1) {
					ous.write(arr);
				}
				is.close();
			}
		}
		ous.flush();
		ous.close();
		return true;
	}

	public static void pack(String folder, String archive) throws IOException {
		File sf = new File(folder);
		if (!sf.exists())
			return;
		if (sf.isFile())
			return;

		File[] listFile = sf.listFiles();
		RandomAccessFile raf = new RandomAccessFile(archive, "rw");
		raf.writeInt(listFile.length);
		for (File file : listFile) {
			raf.writeUTF(file.getName());
			raf.writeLong(file.length());
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			int data;
			while ((data = bis.read()) != -1)
				raf.write(data);
			bis.close();
		}
		raf.close();
	}

	public static void unPack(String folder, String archive, String name) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(archive, "rw");
		int count = raf.readInt();
		for (int i = 0; i < count; i++) {
			String fname = raf.readUTF();
			long size = raf.readLong();
			long pos = raf.getFilePointer();
			if (fname.equals(name)) {
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(folder + "\\unpack-" + name));
				for (int j = 0; j <= size; j++)
					bos.write(raf.read());
				bos.close();
				break;
			} else {
				raf.seek(pos + size);
			}
		}
		raf.close();
	}

	public static String fileType(String fname) {
		String ext = "";
		File sf = new File(fname);
		if (!sf.exists())
			return "pathname not exists.";
		if (sf.isDirectory())
			return "pathname is adirectory.";
		if (sf.isFile()) {
			ext = sf.getName();
			int dotIndex = ext.lastIndexOf('.');
			return (dotIndex == -1) ? "" : ext.substring(dotIndex + 1);
		}
		return ext;
	}

	public static void main(String[] args) throws IOException {
		BaiTapIO bt = new BaiTapIO();

		String folder = "pack";
		String name = "hint.docx";
		String archive = folder + "/archive.lvt";

		String path = "splitFile\\face-recognition.pdf";

		// System.out.println(bt.splitFile(path, 1024 * 1000));

		// pack(folder, archive);
		// unPack(folder, archive, name);
		// System.out.println(bt.joinFile(path));

		// System.out.println(fileType(path));;
	}

}

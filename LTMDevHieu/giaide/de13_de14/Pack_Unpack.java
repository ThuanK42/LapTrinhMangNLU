package de13_de14;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class Pack_Unpack {
	
	public static void main(String[] args) throws Exception {
//		System.out.println(pack("C:\\Users\\Administrator\\Desktop\\tt", "C:\\Users\\Administrator\\Desktop\\tt\\a.pack"));
		System.out.println(unpack("C:\\Users\\Administrator\\Desktop\\tt\\a.pack", "C:\\Users\\Administrator\\Desktop\\tt\\20.jpg", "2.jpg"));
	}
	
	public static boolean pack(String source, String dest) throws Exception {
		// khởi tạo
		File sf = new File(source);
		if(!sf.exists() || sf.isFile()) return false;
		File df = new File(dest);
		File[] files = sf.listFiles();
		RandomAccessFile raf = new RandomAccessFile(df, "rw");
		BufferedInputStream bis;
		byte[] data;
		long nextEntry, position;
		for(int i = 0; i < files.length ; i ++) {
			/*
			 *  Nếu là file cuối thì:
			 *  Lưu lần lược theo yêu cầu đ�?: nextEntry = 0
			 *  return khi đã lưu xong
			 */
			if(i == (files.length -1)) {
				raf.writeLong(0);
				raf.writeLong(files[i].length());
				raf.writeUTF(files[i].getName());
				bis = new BufferedInputStream(new FileInputStream(files[i]));
				data = new byte[1024];
				while((bis.read(data)) != -1){
					raf.write(data);
				}
				return true;
			}
			/*
			 * Nếu không phải là file cuối thì:
			 * Biến nextEntry để tý seek lại chổ này lưu lại vị trí của FILE TIEP THEO
			 * Biến position là vị trí của FILE TIEP THEO
			 * Chạy cho đến file cuối rồi return
			 */
			nextEntry = raf.getFilePointer();
			raf.writeLong(0);
			raf.writeLong(files[i].length());
			raf.writeUTF(files[i].getName());
			bis = new BufferedInputStream(new FileInputStream(files[i]));
			data = new byte[1024];
			while((bis.read(data)) != -1){
				raf.write(data);
			}
			position = raf.getFilePointer();
			raf.seek(nextEntry);
			raf.writeLong(position);
			raf.seek(position);
		}
		return false;
	}
	
	public static boolean unpack(String source, String dest, String name) throws Exception {
		File sf = new File(source);
		if(!sf.exists() || sf.isDirectory()) return false;
		File df = new File(dest);
		// Khởi tạo
		RandomAccessFile raf = new RandomAccessFile(sf, "rw");
		long nextEntry, position, fsize;
		String fname;
		BufferedOutputStream bos;
		int data;
		while(true) {
			nextEntry = raf.readLong();
			/*
			 * Kiểm tra nếu = 0 là file cuối, so sánh với name
			 * Giống return true, sai return false 
			 */
			if(nextEntry == 0) {
				fsize = raf.readLong();
				fname = raf.readUTF();
				if(fname.equals(name)) {
					bos = new BufferedOutputStream(new FileOutputStream(df));
					for(int i = 0 ; i < fsize;i++) {
						data = raf.read();
						bos.write(data);
					}
					bos.close();
					return true;
				}else {
					return false;
				}
			}
			/*
			 * Nếu không là file cuối thì kiểm tra
			 * Nếu giống thì lưu ra file mới là đư�?ng dẫn dest, và return true
			 * Nếu không thì chuyển đến vị trí nextEntry: vị trí file tiếp theo
			 * Lần lượt như thế cho đến khi nextEntry = 0, thì thực hiện cái if bên trên
			 */
			fsize = raf.readLong();
			fname = raf.readUTF();
			position = raf.getFilePointer();
			if(fname.equals(name)) {
				bos = new BufferedOutputStream(new FileOutputStream(df));
				for(int i = 0 ; i < fsize;i++) {
					data = raf.read();
					bos.write(data);
				}
				bos.close();
				return true;
			}else {
				raf.seek(nextEntry);
			}
		}
	}

}

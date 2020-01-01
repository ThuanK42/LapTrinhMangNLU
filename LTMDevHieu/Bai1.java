package baiTap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Bai1 {
	public static boolean copyFile(String pathSrc, String pathDest,
			boolean moved) throws IOException {
		// InputStream fis = new BufferedInputStream(new
		// FileInputStream(pathSrc));
		// OutputStream fos = new FileOutputStream(pathDest);
		boolean result = false;
		if (!moved) {
			System.out.println("Chua thuc hien");
			result = false;
			return result;
		} else {

			File in = new File(pathSrc);
			File out = new File(pathDest);

			if (in.exists()) {

				long sTime = System.currentTimeMillis();
				InputStream input = new BufferedInputStream(
						new FileInputStream(in));
				// OutputStream output = new FileOutputStream(out);
				OutputStream output = null;
				if (out.exists()) {
					out = new File(pathDest, in.getName());
					out.createNewFile();
					output = new BufferedOutputStream(new FileOutputStream(out));
				} else {
					output = new BufferedOutputStream(new FileOutputStream(out));// "D:\\tmp\\baitap-copy.docx"
				}
				byte[] arr = new byte[1024 * 100];
				int readData;
				while ((readData = input.read()) != -1) {
					output.write(arr, 0, readData);

					result = true;

				}
				long eTime = System.currentTimeMillis();
				long total = (eTime - sTime);
				System.out.println(total);

				input.close();
				output.close();

			}
		}
		return result;

		// int data ;
		// byte[] byteData = pathScr.getBytes();
		// while((data = fis.read(byteData)) != -1){
		// fos.write(data);
		// moved = true;
		// }
		//
		//
		// fis.close();
		//
		// fos.close();
		// return moved;
		//

		// }

	}

	public static boolean copyFolder(String pathSrc, String pathDest,
			boolean moved) throws IOException {
		// InputStream fis = new BufferedInputStream(new
		// FileInputStream(pathSrc));
		// OutputStream fos = new FileOutputStream(pathDest);
		boolean result = false;
		if (!moved) {
			System.out.println("Chua thuc hien");
			result = false;
			return result;
		} else {

			File in = new File(pathSrc);
			File out = new File(pathDest);

			if (in.exists()) {
				result=  true;
				if(!out.exists()){
					out.mkdirs();
				}
			}
				
				long sTime = System.currentTimeMillis();
				InputStream input = new BufferedInputStream(
						new FileInputStream(in));
				OutputStream output = new FileOutputStream(out);
				if(in.isDirectory()){//thu muc
					for(File sub:in.listFiles()){
						String nameFile=sub.getName();
						File subIn=new File(in, nameFile);
						File subOut= new File(out, nameFile);
						copyFolder(subIn.getAbsolutePath(), subOut.getAbsolutePath(), moved);
						
					}
				}
			
			else{
				InputStream input1 = new BufferedInputStream(
						new FileInputStream(in));
				OutputStream output1 = new FileOutputStream(out);
				byte[] arr = new byte[1024 * 100];
				int readData;
				while ((readData = input1.read()) != -1) {
					output1.write(arr, 0, readData);


				}
				

				input.close();
				output.close();
			
	}
			

	public static void main(String[] args) throws IOException {
		// String pathSrc = "D:\\tmp\\baitap.docx";
		// String pathDest = "D:\\tmp\\baitap-copy.docx";//
		// String pathDest1= "D:\\tmpTest\\";//lay ten cua tap dich chuyen sang
		// tep copy
		String pathSrc = "D:\\OOAD For Student.rar";
		String pathDest = "D:\\tmp\\baitap-copy.docx";//
		String pathDest1 = "D:\\tmpTest\\";
		boolean moved = true;
		System.out.println(copyFile(pathSrc, pathDest1, moved));
	}
}

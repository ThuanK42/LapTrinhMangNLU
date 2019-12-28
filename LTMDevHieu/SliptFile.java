package bai_7;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SliptFile {
//	public static boolean split(String pathSrc, int sizeSplit)
//			throws IOException {
//		String destFile = null;
//		boolean result = false;
//		File fileIn = new File(pathSrc);
//		File fileOut = new File(destFile);
//		int numberFile = 0;
//		if (fileIn.exists()) {
//			if (pathSrc.length() % sizeSplit == 0) {
//				numberFile = pathSrc.length() % sizeSplit;
//			} else {
//				numberFile = pathSrc.length() % sizeSplit + 1;
//			}
//			result = true;
//		} else {
//			result = false;
//		}
//	//	InputStream fis = new BufferedInputStream(new FileInputStream(fileIn));
//		//OutputStream fos = new BufferedOutputStream(new FileOutputStream(
////				fileOut));
//		Byte[] arr = new Byte[sizeSplit];
//		int data;
//		for (int i = 0; i < numberFile; i++) {
//		}
//
//		return result;
//
//	}

	public static boolean splitFile(String pathSrc1, int sizeSplit1) throws IOException {
		boolean result = false;
		File in = new File(pathSrc1);
		if (in.exists()) {
			result = true;
			int numberOfPart;
			BufferedInputStream fis = new BufferedInputStream(
					new FileInputStream(in));
			BufferedOutputStream fos = null;
			if (in.length() % sizeSplit1 == 0) {
				numberOfPart = (int) (in.length() % sizeSplit1);

			} else {
				numberOfPart = (int) (in.length() % sizeSplit1 + 1);
			}
			byte[] arr = new byte[sizeSplit1];
			int dataRead = 0;
			for (int i = 0; i < numberOfPart; i++) {
				String pathDest = in.getPath() + ".part" + i+".txt";
				dataRead = fis.read(arr);
				if (dataRead != -1) {
					fos = new BufferedOutputStream(new FileOutputStream(pathDest));
					fos.write(arr);
					fos.close();

				}

			}
			fis.close();
		}

		return result;

	}
	public static boolean zipFile(String pathSrc, String pathDest) throws IOException{
		boolean result = false;
		
		File in = new File(pathSrc);
		File out =new File(pathDest);
		
		if(in.exists()){
			result=true;
			InputStream fis = new BufferedInputStream(new FileInputStream(in));
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(out));
			ZipEntry zipentry = new ZipEntry(in.getName());
			zos.putNextEntry(zipentry);
			byte[] data = new byte[1024];
			int read = 0;
			while((read=fis.read(data))!=-1){
				zos.write(data,0,read);
				
			}
			zos.closeEntry();
			zos.close();
			fis.close();
			
			
		}
		
		
		
		
		return result;
		
	}
	public static boolean zipFile2(String pathSrc,String PathDest) throws IOException{
		boolean result = false;
		File in = new File(pathSrc);
		if (in.exists()) {
			result = true;
		FileInputStream input = new FileInputStream(in);
		ZipOutputStream output=new ZipOutputStream(new FileOutputStream(new File(PathDest)));
		ZipEntry entry =new ZipEntry(in.getName());
		output.putNextEntry(entry);
		byte[] buffer = new byte[1024*100];
		int data=0;
		while((data=input.read(buffer))!=-1){
		output.write(buffer, 0, data);
		}
		output.closeEntry();
		output.close();
		input.close();
		}
		return result;
			
			
	}
	
	public static void main(String[] args) throws IOException {
		String psthSrc ="D:\\IO\\IO\\LTM_Thu6\\HOW TO DESIGN PROGRAMS.pdf";
		int sizeSplit=1;
		System.out.println(splitFile(psthSrc, sizeSplit));
		
		String pathSrc ="D:\\IO\\IO\\LTM_Thu6\\HOW TO Net-Program-OutCames-Exercise.docx";
		String pathDest = "D:\\IO\\IO\\LTM_Thu6\\NewWinRAR.zip";
		System.out.println(zipFile2(pathSrc, pathDest));
	}
}

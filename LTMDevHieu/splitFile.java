package LTM_T3_456;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class splitFile {
	//Teacher Solution
	public static boolean splitFile(String pathSrc, int sizeSplit) throws IOException{
		boolean result = false;
		File in = new File(pathSrc);
		if(in.exists()){
			result = true;
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(in));
			BufferedOutputStream output = null;
			int sizetotal = (int) in.length();
			byte[] temp = null;
			int totalRead = 0;
			int numOfPart = 0;
			while(totalRead < sizetotal){
				String pathDest = pathSrc+" . part " +numOfPart;
				int sizetmp =sizetotal - totalRead;//file temp
				if(sizetotal < sizeSplit){
					sizeSplit = sizetmp;
				}
				temp = new byte[sizeSplit];
				int read = input.read(temp, 0, sizeSplit);
				if(read > 0){
					totalRead+=read;
					numOfPart++;
				}
				
				output = new BufferedOutputStream(new FileOutputStream(new File(pathDest)));
				output.write(temp);
				output.close();
			}
			input.close();
		
		}
		return result;
		
	}
	public static boolean zipFile(String pathSrc, String nameZip) throws IOException{
		boolean result = false;
		File in = new File(pathSrc);
		if(in.exists()){
			result = true;
			FileInputStream input = new FileInputStream(in);
			ZipOutputStream output = new ZipOutputStream(new FileOutputStream(new File(nameZip)));
			ZipEntry zipentry = new ZipEntry(in.getName());
			output.putNextEntry(zipentry);
			byte[] data = new byte[1024];
			int read = 0;
			while((read=input.read(data))!=-1){
				output.write(data,0,read);
				
			}
			output.closeEntry();
			output.close();
			input.close();
			
		}
		return result;
	}
	
	
	public static void main(String[] args) throws IOException {
		String path="D:\\BAITAP\\baitap\\DesFile\\b.txt";
		int sizeSplit = 5;
		//System.out.println(splitFile(path, sizeSplit));
		System.out.println(zipFile(path, "D:\\BAITAP\\baitap\\DesFile\\b.zip"));
	}
}

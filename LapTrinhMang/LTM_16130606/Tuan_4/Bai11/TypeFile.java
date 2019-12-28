package Tuan_4.Bai11;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TypeFile {
	// Tham khao tu code co Tram
	public static String generateMagicArray(File file) {
		String createMagicArray = "public final int[] MagicHeader = new int[] {";
		try {
			FileInputStream fis = new FileInputStream(file);
			for (int i = 0; i < 8; i++) {
				createMagicArray += "0x" + Integer.toHexString(fis.read()) + ",";
			}
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(createMagicArray);
		return createMagicArray;
	}

	public static String typeFile(File sFile) throws IOException {
		String s = "";
		InputStream inputStream = new FileInputStream(sFile);
		int[] headersBytes = new int[8];
		// txt
		int[] txtSignature = { 0x0, 0x8, 0x31, 0x35, 0x31, 0x33, 0x30, 0x36 };
//		int[] zipSignature = { 0x50, 0x4b, 0x5, 0x6, 0x0, 0x0, 0x0, 0x0 };
//		int[] rarSignature = { 0x52, 0x61, 0x72, 0x21, 0x1a, 0x7, 0x1, 0x0 };
//		int[] docxSignature = { 0x50, 0x4b, 0x3, 0x4, 0x14, 0x0, 0x6, 0x0 };
		boolean isTXT = true;
//		boolean isZIP = true;
//		boolean isRAR = true;
//		boolean isDOC = true;
		for (int i = 0; i < 8; i++) {
			headersBytes[i] = inputStream.read();
			if (headersBytes[i] != txtSignature[i]) {
				isTXT = false;
				break;
			}
		}
//		for (int i = 0; i < 8; i++) {
//			headersBytes[i] = inputStream.read();
//			if (headersBytes[i] != zipSignature[i]) {
//				isZIP = false;
//				break;
//			}
//		}
//		for (int i = 0; i < 8; i++) {
//			headersBytes[i] = inputStream.read();
//			if (headersBytes[i] != rarSignature[i]) {
//				isRAR = false;
//				break;
//			}
//		}
//		for (int i = 0; i < 8; i++) {
//			headersBytes[i] = inputStream.read();
//			if (headersBytes[i] != docxSignature[i]) {
//				isDOC = false;
//				break;
//			}
//		}
		inputStream.close();
		System.out.println("Is TXT file " + isTXT);
//		System.out.println("Is RAR file " + isRAR);
//		System.out.println("Is ZIP file " + isZIP);
//		System.out.println("Is DOCZ file " + isDOC);
		if (isTXT == true) {
			s = "File type is TXT";
		}
//		if (isZIP == true) {
//			s = "File type is ZIP";
//		}
//		if (isRAR == true) {
//			s = "File type is RAR";
//		}
//		if (isDOC == true) {
//			s = "File type is DOC";
//		}
		return s;
	}

	public static void main(String[] args) throws IOException {
		File doc = new File("src\\TypeFile\\Net-Program-OutCames-Exercise.docx");
		File txt = new File("src\\TypeFile\\test.txt");
		File zip = new File("src\\TypeFile\\test.zip");
		File rar = new File("src\\TypeFile\\test2.rar");
		// System.out.println(generateMagicArray(txt));
		System.out.println(TypeFile.typeFile(txt));
	}
}

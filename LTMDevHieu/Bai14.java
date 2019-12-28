import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class Bai14 {
	public static void pack(String address, String packFile) throws IOException {
		File file = new File(address);
		if (!file.exists() || !file.isDirectory())
			return;

		File[] filesToPack = file.listFiles(File::isFile);

		DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(packFile)));
		outputStream.writeInt(filesToPack.length);

		DataInputStream inputStream;
		int data;
		for (File temp : filesToPack) {
			outputStream.writeUTF(temp.getName());
			outputStream.writeLong(temp.length());
			inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(temp)));
			while ((data = inputStream.read()) != -1)
				outputStream.write(data);
			inputStream.close();
		}
		outputStream.close();
	}

	public static void unpack(String packFile, String addressToExtract) throws IOException {
		File extractFolder = new File(addressToExtract);
		extractFolder.mkdirs();
		if (!extractFolder.isDirectory())
			return;

		DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(packFile)));
		int totalFile = inputStream.readInt();
		OutputStream outputStream;
		for (int i = 0; i < totalFile; i++) {
			String tempFile = extractFolder + "\\" + inputStream.readUTF();
			outputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
			long tempSize = inputStream.readLong();
			for (int j = 0; j < tempSize; j++) {
				outputStream.write(inputStream.read());
			}
			outputStream.close();
		}
		inputStream.close();
	}

	public static void extract(String packFile, String fileToExtract) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(packFile, "r");
		int totalFile = raf.readInt();
		String fileName;
		long fileSize;
		for (int i = 0; i < totalFile; i++) {
			fileName = raf.readUTF();
			fileSize = raf.readLong();
			if (fileName.equalsIgnoreCase(fileToExtract)) {
				fileToExtract = new File(packFile).getParent() + "\\" + fileToExtract;
				DataOutputStream outputStream = new DataOutputStream(
						new BufferedOutputStream(new FileOutputStream(fileToExtract)));
				for (int j = 0; j < fileSize; j++) {
					outputStream.write(raf.read());
				}
				outputStream.close();
				break;
			} else {
				raf.seek(raf.getFilePointer() + fileSize);
			}
		}
		raf.close();
	}

	public static void main(String[] args) throws IOException {
		pack("D:\\Learn", "D:\\learn.nen");
		unpack("D:\\learn.nen", "D:\\learn2");
		extract("D:\\learn.nen", "TKB.PNG");
	}
}

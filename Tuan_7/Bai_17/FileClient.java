package Tuan_7.Bai_17;

import java.io.*;
import java.net.Socket;


public class FileClient {
    //static Socket socket;

    public void copyFileClient(String sFile) throws IOException {
       Socket socket = new Socket("127.0.0.1", 1357);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(sFile)));
        byte[] buff = new byte[100 * 1024];
        int data;
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        while ((data = dataInputStream.read(buff)) != -1) {
            bos.write(buff, 0, data);
            bos.flush();
        }
        socket.close();
        bos.close();
        dataInputStream.close();
    }

    public static void copyFolderServer(String sFile, String dFile) throws IOException {
       Socket socket = new Socket("127.0.0.1", 1357);
        File sf = new File(sFile);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String destFile = dataInputStream.readUTF();
        File df = new File(destFile);
        if (sf.isDirectory()) {
            if (!df.exists()) {
                df.mkdir();
            }
            String files[] = sf.list();
            for (String file : files) {
                File srcFile = new File(sf, file);
                File desFile = new File(df, file);
                copyFolderServer(srcFile.getAbsolutePath(), desFile.getAbsolutePath());
            }

        } else {
            // Files.copy(sf.toPath(), df.toPath(), StandardCopyOption.REPLACE_EXISTING);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(sf));
            byte[] buff = new byte[10 * 1024];
            int data = 0;
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(df));
            while ((data = bufferedInputStream.read(buff)) != -1) {
                bos.write(buff, 0, data);
                bos.flush();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        FileClient fileClient = new FileClient();
       // Socket socket = new Socket("127.0.0.1", 1357);
       // File destFile = new File(String.valueOf(socket.getOutputStream()));
        fileClient.copyFileClient("F:\\Nam 4\\Lap_Trinh_Mang\\src\\Tuan_7\\Bai_17\\FileClient\\Net-Program-OutCames-Exercise-Copy.docx");
      //  fileClient.copyFolderServer("F:\\Nam 4\\16130606_LeVanThuan\\16130606_LeVanThuan\\src\\Tuan_7\\Bai_17\\FolderClient", destFile.getAbsolutePath());
    }
}

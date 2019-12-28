package Tuan_7.Bai_17;

import java.io.*;
import java.net.*;

public class FileServer {
    static ServerSocket serverSocket;
    static Socket socket;

    public static void copyFileServer(String sFile) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1357);
        Socket socket = serverSocket.accept();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(sFile)));
        byte[] buff = new byte[10 * 1024];
        int data = 0;
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        while ((data = bufferedInputStream.read(buff)) != -1) {
            dataOutputStream.write(buff, 0, data);
            dataOutputStream.flush();
        }
        serverSocket.close();
        bufferedInputStream.close();
        dataOutputStream.close();
    }

    public static void copyFolderServer(String sFile, String dFile) throws IOException {
        serverSocket = new ServerSocket(1357);
        socket = serverSocket.accept();
        File sf = new File(sFile);
        File df = new File(dFile);
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
        FileServer fileServer = new FileServer();
        //  File destFile = new File(String.valueOf(socket.getInputStream()));
        //System.out.println(destFile.getAbsolutePath().toString());
        fileServer.copyFileServer("F:\\Nam 4\\Lap_Trinh_Mang\\src\\Tuan_7\\Bai_17\\FileServer\\Net-Program-OutCames-Exercise.docx");
        //  fileServer.copyFolderServer("F:\\Nam 4\\16130606_LeVanThuan\\16130606_LeVanThuan\\src\\Tuan_7\\Bai_17\\FolderServer", destFile.getAbsolutePath());
    }
}

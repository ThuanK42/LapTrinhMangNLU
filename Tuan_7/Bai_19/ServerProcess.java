package Tuan_7.Bai_19;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class ServerProcess extends Thread {
    Socket socket;
    DataInputStream netIn;
    DataOutputStream netOut;
    String serverDir;

    public ServerProcess(Socket socket) throws IOException {
        this.socket = socket;
        netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    @Override
    public void run() {
        String line;
        String df;
        StringTokenizer st;
        String command;
        try {
            while (true) {
                line = netIn.readUTF();
                if ("QUIT".equalsIgnoreCase(line)) break;
                st = new StringTokenizer(line);
                command = st.nextToken();
                switch (command) {
                    case "SET_SERVER_DIR":
                        serverDir = st.nextToken();
                        break;
                    case "SEND":
                        df = st.nextToken();
                        download(df);
                        break;
                    default:
                        break;
                }

            }
            netIn.close();
            netOut.flush();
            netOut.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void download(String df) throws IOException {
        File file = new File(serverDir + File.separator + df);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

        long size = netIn.readLong();
        int byteRead, byteMustRead;
        long remain = size;
        byte[] buff = new byte[10 * 1024];

        while (remain > 0) {
            byteMustRead = buff.length > remain ? (int) remain : buff.length;
            byteRead = netIn.read(buff, 0, byteMustRead);
            bos.write(buff, 0, byteRead);
            remain -= byteRead;
        }
        bos.flush();
        bos.close();
    }

}

package Tuan_8.Bai_21;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    private static ArrayList<Student> listStudent = new ArrayList<Student>();
    final static int port = 4444;
    ServerSocket s;
    Socket sc;
    BufferedReader br;
    PrintWriter pw;

    public Server(Socket sc, BufferedReader br, PrintWriter pw) {
        super();
        this.sc = sc;
        this.br = br;
        this.pw = pw;
    }

    @Override
    public void run() {
        try {

            // get information from client
            br = new BufferedReader(new InputStreamReader(sc.getInputStream()));

            // Read client
            String readClient = "";
            readClient = br.readLine();
            System.out.println(readClient);

            String[] processInf = readClient.split(" ");
            String comm = processInf[0];
            System.out.println(comm.equalsIgnoreCase("findByName"));
            pw = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()), true);

            String line = "";
            String res = "";
            while (processInf != null) {
                System.out.println("connected");
                if (comm.equalsIgnoreCase("findByName")) {
                    String name = processInf[1];
                    System.out.println(" findByName");
                    res = findByName(name);
                } else if (comm.equalsIgnoreCase("findByLessAge")) {
                    int age = Integer.parseInt(processInf[1]);
                    res = findByLessAge(age);
                } else if (comm.equalsIgnoreCase("findByLessScore")) {
                    double score = Double.parseDouble(processInf[1]);
                    res = findByLessScore(score);
                } else {
                    pw.println("No found Command");
                }
                System.out.println("kq    " + res);
                pw.println(res);
            }


            br.close();
            pw.close();
            s.close();
            sc.close();
            System.out.println("final.....");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    // Create a list of students
    public static void createListStudent() {
        listStudent.add(new Student("An", 20, 8.0));
        listStudent.add(new Student("Binh", 19, 9.0));
        listStudent.add(new Student("Cuc", 20, 8.5));
        listStudent.add(new Student("Dung", 19, 10.0));
        listStudent.add(new Student("Duc", 21, 9.5));

    }

    // Find by name
    public String findByName(String name) {
        String s = "";
        for (Student st : listStudent) {
            if (st.getName().equalsIgnoreCase(name)) {
                s += st;
            }
        }
        return s;
    }


    // Find under age
    public String findByLessAge(int age) {
        String s = "";
        for (Student st : listStudent) {
            if (st.getAge() <= age) {
                s += st;
            }
        }
        return s;
    }


    // Find under score
    public String findByLessScore(double score) {
        String s = "";
        for (Student st : listStudent) {
            if (st.getScore() <= score) {
                s += st;
            }
        }
        return s;
    }

    public static void main(String[] args) throws IOException {

        createListStudent();
        for (Student string : listStudent) {
            System.out.println(string);
        }
        ServerSocket s = new ServerSocket(port);
        System.out.println("Start Connect.......");
        System.out.println("Waiting Connect......");
        Socket sc = s.accept();
        System.out.println("Accept connection.......");
        BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()), true);
        Server server = new Server(sc, br, pw);
        server.start();
    }
}

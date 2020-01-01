package ConnectJDBC;

import java.sql.*;
import java.util.ArrayList;

public class ServerProcess {
    public ArrayList<Student> loadDataAcess() throws ClassNotFoundException, SQLException {
        ArrayList listST = new ArrayList();
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        String db = "D:\\Nam 4\\Lap_Trinh_Mang\\test.mdb";
        String dbUrl = "jdbc:ucanaccess://" + db;
        Connection con = DriverManager.getConnection(dbUrl);
        Statement st = con.createStatement();
        ResultSet re = st.executeQuery("select * from sinhvien");
        while (re.next()) {
            int id = Integer.parseInt(re.getString("ID_ST"));
            String name = re.getString("NAME_ST");
            int score = Integer.parseInt(re.getString("SCORE_ST"));
            int age = Integer.parseInt(re.getString("AGE_ST"));
            Student student = new Student(id, name, score, age);
            listST.add(student);
        }

        return listST;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ServerProcess sp = new ServerProcess();
        ArrayList<Student> list = sp.loadDataAcess();
        for (Student st:list) {
            System.out.println(st.toString());
        }
    }
}

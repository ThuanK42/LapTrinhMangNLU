package benhnhan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProcessServer {
	public ArrayList<BenhNhan> getDataAccsess() throws ClassNotFoundException, SQLException{
		ArrayList<BenhNhan> list = new ArrayList<BenhNhan>();
		String driver="sun.jdbc.odbc.JdbcOdbcDriver";
		String url="Jdbc:Odbc:BenhNhan";
		Class.forName(driver);
		Connection conn= DriverManager.getConnection(url);
		Statement sta= conn.createStatement();
		ResultSet re= sta.executeQuery("select * from benhnhan");
		BenhNhan bn=null;
		while(re.next()) {
			bn= new BenhNhan(re.getString("ten"), re.getString("loaibenh"), Double.parseDouble(re.getString("vienphi")));
			list.add(bn);
			
		}
		
		
		return list;
		
	}
	public String getTienVienPhi(String name) throws ClassNotFoundException, SQLException {
		ArrayList<BenhNhan> list = getDataAccsess();
		String s="";
		for (BenhNhan benhNhan : list) {
			if(benhNhan.getTen().equalsIgnoreCase(name)) {
				s+=benhNhan.getVienphi();
			}
		}
		return s;
	}
	public String getLoaiBenh(String name) throws ClassNotFoundException, SQLException {
		ArrayList<BenhNhan> list = getDataAccsess();
		String s="";
		for (BenhNhan benhNhan : list) {
			if(benhNhan.getTen().equalsIgnoreCase(name)) {
				s+=benhNhan.getLoaibenh();
			}
		}
		return s;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ProcessServer ps= new ProcessServer();
		
		ArrayList<BenhNhan> list = ps.getDataAccsess();
		for (BenhNhan benhNhan : list) {
			System.out.println(benhNhan.toString());
		}
	}

}

package TCP.InfomationFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class ProvidedInfomationFile extends Thread {
	Socket s;
	PrintWriter netOut;
	BufferedReader netIn;
	String saveDefault="upload";
	String keyWord;
	String message="";
	boolean checkFileExists=false;
	File sf;
	public ProvidedInfomationFile(Socket socket) throws IOException {
		this.s = socket;
		netIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		netOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
	}

	@Override
	public void run() {
		netOut.println("Welcome!!!");
		while (true) {
			try {
				String request = netIn.readLine();
				if("QUIT".equalsIgnoreCase(request)){
					netOut.println("Thanks.Goodbye and see you again!!!");break;
				}
				
				StringTokenizer st = new StringTokenizer(request,  " ");
				keyWord=st.nextToken().toUpperCase();
				switch (keyWord) {
				case "SET_DIR":
					String saveChanged = st.nextToken();
					message = "Changed the default directogy from "+saveDefault+" to "+saveChanged+" success!";
					saveDefault = saveChanged;
					break;
				case "SET_FILE":
					String fileName = st.nextToken();
					 sf = new File(saveDefault+File.separator+fileName);
					if(!sf.exists()){
						checkFileExists=false;
						message="File not exists!!!";break;
					}
					checkFileExists=true;
					message="File is exists.";
					break;
				case "GET_NUM":
					if(!checkFileExists){
						message="File not exists!!!.Please re-enter";
						break;
					}
					message="The total value of all the numbers in the given file is "+getNum(sf);
					break;
				case "GET_WORDS":
					if(!checkFileExists){
						message="File not exists!!!.Please re-enter";
						break;
					}
					message="The total value of all the words in the given file is "+getWords(sf);
					break;
				default:
					message="key command not exactly.Please re-enter!!!";
					break;
				}
				
				netOut.println(message);
				message="";
			} catch (IOException e) {
				e.printStackTrace();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			
			
		}
	}

	private long getWords(File sf2) throws IOException {
		long sum=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sf)));
		String line=br.readLine();
		while(line!=null){
			StringTokenizer st = new StringTokenizer(line, " ");
			while(st.hasMoreTokens()){
				String word=st.nextToken();
				try {
					Long.parseLong(word);
				} catch (Exception e) {
					sum++;
				}
			}
			line=br.readLine();
		}
		br.close();
		return sum;
	}

	private long getNum(File sf) throws IOException {
		long sum=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sf)));
		String line=br.readLine();
		while(line!=null){
			StringTokenizer st = new StringTokenizer(line, " ");
			while(st.hasMoreTokens()){
				String word=st.nextToken();
				try {
					long i = Long.parseLong(word);
					sum+=i;
				} catch (Exception e) {
					continue;
				}
			}
			line=br.readLine();
		}
		br.close();
		return sum;
	}
}

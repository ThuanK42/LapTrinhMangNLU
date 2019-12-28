package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;

public class UDPServerStudent extends Thread {
	DatagramSocket sc;
	final static int port = 5555;
	static ArrayList<Student> list = new ArrayList<Student>();

	public UDPServerStudent(DatagramSocket sc) {
		super();
		this.sc = sc;

	}

	@Override
	public void run() {

		try {
			byte[] receiveData = new byte[1024 * 10];
			byte[] sendData = new byte[1024 * 10];
			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				sc.receive(receivePacket);
				String sentence = new String(receiveData, 0,
						receivePacket.getLength());
				System.out.println("RECEIVED: " + sentence);
				InetAddress IPAddress = receivePacket.getAddress();
				int portB = receivePacket.getPort();		
				String[] info = sentence.split(" ");
				String result = "";
				String comm = info[0];
				while (sentence != null) {
					
					if (info.length == 1) {
						if (comm.equalsIgnoreCase("findlist")) {
							result = findlist();
						}else if (comm.equalsIgnoreCase("Exit")) {
							System.out.println("Goodbye!");
							result=comm;
							sendData = result.getBytes();
							DatagramPacket sendPacket = new DatagramPacket(sendData,
									sendData.length, IPAddress, portB);
							sc.send(sendPacket);
							sc.close();
							return;
						}
					} else if (info.length == 2) {
						String agr = info[1];
						if (comm.equalsIgnoreCase("findByName")) {
							result = findByName(agr);
						} else if (comm.equalsIgnoreCase("findAgeUnder")) {
							result = findAgeUnder(Integer.parseInt(agr));
						} else if (comm.equalsIgnoreCase("findAgeUpper")) {
							result = findAgeUpper(Integer.parseInt(agr));
						} else if (comm.equalsIgnoreCase("findGpaUnder")) {
							result = findGpaUnder(Integer.parseInt(agr));
						} else if (comm.equalsIgnoreCase("findGpaUpper")) {
							result = findGpaUpper(Integer.parseInt(agr));
						}else{
							result ="Incorrect";
							System.out.println(result);
							sendData = result.getBytes();
							DatagramPacket sendPacket = new DatagramPacket(sendData,
									sendData.length, IPAddress, portB);
							sc.send(sendPacket);
						}

					} else {
						result ="Incorrect";
						System.out.println(result);
						sendData = result.getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData,
								sendData.length, IPAddress, portB);
						sc.send(sendPacket);

					}

					System.out.println("kq   " + result);
					sendData = result.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData,
							sendData.length, IPAddress, portB);
					sc.send(sendPacket);
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<Student> listStudent() {
		list.add(new Student("1", "NguyenVanA", 20, 8));
		list.add(new Student("2", "NguyenVanB", 21, 9));
		list.add(new Student("3", "NguyenVanC", 26, 8));
		list.add(new Student("4", "NguyenVanD", 22, 9));
		list.add(new Student("5", "NguyenVanE", 25, 10));
		list.add(new Student("6", "NguyenVanF", 18, 9));
		return list;

	}

	public String findlist() {
		String text = "";
		for (Student s : list) {
			text += s.getIdStudent() + "." + s.getNameStudent() + ", "
					+ s.getAge() + ", " + s.getGpA() + "  ";
		}
		return text;
	}

	public String findByName(String name) {
		
		String text = "";
		for (Student s : list) {
			System.out.println("check:" + s.getNameStudent() + ".constains="
					+ s.getNameStudent().contains(name));
			if (s.getNameStudent().contains(name))
				text += s.getIdStudent() + "." + s.getNameStudent() + ", "
						+ s.getAge() + ", " + s.getGpA() + "\n";
		}
		return text;
	}

	public String findAgeUnder(int age) throws IOException {
		String text = "";
		for (Student s : list) {
			if (s.getAge() <= age)
				text += s.getIdStudent() + "." + s.getNameStudent() + ", "
						+ s.getAge() + ", " + s.getGpA() + "\n";
		}
		return text;
	}

	public String findAgeUpper(int age) throws IOException {
		String text = "";
		for (Student s : list) {
			if (s.getAge() >= age)
				text += s.getIdStudent() + "." + s.getNameStudent() + ", "
						+ s.getAge() + ", " + s.getGpA() + "\n\r";
		}
		return text;
	}

	public String findGpaUnder(int gpa) throws IOException {
		String text = "";
		for (Student s : list) {
			if (s.getGpA() <= gpa)
				text += s.getIdStudent() + "." + s.getNameStudent() + ", "
						+ s.getAge() + "\n";
		}
		return text;
	}

	public String findGpaUpper(int gpa) throws IOException {
		String text = "";
		for (Student s : list) {
			if (s.getGpA() >= gpa)
				text += s.getIdStudent() + "." + s.getNameStudent() + ", "
						+ s.getAge() + ", " + s.getGpA() + "\n";
		}
		return text;
	}

	public static void main(String[] args) {
		try {
			// listStudent();
			for (Student string : listStudent()) {
				System.out.println(string);
			}

			System.out.println("Server ready for connection");
			DatagramSocket serverSocket = null;
			serverSocket = new DatagramSocket(port);
			UDPServerStudent udp = new UDPServerStudent(serverSocket);
			udp.start();
		
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

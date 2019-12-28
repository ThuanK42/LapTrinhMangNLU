package RMIClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.StringTokenizer;

import RMIServer.IStudent;

public class ClientRMI {
	public ClientRMI() throws RemoteException, NotBoundException {
        Registry rs = LocateRegistry.getRegistry(12345);
        IStudent sv = (IStudent) rs.lookup("sv");
        System.out.println("ban hay nhap lenh");
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        String result;

        while (true) {
            String command = sc.nextLine();
            StringTokenizer token = new StringTokenizer(command, "[\t ]");
            String request = token.nextToken();
            switch (request) {
                case "findByName":

                    result = sv.findByName(token.nextToken());
                   System.out.println(result);
                    break;
                case "findByUnderScore":
                    result = sv.findByUnderScore(Double.parseDouble(token.nextToken()));
                    System.out.println(result);
                    break;
                case "findByUpperScore":
                	result = sv.findByUpperScore(Double.parseDouble(token.nextToken()));
    				System.out.println(result);
    				break;
    			case "findByUnderAge":
    				result = sv.findByUnderAge(Integer.valueOf(token.nextToken()));
    				System.out.println(result);
    				break;
    			case "findByUpperAge":
    				result = sv.findByUpperAge(Integer.valueOf(token.nextToken()));
    				System.out.println(result);
    				break;
                default:
                    System.out.println("Nhap lenh sai vui long nhap lai");
                    break;
            }
        }
    }

}

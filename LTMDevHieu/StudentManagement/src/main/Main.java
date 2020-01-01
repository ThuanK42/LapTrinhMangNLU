package main;

import java.io.IOException;

import serverSide.Server;

public class Main {
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.run();
	}
}

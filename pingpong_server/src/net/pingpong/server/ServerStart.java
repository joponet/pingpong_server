package net.pingpong.server;

public class ServerStart {

	public static void main(String[] args) {
		int port = 5000;
		Match match = new Match(port);
		match.start();

	}

}

package net.pingpong.server;

import java.io.IOException;
import java.net.Socket;

public class Player{
	Socket socket;
	
	public Player(Match match,int id){
		try {
			socket = match.server.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Input(match, id).start();
		new Output(match, id).start();
	}
}

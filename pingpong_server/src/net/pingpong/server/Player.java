package net.pingpong.server;

import net.pingpong.lib.MatchState;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Player {
	Socket socket;
	ObjectOutputStream output;
	ObjectInputStream input;
	//Vector position;
	
	public Player(Socket socket){
		this.socket = socket;
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sendObject(MatchState match_state){
		try {
			output.writeObject(match_state);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

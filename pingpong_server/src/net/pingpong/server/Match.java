package net.pingpong.server;

import net.pingpong.lib.Pilota;
import net.pingpong.lib.MatchState;
import java.io.IOException;
import java.net.ServerSocket;

public class Match {
	ServerSocket server;
	MatchState match_state;
	Pilota pilota;
	int port;
	
	public Match(int port){
		this.port = port;
	}
	
	public void start(){
		try {
			server = new ServerSocket(port);
			System.out.println("connectat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		match_state = new MatchState();
		pilota = new Pilota();
		addPlayers();
		
	}
	
	private void addPlayers(){
		for(int i=1; i <= 2; i++){
			try {
				new Player(server.accept(),this, i).start();
				System.out.println("player id:" + i);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}



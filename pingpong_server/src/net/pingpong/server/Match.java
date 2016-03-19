package net.pingpong.server;

import java.io.IOException;
import java.net.ServerSocket;

import net.pingpong.lib.MatchState;

public class Match {
	ServerSocket server;
	Player player1;
	Player player2;
	MatchState match_state;
	int ip;
	int FPS = 60;
	boolean loop = true;
	
	public Match(int ip){
		this.ip = ip;
	}
	
	public void start(){
		try {
			server = new ServerSocket(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		addPlayers();
		
	}
	
	private void addPlayers(){
		try {
			player1 = new Player(server.accept());
			player2 = new Player(server.accept());
		} catch (IOException e) {
			e.printStackTrace();
		}
		startMatch();
	}
	private void startMatch(){
		while(loop){
			
		}
	}
	public void stop(){
		loop = false;
	}

}


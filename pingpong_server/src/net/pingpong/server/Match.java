package net.pingpong.server;

import net.pingpong.lib.GameConst;
import net.pingpong.lib.Pilota;
import net.pingpong.lib.Tick;

import java.io.IOException;
import java.net.ServerSocket;

public class Match {
	ServerSocket server;
	DataMatch data_match;
	Pilota pilota;
	Player[] players;
	boolean loop = true;
	
	public void start(){
		try {
			server = new ServerSocket(GameConst.PORT);
			System.out.println("connectat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		data_match = new DataMatch();
		pilota = new Pilota();
		addPlayers();
		
	}
	
	private void addPlayers(){
		players = new Player[2];
		for(int i=0; i < 2; i++){
			players[i] = new Player(this, (i+1));
			System.out.println("player id:" + (i+1));
		}
	}
	
	public void run(){
		pilota.init();
		Tick tick = new Tick();
		tick.start();
		while(loop){
			if(tick.update()){
				pilota.tick();
			}
			
		}
	}
}



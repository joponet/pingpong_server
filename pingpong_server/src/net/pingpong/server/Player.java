package net.pingpong.server;

import net.pingpong.lib.Tick;
import net.pingpong.lib.PlayerState;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Player extends Thread {
	Socket socket;
	ObjectOutputStream output;
	ObjectInputStream input;
	Match match;
	int id;
	boolean loop;
	
	public Player(Socket socket,Match match,int id){
		this.socket = socket;
		this.match = match;
		this.id = id;
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Thread fuction
	public void run(){
		sendID();
		System.out.println("ID enviada");
		loop = true;
		match.pilota.init();
		Tick tick = new Tick();
		tick.start();
		while(loop){
			if(tick.update()){
				sendMatchState();
				if(id == 1)match.pilota.tick();
				updateMatchState();
				match.match_state.setBposX(match.pilota.get_x());
				match.match_state.setBposY(match.pilota.get_y());
			}
		}
	}
	
	public void updateMatchState(){
		PlayerState player_state = recivePlayerState();
		if(id == 1){
			match.match_state.setP1posX(player_state.getPos());
			if(player_state.isGoal()){
				match.match_state.incP2goal();
				System.out.println("Player2 make a goal");
			}
			if(player_state.getShoot() != 0){
				match.pilota.shoot(player_state.getShoot());
			}
		}
		if(id == 2){
			match.match_state.setP2posX(player_state.getPos());
			if(player_state.isGoal()){
				match.match_state.incP1goal();
				System.out.println("Player1 make a goal");
			}
			if(player_state.getShoot() != 0){
				match.pilota.shoot(player_state.getShoot());
			}
		}
	}
	
	//recive methods
	public PlayerState recivePlayerState(){
		PlayerState  player_state = null;
		try {
			player_state = (PlayerState) input.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return player_state;
	}
	
	
	//send methods
	public void sendMatchState(){
		try {
			output.writeObject(match.match_state);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendID(){
		try {
			output.writeShort(id);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//exit
	public void exit(){
		try {
			loop = false;
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

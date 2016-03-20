package net.pingpong.server;

import java.io.IOException;
import java.io.ObjectInputStream;

import net.pingpong.lib.PlayerState;

public class Input extends Thread{
	ObjectInputStream in;
	Match match;
	int id;
	
	public Input(Match match, int id){
		super("Input(" + id + ")" );
		this.match = match;
		this.id = id;
	}
	
	public void run(){
		try {
			in = new ObjectInputStream(match.players[id-1].socket.getInputStream());
		} catch (IOException e) {e.printStackTrace();}
		
		//loop
		while(true){
			updateMatchState();
		}
	}
	
	public void updateMatchState(){
		PlayerState player_state = recivePlayerState();
		if(id == 1){
			match.data_match.setP1posX(player_state.getPos());
			if(player_state.isGoal()){
				match.data_match.incP2goal();
				System.out.println("Player2 make a goal");
			}
			if(player_state.getShoot() != 0){
				match.pilota.shoot(player_state.getShoot());
			}
		}
		if(id == 2){
			match.data_match.setP2posX(player_state.getPos());
			if(player_state.isGoal()){
				match.data_match.incP1goal();
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
			player_state = (PlayerState) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("player(" + id + ") posX = " + player_state.getPos());
		return player_state;
	}

}

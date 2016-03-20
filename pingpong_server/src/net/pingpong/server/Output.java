package net.pingpong.server;

import java.io.IOException;
import java.io.ObjectOutputStream;

import net.pingpong.lib.MatchState;

public class Output extends Thread{
	ObjectOutputStream out;
	Match match;
	int id;
	public Output(Match match, int id){
		super("Output(" + id + ")" );
		this.match = match;
		this.id = id;
	}
	public void run(){
		try {
			System.out.println(id);
			out = new ObjectOutputStream(match.players[id-1].socket.getOutputStream());
		} catch (IOException e) {e.printStackTrace();}
		
		//loop
		while(true){
			sendMatchState();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void sendMatchState(){
		MatchState match_state = new MatchState();
		if(id == 1){
			match_state.setRposX(match.data_match.getP2posX());
			
			match_state.setLgoal(match.data_match.getP1goals());
			match_state.setRgoal(match.data_match.getP2goals());
			
			match_state.setBpos(match.data_match.getBposX(), match.data_match.getBposY());
		}
		if(id == 2){
			match_state.setRposX(match.data_match.getP1posX());
			
			match_state.setLgoal(match.data_match.getP2goals());
			match_state.setRgoal(match.data_match.getP1goals());
			
			match_state.setBpos(match.data_match.getBposX(), match.data_match.getBposY());

		}
		try {
			out.writeObject(match_state);
			out.flush();
			out.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

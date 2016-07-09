package net.pingpong.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import net.pingpong.lib.GameParameters;
import net.pingpong.lib.MatchState;

public class Output extends Thread{
	DatagramSocket socket;
	Match match;
	byte[] buffer = new byte[256];
	
	public Output(Match match){
		super("Output");
		this.match = match;
	}
	public void run(){
		try {
			socket = new DatagramSocket(GameParameters.SERVER_PORT_OUT);
		} catch (SocketException e) {e.printStackTrace();}
		System.out.println("Output");
		//loop
		while(true){
			for(int i=0; i < match.clients; i++){
				sendMatchState(i);
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	//122
	public void sendMatchState(int i){
		MatchState match_state = new MatchState();
		match_state.set(match.players[invertID(i)].getPos(), match.ball.getPosX(i+1), match.ball.getPosY(i+1),
				match.players[i].getGoals(), match.players[invertID(i)].getGoals(), match.pause);
		buffer = match_state.toByte();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, 
				match.players[i].getIP(), GameParameters.CLIENT_PORT_IN);
		try {
			socket.send(packet);
		} catch (IOException e) {e.printStackTrace();}
		
	}
	public int invertID(int id){
		if(id == 0){return 1;}
		if(id == 1){return 0;}
		return 0;
	}
}

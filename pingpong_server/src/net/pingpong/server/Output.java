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
			if(match.init){
				for(int i=0; i < 1; i++){
					sendMatchState(i);
				}
			}
			else{System.out.println("Output waiting");}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	public void sendMatchState(int i){
		MatchState match_state = new MatchState();
		match_state.setRposX(match.players[i].getPos());
		buffer = match_state.toByte();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, 
				match.players[i].getIP(), GameParameters.CLIENT_PORT_IN);
		try {
			socket.send(packet);
		} catch (IOException e) {e.printStackTrace();}
		
	}
}

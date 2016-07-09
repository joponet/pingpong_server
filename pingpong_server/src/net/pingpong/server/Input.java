package net.pingpong.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import net.pingpong.lib.GameParameters;
import net.pingpong.lib.PlayerState;

public class Input extends Thread{
	DatagramSocket socket;
	DatagramPacket packet;
	Match match;
	byte[] buffer = new byte[256];
	
	public Input(Match match){
		super("Input");
		this.match = match;
	}
	
	public void run(){
		//init
		try {
			socket = new DatagramSocket(GameParameters.SERVER_PORT_IN);
		} catch (SocketException e) {e.printStackTrace();}
		System.out.println("conectat: " + socket.getInetAddress());
		packet = new DatagramPacket(buffer, buffer.length);
		addPlayer();
		System.out.println("start");
		//loop
		while(true){
			try {
				socket.receive(packet);
			} catch (IOException e) {e.printStackTrace();}
			updateMatchState();
		}
	}
	
	public void updateMatchState(){
		PlayerState player_state = new PlayerState();
		player_state.set(packet.getData());
		int id = 0;
		if(packet.getAddress().equals(match.players[0].getIP())){id = 1;}
		if(packet.getAddress().equals(match.players[1].getIP())){id = 2;}
		if(id != 0){
			match.players[(id - 1)].setPos(player_state.getPos());
			//System.out.println("pos: " + player_state.getPos());
			if(player_state.isGoal()){
				//match.players[invertID(id-1)].incGoals();
				match.ball.goal(id);
			}
			else if(player_state.getShoot()){
				match.ball.shoot(id);
			}
		}
		else{System.out.println("Error with the ip");}
	}
	
	public void addPlayer(){
		try {
			socket.receive(packet);
		} catch (IOException e) {e.printStackTrace();}
		match.players[0] = new Player(1 , packet.getAddress());
		match.players[1] = new Player(2);
		match.clients++;
		System.out.println("jugador 1 conectat");
		boolean loop = true;
		while(loop){
			try {
				socket.receive(packet);
			} catch (IOException e) {e.printStackTrace();}
			if(!packet.getAddress().equals( match.players[0].getIP())){
				match.players[1].setIP(packet.getAddress());
				System.out.println("jugador 2 conectat");
				loop = false;
			}
		}
		match.clients++;
		match.pause = false;
	}
	public int invertID(int id){
		if(id == 0){return 1;}
		if(id == 1){return 0;}
		return 0;
	}
}

package net.pingpong.server;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class Player{
	int id;
	InetAddress ip;
	int pos;
	int goals;
	
	public Player(int id , InetAddress ip){
		this.id = id;
		this.ip = ip;
	}
	
	public void setPos(int x){
		pos = x;
	}
	public int getPos(){
		return pos;
	}
	public int getGoals(){
		return goals;
	}
	public void incGoals(){
		goals++;
	}
	public InetAddress getIP(){
		return ip;
	}
	public int getID(){
		return id;
	}
}

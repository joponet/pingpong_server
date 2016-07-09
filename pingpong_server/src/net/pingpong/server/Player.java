package net.pingpong.server;

import java.net.InetAddress;

import net.pingpong.lib.GameParameters;

public class Player{
	int id;
	InetAddress ip;
	int pos;
	int goals;
	
	public Player(int id , InetAddress ip){
		this.id = id;
		this.ip = ip;
	}
	
	public Player(int id){
		this.id = id;
		pos = 122;
	}
	
	public void setIP(InetAddress ip){
		this.ip = ip;
	}
	
	public void setPos(int x){
		pos = x;
	}
	public int getPos(){
		return GameParameters.GAME_WIDTH - GameParameters.PLAYER_WIDTH - pos;
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

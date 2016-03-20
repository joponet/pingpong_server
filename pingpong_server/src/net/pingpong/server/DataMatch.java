package net.pingpong.server;

public class DataMatch {
	int posX_P1;
	int posX_P2;
	int posX_Ball , posY_Ball;  
	int goals_P1 , goals_P2;
	boolean pause;
	
	//player1
	public int getP1posX(){
		return posX_P1;
	}
	public void setP1posX(int pos){
		posX_P1 = pos;		
	}
	
	//Player2
	public int getP2posX(){
		return posX_P2;
	}
	public void setP2posX(int pos){
		posX_P2 = pos;		
	}
	
	//Goals
	public int getP1goals(){
		return goals_P1;
	}
	public void incP1goal(){
		goals_P1++;
	}
	public int getP2goals(){
		return goals_P2;
	}
	public void incP2goal(){
		goals_P2++;
	}
	
	//Ball
	public int getBposX(){
		return posX_Ball;
	}
	public void setBposX(int pos){
		posX_Ball = pos;
	}
	public int getBposY(){
		return posY_Ball;
	}
	public void setBposY(int pos){
		posY_Ball = pos;
	}
	
	//Pause
	public void pause(){
		pause = true;
	}
	public void start(){
		pause = false;
	}
	public boolean isPaused(){
		return pause;
	}
}

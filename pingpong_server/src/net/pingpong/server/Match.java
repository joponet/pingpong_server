package net.pingpong.server;

import net.pingpong.lib.Pilota;
import net.pingpong.lib.Tick;

public class Match {
	Pilota pilota;
	Player[] players;
	boolean init = false;
	
	public void start(){

		pilota = new Pilota();
		players = new Player[2];
		new Input(this).start();
		new Output(this).start();
	}
	/*
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
	*/
}



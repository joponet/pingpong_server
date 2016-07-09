package net.pingpong.server;

import net.pingpong.lib.GameParameters;
import net.pingpong.lib.Tick;

public class Match {
	Pilota ball;
	Player[] players;
	int clients = 0;
	boolean pause = true;
	
	public void start(){
		ball = new Pilota();
		ball.init(GameParameters.GAME_WIDTH, GameParameters.GAME_HEIGHT);
		players = new Player[2];
		new Input(this).start();
		new Output(this).start();
		run();
	}
	
	public void run(){
		int timer=0;
		Tick tick = new Tick();
		tick.start();
		ball.start();
		while(true){
			//System.out.println("pilota");
			if(tick.update()){
				if(!pause){
					ball.tick();
					int goal = ball.getGoal();
					if(goal == 1){
						players[0].incGoals();
						System.out.println("Gol del jugador 1");
						pause = true;
					}
					if(goal == 2){
						players[1].incGoals();
						System.out.println("Gol del jugador 2");
						pause = true;
					}
				}
				else if (timer > 0) {
					timer--;
					if (timer==0) {
						ball.reset();
						ball.start();
						pause = false;
					}
				}
				else if (clients == 2 && timer == 0) {timer=500;}
			}
		}
	}
}



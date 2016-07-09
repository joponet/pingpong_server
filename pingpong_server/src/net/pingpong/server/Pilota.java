package net.pingpong.server;

import net.pingpong.lib.GameParameters;

public class Pilota {
	final int width = GameParameters.BALL_WIDTH;
	final int height = GameParameters.BALL_HEIGHT;
	int xmax;
	int xmin;
	int ymax;
	int ymin;
	int x;
	int y;
	double xd;
	double yd;
	int xa;
	int ya;
	int delaystart=GameParameters.DELAYSTART;
	double speed;
	int goal; // 0: no goal, 1:goal playerl, 2: goal player2
	boolean stop;
	public boolean active;
	boolean reset;
	
	public void init(int groundWidth, int groundHeight) {
		xmax = groundWidth-width;
		xmin=0;
		//ymax = GameParameters.HEIGHT-height-1;
		ymax = groundHeight-height;
		ymin = 0;
		xa=1;
		ya=1;
		reset();
		stop=true;
		active=false;
	}
	
	public void reset() {
		speed = GameParameters.SPEED;
		goal=0;
		reset=false;
		x = xmax/2;
		xd=x;
		y = ymax/2;
		yd=y;
	}
	
	public void start() {
		stop=false;
		delaystart = GameParameters.DELAYSTART;
	}
	
	public void stop() {
		stop=true;
	}
	
	public boolean stopped() {
		return stop;
	}
	
	public void tick() {
		if (stop) return;
		if (goal>0) reset();
		if (reset) reset();
		if (delaystart>0) {
			delaystart--;
			return;
		}
		
		xd +=xa*speed;
		x = (int) xd;
		yd +=ya*speed;
		y = (int) yd;
		
		if (x<=xmin) {
			xa=1;
		}

		if (x>=xmax) {
			xa=-1;
		}

		if (y<ymin) {
			y=ymin;
//			stop=true;
//			goal=1;
//			ya=1;
		}
		
		if (y>ymax) {
			y=ymax;
//			stop=true;
//			ya=-1;
//			goal=2;
		}
	}	
	
	public int getGoal () {
		return goal;
	}
	
	public void shoot(int id) {
		if (((id==1) && (ya==1)) || (id==2) && (ya==-1)) speed += (speed<GameParameters.MAXSPEED)?GameParameters.INCSPEED:0;
		if(id == 1){ya = -1;}
		if(id == 2){ya = 1;}
		
	}
	public int get_ymin () {
		return y;
	}
	
	public int get_ymax () {
		return y+height;
	}

	public int get_xmin () {
		return x;
	}
	
	public int get_xmax () {
		return x+width;
	}

	public int get_ya () {
		return ya;
	}
	
	public int get_centerX () {
		return x+(width/2);
	}
	public int getPosX(int id){
		if(id == 1){return x;}
		if(id == 2){return GameParameters.GAME_WIDTH - GameParameters.BALL_WIDTH - x;}
		return 0;
	}
	public int getPosY(int id){
		if(id == 1){return y;}
		if(id == 2){return GameParameters.GAME_HEIGHT -GameParameters.BALL_HEIGHT - y;}
		return 0;
	}
	public void goal(int id){
		stop = true;
		if(id == 1){ya = 1;}
		if(id == 2){ya = -1;}
		if(goal == 0){goal = id;}
	}
}

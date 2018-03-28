package game;

import entity.Entity;

public class Camera {
	
	private int x, y;
	
	public void update(Entity ball) {
		setX(-ball.getX() + Game.WIDTH * Game.SCALE / 2);
	//	setY(-ball.getY() + Game.HEIGHT * Game.SCALE / 2);
		
	}
	public void update2(Entity ball){
		setY(-ball.getY() + Game.HEIGHT * Game.SCALE / 2);
			
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}

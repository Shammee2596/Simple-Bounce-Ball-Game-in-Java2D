package entity;

import java.awt.Graphics;

import game.Game;
import game.Handler;
import game.ID;

public class Enimy extends Entity {

	public Enimy(int x, int y, int width, int height, boolean solid, ID id,
			Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}
	
	public void render(Graphics g) {
		
		g.drawImage(Game.enimy.getImage(), x, y, width, height, null);
	}

	public void update() {
		//x += dx;
		//y += dy;
	}
	
}

package entity;

import java.awt.Graphics;
import java.util.Random;
import tile.Tile;
import game.Game;
import game.Handler;
import game.ID;

public class Spider extends Entity {

	public Spider(int x, int y, int width, int height, boolean solid, ID id,
			Handler handler) {
		super(x, y, width, height, solid, id, handler);
		Random random = new Random();
		int direction = random.nextInt(2);
		switch(direction){
		case 0:
			setDY(-2);
			break;
		
		case 1:
			setDY(2);
			break;
		}
		
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.spider.getImage(), x, y, width, height, null);
	}

	@Override
	public void update() {
		/*x += dx;
		y += dy;
		
		if (x <= 0)x = 0;
		if (y <= 0)y = 0;
		
		for (Tile t: handler.tile) {
			if (!t.isSolid())
				continue;
			
			if (t.getID() == ID.wall ) {
				if (getBoundsTop().intersects(t.getBounds())) {
					setDY(0);
					y = t.getY() + t.getHeight();
					if (jumping) {
						jumping = false;
						gravity = 0.08;
						falling = true;
					}
				}
				if (getBoundsBottom().intersects(t.getBounds())) {
					setDY(0);
					if (falling) falling = false;
					y = t.getY() - height;
				}
			 }
			}*/
		
		if (jumping) {
			gravity -= 0.08;
			setDY((int)-gravity);
			if (gravity <= 1.0) {
				jumping = false;
				falling = true;
			}
		}
		if (falling) {
			gravity += 0.08;
			setDY((int)gravity);
		}
		
	}
}

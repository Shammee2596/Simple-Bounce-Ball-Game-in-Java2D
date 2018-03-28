package entity;

import java.awt.Graphics;
import java.util.logging.Level;

import game.Game;
import game.Handler;
import game.ID;
import tile.Tile;

public class Ball extends Entity {
	

	public Ball(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	public void render(Graphics g) {
		g.drawImage(Game.ball.getImage(), x, y, width, height, null);
	}
	int count = 0;
	
	
	public void update() {
		
		x += dx;
		y += dy;
		
		if (x <= 0)x = 0;
		if (y <= 0)y = 0;
		
		for (Tile t: handler.tile) {
			if (!t.isSolid())
				continue;
			
			if (t.getID() == ID.wall || t.getID() == ID.rings) {
				if (getBoundsTop().intersects(t.getBounds()) && t.getID() != ID.rings) {
					setDY(0);
					y = t.getY() + t.getHeight();
					if (jumping) {
						jumping = false;
						gravity = 1.0;
						falling = true;
					}
				}
				if (getBoundsBottom().intersects(t.getBounds()) && t.getID() != ID.rings) {
					setDY(0);
					if (falling)
						falling = false;
					y = t.getY() - height;
				} 
				else {
					if (!falling && !jumping) {
						gravity = 1.0;
						falling = true;
					}
				}
				if (getBoundsLeft().intersects(t.getBounds()) && t.getID() != ID.rings) {
					setDX(0);
					x = t.getX() + t.getWidth();
				}
				if (getBoundsRight().intersects(t.getBounds()) && t.getID() != ID.rings) {
					setDX(0);
					x = t.getX() - width;
				}
			}
			if(getBounds().intersects(t.getBounds())&& t.getID() != ID.rings){
				if(t.getID() == ID.flag){
					if(Game.level == 0 && count == 6)
						Game.nextLevel();
					else if(Game.level == 1 && count == 7)
						Game.nextLevel();
					else if(Game.level == 2 && count == 7)
						Game.nextLevel();
					else if(Game.level == 3 && count == 6)
						Game.nextLevel();
				}
			}
		}
		for(int i =0;i<handler.entity.size();i++){
			
			Entity e = handler.entity.get(i);
			if(e.getId() == ID.enemy){
				if(getBounds().intersects(e.getBounds())){
					this.die();
				}
			}
		}
		for(int i =0;i<handler.entity.size();i++){
			
			Entity e = handler.entity.get(i);
			if(e.getId() == ID.spider){
				if(getBounds().intersects(e.getBounds())){
					Game.rings -= 50; 
					this.die();
				}
			}
		}
	
		for(int i =0;i<handler.tile.size();i++){
			
			Tile t = handler.tile.get(i);
			if(t.getID() == ID.rings){
				if(getBounds().intersects(t.getBounds())){
					count++;
					Game.rings += 50 ;
					t.die();
				}
			}
		}	
		if (jumping) {
			gravity -= 0.12;
			setDY((int)-gravity);
			if (gravity <= 1.0) {
				jumping = false;
				falling = true;
			}
		}
		if (falling) {
			gravity += 0.12;
			setDY((int)gravity);
		}
		
	 }
	
}

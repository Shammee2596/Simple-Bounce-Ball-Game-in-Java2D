package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;
import game.Handler;
import game.ID;

public abstract class Entity {
	
	protected int x, y;
	protected int width;
	protected int height;
	protected int dx, dy;
	protected Handler handler;
	
	private boolean solid = false;
	private ID id;
	
	protected boolean jumping = false;
	
	protected boolean falling = true;
	
	public double gravity = 0.0;
	
	public Entity(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.id = id;
		this.handler = handler;
	}
	
	public abstract void render(Graphics g);
	
	public abstract void update();
	
	public void die(){
		handler.removeEntity(this);
		Game.lives--;
		Game.showDeathScreen = true;
		if(Game.lives <= 0){
			Game.gameOver = true;
		}
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

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public void setDX(int dx) {
		this.dx = dx;
	}

	public void setDY(int dy) {
		this.dy = dy;
	}

	public ID getId() {
		return id;
	}
	
	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle(x + 10, y, width-20, 5);
	}
	
	public Rectangle getBoundsBottom(){
		return new Rectangle(x + 10, y + height - 5, width-20, 5);
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle(x, y + 10, 5, height - 20);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle(x + width - 5, y + 10, 5, height - 20);
	}
	
}

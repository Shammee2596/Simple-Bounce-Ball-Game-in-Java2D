package tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import game.ID;

public abstract class Tile {
	
	protected int x, y;
	protected int width, height;
	private int dx, dy;
	private Handler handler;
	
	private boolean solid = false;
	private ID id;
	
	public Tile(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		
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
		handler.removeTile(this);
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

	public void setDX(int DX) {
		this.dx = DX;
	}

	public void setDY(int DY) {
		this.dy = DY;
	}

	public ID getID() {
		return id;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
}

package menu;

import game.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Button {
	
	public int x,y;
	public int width,height;
	public String lebel;
	
	public Button(int x, int y, int width, int height, String lebel) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.lebel = lebel;
	}
	
	public void render(Graphics g){
		
		// create button
		g.setColor(new Color(182,182,182));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		
		
		// create lebel
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier",Font.BOLD,30));
		
		g.drawString(getLebel(),getX()+10, getY()+35);
		
	}
	
	public void triggerEvent(){
		
		if(getLebel().toLowerCase().contains("start game")){
			Game.playing = true;
		}
		else if(getLebel().toLowerCase().contains("exit game")){
			System.exit(0);
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getLebel() {
		return lebel;
	}
	public void setLebel(String lebel) {
		this.lebel = lebel;
	}
	
}

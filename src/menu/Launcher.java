package menu;

import game.Game;

import java.awt.Color;
import java.awt.Graphics;

public class Launcher {
	
	public Button[] buttons;
	
	public Launcher(){
		
		buttons = new Button[2];
		
		buttons[0] = new Button(300, 130,200, 60, "START GAME");
		buttons[1] = new Button(300, 200, 200, 60, "EXIT GAME");
		
	} 
	public void render(Graphics g){
		
		g.setColor(new Color(63,210,255));
		g.fillRect(0, 0, Game.getFrameWidth()+10, Game.getFrameHeight()+10);
		
		for(int i = 0;i<buttons.length;i++){
			buttons[i].render(g);
		}
		
	}
}

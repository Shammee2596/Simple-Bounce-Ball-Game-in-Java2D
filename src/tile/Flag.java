package tile;

import java.awt.Graphics;

import game.Game;
import game.Handler;
import game.ID;

public class Flag extends Tile{

	public Flag(int x, int y, int width, int height, boolean solid, ID id,
			Handler handler) {
		super(x, y, width, height, solid, id, handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Game.flag.getImage(),getX(),getY() ,30, 64,null);
	}

	public void update() {
		
	}

}

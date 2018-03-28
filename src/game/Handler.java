package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

import entity.Ball;
import entity.Enimy;
import entity.Entity;
import entity.Spider;
import tile.Flag;
import tile.Ring;
import tile.Tile;
import tile.Wall;

public class Handler {
	
	public LinkedList<Entity> entity = new  LinkedList<Entity>();
	public LinkedList<Tile> tile = new LinkedList<Tile>();
	
	public Handler(){
		//createLevel();
	}
	
	public void render(Graphics g){
		for (Entity e : entity){
			e.render(g);
		}
		for (Tile t : tile){
			t.render(g);
		}
	}
	
	public synchronized void update(){
		try{
		for (Entity e : entity){
			e.update();
		}
		for (Tile t : tile){
			t.update();
		}
		}catch(ConcurrentModificationException e){
			//System.out.println(e);
		}
	}
	
	public void addEntity(Entity e){
		entity.add(e);
	}
	
	public void removeEntity(Entity e){
		entity.remove(e);
	}
	
	public void addTile(Tile t){
		tile.add(t);
	}
	
	public void removeTile(Tile t){
		tile.remove(t);
	}
	
	public void clearLevel(){
		
		entity.clear();
		tile.clear();
	}
	
	public void createLevel(BufferedImage level){
		
		int width = level.getWidth();
		int height = level.getHeight();
		
		for(int y=0;y<height;y++){
			for(int x =0;x<width;x++){
				
				int pixel = level.getRGB(x, y);
				int red = (pixel >> 16 )& 0xff;
				int green = (pixel >> 8 )& 0xff;
				int blue = (pixel  )& 0xff;
				
				if(red ==0 && green ==0 && blue == 0)
					addTile(new Wall(x*64, y*64, 64, 64, true, ID.wall, this));
				
				if(red ==0 && green == 255 && blue == 33)
					addEntity(new Ball(x*64, y*64, 50, 50, true, ID.ball, this));
				
				if(red ==255 && green == 0 && blue == 0)
					addEntity(new Enimy(x*64, y*64, 40, 64, true, ID.enemy, this));
				
				if(red ==255 && green == 106 && blue == 0)
					addTile(new Ring(x*64, y*64, 30, 64, true, ID.rings, this));
				
				if(red ==255 && green == 0 && blue == 220)
					addTile(new Flag(x*64, y*64, 64 , 64, true, ID.flag, this));
				
				if(red == 178 && green == 0 && blue == 255)
					addEntity(new Spider(x*64, y*64, 64, 64, true, ID.spider, this));
				
			}
		}
		
		/*if(Game.level ==0){
		
		// for horizental lines
	    for (int i = 0; i < 20; i++){
	    	addTile(new Wall(i*64, 0, 64, 64, true, ID.wall, this));
	    	addTile(new Wall(i*64, Game.HEIGHT*Game.SCALE-64, 64, 64, true, ID.wall, this));
		
	    }
	    // vertical's line
	    for (int i = 0; i < Game.HEIGHT*Game.SCALE/64; i++){
	    	addTile(new Wall(0, i*64, 64, 64, true, ID.wall, this));
	    	addTile(new Wall(1216  ,i*64 , 64, 64, true, ID.wall, this));
	    }
	    for (int i = 4; i < 8; i++) {
	    	addTile(new Wall(i*30, 290, 50, 30, true, ID.wall, this));

	    }
	    for (int i = 13; i < 17; i++) {
	    	addTile(new Wall(i*64, 350, 64, 30, true, ID.wall, this));

	    }
	    
	    
	}
	if(Game.level == 1){
		
		// for horizental lines
	    for (int i = 6; i < 40; i++){
	    	addTile(new Wall(i*64, 288-64, 64, 64, true, ID.wall, this));
	    	addTile(new Wall(i*64, Game.HEIGHT*Game.SCALE + 224, 64, 64, true, ID.wall, this));
		
	    }
		for (int i = 4; i < 18; i++){ // 15 = Game.HEIGHT*Game.SCALE
		    addTile(new Wall(384, i*64, 64, 64, true, ID.wall, this));
		    addTile(new Wall(2496,i*64 , 64, 64, true, ID.wall, this)); // 384 = 6*64;cos x sarts at 384
		}
		for (int i = 8; i < 12; i++) {
		    addTile(new Wall(i*64, 500, 64, 30, true, ID.wall, this));

		}
	    for (int i = 20; i < 25; i++) {
		    addTile(new Wall(i*64, 700, 64, 30, true, ID.wall, this));

	   }
	}*/
		
	}
}

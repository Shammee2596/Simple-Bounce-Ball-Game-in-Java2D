package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Entity;
import game.Game;

public class KeyInput implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (Entity en: Game.handler.entity) {
			if (key == KeyEvent.VK_UP){
				if (!en.isJumping() ) {
					en.setJumping(true);
					en.gravity = 9.0;
				}
			}
			else if (key == KeyEvent.VK_DOWN){
			}
			else if (key == KeyEvent.VK_LEFT){
				en.setDX(-5);
			}
			else if (key == KeyEvent.VK_RIGHT){
				en.setDX(5);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (Entity en: Game.handler.entity) {
			if (key == KeyEvent.VK_UP){
				en.setDY(0);
			}
			else if (key == KeyEvent.VK_DOWN){
			}
			else if (key == KeyEvent.VK_LEFT){
				en.setDX(0);
			}
			else if (key == KeyEvent.VK_RIGHT){
				en.setDX(0);
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}

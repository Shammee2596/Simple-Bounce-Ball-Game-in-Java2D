package menu;

import game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener,MouseMotionListener {

	public int x,y;
	
	public void mouseDragged(MouseEvent e) {
		
		
	}

	public void mouseMoved(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();
	}

	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
	
		
	}

	public void mousePressed(MouseEvent e) {
		
		for(int i =0; i < Game.launcher.buttons.length;i++){
			Button button = Game.launcher.buttons[i];
			
		if(x >= button.getX()  &&x <= button.getX()+ button.getWidth()
				&& y >= button.getY() && y <= button.getY()+ button.getHeight())
			
			button.triggerEvent();
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

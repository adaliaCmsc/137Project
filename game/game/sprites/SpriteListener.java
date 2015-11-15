package game.sprites;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.gui.GameGUI;

public class SpriteListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		final int x = (int) MouseInfo.getPointerInfo().getLocation().getX()-10;
		final int y = (int) MouseInfo.getPointerInfo().getLocation().getY()-30;
		
		Sprite sprite;
		if(arg0.isControlDown()){
			sprite = new Sprite(x, y, GameGUI.sprites.size(), 0);
		}
		else{
			sprite = new Sprite(x, y, GameGUI.sprites.size(), 1);
		}
		GameGUI.sprites.add(sprite);
		GameGUI.gamePanel.revalidate();
		GameGUI.gamePanel.repaint();
		System.out.println("Sprite has been created!");
		System.out.println("Total Sprites: " + GameGUI.sprites.size());
		sprite.start();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}

package game.sprites;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.gui.GameGUI;

public class Sprite extends Thread{
	int index; // Index of the sprite in the container
	int playerid; // Id of whoever owns the this sprite
	
	// Attributes of this sprite
	int width = 30;
	int height = 30;
	int life = 100;
	int armor = 5;
	int attack = 15;
	double attackSpeed = 0.4;
	
	// Position of the sprite in the game panel
	int xBound;
	int yBound;
	
	JLabel container = new JLabel();
	
	public Sprite(int xBound, int yBound, int index, int playerid){
		this.index = index;
		this.xBound = xBound;
		this.yBound = yBound;
		this.playerid = playerid;
		
		initialize();
	}
	
	public void initialize(){
		this.container.setText(Integer.toString(this.life));
		this.container.setVerticalAlignment(SwingConstants.CENTER);
		this.container.setHorizontalAlignment(SwingConstants.CENTER);
		this.container.setBounds(this.xBound, this.yBound, this.width, this.height);
		if(this.playerid == 0) this.container.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		else this.container.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		GameGUI.gamePanel.add(this.container);
	}
	
	public void attack(){
		this.life -= (this.attack-this.armor);
	}
	
	public int getClosestSprite(){
		// Gets the closest sprite with reference to this sprite and goes to it.
		int minIndex = -1;
		double minDistance = -1;
		for(int i = 0; i < GameGUI.sprites.size(); i++){
			Sprite sprite = GameGUI.sprites.get(i);
			// The sprite must not be itself, must be an enemy sprite, and alive
			if((i != this.index) && (sprite.playerid != this.playerid) && (sprite.life > 0)){
				double xx = (sprite.xBound - this.xBound)*(sprite.xBound - this.xBound);
				double yy = (sprite.yBound - this.yBound)*(sprite.yBound - this.yBound);
				double distance = Math.sqrt(xx+yy);

				if(i != this.index) minDistance = distance;
				if(minDistance >= distance){
					minIndex = i;
					minDistance = distance;
				}
				/*
				 * Uncomment for debugging purposes
				 * 
					System.out.println("Index: " + i);
					System.out.println("This: " + this.playerid + " To: " + sprite.playerid);
					System.out.println("X: " + sprite.xBound + " Y: " + sprite.yBound);
					System.out.println("XX: " + xx + " YY: " + yy);
					System.out.println("Distance: " + minDistance);
				*/
			}
		}
		return minIndex;
	}
	
	@Override
	public void run(){
		// Finds the index of the closest sprite in the list
		int index = this.getClosestSprite();
		while(index == -1){
			try {
				Thread.sleep(1000);
				index = this.getClosestSprite();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Sprite trail = GameGUI.sprites.get(index);
		int increment = 1;
		int trailx = trail.xBound;
		int traily = trail.yBound;
		while(true){
			try{
				// Gets the distance of the two sprites
				double distance = Math.sqrt(((this.xBound - trailx)*(this.xBound - trailx)) - ((this.yBound - traily)*(this.yBound - traily)));
				// Moves the sprites if an enemy sprite has been found.
				if(trail.playerid != this.playerid){
					if(this.xBound < trailx - width) this.xBound += increment;
					if(this.xBound > trailx + width) this.xBound -= increment;
					if(this.yBound < traily - height) this.yBound += increment;
					if(this.yBound > traily + height) this.yBound -= increment;
				}
				container.setBounds(xBound, yBound, width, height);
				// Finds the next closest sprite. This can be the same sprite or a new target
				index = this.getClosestSprite();
				while(index == -1){
					try {
						Thread.sleep(1000);
						index = this.getClosestSprite();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				trail = GameGUI.sprites.get(index);
				trailx = trail.xBound;
				traily = trail.yBound;
				Thread.sleep(10);
				
				// Compares the past distance and the present distance. If it doesn't change, it means 
				//		no movement has been done and the sprite proceed on attacking
				if(	Math.sqrt(((this.xBound - trailx)*(this.xBound - trailx)) - ((this.yBound - traily)*(this.yBound - traily))) == distance)
				{
					// The sprite attacks the last targeted sprite after finding it.
					while(true){
						try {
							System.out.println("[" + this.playerid + "] Life: " + trail.life);
							trail.attack();
							trail.container.setText(Integer.toString(trail.life));
							if(trail.life <= 0){
								System.out.println("You have won!");
								trail.container.setText("H");
								Thread.sleep(500);
								trail.container.setVisible(false);
								break;
							}
							if(this.life <= 0){
								System.out.println("Opponent won!");
								break;
							}
							Thread.sleep((int)(this.attackSpeed*1000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if(this.life <= 0) break;
				}
				/*
				System.out.println("this.x = " + this.xBound + " trailx: " + trailx);
				System.out.println("this.y = " + this.yBound + " traily: " + traily);
				System.out.println("Distance: " + distance);
				*/
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}

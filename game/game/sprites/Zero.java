package game.sprites;

import java.awt.CardLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Zero extends JPanel{
	
	final JPanel mainPanel = new JPanel(new CardLayout());
	final JPanel slash_right = new JPanel(new CardLayout());
	final JPanel slash_left = new JPanel(new CardLayout());

	public void initialize(){
		slash_right.setOpaque(false);
		slash_left.setOpaque(false);
		try {
			for(int i = 1; i <= 12; i++){
				slash_right.add(new JLabel(new ImageIcon(ImageIO.read(new File("src/sprites/zero/swordslash_right/"+i+".png")))));
				slash_left.add(new JLabel(new ImageIcon(ImageIO.read(new File("src/sprites/zero/swordslash_left/"+i+".png")))));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPanel.add(slash_right, "SS_RIGHT");
		mainPanel.add(slash_left, "SS_LEFT");
		mainPanel.setOpaque(false);
		this.setOpaque(false);
		this.add(mainPanel);
	}
	
	public void slash_right() throws InterruptedException{
		new Thread( new Runnable(){
			CardLayout mainCard = (CardLayout) mainPanel.getLayout();
			CardLayout cards = (CardLayout) slash_right.getLayout();
		    @Override
		    public void run() {
			    mainCard.show(mainPanel, "SS_RIGHT");
				slash_right.setVisible(true);
		    	cards.first(slash_right);
				for(int i = 0; i <= 12; i++){
					cards.next(slash_right);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				cards.first(slash_right);
				slash_right.setVisible(false);
		    }
		}).start();
	}
	
	public void slash_left() throws InterruptedException{
		new Thread( new Runnable(){
			CardLayout mainCards = (CardLayout) mainPanel.getLayout();
			CardLayout cards = (CardLayout) slash_left.getLayout();
		    @Override
		    public void run() {
		    	mainCards.show(mainPanel, "SS_LEFT");
		    	slash_left.setVisible(true);
		    	cards.first(slash_left);
				for(int i = 0; i <= 12; i++){
					cards.next(slash_left);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				cards.first(slash_left);
				slash_left.setVisible(false);
		    }
		}).start();
	}
}

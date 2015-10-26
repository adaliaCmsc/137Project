package game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import game.network.Listener;
import game.network.Transmitter;

public class GameGUI implements WritableGUI{
	final JTextArea chatArea = new JTextArea();
	final JTextArea chatFeed = new JTextArea();

	JFrame mainFrame = new JFrame("COC v1");
	JPanel mainPanel = new JPanel(new BorderLayout());
	JPanel gamePanel = new JPanel();
	JPanel sidePanel = new JPanel(new BorderLayout());		
	JPanel chatPanel = new JPanel(new BorderLayout());
	JPanel spritePanel = new JPanel(new GridLayout(7,3,2,2));
	
	public GameGUI(){
		this.initialize();
	}
	
	public void initialize(){
		Color themeColor = new Color(102, 204, 153);
		chatPanel.setBackground(Color.YELLOW);
		spritePanel.setBackground(themeColor);
		gamePanel.setBackground(Color.BLUE);
		sidePanel.setBackground(Color.RED);
		
		JButton [] sprites = new JButton[21];
		for(int i = 0; i < 21; i++){
			sprites[i] = new JButton(""+i);
			spritePanel.add(sprites[i]);
		}
		spritePanel.setPreferredSize(new Dimension(200,280));
		spritePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JScrollPane chatAreaPane = new JScrollPane(chatArea);
		JScrollPane chatFeedPane = new JScrollPane(chatFeed);
	
		Listener listener;
		listener = new Listener(this, 4422);
		listener.start();
		System.out.println("Client Listener has been initialized!");
		
		chatFeed.setLineWrap(true);
		chatFeed.setEditable(false);
		chatArea.setLineWrap(true);
		
		chatArea.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e){}
			
			@Override
			public void keyPressed(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					Transmitter transmitter;
					String text = chatArea.getText();
					if(text.equals("!quit\n")){
						transmitter = new Transmitter(null, null, "192.168.1.5", 4422);
						System.out.println("You have terminated the connection.");
					}
					else transmitter = new Transmitter(null, text, "192.168.1.5", 4422);
					transmitter.start();
					chatArea.setText("");
				}
			}
		});
		
		chatPanel.add(chatFeedPane, BorderLayout.CENTER);
		chatPanel.add(chatAreaPane, BorderLayout.SOUTH);
		chatPanel.setPreferredSize(new Dimension(200, 200));
		
		sidePanel.add(spritePanel, BorderLayout.NORTH);
		sidePanel.add(chatPanel, BorderLayout.SOUTH);
		sidePanel.setPreferredSize(new Dimension(200, 200));
		
		mainPanel.add(gamePanel, BorderLayout.CENTER);
		mainPanel.add(sidePanel, BorderLayout.EAST);
		mainPanel.setPreferredSize(new Dimension(640, 480));
		mainPanel.setVisible(true);
		
		mainFrame.add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.pack();
	}
	
	@Override
	public void write(String string) {
		chatFeed.append(string+"\n");
	}
}

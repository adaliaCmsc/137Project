package game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import game.network.Listener;
import game.network.Transmitter;
import game.sprites.Sprite;
import game.sprites.SpriteListener;

public class GameGUI implements WritableGUI{
	final JTextArea chatArea = new JTextArea();
	final JTextArea chatFeed = new JTextArea();
	JFrame mainFrame = new JFrame("COC");
	JPanel mainPanel = new JPanel(new BorderLayout());
	public static JPanel gamePanel = new JPanel(null);
	public static ArrayList <Sprite> sprites = new ArrayList<Sprite>();
	JPanel sidePanel = new JPanel(new BorderLayout());		
	JPanel chatPanel = new JPanel(new BorderLayout());
	JPanel spritePanel = new JPanel(new GridLayout(3,3,2,2));
	public static boolean isConnected = false;
	public static JButton [] spritesSelector = new JButton[9];
	
	public static String serverIP;
	public static int port = 4444;
	
	public GameGUI(){
		Listener listener = new Listener(this, port);
		listener.start();
		System.out.println("Client Listener has been initialized!");
		
		GameGUI.serverIP = JOptionPane.showInputDialog(mainFrame, "Input Server IP:", "192.168.1.5").toString();
		GameGUI.port = Integer.parseInt(JOptionPane.showInputDialog(mainFrame, "Input Port:", "4444").toString());
		Transmitter transmitter = new Transmitter("[COMMAND] [CONNECT]", serverIP, port);
		transmitter.start();
		System.out.println("Connect Request has been sent!");
		
		this.initialize();
	}
	
	public void initialize(){
		Color themeColor = new Color(102, 204, 153);
		final Random random = new Random();
		chatPanel.setBackground(Color.YELLOW);
		spritePanel.setBackground(themeColor);
		gamePanel.setBackground(Color.WHITE);
		sidePanel.setBackground(Color.RED);
		
		
		for(int i = 0; i < 9; i++){
			spritesSelector[i] = new JButton(""+i);
			spritesSelector[i].setFocusPainted(false);
			spritesSelector[i].setFocusable(false);
			spritePanel.add(spritesSelector[i]);
		}
		
		spritesSelector[0].setText(Integer.toString(random.nextInt(10)+1));
		spritesSelector[0].setBackground(Color.RED);
		spritesSelector[0].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int x = random.nextInt(gamePanel.getHeight()-60)+30;
				int y = random.nextInt(gamePanel.getWidth()-60)+30;
				
				Transmitter transmitter = new Transmitter("[COMMAND] [ADD] " + "0 "+ x + " " + y, serverIP, port);
				transmitter.start();
				int count = Integer.parseInt(spritesSelector[0].getText());
				if(count != 0) count -= 1;
				spritesSelector[0].setText(Integer.toString(count));
				System.out.println("Sprite request has been sent! (" + x + ", " + y + ")");
				if(count == 0) spritesSelector[0].setEnabled(false);
			}
		});
		
		spritesSelector[1].setText(Integer.toString(random.nextInt(10)+1));
		spritesSelector[1].setBackground(Color.ORANGE);
		spritesSelector[1].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int x = random.nextInt(gamePanel.getHeight()-60)+30;
				int y = random.nextInt(gamePanel.getWidth()-60)+30;
				
				Transmitter transmitter = new Transmitter("[COMMAND] [ADD] " + "1 "+ x + " " + y, serverIP, port);
				transmitter.start();
				int count = Integer.parseInt(spritesSelector[1].getText());
				if(count != 0) count -= 1;
				spritesSelector[1].setText(Integer.toString(count));
				System.out.println("Sprite request has been sent! (" + x + ", " + y + ")");
				if(count == 0) spritesSelector[1].setEnabled(false);
			}
		});
		
		spritesSelector[2].setText(Integer.toString(random.nextInt(10)+1));
		spritesSelector[2].setBackground(Color.YELLOW);
		spritesSelector[2].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int x = random.nextInt(gamePanel.getHeight()-60)+30;
				int y = random.nextInt(gamePanel.getWidth()-60)+30;
				
				Transmitter transmitter = new Transmitter("[COMMAND] [ADD] " + "2 "+ x + " " + y, serverIP, port);
				transmitter.start();
				int count = Integer.parseInt(spritesSelector[2].getText());
				if(count != 0) count -= 1;
				spritesSelector[2].setText(Integer.toString(count));
				System.out.println("Sprite request has been sent! (" + x + ", " + y + ")");
				if(count == 0) spritesSelector[2].setEnabled(false);
			}
		});
		
		spritesSelector[3].setText(Integer.toString(random.nextInt(10)+1));
		spritesSelector[3].setBackground(Color.GREEN);
		spritesSelector[3].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int x = random.nextInt(gamePanel.getHeight()-60)+30;
				int y = random.nextInt(gamePanel.getWidth()-60)+30;
				
				Transmitter transmitter = new Transmitter("[COMMAND] [ADD] " + "3 "+ x + " " + y, serverIP, port);
				transmitter.start();
				int count = Integer.parseInt(spritesSelector[3].getText());
				if(count != 0) count -= 1;
				spritesSelector[3].setText(Integer.toString(count));
				System.out.println("Sprite request has been sent! (" + x + ", " + y + ")");
				if(count == 0) spritesSelector[3].setEnabled(false);
			}
		});
		
		spritesSelector[4].setText(Integer.toString(random.nextInt(10)+1));
		spritesSelector[4].setBackground(Color.CYAN);
		spritesSelector[4].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int x = random.nextInt(gamePanel.getHeight()-60)+30;
				int y = random.nextInt(gamePanel.getWidth()-60)+30;
				
				Transmitter transmitter = new Transmitter("[COMMAND] [ADD] " + "4 "+ x + " " + y, serverIP, port);
				transmitter.start();
				int count = Integer.parseInt(spritesSelector[4].getText());
				if(count != 0) count -= 1;
				spritesSelector[4].setText(Integer.toString(count));
				System.out.println("Sprite request has been sent! (" + x + ", " + y + ")");
				if(count == 0) spritesSelector[4].setEnabled(false);
			}
		});
		
		spritesSelector[5].setText(Integer.toString(random.nextInt(10)+1));
		spritesSelector[5].setBackground(Color.MAGENTA);
		spritesSelector[5].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int x = random.nextInt(gamePanel.getHeight()-60)+30;
				int y = random.nextInt(gamePanel.getWidth()-60)+30;
				
				Transmitter transmitter = new Transmitter("[COMMAND] [ADD] " + "5 "+ x + " " + y, serverIP, port);
				transmitter.start();
				int count = Integer.parseInt(spritesSelector[5].getText());
				if(count != 0) count -= 1;
				spritesSelector[5].setText(Integer.toString(count));
				System.out.println("Sprite request has been sent! (" + x + ", " + y + ")");
				if(count == 0) spritesSelector[4].setEnabled(false);
			}
		});
		
		spritesSelector[6].setText("</3");
		spritesSelector[6].setEnabled(false);
		spritesSelector[7].setText("</3");
		spritesSelector[7].setEnabled(false);
		spritesSelector[8].setText("</3");
		spritesSelector[8].setEnabled(false);
		
		spritePanel.setPreferredSize(new Dimension(200,280));
		spritePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JScrollPane chatAreaPane = new JScrollPane(chatArea);
		JScrollPane chatFeedPane = new JScrollPane(chatFeed);
		
		chatFeed.setLineWrap(true);
		chatFeed.setEditable(false);
		chatArea.setLineWrap(true);
		gamePanel.addMouseListener(new SpriteListener());
		chatArea.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e){}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					Transmitter transmitter;
					String text = chatArea.getText();
					if(text.equals("!quit\n")){
						transmitter = new Transmitter(null, "192.168.1.5", 4444);
						System.out.println("You have terminated the connection.");
					}
					else transmitter = new Transmitter(text, "192.168.1.5", 4444);
					transmitter.start();
					chatArea.setText("");
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {}
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

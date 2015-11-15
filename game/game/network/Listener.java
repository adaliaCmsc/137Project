package game.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

import game.gui.GameGUI;
import game.gui.WritableGUI;
import game.sprites.Sprite;

public class Listener extends Thread{
	ServerSocket server;
	WritableGUI gui;
	int port = 4444;
	
	public Listener(WritableGUI gui, int port){
		this.port = port;
		this.gui = gui;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Listener(){
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		Socket clientSocket;
		String line;
		try {
			while((clientSocket = server.accept()) != null){
				InputStream input = clientSocket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				line = reader.readLine();
				
				if(Pattern.matches(".*\\[SERVER\\].*", line)){
					System.out.println(line);
					String [] command = line.split(" ");
					if(command[2].equals("[CONNECTED]")){
						GameGUI.isConnected = true;
						System.out.println("Connected to Server!\n");
					}
					if(command[2].equals("[ADD]")){
						Sprite sprite = new Sprite(Integer.parseInt(command[4]), Integer.parseInt(command[5]), GameGUI.sprites.size(), Integer.parseInt(command[6]), Integer.parseInt(command[3]));
						GameGUI.sprites.add(sprite);
						GameGUI.gamePanel.revalidate();
						GameGUI.gamePanel.repaint();
						
						System.out.println("Sprite has been created!");
						sprite.start();
					}
				}
				else{
					System.out.println(line);
					gui.write(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package game.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import game.gui.WritableGUI;

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
		try {
			while((clientSocket = server.accept()) != null){
				InputStream input = clientSocket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				String line = reader.readLine();
				
				if(line != null){
					System.out.println(line);
					gui.write(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

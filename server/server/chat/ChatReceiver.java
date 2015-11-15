package server.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatReceiver extends Thread{
	ServerSocket server;
	String ip;
	
	public ChatReceiver(String ip){
		try {
			this.server = new ServerSocket(4422);
			this.ip = ip;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		Socket clientSocket;
		try {
			while(true){
				clientSocket = server.accept();
				
				// Gets the receiving IP of format /192.198.xxx.xxx then gets the IP only.
				String receivingIP = clientSocket.getInetAddress().toString().split("/")[1];
				System.out.println("Connection with " + receivingIP + " has been made!");
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String line = reader.readLine();
				
				if(line != null){
					System.out.println(receivingIP + ": " + line);
					
					// Transmits the data back to the client
						ChatTransmitter transmitter = new ChatTransmitter(null, line, this.ip);
						transmitter.start();
				}
				else{
					System.out.println("Connection with " + receivingIP + " has been terminated!");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

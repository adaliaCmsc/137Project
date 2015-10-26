package server.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatReceiver extends Thread{
	ServerSocket server;
	ArrayList<String> ip;
	
	public ChatReceiver(){
		try {
			server = new ServerSocket(4422);
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
				
				if(!ip.contains(receivingIP)){
					System.out.println("Connection with " + receivingIP + " has been established!");
					this.ip.add(receivingIP);
				}
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String line = reader.readLine();
				
				if(line != null){
					System.out.println(receivingIP + ": " + line);
					
					if(line.equals("!print")){
						System.out.println("IP Addresses that are connected.");
						for(String ip : ip){
							System.out.println("--" + ip);
						}
					}
					// Transmits the data back to the client
					ChatTransmitter transmitter = new ChatTransmitter(null, line, receivingIP);
					transmitter.start();
				}
				else{
					System.out.println("Connection with " + receivingIP + " has been terminated!");
					ip.remove(receivingIP);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

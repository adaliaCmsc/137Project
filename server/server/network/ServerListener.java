package server.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerListener extends Thread{
	ServerSocket socket;
	String ip;
	ArrayList<String> ipPool = new ArrayList<String>();
	int port;
	
	public ServerListener(int port){
		this.port = port;
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Socket clientSocket;
		try {
			while(true){
				clientSocket = socket.accept();
				
				// Gets the receiving IP of format /192.198.xxx.xxx then gets the ip only.
				String receivingIP = clientSocket.getInetAddress().toString().split("/")[1];
				this.ip = socket.getInetAddress().getHostAddress().toString();
				System.out.println("uihsadfuhasdf: " + this.ip);
				// If the IP exists in the ipPool, then adds it to the pool for tracking.
				if(!ipPool.contains(receivingIP)){
					System.out.println("Connection with " + receivingIP + " has been established!");
					ipPool.add(receivingIP);
				}
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String line = reader.readLine();
				
				if(line != null){
					System.out.println(receivingIP + ": " + line);
					
					if(line.equals("!print")){
						System.out.println("IP Addresses that are connected.");
						for(String ip : ipPool){
							System.out.println("--" + ip);
						}
					}
					// Transmits the data back to the client
					ServerTransmitter transmitter = new ServerTransmitter(line, receivingIP, 4444);
					transmitter.start();
				}
				else{
					System.out.println("Connection with " + receivingIP + " has been terminated!");
					ipPool.remove(receivingIP);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getIP(){
		return this.ip;
	}
}

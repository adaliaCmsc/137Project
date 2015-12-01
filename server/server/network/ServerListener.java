package server.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ServerListener extends Thread{
	ArrayList<String> playerList = new ArrayList<String>();
	ArrayList<int []> sprites = new ArrayList<int []>();
	ArrayList<String> connectedClients = new ArrayList<String>();
	ServerSocket socket;
	String ip;
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
		String line;
		try {
			while(true){
				clientSocket = socket.accept();
				// Gets the receiving IP of format /192.198.xxx.xxx then gets the ip only.
				String receivingIP = clientSocket.getInetAddress().toString().split("/")[1];
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				line = reader.readLine();
				if(Pattern.matches("\\[COMMAND\\].*", line)){
					String [] command = line.split(" ");
					if(command[0].equals("[COMMAND]")){
						if(command[1].equals("[CONNECT]")){
							if(!connectedClients.contains(receivingIP)) connectedClients.add(receivingIP);
							System.out.println("Connected to client: " + receivingIP);
							ServerTransmitter transmitter = new ServerTransmitter("[SERVER] [CONNECTED]", receivingIP, port);
							transmitter.start();
						}
						if(command[1].equals("[ADD]")){
							// Stores the kind of sprite and the coordinates
							int [] coordinates = {Integer.parseInt(command[2]),Integer.parseInt(command[3]), Integer.parseInt(command[4])};
							sprites.add(coordinates);
							System.out.println("Coordinates of Sprite stored.");
							
							int index = -1;
							for(int i = 0; i < playerList.size(); i++){
								if(connectedClients.get(i).equals(receivingIP)){
									index = i;
									break;
								}
							}
							for(String ip : connectedClients){
								// Transmits the data back to the client
								ServerTransmitter transmitter = new ServerTransmitter("[SERVER] [ADD] " + Integer.parseInt(command[2]) + " " + Integer.parseInt(command[3]) + " " + Integer.parseInt(command[4]) + " " + index, ip, port);
								transmitter.start();
							}
						}
					}
				}
				else{
					System.out.println(receivingIP + ": " + line);
					for(String ip : connectedClients){
						// Transmits the data back to the client
						ServerTransmitter transmitter = new ServerTransmitter(line, ip, 4444);
						transmitter.start();
					}
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

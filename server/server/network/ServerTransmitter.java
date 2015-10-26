package server.network;

import java.io.IOException;
import java.net.Socket;

public class ServerTransmitter extends Thread{
	String message;
	String hostname;
	int port;
	
	public ServerTransmitter(String message, String hostname, int port){
		this.message = message;
		this.hostname = hostname;
		this.port = port;
	}
	
	@Override
	public void run(){
		try{
			Socket socket = new Socket(hostname, port);
			String receivingIP = socket.getInetAddress().toString().split("/")[1];
				message = receivingIP + ": " + message;
				socket.getOutputStream().write(message.getBytes());
				socket.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}

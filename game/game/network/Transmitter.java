package game.network;

import java.io.IOException;
import java.net.Socket;

public class Transmitter extends Thread{
	String message;
	String hostname;
	int port;
	
	public Transmitter(String message, String hostname, int port){
		this.message = message;
		this.hostname = hostname;
		this.port = port;
	}
	
	public Transmitter(String hostname, int port){
		this.hostname = hostname;
		this.port = port;
	}
	
	@Override
	public void run(){
		try {
			Socket socket = new Socket(hostname, port);
			if(message != null) socket.getOutputStream().write(message.getBytes());
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}

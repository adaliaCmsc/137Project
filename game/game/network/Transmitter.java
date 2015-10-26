package game.network;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class Transmitter extends Thread{
	HashMap<String, String> data;
	String message;
	String hostname;
	int port;
	
	public Transmitter(HashMap<String, String> data, String message, String hostname, int port){
		this.data = data;
		this.message = message;
		this.hostname = hostname;
		this.port = port;
	}
	
	public Transmitter(){
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

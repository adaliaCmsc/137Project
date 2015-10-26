package server.chat;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class ChatTransmitter extends Thread{
	HashMap<String, String> data = new HashMap<String, String>();
	String message;
	String hostname;
	int port = 4422;
	
	public ChatTransmitter(HashMap<String, String> data, String message, String hostname){
		this.data = data;
		this.message = message;
		this.hostname = hostname;
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

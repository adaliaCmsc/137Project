package server.gui;

import java.awt.BorderLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import server.network.ServerListener;

public class ServerGUI {
	public JFrame frame = new JFrame("COC v1 | Server");
	public int port = 4444;
	public void initialize(){
		JPanel contentPane = new JPanel(new BorderLayout());
		
		ServerListener server = new ServerListener(port);
		server.start();
		//ChatReceiver chatServer = new ChatReceiver("192.168.1.4");
		//chatServer.start();
		String currentIP = "0.0.0.0";
		try {
			currentIP = InetAddress.getLocalHost().toString().split("/")[1];
			System.out.println("IP: " + currentIP + " Port: " + port);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		JLabel label = new JLabel("IP: " + currentIP + " Port: " + port);
		contentPane.add(label);
		frame.add(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
}

package server.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import server.chat.ChatReceiver;

public class ServerGUI {
	public JFrame frame = new JFrame("COC v1 | Server");
	
	public void initialize(){
		JPanel contentPane = new JPanel(new BorderLayout());
		
		ChatReceiver chatServer = new ChatReceiver("192.168.1.4");
		chatServer.start();
		try {
			String currentIP = InetAddress.getLocalHost().toString().split("/")[1];
			System.out.println(currentIP);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		frame.add(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
}

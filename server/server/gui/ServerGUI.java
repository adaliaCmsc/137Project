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
		JPanel portPanel = new JPanel(new BorderLayout());
		JPanel hostPanel = new JPanel(new BorderLayout());
		final JTextArea portTextArea = new JTextArea();
		final JTextArea hostTextArea = new JTextArea();
		JLabel portLabel = new JLabel("Port");
		JLabel hostLabel = new JLabel("Hostname");
		JButton emit = new JButton("Start Listening");
		
		emit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> ip = new ArrayList<String>();
				ip.add("192.168.1.1");
				ChatReceiver chatServer = new ChatReceiver(ip);
				chatServer.start();
				try {
					String currentIP = InetAddress.getLocalHost().toString().split("/")[1];
					hostTextArea.setText(currentIP);
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		portPanel.setBorder(new EmptyBorder(5,0,5,0));
		hostPanel.setBorder(new EmptyBorder(5,0,5,0));
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hostLabel.setHorizontalAlignment(SwingConstants.CENTER);
		portTextArea.setPreferredSize(new Dimension(250, 20));
		hostTextArea.setPreferredSize(new Dimension(250, 20));
		portPanel.add(portLabel, BorderLayout.NORTH);
		portPanel.add(portTextArea, BorderLayout.CENTER);
		hostPanel.add(hostLabel, BorderLayout.NORTH);
		hostPanel.add(hostTextArea, BorderLayout.CENTER);
		
		contentPane.add(portPanel, BorderLayout.WEST);
		contentPane.add(hostPanel, BorderLayout.EAST);
		contentPane.add(emit, BorderLayout.SOUTH);
		contentPane.setBorder(new EmptyBorder(180,20,220,20));
		contentPane.setPreferredSize(new Dimension(640, 480));
		contentPane.setVisible(true);
		
		frame.add(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
}

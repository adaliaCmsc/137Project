package game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GameLogin {
	final private JFrame frame = new JFrame("COC v1 | Login");
	Map<String, String> data = new HashMap<String, String>();
	
	public void initialize(){
		JPanel contentPane = new JPanel(new BorderLayout()); 
		Color themeColor = new Color(102, 204, 153);
		
		contentPane.setBackground(themeColor);
		contentPane.setBorder(new EmptyBorder(120, 100, 190, 100));
		contentPane.setPreferredSize(new Dimension(640, 480));
		
		JLabel title = new JLabel("-- COC v1 | GAME LOGIN --");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 32));
		contentPane.add(title, BorderLayout.PAGE_START);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		
		JPanel usernamePanel = new JPanel(new BorderLayout());
		JPanel passwordPanel = new JPanel(new BorderLayout());
		
		final JTextField username = new JTextField();
		username.setBackground(new Color(255, 255, 255));
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setToolTipText("Username\r\n");
		
		JLabel usernameTitle = new JLabel("Username");
		usernameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		usernamePanel.setOpaque(false);
		usernamePanel.setPreferredSize(new Dimension(220,10));
		usernamePanel.setBorder(new EmptyBorder(20,0,20,0));
		usernamePanel.add(usernameTitle, BorderLayout.PAGE_START);
		usernamePanel.add(username, BorderLayout.CENTER);
		
		panel.add(usernamePanel, BorderLayout.WEST);
		
		final JPasswordField password = new JPasswordField();
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBackground(new Color(255, 255, 255));
		password.setToolTipText("Password\r\n");
		
		JLabel passwordTitle = new JLabel("Password");
		passwordTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		passwordPanel.setOpaque(false);
		passwordPanel.setPreferredSize(new Dimension(220,10));
		passwordPanel.setBorder(new EmptyBorder(20,0,20,0));
		passwordPanel.add(password, BorderLayout.CENTER);
		passwordPanel.add(passwordTitle, BorderLayout.PAGE_START);
		
		panel.add(passwordPanel, BorderLayout.EAST);
		
		JButton login = new JButton("LOGIN");
		login.setFont(new Font("Tahoma", Font.PLAIN, 20));
		login.setBackground(new Color(102, 204, 153));
		login.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				data.put("username", username.getText());
				data.put("password", password.getText());
				GameGUI gui1 = new GameGUI();
			}
			
		});
		
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(login, BorderLayout.PAGE_END);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(contentPane);
		frame.setVisible(true);
		frame.pack();
	}
}

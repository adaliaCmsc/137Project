package game.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
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
		JPanel contentPane = new JPanel(null); 
		
		JLabel background = new JLabel();
		background.setIcon(new ImageIcon("src/images/bg.jpg"));
		background.setVisible(true);
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setBounds(0, 0, 640, 480);
		
		JLabel title = new JLabel(new ImageIcon("src/images/login.png"));
		title.setBounds(15, 80, 620, 400);
		contentPane.add(title);
		contentPane.add(background);
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(120, 100, 190, 100));
		contentPane.setPreferredSize(new Dimension(640, 480));
		
		final JTextField textField = new JTextField();
		textField.setBounds(65,275,225,30);
		textField.setFont(new Font("Arial", Font.PLAIN, 28));
		contentPane.add(textField);
		
		final JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(350,275,225,30);
		passwordField.setFont(new Font("Arial", Font.PLAIN, 28));
		contentPane.add(passwordField);
		
		final JButton loginButton = new JButton(new ImageIcon("src/images/loginbutton.png"));
		loginButton.setFocusPainted(false);
		loginButton.setBounds(250,350,150,50);
		contentPane.add(loginButton);
		loginButton.addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				data.put("username", textField.getText());
				data.put("password", passwordField.getText());
				@SuppressWarnings("unused")
				GameGUI gui1 = new GameGUI();
			}
			
		});
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(contentPane);
		frame.pack();
		frame.setVisible(true);
	}
}

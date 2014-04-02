package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField inUsername;
	private JPasswordField inPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// App Logo
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("Logo.png"));
		logo.setBounds(112, 23, 269, 210);
		contentPane.add(logo);
		
		// Login Button
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inUsername.getText().length()==0)
					JOptionPane.showMessageDialog(null, "Username Field is Empty!");
				else if (inPassword.getPassword().length==0)
					JOptionPane.showMessageDialog(null, "Password field is empty!");
				else{
					JOptionPane.showMessageDialog(null, "Get role");
				}
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setBounds(112, 325, 97, 25);
		contentPane.add(btnLogin);
		
		// Exit Button
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(221, 325, 97, 25);
		contentPane.add(btnExit);
		
		// Username Label
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(83, 257, 97, 19);
		contentPane.add(lblUsername);
		
		// Username Input
		inUsername = new JTextField();
		inUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inUsername.setBounds(192, 255, 173, 22);
		contentPane.add(inUsername);
		inUsername.setColumns(10);
		
		// Password Label
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(83, 292, 97, 19);
		contentPane.add(lblPassword);
		
		// Password Input
		inPassword = new JPasswordField();
		inPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inPassword.setBounds(192, 290, 173, 22);
		contentPane.add(inPassword);
		
	}
}

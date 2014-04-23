/**
 * Copyright 2014 Andreas Andreou & Maria Christodoulou
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * The Login class gives access to a user's viewpoint according
 * to their credentials.
 * @author Andreas Andreou & Maria Christodoulou
 * @version 1.0
 */
@SuppressWarnings({"serial", "deprecation"})
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
		setTitle("Login");
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
				if (inUsername.getText().length() == 0)
					JOptionPane.showMessageDialog(null,
							"Username Field is Empty!");
				else if (inPassword.getPassword().length == 0)
					JOptionPane.showMessageDialog(null,
							"Password field is empty!");
				else {
					String[] info = new String[2]; // [0]=username, [1]=role
					boolean isUser = verifyUser(info);
					if (!isUser) JOptionPane.showMessageDialog(null,"Unable to communicate with DB");
					else{
						int role = Integer.valueOf(info[1]);
						switch (role) {
							case 0: // receptionist
									break;
							case 1:
									legalStaff.LSMenu ls = new legalStaff.LSMenu(info[0]); // Create Legal Staff window
									ls.setVisible(true); // Show Legal Staff window
									setVisible(false); // Hide Login window
									break;
							case 2: // LegalRecordsStuff
									break;
							case 3: // Management
									break;
							default:
									JOptionPane.showMessageDialog(null,"Unable to login, please verify username/password.");
						}
					}
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
	
	/**
	 * Method for verifying a user. It fills the given table with
	 * the user's username and role and returns if the communication
	 * with the database was successful.
	 * @param table user's info: [0]=username, [1]=role.
	 * @return successful communication with DB
	 */
	private boolean verifyUser(String[] table){
		String user = inUsername.getText();
		String pass = inPassword.getText();
		Object o = "SELECT * FROM dbo.USERS WHERE USERNAME = '"+user+"' AND PASSWORD = '"+pass+"'";
		Object[][] rs = (Object[][]) client.send(o);
		if (rs==null) return false;
		try{
			table[0]=rs[1][0].toString(); // fill username
			table[1]=rs[1][2].toString(); // fill role
		}catch(ArrayIndexOutOfBoundsException e){
			table[0]="N/A"; // fill username
			table[1]="-1"; // fill role
		}
		return true;
	}
}

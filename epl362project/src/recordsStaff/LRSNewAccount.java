/**
 * Copyright 2014 Andreas K. Andreou
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
package recordsStaff;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import client.client;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

/**
 * The New Account class, gives the records staff the ability to 
 * create a new account.
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LRSNewAccount extends JFrame {

	private JPanel contentPane;
	private JTextField Username;		// Account's username
	private JPasswordField Pass;		// Account's password
	private JPasswordField Confirm;		// Confirm password
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;			// List of roles
	private JButton btnCreate;			// Create account button
	private JButton btnCancel;			// Cancel creation button
	
	/**
	 * Class constructor. It creates the frame
	 * for the new account window.
	 * @param user Staff's username.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LRSNewAccount() {
		setTitle("New Account");
		setBounds(550, 100, 386, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// App Logo
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoSmall.png"));
		logo.setBounds(5, 5, 86, 71);
		contentPane.add(logo);

		// Title
		JLabel lblNewLabel = new JLabel("New Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(132, 13, 166, 55);
		contentPane.add(lblNewLabel);

		// Line Separator
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 85, 368, 2);
		contentPane.add(separator);
		
		// Account Username Label
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblUsername.setBounds(22, 92, 221, 30);
		contentPane.add(lblUsername);
				
		// Account Username
		Username = new JTextField();
		Username.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Username.setBounds(22, 127, 221, 30);
		contentPane.add(Username);
		Username.setColumns(10);
		
		// Account Password Label
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPassword.setBounds(22, 170, 221, 30);
		contentPane.add(lblPassword);
		
		// Account Password
		Pass = new JPasswordField();
		Pass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Pass.setBounds(22, 200, 221, 30);
		contentPane.add(Pass);
		
		// Password Confirm Label
		JLabel lblConfirm = new JLabel("Confirm Password:");
		lblConfirm.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblConfirm.setBounds(22, 242, 221, 30);
		contentPane.add(lblConfirm);
		
		// Password Confirm
		Confirm = new JPasswordField();
		Confirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Confirm.setBounds(22, 272, 221, 30);
		contentPane.add(Confirm);
		
		// Account Role Label
		JLabel lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblRole.setBounds(22, 315, 221, 30);
		contentPane.add(lblRole);
		
		// Account Role
		String[] roles = {"Receptionist", "Legal Staff", "Legal Records Staff", "Manager"};
		comboBox = new JComboBox(roles);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(22, 349, 290, 30);
		contentPane.add(comboBox);
		
		// Create Account button
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String u = Username.getText();
				String p = Pass.getText();
				String c = Confirm.getText();
				
				if (u.length()==0 || p.length()==0 || c.length()==0){
					JOptionPane.showMessageDialog(null,"Please fill all fields!");
				}
				else if (!p.equals(c)){
					JOptionPane.showMessageDialog(null,"Password mismatch!");
				}
				else{
					createAccount();
					dispose();
				}
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCreate.setBounds(23, 417, 140, 25);
		contentPane.add(btnCreate);
		
		// Cancel Creation
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(172, 417, 140, 25);
		contentPane.add(btnCancel);
	}
	
	/**
	 * Create a new account
	 */
	@SuppressWarnings("deprecation")
	private void createAccount(){
		String u = Username.getText();
		String p = Pass.getText();
		int role = comboBox.getSelectedIndex();
		
		String str = "INSERT INTO dbo.USERS (USERNAME,PASSWORD,ROLE) VALUES "+
				 "('"+u+"','"+p+"','"+role+"')";
	
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to insert in DB!");
		}
		else{
			JOptionPane.showMessageDialog(null, "Account added successfully!");
		}
	}
}

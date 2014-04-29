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

/**
 * The New Client class, gives the records staff the ability to 
 * create a new client.
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LRSNewClient extends JFrame {

	private JPanel contentPane;
	private JTextField ID;		// Client's ID
	private JTextField Name;	// Client's Name
	private JTextField Surname; // Client's Surname
	private JButton btnCreate;	// Create client button
	private JButton btnCancel;	// Cancel creation button
	
	
	/**
	 * Class constructor. It creates the frame
	 * for the new account window.
	 */
	public LRSNewClient() {
		setTitle("New Client");
		setBounds(550, 100, 386, 400);
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
		JLabel lblNewLabel = new JLabel("New Client");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(132, 13, 166, 55);
		contentPane.add(lblNewLabel);

		// Line Separator
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 85, 368, 2);
		contentPane.add(separator);
		
		// Client's ID Label
		JLabel lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblID.setBounds(22, 100, 221, 30);
		contentPane.add(lblID);
				
		// Client's ID
		ID = new JTextField();
		ID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ID.setBounds(22, 135, 221, 30);
		contentPane.add(ID);
		ID.setColumns(10);
		
		// Client's Name Label
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblName.setBounds(22, 170, 221, 30);
		contentPane.add(lblName);
		
		// Client's Name
		Name = new JTextField();
		Name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Name.setColumns(10);
		Name.setBounds(22, 210, 221, 30);
		contentPane.add(Name);
		
		// Client's Surname Label
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSurname.setBounds(22, 242, 221, 30);
		contentPane.add(lblSurname);
		
		// Client's Surname
		Surname = new JTextField();
		Surname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Surname.setColumns(10);
		Surname.setBounds(22, 274, 221, 30);
		contentPane.add(Surname);
		
		// Create Client button
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = ID.getText();
				String n = Name.getText();
				String s = Surname.getText();
				
				if (id.length()==0 || n.length()==0 || s.length()==0){
					JOptionPane.showMessageDialog(null,"Please fill all fields!");
				}
				else{
					try{
						int cid = Integer.parseInt(id);
						addClient(cid, n, s);
					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(null,"Please fill the correct formate for ID!");
					}
				}
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCreate.setBounds(22, 317, 140, 25);
		contentPane.add(btnCreate);
		
		// Cancel Creation
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(171, 317, 140, 25);
		contentPane.add(btnCancel);
	}
	
	/**
	 * Adds a new client to the system.
	 * @param id Client's id.
	 * @param n Client's name.
	 * @param s Client's surname.
	 */
	private void addClient(int id, String n, String s){
		
		String str = "INSERT INTO dbo.CLIENT (CID,FLAG,FNAME,LNAME,DELETED) VALUES "+
				 "('"+id+"','false','"+n+"','"+s+"','false')";
	
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to add new client!");
		}
		else{
			JOptionPane.showMessageDialog(null, "Client added successfully!");
		}
	}
}

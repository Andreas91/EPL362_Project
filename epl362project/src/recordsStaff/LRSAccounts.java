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
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import client.client;

import javax.swing.JComboBox;

/**
 * The Accounts class, gives the records staff access to all user accounts
 * Through the interface, the staff can search any account by their username
 * and make changes.
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LRSAccounts extends JFrame {

	private JPanel contentPane;
	private String username;			// Staff's username
	private JTextField Search;			// Search input
	private JButton btnSearch;			// Search button
	private JButton btnReset;			// Reset Search button
	private DefaultTableModel model;	// Table model for account list
	private JTable table;				// Table for account list
	private JButton btnDel;			// Deactivate an account button
	private JButton btnResetPass;		// Reset Password Button
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;			// List of roles
	private JButton btnRole;			// Change account's role
	
	
	/**
	 * Class constructor. It creates the frame
	 * for the accounts window.
	 * @param user Staff's username.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LRSAccounts(String user) {
		setBounds(550, 100, 532, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.username=user;
		setTitle("Accounts - "+this.username);

		// App Logo
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoSmall.png"));
		logo.setBounds(5, 5, 86, 71);
		contentPane.add(logo);

		// Title
		JLabel lblNewLabel = new JLabel("Accounts");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(186, 13, 126, 55);
		contentPane.add(lblNewLabel);

		// Line Separator
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 85, 514, 2);
		contentPane.add(separator);
		
		// Search by username Label
		JLabel lblSearchBy = new JLabel("Search by username:");
		lblSearchBy.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSearchBy.setBounds(22, 92, 166, 30);
		contentPane.add(lblSearchBy);
				
		// Search by username Text Field
		Search = new JTextField();
		Search.setBounds(21, 119, 221, 22);
		contentPane.add(Search);
		Search.setColumns(10);
				
		// Search by username button
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = Search.getText();
				if (str.length()==0){
					JOptionPane.showMessageDialog(null,"Nothing to search!");
				}
				else{
					searchAccount(str);
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearch.setBounds(253, 118, 97, 25);
		contentPane.add(btnSearch);
		
		// Reset Search button
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search.setText("");
				insertAccounts();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReset.setBounds(362, 118, 97, 25);
		contentPane.add(btnReset);
		
		// Accounts Table
		String[] col = { "Username", "Role"};
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		// Scroll Pane for Accounts Table
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(21, 154, 470, 144);
		contentPane.add(scrollPane);
		
		// Delete Selected Account
		btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select an account first!");
				}
				else {
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?");
					if (option==0){
						deleteAccount();
					}
				}
			}
		});
		btnDel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDel.setBounds(22, 311, 112, 25);
		contentPane.add(btnDel);

		// Reset Account Password
		btnResetPass = new JButton("Reset Password");
		btnResetPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select an account first!");
				} else {
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the password for this account?");
					if (option==0){
						resetPass();
					}
				}
			}
		});
		btnResetPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnResetPass.setBounds(146, 311, 166, 25);
		contentPane.add(btnResetPass);
		
		String[] roles = {"Receptionist", "Legal Staff", "Legal Records Staff", "Manager"};
		
		// Role list comboBox
		comboBox = new JComboBox(roles);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(22, 351, 290, 30);
		contentPane.add(comboBox);
		
		// Change account role button
		btnRole = new JButton("Change Role");
		btnRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select an account first!");
				}
				else {
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to change role for this account?");
					if (option==0){
						changeRole();
					}
				}
			}
		});
		btnRole.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRole.setBounds(22, 394, 140, 25);
		contentPane.add(btnRole);
		
		// Fill Table with Clients
		insertAccounts();
	}
	
	/**
	 * Fills the table with all the accounts.
	 */
	public void insertAccounts() {
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Get accounts from database
		String str = "SELECT U.USERNAME, R.RNAME "+
					 "FROM dbo.USERS U, dbo.ROLES R "+
					 "WHERE U.ROLE=R.ROLE AND U.USERNAME!='"+username+"'";
		Object[][] rs = (Object[][]) client.send(str);
		
		// Insert accounts to table
		for (int i=1;i<rs.length;i++){
			model.addRow(new Object[] {rs[i][0], rs[i][1]});
		}
	}
	
	/**
	 * Finds the account that matches the given username.
	 * @param u account's username.
	 */
	public void searchAccount(String u){
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Search account from database
		String str = "SELECT U.USERNAME, R.RNAME "+
					 "FROM dbo.USERS U, dbo.ROLES R "+
					 "WHERE U.USERNAME='"+u+"' AND U.ROLE=R.ROLE AND U.USERNAME!='"+username+"'";
		Object[][] rs = (Object[][]) client.send(str);
				
		// Insert account to table
		for (int i=1;i<rs.length;i++){
			model.addRow(new Object[] {rs[i][0], rs[i][1]});
		}
	}

	/**
	 * Changes the selected account's role.
	 */
	private void changeRole() {
		int row = table.getSelectedRow();
		String role = (String) model.getValueAt(row, 1);
		String user = (String) model.getValueAt(row, 0);
		int iNew = comboBox.getSelectedIndex();
		String sNew = (String) comboBox.getSelectedItem();
		
		if (role.equals(sNew)){
			JOptionPane.showMessageDialog(null,"The account is already "+sNew+"!");
			return;
		}
		
		String str = "UPDATE dbo.USERS "+
					 "SET ROLE='"+iNew+"' "+
					 "WHERE USERNAME='"+user+"';";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to change role!");
		}
		else{
			model.setValueAt(sNew, row, 1);
			JOptionPane.showMessageDialog(null, "Role changed!");
		}
	}
	
	/**
	 * Deletes a selected account.
	 */
	private void deleteAccount(){
		int row = table.getSelectedRow();
		String user = (String) model.getValueAt(row, 0);
		
		String str = "Delete FROM dbo.USERS "+
					 "WHERE USERNAME='"+user+"'";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to delete account!");
		}
		else{
			model.removeRow(row);
			JOptionPane.showMessageDialog(null, "Account deleted!");
		}
	}
	
	private void resetPass(){
		int row = table.getSelectedRow();
		String user = (String) model.getValueAt(row, 0);
		
		String str = "UPDATE dbo.USERS "+
					 "SET PASSWORD='1234' "+
					 "WHERE USERNAME='"+user+"';";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to reset password!");
		}
		else{
			JOptionPane.showMessageDialog(null, "Password reset to '1234'!");
		}
	}
}

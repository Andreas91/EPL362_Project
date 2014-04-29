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
import javax.swing.JTextArea;

/**
 * The History class, gives the records staff access to all of the user's 
 * transactions. Through the interface, the staff can search any transaction 
 * by the user's username.
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LRSHistory extends JFrame {

	private JPanel contentPane;
	private String username;			// Staff's username
	private JTextField Search;			// Search input
	private JButton btnSearch;			// Search button
	private JButton btnReset;			// Reset Search button
	
	private DefaultTableModel model;	// Table model for history list
	private JTable table;				// Table for history list
	private JButton btnShow;			// Show transaction button
	private JTextArea Text;				// Transactions text
	
	
	/**
	 * Class constructor. It creates the frame
	 * for the transactions history window.
	 * @param user Staff's username.
	 */
	public LRSHistory(String user) {
		setBounds(550, 100, 532, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.username=user;
		setTitle("History - "+this.username);

		// App Logo
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoSmall.png"));
		logo.setBounds(5, 5, 86, 71);
		contentPane.add(logo);

		// Title
		JLabel lblNewLabel = new JLabel("History");
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
				Text.setText("");
				insertHistory();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReset.setBounds(362, 118, 97, 25);
		contentPane.add(btnReset);
		
		// History Table
		String[] col = { "A/a", "Username", "Date", "Command"};
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		// Scroll Pane for History Table
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(21, 154, 470, 144);
		contentPane.add(scrollPane);
		
		// Show Selected Transaction
		btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select a transaction first!");
				}
				else {
					Text.setText((String)model.getValueAt(table.getSelectedRow(), 3));
				}
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShow.setBounds(22, 311, 112, 25);
		contentPane.add(btnShow);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 349, 470, 154);
		contentPane.add(scrollPane_1);
		
		// Transaction Text
		Text = new JTextArea();
		Text.setEditable(false);
		scrollPane_1.setViewportView(Text);
		
		// Fill Table with Transactions
		insertHistory();
	}
	
	/**
	 * Fills the table with all the transactions.
	 */
	public void insertHistory() {
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Get accounts from database
		String str = "SELECT * FROM dbo.HISTORY ORDER BY HDATE";
		Object[][] rs = (Object[][]) client.send(str);
		
		// Insert accounts to table
		for (int i=1;i<rs.length;i++){
			model.addRow(new Object[] {rs[i][0], rs[i][1], rs[i][2], rs[i][3]});
		}
	}
	
	/**
	 * Finds the account's history that matches the given username.
	 * @param u account's username.
	 */
	public void searchAccount(String u){
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Search account's transactions
		String str = "SELECT * FROM dbo.HISTORY H "+
					 "WHERE H.USERNAME='"+u+"' "+
					 "ORDER BY H.HDATE";
		Object[][] rs = (Object[][]) client.send(str);
				
		// Insert transactions to table
		for (int i=1;i<rs.length;i++){
			model.addRow(new Object[] {rs[i][0], rs[i][1], rs[i][2], rs[i][3]});
		}
	}
}

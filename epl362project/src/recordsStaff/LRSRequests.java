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

/**
 * The Requests class, gives the records staff access to all of the 
 * requests of changing a clients informations.
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LRSRequests extends JFrame {

	private JPanel contentPane;
	private String username;			// Staff's username
	private JTextField Search;			// Search input
	private JButton btnSearch;			// Search button
	private JButton btnReset;			// Reset Search button
	
	private DefaultTableModel model;	// Table model for requests list
	private JTable table;				// Table for requests list
	private JButton btnOpen;			// Open request button
	private JButton btnClose;			// Close request button
	private JTextField oldName;			// Old client's name
	private JTextField newName;			// New client's name
	private JTextField oldSurname;		// Old client's surname
	private JTextField newSurname;		// New client's surname
	
	
	private JButton btnAccept;			// Accept request button
	private JButton btnDecline;			// Decline request button
	
	
	/**
	 * Class constructor. It creates the frame
	 * for the requests window.
	 * @param user Staff's username.
	 */
	public LRSRequests(String user) {
		setBounds(550, 100, 532, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.username=user;
		setTitle("Requests - "+this.username);

		// App Logo
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoSmall.png"));
		logo.setBounds(5, 5, 86, 71);
		contentPane.add(logo);

		// Title
		JLabel lblNewLabel = new JLabel("Requests");
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
					searchRequests(str);
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
				insertRequests();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReset.setBounds(362, 118, 97, 25);
		contentPane.add(btnReset);
		
		// Requests Table
		String[] col = { "A/a", "Username", "CID", "New Name", "New Surname"};
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		// Scroll Pane for Requests Table
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(21, 154, 470, 144);
		contentPane.add(scrollPane);
		
		// Opens Selected Request
		btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select a request first!");
				}
				else {
					openRequest();
				}
			}
		});
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOpen.setBounds(129, 311, 112, 25);
		contentPane.add(btnOpen);
		
		// Closes Request
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeRequest();
			}
		});
		btnClose.setEnabled(false);
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBounds(254, 312, 112, 25);
		contentPane.add(btnClose);
		
		// Name Label
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblName.setBounds(22, 357, 69, 30);
		contentPane.add(lblName);
		
		// Old Name
		oldName = new JTextField();
		oldName.setText("N/A");
		oldName.setEditable(false);
		oldName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		oldName.setColumns(10);
		oldName.setBounds(22, 400, 207, 30);
		contentPane.add(oldName);
		
		// >> Label
		JLabel label = new JLabel(">>");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(241, 400, 38, 30);
		contentPane.add(label);
		
		// New Name
		newName = new JTextField();
		newName.setText("N/A");
		newName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		newName.setEditable(false);
		newName.setColumns(10);
		newName.setBounds(284, 400, 207, 30);
		contentPane.add(newName);
		
		// Surname Label
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblSurname.setBounds(22, 438, 97, 30);
		contentPane.add(lblSurname);
		
		// Old Surname
		oldSurname = new JTextField();
		oldSurname.setText("N/A");
		oldSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		oldSurname.setEditable(false);
		oldSurname.setColumns(10);
		oldSurname.setBounds(22, 474, 207, 30);
		contentPane.add(oldSurname);
		
		// >> Label
		JLabel label_1 = new JLabel(">>");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_1.setBounds(241, 474, 38, 30);
		contentPane.add(label_1);
		
		// New Surname
		newSurname = new JTextField();
		newSurname.setText("N/A");
		newSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		newSurname.setEditable(false);
		newSurname.setColumns(10);
		newSurname.setBounds(284, 474, 207, 30);
		contentPane.add(newSurname);
		
		// Accept Button
		btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accept();
				closeRequest();
			}
		});
		btnAccept.setEnabled(false);
		btnAccept.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAccept.setBounds(117, 538, 112, 25);
		contentPane.add(btnAccept);
		
		// Decline Button
		btnDecline = new JButton("Decline");
		btnDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decline();
				closeRequest();
			}
		});
		btnDecline.setEnabled(false);
		btnDecline.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDecline.setBounds(284, 539, 112, 25);
		contentPane.add(btnDecline);
		
		// Fill Table with Transactions
		insertRequests();
	}
	
	/**
	 * Fills the table with all the requests.
	 */
	public void insertRequests() {
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Get requests from database
		String str = "SELECT * FROM dbo.REQUESTS";
		Object[][] rs = (Object[][]) client.send(str);
		
		// Insert requests to table
		for (int i=1;i<rs.length;i++){
			model.addRow(new Object[] {rs[i][0], rs[i][1], rs[i][2], rs[i][3], rs[i][4]});
		}
	}
	
	/**
	 * Finds the requests that matches the given username.
	 * @param u account's username.
	 */
	public void searchRequests(String u){
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Search account's transactions
		String str = "SELECT * FROM dbo.REQUESTS R "+
					 "WHERE R.USERNAME='"+u+"' ";
		Object[][] rs = (Object[][]) client.send(str);
				
		// Insert transactions to table
		for (int i=1;i<rs.length;i++){
			model.addRow(new Object[] {rs[i][0], rs[i][1], rs[i][2], rs[i][3], rs[i][4]});
		}
	}
	
	/**
	 * Opens a selecte request
	 */
	private void openRequest(){
		this.Search.setEditable(false);
		this.btnSearch.setEnabled(false);
		this.btnReset.setEnabled(false);
		this.table.setEnabled(false);
		this.btnOpen.setEnabled(false);
		this.btnClose.setEnabled(true);
		this.btnAccept.setEnabled(true);
		this.btnDecline.setEnabled(true);
		
		int row = table.getSelectedRow();
		int cid = (int) model.getValueAt(row, 2);
		String newN = (String) model.getValueAt(row, 3);
		String newS = (String) model.getValueAt(row, 4);
		
		// Get old info of client from database
		String str = "SELECT * FROM dbo.CLIENT WHERE CID='"+cid+"'";
		Object[][] rs = (Object[][]) client.send(str);
		
		if (rs==null || rs.length==1) {
			this.btnClose.doClick();
			return;
		}
		
		this.oldName.setText((String)rs[1][2]);
		this.oldSurname.setText((String)rs[1][3]);
		this.newName.setText(newN);
		this.newSurname.setText(newS);
		
	}
	
	/**
	 * Closes an open request
	 */
	private void closeRequest(){
		this.Search.setEditable(true);
		this.btnSearch.setEnabled(true);
		this.btnReset.setEnabled(true);
		this.table.setEnabled(true);
		this.btnOpen.setEnabled(true);
		this.btnClose.setEnabled(false);
		this.btnAccept.setEnabled(false);
		this.btnDecline.setEnabled(false);
		this.oldName.setText("N/A");
		this.newName.setText("N/A");
		this.oldSurname.setText("N/A");
		this.newSurname.setText("N/A");
	}
	
	/**
	 * Accepts the selected change request.
	 */
	private void accept(){
		int row = table.getSelectedRow();
		int rid = (int)model.getValueAt(row, 0);
		int cid = (int)model.getValueAt(row, 2);
		String name = this.newName.getText();
		String surname = this.newSurname.getText();
		
		String str = "UPDATE dbo.CLIENT "+
				 	 "SET FNAME='"+name+"', LNAME='"+surname+"' "+
				 	 "WHERE CID='"+cid+"';";
	
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to accept request!");
			return;
		}
		JOptionPane.showMessageDialog(null, "Request accepted!");
		
		str="Delete FROM dbo.REQUESTS WHERE RID='"+rid+"'";
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to remove request!");
			return;
		}
		model.removeRow(row);
	}
	
	/**
	 * Declines the selected change request.
	 */
	private void decline(){
		int row = table.getSelectedRow();
		int rid = (int)model.getValueAt(row, 0);
		String str="Delete FROM dbo.REQUESTS WHERE RID='"+rid+"'";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to remove request!");
			return;
		}
		model.removeRow(row);
		
	}
	
}

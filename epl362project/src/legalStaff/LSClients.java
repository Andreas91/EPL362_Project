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
package legalStaff;

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
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import client.client;

/**
 * The Clients class, gives the lawyer access to his clients records
 * and their comments and cases. Through the interface, the lawyer can
 * search any of his clients (and his clients only) by their id.
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LSClients extends JFrame {

	private JPanel contentPane;
	private String username;			// Lawyer's username
	private JTextField Search;			// Search input
	private JButton btnSearch;			// Search button
	private JButton btnReset;			// Reset Search button
	private DefaultTableModel model;	// Table model for clients list
	private JTable table;				// Table for clients list
	private JButton btnOpen;			// Open a client record button
	private JButton btnClose;			// Close a client record button
	private JButton btnDeac;			// Deactivate a client button
	private JButton btnActivate;		// Activate a client button
	
	private JTextField CID;				// Client's ID
	private JTextField CName;			// Client's Name
	private JTextField CSurname;		// Client's Surname
	private JCheckBox CFlag;			// Client's Flag
	private JButton btnCEdit;			// Client Edit Button
	private JButton btnCSave;			// Save Changes Button
	private JButton btnCCancel;			// Cancel Changes Button
	private JButton btnCCom;			// Client Comments Button
	private Comments comm;				// Comment window for a client
	private LSCases cases;				// Cases window for a client
	private JButton btnCases;			// Client's cases button
	
	private boolean tFlag;				// Temporary stores Client's Flag if edit is cancel
	private String tName;				// Temporary stores Client's Name if edit is cancel
	private String tSurname;			// Temporary stores Client's Surname if edit is cancel
	
	
	/**
	 * Class constructor. It creates the frame
	 * for the lawyer's clients.
	 * @param user Lawyer's username.
	 */
	public LSClients(String user) {
		setTitle("My Clients");
		setBounds(550, 100, 682, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.username=user;

		// App Logo
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoSmall.png"));
		logo.setBounds(5, 5, 86, 71);
		contentPane.add(logo);

		// Title
		JLabel lblNewLabel = new JLabel("My Clients");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(272, 13, 201, 55);
		contentPane.add(lblNewLabel);

		// Line Separator
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 85, 671, 2);
		contentPane.add(separator);
		
		// Search by id Label
		JLabel lblSearchById = new JLabel("Search by id:");
		lblSearchById.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSearchById.setBounds(22, 92, 119, 30);
		contentPane.add(lblSearchById);
				
		// Search by id Text Field
		Search = new JTextField();
		Search.setBounds(21, 119, 221, 22);
		contentPane.add(Search);
		Search.setColumns(10);
				
		// Search by id button
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = Search.getText();
				if (str.length()==0){
					JOptionPane.showMessageDialog(null,"Nothing to search!");
				}
				else{
					try{
						int cid = Integer.valueOf(str);
						searchClient(cid);
					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(null,"Wrong format for id!");
					}
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
				insertClients();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReset.setBounds(362, 118, 97, 25);
		contentPane.add(btnReset);
		
		// Clients Table
		String[] col = { "ID", "Name", "Surname", "Flag", "Read Only"};
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		// Scroll Pane for Clients Table
		JScrollPane scrollApp = new JScrollPane(table);
		scrollApp.setBounds(21, 154, 630, 144);
		contentPane.add(scrollApp);

		// Open Selected Client Button
		btnOpen = new JButton("Show");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select a client first!");
				} else {
					openClient();
				}
			}
		});
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOpen.setBounds(21, 311, 97, 25);
		contentPane.add(btnOpen);
		
		// Close Selected Client Button
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeClient();
			}
		});
		btnClose.setEnabled(false);
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBounds(130, 311, 97, 25);
		contentPane.add(btnClose);
		
		// Deactivate Selected Client
		btnDeac = new JButton("Deactivate");
		btnDeac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select a client first!");
				} else {
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to deactivate this client?");
					if (option==0){
						deactivateClient();
					}
				}
			}
		});
		btnDeac.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeac.setBounds(238, 311, 112, 25);
		contentPane.add(btnDeac);
		
		// Activate Selected Client
		btnActivate = new JButton("Activate");
		btnActivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select a client first!");
				} else {
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to activate this client?");
					if (option==0){
						activateClient();
					}
				}
			}
		});
		btnActivate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnActivate.setBounds(362, 311, 112, 25);
		contentPane.add(btnActivate);

		// Line Separator
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 349, 671, 2);
		contentPane.add(separator_1);

		// Client's Info Title
		JLabel lblClientsInfo = new JLabel("Client's Info:");
		lblClientsInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblClientsInfo.setBounds(21, 364, 119, 30);
		contentPane.add(lblClientsInfo);

		// Client's ID Label
		JLabel lblCID = new JLabel("ID:");
		lblCID.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCID.setBounds(18, 403, 42, 25);
		contentPane.add(lblCID);

		// Client's ID
		CID = new JTextField();
		CID.setText("N/A");
		CID.setEditable(false);
		CID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CID.setBounds(72, 404, 116, 22);
		contentPane.add(CID);
		CID.setColumns(10);

		// Client's Flag Label
		JLabel lblFlag = new JLabel("Flag:");
		lblFlag.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFlag.setBounds(18, 437, 42, 25);
		contentPane.add(lblFlag);

		// Client's Flag
		CFlag = new JCheckBox("");
		CFlag.setEnabled(false);
		CFlag.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CFlag.setBounds(68, 438, 49, 25);
		contentPane.add(CFlag);

		// Client's Name Label
		JLabel lblCName = new JLabel("Name:");
		lblCName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCName.setBounds(200, 403, 57, 25);
		contentPane.add(lblCName);

		// Client's Name
		CName = new JTextField();
		CName.setText("N/A");
		CName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CName.setEditable(false);
		CName.setColumns(10);
		CName.setBounds(285, 404, 183, 22);
		contentPane.add(CName);

		// Client Surname Label
		JLabel lblCSurname = new JLabel("Surname:");
		lblCSurname.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCSurname.setBounds(200, 437, 86, 25);
		contentPane.add(lblCSurname);

		// Client Surname
		CSurname = new JTextField();
		CSurname.setText("N/A");
		CSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CSurname.setEditable(false);
		CSurname.setColumns(10);
		CSurname.setBounds(285, 438, 183, 22);
		contentPane.add(CSurname);

		// Client Edit Button
		btnCEdit = new JButton("Edit");
		btnCEdit.setEnabled(false);
		btnCEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tFlag = CFlag.isSelected();
				tName = CName.getText();
				tSurname = CSurname.getText();
				editClient();
			}
		});
		btnCEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCEdit.setBounds(480, 403, 73, 25);
		contentPane.add(btnCEdit);

		// Save Client Changes Button
		btnCSave = new JButton("Save");
		btnCSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String n = CName.getText();
				String s = CSurname.getText();
				if (n.length()==0 || s.length()==0){
					JOptionPane.showMessageDialog(null, "Please fill all fields!");
				}else{
					saveClient(Integer.valueOf(CID.getText()));
					editClient();
				}
			}
		});
		btnCSave.setEnabled(false);
		btnCSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCSave.setBounds(480, 437, 73, 25);
		contentPane.add(btnCSave);

		// Cancel Client Changes Button
		btnCCancel = new JButton("Cancel");
		btnCCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CFlag.setSelected(tFlag);
				CName.setText(tName);
				CSurname.setText(tSurname);
				editClient();
			}
		});
		btnCCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCCancel.setEnabled(false);
		btnCCancel.setBounds(565, 437, 86, 25);
		contentPane.add(btnCCancel);

		// Client Comments Button
		btnCCom = new JButton("Comments");
		btnCCom.setEnabled(false);
		btnCCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = model.getValueAt(table.getSelectedRow(),0);
				int cid = (int) o;
				if (comm!=null && comm.isVisible()) comm.dispose();
				comm = new Comments(cid,username);
				comm.setVisible(true);
			}
		});
		btnCCom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCCom.setBounds(19, 486, 109, 25);
		contentPane.add(btnCCom);
		
		// Client's Cases Button
		btnCases = new JButton("Cases");
		btnCases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = model.getValueAt(table.getSelectedRow(),0);
				int cid = (int) o;
				if (cases!=null && cases.isVisible()) cases.dispose();
				cases = new LSCases(cid,username);
				cases.setVisible(true);
			}
		});
		btnCases.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCases.setEnabled(false);
		btnCases.setBounds(138, 487, 91, 25);
		contentPane.add(btnCases);
		
		// Fill Table with clients
		insertClients();
	}
	
	/**
	 * Fills the table with the lawyer's clients.
	 */
	public void insertClients() {
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Get clients from database
		String str = "SELECT DISTINCT C.CID, C.FNAME, C.LNAME, C.FLAG, C.DELETED "+
					 "FROM dbo.MEETING M, dbo.CLIENT C "+
					 "WHERE M.LUSER='"+this.username+"' AND M.CID=C.CID";
		Object[][] rs = (Object[][]) client.send(str);
		
		// Insert clients to table
		for (int i=1;i<rs.length;i++){
			model.addRow(new Object[] {rs[i][0], rs[i][1], rs[i][2], rs[i][3], rs[i][4]});
		}
	}
	
	/**
	 * Finds the client the matches the given id.
	 * @param cid client's id.
	 */
	public void searchClient(int cid){
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Search client from database
		String str = "SELECT DISTINCT C.CID, C.FNAME, C.LNAME, C.FLAG, C.DELETED "+
					 "FROM dbo.MEETING M, dbo.CLIENT C "+
					 "WHERE M.LUSER='"+this.username+"' AND M.CID=C.CID AND C.CID='"+cid+"'";
		Object[][] rs = (Object[][]) client.send(str);
				
		// Insert client to table
		for (int i=1;i<rs.length;i++){
			model.addRow(new Object[] {rs[i][0], rs[i][1], rs[i][2], rs[i][3], rs[i][4]});
		}
	}
	
	/**
	 * Opens a specific client.
	 */
	private void openClient(){
		clearClient();
		
		// Set access to clients controls
		this.table.setEnabled(false);
		this.btnSearch.setEnabled(false);
		this.btnReset.setEnabled(false);
		this.btnOpen.setEnabled(false);
		this.btnClose.setEnabled(true);
		this.btnDeac.setEnabled(false);
		this.btnActivate.setEnabled(false);
		this.btnCEdit.setEnabled(true);
		this.btnCSave.setEnabled(false);
		this.btnCCancel.setEnabled(false);
		this.btnCCom.setEnabled(true);
		this.btnCases.setEnabled(true);
		
		Object o1 = model.getValueAt(table.getSelectedRow(), 0);
		Object o2 = model.getValueAt(table.getSelectedRow(), 1);
		Object o3 = model.getValueAt(table.getSelectedRow(), 2);
		Object o4 = model.getValueAt(table.getSelectedRow(), 3);
		Object o5 = model.getValueAt(table.getSelectedRow(), 4);
		CID.setText(o1.toString());
		CName.setText(o2.toString());
		CSurname.setText(o3.toString());
		CFlag.setSelected((boolean)o4);
		if ((boolean)o4)
			JOptionPane.showMessageDialog(null, "*** ATTENTION***\nThis client has benn flagged!");
		if ((boolean)o5){
			this.btnCEdit.setEnabled(false);
			JOptionPane.showMessageDialog(null, "*** ATTENTION***\nThis client record is Read Only!");
		}
	}
	
	/**
	 * Closes an opened client.
	 */
	private void closeClient(){
		clearClient();

		// Set access to clients controls
		this.table.setEnabled(true);
		this.btnSearch.setEnabled(true);
		this.btnReset.setEnabled(true);
		this.btnOpen.setEnabled(true);
		this.btnClose.setEnabled(false);
		this.btnDeac.setEnabled(true);
		this.btnActivate.setEnabled(true);
		this.btnCEdit.setEnabled(false);
		this.btnCSave.setEnabled(false);
		this.btnCCancel.setEnabled(false);
		this.btnCCom.setEnabled(false);
		this.btnCases.setEnabled(false);
	}
	
	/**
	 * Clears the fields of an open client.
	 */
	private void clearClient(){
		this.CID.setText("N/A");
		this.CName.setText("N/A");
		this.CSurname.setText("N/A");
		this.CFlag.setSelected(false);
	}

	/**
	 * Enables/Disables the ability to edit a client.
	 */
	private void editClient() {
		// Enable/Disable Input
		CName.setEditable(!CName.isEditable());
		CSurname.setEditable(!CSurname.isEditable());
		CFlag.setEnabled(!CFlag.isEnabled());

		// Enable/Disable Edit Buttons
		btnCEdit.setEnabled(!btnCEdit.isEnabled());
		btnCSave.setEnabled(!btnCSave.isEnabled());
		btnCCancel.setEnabled(!btnCCancel.isEnabled());
		btnCCom.setEnabled(!btnCCom.isEnabled());
		btnCases.setEnabled(!btnCases.isEnabled());
	}

	/**
	 * Saves the changes of the given client.
	 * @param cid client's id.
	 */
	private void saveClient(int cid) {
		String sName = this.CName.getText();
		String sSurname = this.CSurname.getText();
		boolean bFlag = this.CFlag.isSelected();
		
		// Change client's flag
		if (bFlag!=this.tFlag){
			String str = "UPDATE dbo.CLIENT "+
					 	 "SET FLAG='"+bFlag+"' "+
					 	 "WHERE CID='"+cid+"';";
		
			if (!(boolean)client.send(str)){
				JOptionPane.showMessageDialog(null, "Unable to change flag!");
			}
			else{
				model.setValueAt(bFlag, table.getSelectedRow(), 3);
				JOptionPane.showMessageDialog(null, "Client's flag has changed!");
			}
		}
		
		// Send request for changing name/surname
		if (!sName.equals(this.tName) || !sSurname.equals(this.tSurname)){
			String str = "INSERT INTO dbo.REQUESTS (USERNAME,CID,NEW_NAME,NEW_SURNAME) "+
						 "VALUES ('"+this.username+"','"+cid+"','"+sName+"','"+sSurname+"')";
	
			if (!(boolean)client.send(str)){
				JOptionPane.showMessageDialog(null, "Unable to submit name/surname change request!");
			}
			else{
				JOptionPane.showMessageDialog(null, "Name/Surname change request submited!");
			}
		}
		
	}
	
	/**
	 * Deactivates(=read only) a selected client.
	 */
	private void deactivateClient(){
		int row = table.getSelectedRow();
		int cid = (int) model.getValueAt(row, 0);
		String str = "UPDATE dbo.CLIENT "+
					 "SET DELETED='true' "+
					 "WHERE CID='"+cid+"';";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to deactivate client!");
		}
		else{
			model.setValueAt(true, row, 4);
			JOptionPane.showMessageDialog(null, "Client deactivated!");
		}
	}
	
	/**
	 * Activates a selected client.
	 */
	private void activateClient(){
		int row = table.getSelectedRow();
		int cid = (int) model.getValueAt(row, 0);
		String str = "UPDATE dbo.CLIENT "+
					 "SET DELETED='false' "+
					 "WHERE CID='"+cid+"';";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to activate client!");
		}
		else{
			model.setValueAt(false, row, 4);
			JOptionPane.showMessageDialog(null, "Client activated!");
		}
	}

	/**
	 * Kills the viewpoint and everything open.
	 */
	public void kill() {
		if (comm!= null && comm.isVisible()) comm.dispose();
		dispose();
	}
}

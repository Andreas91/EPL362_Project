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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import client.client;

import javax.swing.JTextArea;

/**
 * The Cases class, gives the lawyer access to all cases of a 
 * selected client. The lawyer can see a the description of the
 * case and add changes.
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LSCases extends JFrame {

	private JPanel contentPane;
	private int ClientID;				// Client's id
	private String username;			// Lawyer's username
	private DefaultTableModel model;	// Table model for Cases List
	private JTable table;				// Table for Cases List
	private JButton btnOpen;			// Open a case button
	private JButton btnClose;			// Close a case button
	
	private int Case_ID;				// Selected Case ID
	private String tDesc;				// Temporary stores description if edit is cancel
	
	private JTextArea Desc;				// Description of case
	private JButton btnEdit;			// Edit Case Description
	private JButton btnSave;			// Save Case Description
	private JButton btnCancel;			// Cancel the edit of Description
	
	private JButton btnOp;				// Open Legal Opinion for this case
	private JButton btnRec;				// Open Legal Recommendations for this case
	private JButton btnUpdate;			// Update Case Button
	
	private LSOpinions lso;				// Legal Opinions for a case
	private LSRecom lsr;				// Legal Recommendations for a case

	/**
	 * Class constructor. It creates the frame
	 * for the given client's cases.
	 * @param cid client's id
	 */
	public LSCases(int cid, String user) {
		setTitle("Cases");
		setBounds(550, 100, 803, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.ClientID=cid;
		this.username=user;
		
		// App Logo
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoSmall.png"));
		logo.setBounds(5, 5, 86, 71);
		contentPane.add(logo);

		// Title
		JLabel lblNewLabel = new JLabel("Cases");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(272, 13, 201, 55);
		contentPane.add(lblNewLabel);

		// Line Separator
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 85, 785, 2);
		contentPane.add(separator);

		// Cases Table
		String[] col = { "A/a", "Type", "Description", "Updated"};
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		// Scroll Pane for Cases Table
		JScrollPane scrollCases = new JScrollPane(table);
		scrollCases.setBounds(19, 100, 719, 144);
		contentPane.add(scrollCases);

		// Open Selected Case Button
		btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select a case first!");
				} else {
					Object o = model.getValueAt(table.getSelectedRow(), 0);
					Case_ID=(int)o;
					String str = (String) model.getValueAt(table.getSelectedRow(), 2);
					Desc.setText(str);
					openCase();
				}
			}
		});
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOpen.setBounds(29, 252, 97, 25);
		contentPane.add(btnOpen);
		
		// Close Selected Case Button
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeCase();
			}
		});
		btnClose.setEnabled(false);
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBounds(138, 253, 97, 25);
		contentPane.add(btnClose);

		// Line Separator
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 290, 785, 2);
		contentPane.add(separator_1);
		
		// Scroll Pane for Description
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 305, 719, 155);
		contentPane.add(scrollPane);
				
		// Case Description
		Desc = new JTextArea();
		Desc.setEditable(false);
		scrollPane.setViewportView(Desc);
				
		// Edit Description button
		btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tDesc=Desc.getText();
				editCase();
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.setBounds(19, 468, 97, 25);
		contentPane.add(btnEdit);
				
		// Save Description button
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveCase();
				editCase();
			}
		});
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(128, 469, 97, 25);
		contentPane.add(btnSave);
				
		// Cancel Description button
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desc.setText(tDesc);
				editCase();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setEnabled(false);
		btnCancel.setBounds(237, 469, 97, 25);
		contentPane.add(btnCancel);

		// Update Button
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCase();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate.setBounds(19, 567, 157, 30);
		contentPane.add(btnUpdate);
		
		// Case Legal Opinions Button
		btnOp = new JButton("Legal Opinions");
		btnOp.setEnabled(false);
		btnOp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lso!=null && lso.isVisible()) lso.dispose();
				lso = new LSOpinions(Case_ID, username);
				lso.setVisible(true);
			}
		});
		btnOp.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOp.setBounds(19, 524, 230, 30);
		contentPane.add(btnOp);
		
		// Case Legal Recommendations Button
		btnRec = new JButton("Legal Recommendations");
		btnRec.setEnabled(false);
		btnRec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lsr!=null && lsr.isVisible()) lsr.dispose();
				lsr = new LSRecom(Case_ID, ClientID, username);
				lsr.setVisible(true);
			}
		});
		btnRec.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRec.setBounds(261, 524, 256, 30);
		contentPane.add(btnRec);

		// Fill Table with client's cases
		insertCases();
	}
	
	/**
	 * Fills the table with the client's cases.
	 */
	public void insertCases() {
		
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Get cases from database
		String str = "SELECT C.* "+
					 "FROM dbo.MEETING M, dbo.CASES C "+
					 "WHERE M.CID='"+this.ClientID+"' AND M.LUSER='"+this.username+"' AND M.CASEID=C.CASEID";
		Object[][] rs = (Object[][]) client.send(str);
		
		// Insert cases to table
		for (int i=1;i<rs.length;i++){
			model.addRow(new Object[] {rs[i][0], rs[i][3], rs[i][1], rs[i][2]});
		}
	}
	
	/**
	 * Opens a specific case.
	 */
	private void openCase(){		
		// Set access to controls
		this.table.setEnabled(false);
		this.btnOpen.setEnabled(false);
		this.btnClose.setEnabled(true);
		
		this.btnEdit.setEnabled(true);
		this.btnSave.setEnabled(false);
		this.btnCancel.setEnabled(false);
		
		this.btnOp.setEnabled(true);
		this.btnRec.setEnabled(true);
		this.btnUpdate.setEnabled(true);
	}
	
	/**
	 * Closes an opened case.
	 */
	private void closeCase(){
		this.Desc.setText("");
		if (lso!=null && lso.isVisible()) lso.dispose();
		if (lsr!=null && lsr.isVisible()) lsr.dispose();
		
		// Set access to controls
		this.table.setEnabled(true);
		this.btnOpen.setEnabled(true);
		this.btnClose.setEnabled(false);
				
		this.btnEdit.setEnabled(false);
		this.btnSave.setEnabled(false);
		this.btnCancel.setEnabled(false);
				
		this.btnOp.setEnabled(false);
		this.btnRec.setEnabled(false);
		this.btnUpdate.setEnabled(false);
	}

	/**
	 * Enables/Disables the ability to edit a case description.
	 */
	private void editCase() {
		// Enable/Disable Input
		btnClose.setEnabled(!btnClose.isEnabled());
		btnEdit.setEnabled(!btnEdit.isEnabled());
		btnSave.setEnabled(!btnSave.isEnabled());
		btnCancel.setEnabled(!btnCancel.isEnabled());
		btnOp.setEnabled(!btnOp.isEnabled());
		btnRec.setEnabled(!btnRec.isEnabled());
		btnUpdate.setEnabled(!btnUpdate.isEnabled());
		Desc.setEditable(!Desc.isEditable());		
	}

	/**
	 * Saves the changes to the case description
	 */
	private void saveCase() {
		String text = this.Desc.getText();
		
		String str = "UPDATE dbo.CASES "+
				 	 "SET DESCRIPTION='"+text+"' "+
				 	 "WHERE CASEID='"+this.Case_ID+"'";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to save changes!");
		}
		else{
			model.setValueAt(text, table.getSelectedRow(), 2);
			saveTransaction("Update description of case: "+this.Case_ID);
			JOptionPane.showMessageDialog(null, "Changes were saved!");
		}
	}
	
	/**
	 * Updates the case's update date to the current one.
	 */
	@SuppressWarnings("deprecation")
	private void updateCase(){
		Date d = new Date();
		String today = (d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "
				+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		
		String str = "UPDATE dbo.CASES "+
					 "SET UPDATE_DATE='"+today+"' "+
					 "WHERE CASEID='"+this.Case_ID+"'";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to update!");
		}
		else{
			model.setValueAt(today, table.getSelectedRow(), 3);
			saveTransaction("Update case: "+this.Case_ID);
			JOptionPane.showMessageDialog(null, "Case is now updated!");
		}
		
	}

	/**
	 * Kills the viewpoint and everything open.
	 */
	public void kill() {
		if (lso!=null && lso.isVisible()) lso.dispose();
		if (lsr!=null && lsr.isVisible()) lsr.dispose();
		dispose();
	}
	
	/**
	 * Saves the given command to systems history.
	 * @param command sql query to be save.
	 */
	@SuppressWarnings("deprecation")
	private void saveTransaction(String command){
		Date d = new Date();
		String today = (d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "
				+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		String str = "INSERT INTO dbo.HISTORY (USERNAME,HDATE,COMMAND) VALUES "+
					 "('"+username+"','"+today+"','"+command+"')";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to save transaction!");
		}
	}
}

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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextArea;

import client.client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

/**
 * The Comments class, gives the lawyer access to comments for a selected client.
 * The lawyer can see comments the other lawyers have entered about the specific
 * client and can add his own comments, delete or show a comment.
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Comments extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;	// Table model for comments
	private JTable table;				// Table for comments
	private JTextArea input;			// Text area for displaying/writing comments
	
	private JButton btnShow;			// Show comment button
	private JButton btnNew;				// New comment button
	private JButton btnDelete;			// Delete comment button
	private JButton btnSave;			// Save comment button
	private JButton btnCancel;			// Cancel comment button
	private int ClientID;				// Client's id
	private String username;			// Lawer's username
	

	/**
	 * Class constructor. It creates the frame for the
	 * comments of the given client.
	 * @param cid clint's id.
	 */
	public Comments(int cid, String user) {
		setTitle("Comments");
		setBounds(39, 431, 511, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.ClientID=cid;
		this.username=user;
		
		// Comments Table
		String[] col = {"A/a", "Lawer", "Date/Time", "Description"};
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		
		// Scroll Pane for Comments Table
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 13, 469, 160);
		contentPane.add(scrollPane);
		
		// Show comment button
		btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount()!=1){
					JOptionPane.showMessageDialog(null, "Select a single row to see comment!");
				}
				else{
					String text = (String) model.getValueAt(table.getSelectedRow(), 3);
					input.setText(text);
				}
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShow.setBounds(12, 182, 97, 25);
		contentPane.add(btnShow);
		
		// New comment button
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeState();
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNew.setBounds(116, 183, 97, 25);
		contentPane.add(btnNew);
		
		// Delete a comment
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRowCount()!=1){
					JOptionPane.showMessageDialog(null, "Select a single row to delete!");
				}
				else{
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this comment?");
					if (option==0){
						delCom();
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(225, 183, 97, 25);
		contentPane.add(btnDelete);
		
		// Scroll Pane for the new comment
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 221, 469, 155);
		contentPane.add(scrollPane_1);
				
		// Text input for displaying/writing comments
		input = new JTextArea();
		input.setEditable(false);
		input.setFont(new Font("Monospaced", Font.PLAIN, 14));
		scrollPane_1.setViewportView(input);
		
		// Save comment button
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (input.getText().length()==0){
					JOptionPane.showMessageDialog(null, "You havn't wrote anything");
				}
				else{
					addCom();
					changeState();
				}
			}
		});
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(12, 389, 97, 25);
		contentPane.add(btnSave);
		
		// Cancel new comment button
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeState();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setEnabled(false);
		btnCancel.setBounds(116, 390, 97, 25);
		contentPane.add(btnCancel);
		
		// Insert Comments for the selected client
		insertCom();
	}
	
	/**
	 * Fills the table with client's comments.
	 */
	private void insertCom(){
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
				
		// Insert Comments for this client
		String str = "SELECT COM.COMID, L.FNAME, L.LNAME, COM.CDATE, COM.DESCRIPTION "+
					 "FROM dbo.COMMENTED COM, dbo.LAWER L "+
					 "WHERE COM.CID='"+this.ClientID+"' AND COM.LUSER=L.USERNAME";
		Object[][] rs = (Object[][]) client.send(str);
		for (int i=1;i<rs.length;i++){
			String lname = rs[i][1].toString() + " " + rs[i][2].toString();
			model.addRow(new Object[] {rs[i][0], lname, rs[i][3], rs[i][4]});
		}
	}
	
	/**
	 * Enables/Disables a new comment input.
	 */
	private void changeState(){
		input.setText("");
		btnShow.setEnabled(!btnShow.isEnabled());
		btnNew.setEnabled(!btnNew.isEnabled());
		btnDelete.setEnabled(!btnDelete.isEnabled());
		btnSave.setEnabled(!btnSave.isEnabled());
		btnCancel.setEnabled(!btnCancel.isEnabled());
		table.setEnabled(!table.isEnabled());
		input.setEditable(!input.isEditable());
	}
	
	/**
	 * Adds the written comment to the DB.
	 */
	@SuppressWarnings("deprecation")
	private void addCom(){
		
		String text = this.input.getText();
		Date d = new Date();
		String today = (d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "
				+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		String str = "INSERT INTO dbo.COMMENTED (LUSER, CID, CDATE, DESCRIPTION) VALUES "+
					 "('"+this.username+"','"+this.ClientID+"','"+today+"','"+text+"')";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to insert in DB!");
		}
		else{
			insertCom(); // Refresh table
			JOptionPane.showMessageDialog(null, "One record added successfully!");
		}
	}
	
	/**
	 * Deletes the selected comment.
	 */
	private void delCom(){
		int row = table.getSelectedRow();
		Object o = model.getValueAt(row, 0);
		int comID = Integer.parseInt(o.toString());
		
		String str = "Delete FROM dbo.COMMENTED WHERE COMID='"+comID+"'";
	
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to delete in DB!");
		}
		else{
			model.removeRow(row);
			JOptionPane.showMessageDialog(null, "One record deleted successfully!");
		}
	}
}

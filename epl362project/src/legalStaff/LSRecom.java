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
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;

import client.client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.JTextArea;

/**
 * The Legal Recommendations class, shows any disputes for 
 * recommendations for the given case. The lawyer can see details, 
 * add a new dispute or see side effects of a recommendation. 
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class LSRecom extends JFrame {

	private JPanel contentPane;
	private JTable table;				// Legal Recommendation Table
	private DefaultTableModel model;	// Table model for Legal Recommendation
	private JComboBox cbbRec;			// Legal Recommendation combo box
	private JCheckBox chAccepted;		// Accepted Check box
	private JButton btnShow;			// Show Description button
	private JButton btnDelete;			// Delete Dispute button
	private JButton btnNew;				// New Dispute button
	private JButton btnSave;			// Save dispute button
	private JButton btnCancel;			// Cancel new dispute button
	private JButton btnEffects;			// Show effect for legal recommendation
	private JTextArea inDesc;			// Description area
	private int caseID;					// Given case id
	private int clientID;				// Given client's id
	private String username;			// Lawyers username
	/**
	 * Class constructor. It creates the frame and gives
	 * some functionality to the buttons.
	 * @param caseid given case id.
	 */
	public LSRecom(int caseid, int cid, String user) {
		setTitle("Case: " + caseid);
		setBounds(100, 100, 578, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.caseID=caseid;
		this.clientID=cid;
		this.username=user;
		
		// Legal Recommendations Title
		JLabel label = new JLabel("Legal Recom.:");
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		label.setBounds(19, 13, 164, 30);
		contentPane.add(label);
		
		// Legal Recommendation Table
		String[] col = {"Rec.ID", "Date", "Description", "Accepted" };
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};	
		
		// Scroll Pane for Legal Recommendation
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 45, 536, 148);
		contentPane.add(scrollPane);

		// Show L.R. button
		btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select a row first!");
				} else {
					int recid = (int)model.getValueAt(table.getSelectedRow(),0);
					cbbRec.setSelectedIndex(recid-1);
					String desc = (String)model.getValueAt(table.getSelectedRow(),2);
					inDesc.setText(desc);
					Object o3 = model.getValueAt(table.getSelectedRow(),3);
					if (o3.equals("Yes")) chAccepted.setSelected(true);
					else chAccepted.setSelected(false);
				}
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShow.setBounds(12, 206, 97, 25);
		contentPane.add(btnShow);
		
		// New Legal Recommendation Button
		btnNew = new JButton("+ New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Enable/Disable buttons
				table.setEnabled(false);
				btnShow.setEnabled(false);
				btnDelete.setEnabled(false);
				btnNew.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				
				// Clear input
				chAccepted.setSelected(false);
				cbbRec.setSelectedIndex(0);
				inDesc.setText("");
				inDesc.setEditable(true);
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNew.setBounds(230, 207, 97, 25);
		contentPane.add(btnNew);
		
		// Save New L.R button
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inDesc.getText().length()==0){
					JOptionPane.showMessageDialog(null,"You haven't enter a description");
				}else{
					String acc = checkAcceptance();
					if (acc==null){
						addNew();
					}
					else{
						String msg = "WARNING!!!\nThis client (ID: "+clientID+") is known"
							+ " to delcine the recommendation "+(cbbRec.getSelectedIndex()+1)+
							" "+acc+". Do you want to proceed?";
						int option = JOptionPane.showConfirmDialog(null, msg);
						if (option==0){
							addNew();
						}
						addWarning(msg,option);
					}
					btnCancel.doClick();
				}
			}
		});
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(339, 207, 97, 25);
		contentPane.add(btnSave);
		
		// Delete L.R. button
		btnDelete = new JButton("- Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select a row first!");
				} else {
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?");
					if (option==0){
						int row = table.getSelectedRow();
						Object o = model.getValueAt(row, 1);
						String tdate = o.toString();
						
						String str = "Delete FROM dbo.DISPUTE WHERE DIS_DATE='"+tdate+"' AND CASEID='"+caseID+"'";
						if (!(boolean)client.send(str)){
							JOptionPane.showMessageDialog(null, "Unable to delete from DB!");
						}
						else{
							saveTransaction("Delete a recommendation for case: "+caseID);
							model.removeRow(row);
							JOptionPane.showMessageDialog(null, "Record deleted!");
						}
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(121, 207, 97, 25);
		contentPane.add(btnDelete);
		
		// Cancel New L.R. button
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Enable/Disable buttons
				table.setEnabled(true);
				btnShow.setEnabled(true);
				btnDelete.setEnabled(true);
				btnNew.setEnabled(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				
				// Clear input
				chAccepted.setSelected(false);
				cbbRec.setSelectedIndex(0);
				inDesc.setText("");
				inDesc.setEditable(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setEnabled(false);
		btnCancel.setBounds(448, 207, 97, 25);
		contentPane.add(btnCancel);
		
		// Legal Recommendations ComboBox
		Object[][] rs = (Object[][]) client.send("SELECT LRID,[DESCRIPTION] FROM dbo.LEGAL_RECOMMENDATION");
		String[] listRecom = new String[rs.length-1];
		for (int i=0;i<listRecom.length;i++){
			listRecom[i]=rs[i+1][1].toString();
		}
		cbbRec = new JComboBox(listRecom);
		cbbRec.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbRec.setBounds(12, 244, 452, 38);
		contentPane.add(cbbRec);
		
		// Accepted Check box
		chAccepted = new JCheckBox("Accepted");
		chAccepted.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chAccepted.setBounds(12, 295, 130, 25);
		contentPane.add(chAccepted);
		
		// Side Effects of L.R. button
		btnEffects = new JButton("~ Side-Effects");
		btnEffects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getEffect();
			}
		});
		btnEffects.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEffects.setBounds(150, 295, 130, 25);
		contentPane.add(btnEffects);
		
		// Description Title
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblDescription.setBounds(12, 329, 164, 30);
		contentPane.add(lblDescription);
		
		// Description Scroll Pane
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 363, 536, 174);
		contentPane.add(scrollPane_1);
		
		// Description Text Area
		inDesc = new JTextArea();
		inDesc.setEditable(false);
		scrollPane_1.setViewportView(inDesc);
		
		insertRec();
	}
	
	/**
	 * Fills the table with Legal Recommendations data for the
	 * given case.
	 */
	private void insertRec(){
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Insert Legal Recommendations for this case
		Object[][] rs = (Object[][]) client.send("SELECT * FROM dbo.DISPUTE D WHERE D.CASEID='"+this.caseID+"'");
		String accept = null;
		for (int i=1;i<rs.length;i++){
			if (!(boolean)rs[i][4]) accept="No";
			else accept="Yes";
			model.addRow(new Object[] {rs[i][0], rs[i][2], rs[i][3], accept});
		}
	}
	
	/**
	 * Shows any known side effects for the selected legal recommendation.
	 */
	private void getEffect(){
		int recid = this.cbbRec.getSelectedIndex() + 1;
		Object[][] rs = (Object[][]) client.send("SELECT * FROM dbo.LEGAL_RECOMMENDATION LR WHERE LR.LRID='"+recid+"'");
		String effect = (String)rs[1][2];		
		JOptionPane.showMessageDialog(null,"Side-Effect("+recid+"):\n"+effect);
	}
	
	/**
	 * Adds a new dispute to the database for the given case.
	 */
	@SuppressWarnings("deprecation")
	private void addNew(){
		int LRID = this.cbbRec.getSelectedIndex()+1;
		Date d = new Date();
		String today = (d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "
						+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		int acc=0;
		if (this.chAccepted.isSelected()) acc=1;
		String str = "INSERT INTO dbo.DISPUTE (LRID,CASEID,DIS_DATE,DESCRIPTION,ACCEPTED) "
				+ "VALUES ('"+LRID+"','"+this.caseID+"','"+today+"','"+this.inDesc.getText()+"','"+acc+"')";
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to insert in DB!");
		}
		else{
			String a = "No";
			if (this.chAccepted.isSelected()) a ="Yes";
			model.addRow(new Object[] {LRID, today, this.inDesc.getText(), a});
			saveTransaction("Added new recommendation for case: "+this.caseID);
			JOptionPane.showMessageDialog(null, "One record added successfully!");
		}
	}
	
	/**
	 * Checks if the client accepts the selected recommendation.
	 * @return acceptance of selected recommendation.
	 */
	private String checkAcceptance(){
		int recid = this.cbbRec.getSelectedIndex()+1;
		String str = "SELECT ACCEPTED "+
					 "FROM dbo.MEETING M JOIN dbo.CASES C ON M.CASEID = C.CASEID "+
					 "JOIN dbo.DISPUTE D ON C.CASEID = D.CASEID "+
					 "WHERE LRID = "+recid+" AND CID = "+this.clientID;
		Object[][] rs = (Object[][]) client.send(str);
		
		if (rs.length==1) return null;
		
		int total = rs.length-1;
		int decline = 0;
		
		for (int i=1;i<rs.length;i++){
			if ((boolean)rs[i][0]==false) decline++;
		}
		
		if (decline>=total/2) return decline+"/"+total;
		return null;
	}
	
	@SuppressWarnings("deprecation")
	private void addWarning(String msg, int option){
		Date d = new Date();
		String today = (d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "
						+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		String str = "INSERT INTO dbo.WARNINGS(USERNAME,STATUS,WDATE,DETAILS) VALUES "
				+ "('"+this.username+"','"+option+"','"+today+"', '"+msg+"')";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to insert warning to DB!");
		}
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

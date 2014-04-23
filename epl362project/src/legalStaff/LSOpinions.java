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

import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;

import client.client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

/**
 * The Legal Opinions class, shows any opinions the lawyer 
 * gave for the given case. The lawyer can see details, 
 * add and delete opinions for a case.  
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LSOpinions extends JFrame {

	private JPanel contentPane;
	private JTable table;				// Legal Opinions Table
	private DefaultTableModel model;	// Table model for Legal Opinions
	private JTextArea inOpinion;		// Legal Opinion Text Area
	private JButton btnShow;			// Legal Opinion Show Button
	private JButton btnNew;				// New Legal Opinion Button
	private JButton btnAdd;				// Add Legal Opinion Button
	private String tOpinion;			// Temporary stores Opinion if new is cancel
	private int caseID;					// Case ID to load the Legal Opinions for
	private JButton btnDel;
	
	/**
	 * Class constructor. It creates the frame and gives
	 * some functionality to the buttons.
	 * @param caseID given case id.
	 */
	public LSOpinions(int caseID) {
		setTitle("Case: "+caseID);
		setBounds(100, 100, 465, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.caseID=caseID;
		
		// Legal Opinion Title
		JLabel label = new JLabel("Legal Opinion:");
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		label.setBounds(12, 36, 300, 30);
		contentPane.add(label);
		
		// Legal Opinion Table
		String[] col = {"A/a", "Data", "Description"};
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
				
		// Scroll Pane for Legal Opinions
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 67, 423, 144);
		contentPane.add(scrollPane);
		
		// Show Legal Opinion Button
		btnShow = new JButton("v Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null, "Select an opinion to show!");
				} else {
					Object o = model.getValueAt(table.getSelectedRow(), 2);
					inOpinion.setText((String)o);
				}
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShow.setBounds(12, 224, 97, 25);
		contentPane.add(btnShow);
		
		// Delete an opinions
		btnDel = new JButton("-");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null, "Select an opinion to delete!");
				} else {
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this opinion?");
					if (option==0){
						int row = table.getSelectedRow();
						Object o = model.getValueAt(row, 0);
						int opID = Integer.parseInt(o.toString());
						
						String str = "Delete FROM dbo.LEGAL_OPINION WHERE LOID='"+opID+"'";
						if (!(boolean)client.send(str)){
							JOptionPane.showMessageDialog(null, "Unable to delete from DB!");
						}
						else{
							model.removeRow(row);
							JOptionPane.showMessageDialog(null, "Opinion "+opID+" deleted!");
						}
					}
				}
			}
		});
		btnDel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDel.setBounds(121, 224, 97, 25);
		contentPane.add(btnDel);
		
		// New Legal Opinion Button
		btnNew = new JButton("+");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newOpinion();
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNew.setBounds(229, 224, 97, 25);
		contentPane.add(btnNew);
		
		// Add new Legal Opinion Button
		btnAdd = new JButton("^ Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inOpinion.getText().length()==0){
					JOptionPane.showMessageDialog(null,"You haven't enter an opinion");
				}else{
					addOpinion();
					btnNew.doClick();
					insertOpinions();
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setEnabled(false);
		btnAdd.setBounds(338, 224, 97, 25);
		contentPane.add(btnAdd);
		
		// Legal Opinion Text Area
		inOpinion = new JTextArea();
		inOpinion.setEditable(false);
		inOpinion.setBounds(12, 262, 423, 156);
		contentPane.add(inOpinion);
		
		// Insert Legal Opinions about the given case
		insertOpinions();
	}
	
	/**
	 * Fills the table with Legal Opinions data for the
	 * given case.
	 */
	private void insertOpinions(){
		
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Insert Legal Recommendations for this case
		Object[][] rs = (Object[][]) client.send("SELECT * FROM dbo.LEGAL_OPINION LO WHERE LO.CASEID='"+this.caseID+"'");
		for (int i=1;i<rs.length;i++){
			model.addRow(new Object[] {rs[i][0], rs[i][2], rs[i][1]});
		}
	}
	
	/**
	 * Enable/Disable the ability to create a new Legal Opinion.
	 */
	private void newOpinion() {
		if (this.btnNew.getText().equals("+")) {
			this.btnNew.setText("X");
			this.tOpinion = this.inOpinion.getText();
			this.inOpinion.setText("");
		} else {
			this.btnNew.setText("+");
			this.inOpinion.setText(this.tOpinion);
		}
		inOpinion.setEditable(!inOpinion.isEditable());
		table.setEnabled(!table.isEnabled());
		btnShow.setEnabled(!btnShow.isEnabled());
		btnAdd.setEnabled(!btnAdd.isEnabled());
	}
	
	/**
	 * Adds a new Legal Opinion to the database for the given case.
	 */
	@SuppressWarnings("deprecation")
	private void addOpinion(){
		Date d = new Date();
		String today = (d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "
						+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		String str = "INSERT INTO dbo.LEGAL_OPINION (DESCRIPTION,LODATE,CASEID) VALUES "
				+ "('"+inOpinion.getText()+"','"+today+"','"+this.caseID+"')";
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to insert in DB!");
		}
		else{
			JOptionPane.showMessageDialog(null, "One record added successfully!");
		}
	}
}

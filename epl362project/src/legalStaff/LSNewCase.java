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
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import client.client;

import javax.swing.JTextArea;
import javax.swing.JComboBox;

/**
 * The New Case class, gives the lawyer the ability to add a new
 * case to the system.
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings({"serial","rawtypes"})
public class LSNewCase extends JFrame {

	private JPanel contentPane;
	private JComboBox Type;			// Case Type
	private JTextArea Desc;				// Case Description
	private JButton btnSave;			// Save New Case
	private JButton btnCancel;			// Cancel New Case

	/**
	 * Class constructor. It creates the frame
	 * for the new case window.
	 */
	@SuppressWarnings("unchecked")
	public LSNewCase() {
		setTitle("New Case");
		setBounds(550, 100, 510, 526);
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
		JLabel lblNewLabel = new JLabel("New Case");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(170, 21, 127, 55);
		contentPane.add(lblNewLabel);

		// Line Separator
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 85, 492, 2);
		contentPane.add(separator);
		
		// Case Type label
		JLabel lblNewLabel_1 = new JLabel("Case Type:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(19, 95, 141, 31);
		contentPane.add(lblNewLabel_1);
				
		// Case Type
		String[] types = { "CRIMINAL", "CIVIL"};
		Type = new JComboBox(types);
		Type.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Type.setBounds(19, 139, 315, 36);
		contentPane.add(Type);
		
		// Case Description Label
		JLabel lblCaseDescription = new JLabel("Case Description:");
		lblCaseDescription.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblCaseDescription.setBounds(19, 189, 194, 31);
		contentPane.add(lblCaseDescription);
		
		// Scroll Pane for Description
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 233, 454, 197);
		contentPane.add(scrollPane);
		
		// Case Description
		Desc = new JTextArea();
		scrollPane.setViewportView(Desc);
				
		// Save New Case button
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desc.getText().length()==0){
					JOptionPane.showMessageDialog(null, "You haven't enter a description!");
				}
				else{
					saveCase();
					dispose();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(122, 443, 97, 25);
		contentPane.add(btnSave);
				
		// Cancel New Case button
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(248, 443, 97, 25);
		contentPane.add(btnCancel);
	}

	/**
	 * Saves the changes to the case description
	 */
	@SuppressWarnings("deprecation")
	private void saveCase() {
		
		String text = this.Desc.getText();
		String type = (String) this.Type.getSelectedItem();
		Date d = new Date();
		String today = (d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "
						+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		String str = "INSERT INTO dbo.CASES (DESCRIPTION,UPDATE_DATE,CASE_TYPE) "+
					 "VALUES ('"+text+"','"+today+"','"+type+"')";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to save new case!");
		}
		else{
			JOptionPane.showMessageDialog(null, "New Case Added!");
		}
	}
}

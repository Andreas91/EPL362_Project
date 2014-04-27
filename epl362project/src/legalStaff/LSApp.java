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
import java.util.Date;

import javax.swing.SwingConstants;

import client.client;

/**
 * The Appointments class, gives the lawyer access to his list of appointments
 * and informations about them such as the case, the clients involve, legal 
 * recommendations and opinions and access to all comments from different lawyers
 * about a specific client.
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LSApp extends JFrame {

	private JPanel contentPane;
	private String username;			// Lawer's username
	private DefaultTableModel model;	// Table model for Appointments List
	private JTable tableApp;			// Table for Appointments List
	private JButton btnOpen;			// Open an appointment Button
	private JButton btnClose;			// Close an appointment Button

	private DefaultTableModel modelC;	// Table model for Clients
	private JTable tableClients;		// Appointmend's client table
	private JTextField CID;				// Client's ID
	private JTextField CName;			// Client's Name
	private JTextField CSurname;		// Client's Surname
	private JCheckBox CFlag;			// Client's Flag
	private JButton btnCShow;			// Client's Show Info Button
	private JButton btnCEdit;			// Client Edit/Cancel Button
	private JButton btnCSave;			// Save Changes Button
	private JButton btnCCancel;			// Cancel Changes Button
	private JButton btnCCom;			// Client Comments Button
	private Comments comm;				// Comment window for a client

	private boolean tFlag;				// Temporary stores Client's Flag if edit is cancel
	private String tName;				// Temporary stores Client's Name if edit is cancel
	private String tSurname;			// Temporary stores Client's Surname if edit is cancel
	private int Case_ID;				// Case id
	
	private JButton btnOp;				// Case Legal Opinion Button
	private JButton btnRec;				// Case Legal Recommendations Button
	private JButton btnUpdate;			// Update Case Button
	
	private LSOpinions lso;				// Legal Opinion window
	private LSRecom lsr;				// Legal Recommendations window
	

	/**
	 * Class constructor. It creates the frame
	 * for the lawyer's appointments viewpoint.
	 * @param user Lawyer's username.
	 */
	public LSApp(String user) {
		setTitle("My Appointments");
		setBounds(550, 100, 803, 690);
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
		JLabel lblNewLabel = new JLabel("My Appointments");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(272, 13, 201, 55);
		contentPane.add(lblNewLabel);

		// Line Separator
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 85, 785, 2);
		contentPane.add(separator);

		// Appointments Table
		String[] col = { "A/a", "Type", "Date/Time", "Scheduled","CaseID", "Updated" };
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		tableApp = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		// Scroll Pane for Appointments Table
		JScrollPane scrollApp = new JScrollPane(tableApp);
		scrollApp.setBounds(19, 100, 719, 144);
		contentPane.add(scrollApp);

		// Open Selected Appointment Button
		btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableApp.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select an appointment first!");
				} else {
					openApp();
					Object o = model.getValueAt(tableApp.getSelectedRow(), 4);
					Case_ID=(int)o;
					Object o2 = model.getValueAt(tableApp.getSelectedRow(), 0);
					getApp((int)o2);
				}
			}
		});
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOpen.setBounds(29, 252, 97, 25);
		contentPane.add(btnOpen);
		
		// Close Selected Appointment Button
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeApp();
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

		// Clients Label
		JLabel lblClients = new JLabel("Clients:");
		lblClients.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblClients.setBounds(15, 301, 119, 30);
		contentPane.add(lblClients);

		// Clients table
		String[] colC = { "ID", "Name", "Surname", "Flag" };
		Object[][] dataC = {};
		modelC = new DefaultTableModel(dataC, colC);
		tableClients = new JTable(modelC) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		// Scroll Pane for clients table
		JScrollPane scrollClients = new JScrollPane(tableClients);
		scrollClients.setBounds(15, 333, 424, 158);
		contentPane.add(scrollClients);

		// Client's Info Title
		JLabel lblClientsInfo = new JLabel("Client's Info:");
		lblClientsInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblClientsInfo.setBounds(470, 305, 119, 30);
		contentPane.add(lblClientsInfo);

		// Client's ID Label
		JLabel lblCID = new JLabel("ID:");
		lblCID.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCID.setBounds(470, 344, 42, 25);
		contentPane.add(lblCID);

		// Client's ID
		CID = new JTextField();
		CID.setText("N/A");
		CID.setEditable(false);
		CID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CID.setBounds(524, 345, 116, 22);
		contentPane.add(CID);
		CID.setColumns(10);

		// Client's Flag Label
		JLabel lblFlag = new JLabel("Flag:");
		lblFlag.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFlag.setBounds(654, 344, 42, 25);
		contentPane.add(lblFlag);

		// Client's Flag
		CFlag = new JCheckBox("");
		CFlag.setEnabled(false);
		CFlag.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CFlag.setBounds(704, 345, 49, 25);
		contentPane.add(CFlag);

		// Client's Name Label
		JLabel lblCName = new JLabel("Name:");
		lblCName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCName.setBounds(470, 378, 57, 25);
		contentPane.add(lblCName);

		// Client's Name
		CName = new JTextField();
		CName.setText("N/A");
		CName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CName.setEditable(false);
		CName.setColumns(10);
		CName.setBounds(555, 379, 183, 22);
		contentPane.add(CName);

		// Client Surname Label
		JLabel lblCSurname = new JLabel("Surname:");
		lblCSurname.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCSurname.setBounds(470, 406, 86, 25);
		contentPane.add(lblCSurname);

		// Client Surname
		CSurname = new JTextField();
		CSurname.setText("N/A");
		CSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CSurname.setEditable(false);
		CSurname.setColumns(10);
		CSurname.setBounds(555, 407, 183, 22);
		contentPane.add(CSurname);

		// Show Client Info Button
		btnCShow = new JButton("^ Show");
		btnCShow.setEnabled(false);
		btnCShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tableClients.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select a client to show!");
				} else {
					Object o1 = modelC.getValueAt(tableClients.getSelectedRow(), 0);
					Object o2 = modelC.getValueAt(tableClients.getSelectedRow(), 1);
					Object o3 = modelC.getValueAt(tableClients.getSelectedRow(), 2);
					Object o4 = modelC.getValueAt(tableClients.getSelectedRow(), 3);
					CID.setText(o1.toString());
					CName.setText(o2.toString());
					CSurname.setText(o3.toString());
					CFlag.setSelected((boolean)o4);
					if ((boolean)o4)
						JOptionPane.showMessageDialog(null, "*** ATTENTION***\nThis client has benn flagged!");
				}
			}
		});
		btnCShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCShow.setBounds(465, 435, 90, 25);
		contentPane.add(btnCShow);

		// Client Edit/Cancel Button
		btnCEdit = new JButton("Edit");
		btnCEdit.setEnabled(false);
		btnCEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableClients.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null, "Select a client to edit!");
				} else {
					btnCShow.doClick();
					tFlag = CFlag.isSelected();
					tName = CName.getText();
					tSurname = CSurname.getText();
					editClient();
				}

			}
		});
		btnCEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCEdit.setBounds(567, 434, 73, 25);
		contentPane.add(btnCEdit);

		// Save Client Changes Button
		btnCSave = new JButton("Save");
		btnCSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveClient(Integer.valueOf(CID.getText()));
				editClient();
			}
		});
		btnCSave.setEnabled(false);
		btnCSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCSave.setBounds(654, 434, 95, 25);
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
		btnCCancel.setBounds(654, 466, 95, 25);
		contentPane.add(btnCCancel);

		// Client Comments Button
		btnCCom = new JButton("Comments");
		btnCCom.setEnabled(false);
		btnCCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableClients.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select a client to see/add comment(s)!");
				} else {
					Object o = modelC.getValueAt(tableClients.getSelectedRow(),0);
					int cid = (int) o;
					if (comm!=null && comm.isVisible()) comm.dispose();
					comm = new Comments(cid,username);
					comm.setVisible(true);
				}
			}
		});
		btnCCom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCCom.setBounds(465, 466, 177, 25);
		contentPane.add(btnCCom);

		// Line Separator
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 504, 785, 2);
		contentPane.add(separator_2);

		// Line Separator
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(451, 303, 2, 188);
		contentPane.add(separator_3);

		// Update Button
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCase();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(296, 584, 157, 43);
		contentPane.add(btnUpdate);
		
		// Case Legal Opinions Button
		btnOp = new JButton("Legal Opinions");
		btnOp.setEnabled(false);
		btnOp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableApp.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select an appointment first!");
				} else {
					if (lso!=null && lso.isVisible()) lso.dispose();
					lso = new LSOpinions(Case_ID);
					lso.setVisible(true);
				}
			}
		});
		btnOp.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOp.setBounds(145, 531, 230, 30);
		contentPane.add(btnOp);
		
		// Case Legal Recommendations Button
		btnRec = new JButton("Legal Recommendations");
		btnRec.setEnabled(false);
		btnRec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableClients.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select a client first!");
				} else {
					Object o = modelC.getValueAt(tableClients.getSelectedRow(),0);
					int cid = (int) o;
					if (lsr!=null && lsr.isVisible()) lsr.dispose();
					lsr = new LSRecom(Case_ID, cid, username);
					lsr.setVisible(true);
				}
			}
		});
		btnRec.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRec.setBounds(397, 531, 256, 30);
		contentPane.add(btnRec);

		// Fill Table with appointments
		insertApp();
	}
	
	/**
	 * Fills the table with the lawyer's appointments.
	 */
	public void insertApp() {
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		// Get appointments from database
		String str = "SELECT DISTINCT A.AID, A.ATYPE, A.ADATE, A.SCHEDULED,M.CASEID,C.UPDATE_DATE "+
					 "FROM dbo.APPOINTMENT A, dbo.MEETING M, dbo.CASES C "+
					 "WHERE M.LUSER='"+this.username+"' AND M.AID=A.AID AND M.CASEID=C.CASEID";
		Object[][] rs = (Object[][]) client.send(str);
		
		// Insert appointments to table
		for (int i=1;i<rs.length;i++){
			model.addRow(new Object[] {rs[i][0], rs[i][1], rs[i][2], rs[i][3], rs[i][4], rs[i][5]});
		}
	}
	
	/**
	 * Opens a specific appointment.
	 */
	private void openApp(){
		clearClient();
		
		// Set access to appointments controls
		this.tableApp.setEnabled(false);
		this.btnOpen.setEnabled(false);
		this.btnClose.setEnabled(true);
		
		// Set access to clients controls
		this.tableClients.setEnabled(true);
		this.btnCShow.setEnabled(true);
		this.btnCEdit.setEnabled(true);
		this.btnCSave.setEnabled(false);
		this.btnCCancel.setEnabled(false);
		this.btnCCom.setEnabled(true);
		
		// Set access to rest of controls
		this.btnOp.setEnabled(true);
		this.btnRec.setEnabled(true);
		this.btnUpdate.setEnabled(true);
	}
	
	/**
	 * Closes an opened appointment.
	 */
	private void closeApp(){
		clearClient();
		if (lso!=null && lso.isVisible()) lso.dispose();
		if (lsr!=null && lsr.isVisible()) lsr.dispose();
		if (comm!= null && comm.isVisible()) comm.dispose();
		
		// Set access to appointments controls
		this.tableApp.setEnabled(true);
		this.btnOpen.setEnabled(true);
		this.btnClose.setEnabled(false);
		
		// Set access to clients controls
		this.tableClients.setEnabled(false);
		this.btnCShow.setEnabled(false);
		this.btnCEdit.setEnabled(false);
		this.btnCSave.setEnabled(false);
		this.btnCCancel.setEnabled(false);
		this.btnCCom.setEnabled(false);
		
		// Set access to rest of controls
		this.btnOp.setEnabled(false);
		this.btnRec.setEnabled(false);
		this.btnUpdate.setEnabled(false);
	}
	
	/**
	 * Clears the fields of an open client.
	 */
	private void clearClient(){
		this.CID.setText("N/A");
		this.CName.setText("N/A");
		this.CSurname.setText("N/A");
		this.CFlag.setSelected(false);
		while (this.modelC.getRowCount()!=0) this.modelC.removeRow(0);
	}
	
	/**
	 * Gets info about the given appointment such as
	 * clients and client's info. 
	 * @param appid appointment id.
	 */
	private void getApp(int appid) {
		// Clear Table
		while (modelC.getRowCount() != 0) modelC.removeRow(0);
		
		// Get clients from database
		String str = "SELECT C.CID, C.FNAME, C.LNAME, C.FLAG "+
					 "FROM dbo.MEETING M, dbo.CLIENT C "+
					 "WHERE M.AID='"+appid+"' AND M.CID=C.CID";
		Object[][] rs = (Object[][]) client.send(str);
				
		// Insert clients to table
		for (int i=1;i<rs.length;i++){
			modelC.addRow(new Object[] {rs[i][0], rs[i][1], rs[i][2], rs[i][3]});
		}
	}

	/**
	 * Enables/Disables the ability to edit a client.
	 */
	private void editClient() {
		// Enable/Disable Input
		tableApp.setEnabled(!tableApp.isEnabled());
		btnOpen.setEnabled(!btnOpen.isEnabled());
		tableClients.setEnabled(!tableClients.isEnabled());
		CName.setEditable(!CName.isEditable());
		CSurname.setEditable(!CSurname.isEditable());
		CFlag.setEnabled(!CFlag.isEnabled());

		// Enable/Disable Buttons
		btnCEdit.setEnabled(!btnCEdit.isEnabled());
		btnCSave.setEnabled(!btnCSave.isEnabled());
		btnCCancel.setEnabled(!btnCCancel.isEnabled());
		btnCCom.setEnabled(!btnCCom.isEnabled());
	}

	/**
	 * Saves the changes of the given client.
	 * @param cid client's id.
	 */
	private void saveClient(int cid) {
		String sName = this.CName.getText();
		String sSurname = this.CSurname.getText();
		boolean bFlag = this.CFlag.isSelected();
		
		String str = "UPDATE dbo.CLIENT "+
					 "SET FLAG='"+bFlag+"', FNAME='"+sName+"', LNAME='"+sSurname+"' "+
					 "WHERE CID='"+cid+"';";
		
		if (!(boolean)client.send(str)){
			JOptionPane.showMessageDialog(null, "Unable to save changes!");
		}
		else{
			modelC.setValueAt(sName, tableClients.getSelectedRow(), 1);
			modelC.setValueAt(sSurname, tableClients.getSelectedRow(), 2);
			modelC.setValueAt(bFlag, tableClients.getSelectedRow(), 3);
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
			model.setValueAt(today, tableApp.getSelectedRow(), 5);
			JOptionPane.showMessageDialog(null, "Case is now updated!");
		}
		
	}

	/**
	 * Kills the viewpoint and everything open.
	 */
	public void kill() {
		if (lso!=null && lso.isVisible()) lso.dispose();
		if (lsr!=null && lsr.isVisible()) lsr.dispose();
		if (comm!= null && comm.isVisible()) comm.dispose();
		dispose();
	}
}

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

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class LSApp extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;	// Table model for Appointments List
	private JTable tableApp;			// Table for Appointments List
	private JButton btnOpen;			// Open an appointment Button
	
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
	
	private JTable tableOpinions;		// Legal Opinions Table
	private DefaultTableModel modelO;	// Table model for Legal Opinions
	private JTextArea inOpinion;		// Legal Opinion Text Area
	private JButton btnOShow;			// Legal Opinion Show Button
	private JButton btnONew;			// New Legal Opinion Button
	private JButton btnOAdd;			// Add Legal Opinion Button
	private String tOpinion;				// Temporary stores Opinion if new is cancel
	
	private JTable tableRecom;			// Legal Recommendations Table
	private DefaultTableModel modelR;	// Table model for Legal Recommendations
	private JComboBox cbbRecom;			// Legal Recommendations ComboBox
	private JCheckBox chAccepted;		// Legal Recommendations Accepted
	private JButton btnRAdd;			// Legal Recommendations Add Button
	private JButton btnREffects;		// Legal Recommendations Side Effects
	
	private JButton btnUpdate;			// Update Transaction Button
	

	/**
	 * Create the frame.
	 */
	public LSApp() {
		setTitle("My Appointments");
		setBounds(550, 100, 803, 991);
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
		JLabel lblNewLabel = new JLabel("My Appointments");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(272, 13, 201, 55);
		contentPane.add(lblNewLabel);
		
		// Line Separator
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 85, 785, 2);
		contentPane.add(separator);
		
		// Appointments Table
		String[] col = {"A/a", "Type", "Date/Time", "Scheduled", "Transaction", "Updated"};
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		tableApp = new JTable(model){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		
		// Scroll Pane for Appointments Table
		JScrollPane scrollApp = new JScrollPane(tableApp);
		scrollApp.setBounds(19, 100, 719, 144);
		contentPane.add(scrollApp);
		
		// Button that opens a selected appointment
		btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableApp.getSelectedRowCount()!=1){
					JOptionPane.showMessageDialog(null, "Select an appointment first!");
				}
				else{
					Object o = model.getValueAt(tableApp.getSelectedRow(), 0);
					int aid = Integer.parseInt(o.toString());
					getApp(aid);
				}
			}
		});
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOpen.setBounds(29, 252, 97, 25);
		contentPane.add(btnOpen);
		
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
		String[] colC = {"ID", "Name", "Flag"};
		Object[][] dataC = {};
		modelC = new DefaultTableModel(dataC, colC);
		tableClients = new JTable(modelC){
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
		btnCShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tableClients.getSelectedRowCount()!=1){
					JOptionPane.showMessageDialog(null, "Select a client to show!");
				}
				else{
					Object o = modelC.getValueAt(tableClients.getSelectedRow(), 0);
					getClient((int)o);
				}
			}
		});
		btnCShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCShow.setBounds(465, 435, 90, 25);
		contentPane.add(btnCShow);
		
		// Client Edit/Cancel Button
		btnCEdit = new JButton("Edit");
		btnCEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableClients.getSelectedRowCount()!=1){
					JOptionPane.showMessageDialog(null, "Select a client to edit!");
				}
				else{
					btnCShow.doClick();
					tFlag=CFlag.isSelected();
					tName=CName.getText();
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
				CFlag.setSelected(tFlag);;
				CName.setText(tName);
				editClient();
			}
		});
		btnCCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCCancel.setEnabled(false);
		btnCCancel.setBounds(654, 466, 95, 25);
		contentPane.add(btnCCancel);
		
		// Client Comments Button
		btnCCom = new JButton("Comments");
		btnCCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableClients.getSelectedRowCount()!=1){
					JOptionPane.showMessageDialog(null, "Select a client to see/add comment(s)!");
				}
				else{
					Object o = modelC.getValueAt(tableClients.getSelectedRow(), 0);
					int cid = (int)o;
					if (comm!=null && comm.isVisible()) comm.dispose();
					comm = new Comments(cid);
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
		
		// Legal Opinion Title
		JLabel lblOpinion = new JLabel("Legal Opinion:");
		lblOpinion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblOpinion.setBounds(10, 511, 300, 30);
		contentPane.add(lblOpinion);
		
		// Legal Recommentation Title
		JLabel lblPrevious = new JLabel("Legal Recom.:");
		lblPrevious.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblPrevious.setBounds(19, 720, 164, 30);
		contentPane.add(lblPrevious);
		
		// Line Separator
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(451, 303, 2, 188);
		contentPane.add(separator_3);
		
		// Legal Opinion Table
		String[] colO = {"A/a", "Data", "Description"};
		Object[][] dataO = {};
		modelO = new DefaultTableModel(dataO, colO);
		tableOpinions = new JTable(modelO){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		
		// Scroll Pane for Legal Opinions Table
		JScrollPane scrollOpinion = new JScrollPane(tableOpinions);
		scrollOpinion.setBounds(10, 542, 300, 144);
		contentPane.add(scrollOpinion);
		
		// Show Opinion Button
		btnOShow = new JButton(">> Show");
		btnOShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableOpinions.getSelectedRowCount()!=1){
					JOptionPane.showMessageDialog(null, "Select an opinion to show!");
				}
				else{
					Object o = modelO.getValueAt(tableOpinions.getSelectedRow(), 0);
					getOpinion((int)o);
				}
			}
		});
		btnOShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOShow.setBounds(333, 549, 97, 25);
		contentPane.add(btnOShow);
		
		// New Opinion Button
		btnONew = new JButton("+");
		btnONew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newOpinion();
			}
		});
		btnONew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnONew.setBounds(333, 587, 97, 25);
		contentPane.add(btnONew);
		
		// Add Opinion Button
		btnOAdd = new JButton("<< Add");
		btnOAdd.setEnabled(false);
		btnOAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOAdd.setBounds(333, 625, 97, 25);
		contentPane.add(btnOAdd);
		
		// Scroll Pane Legal Opinion TextArea
		JScrollPane scrollinOpinion = new JScrollPane();
		scrollinOpinion.setBounds(451, 528, 312, 158);
		contentPane.add(scrollinOpinion);

		// Legal Opinion TextArea
		inOpinion = new JTextArea();
		inOpinion.setEditable(false);
		scrollinOpinion.setViewportView(inOpinion);
		
		// Line Separator
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(383, 511, 2, 30);
		contentPane.add(separator_4);
		
		// Line Separator
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(383, 663, 2, 38);
		contentPane.add(separator_5);
		
		// Legal Recommendation Table
		String[] colR = {"Date", "Description", "Accepted"};
		Object[][] dataR = {};
		modelR = new DefaultTableModel(dataR, colR);
		tableRecom = new JTable(modelR){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		
		// Scroll Pane for Legal Recommendation Table
		JScrollPane scrollRecom = new JScrollPane(tableRecom);
		scrollRecom.setBounds(12, 752, 378, 188);
		contentPane.add(scrollRecom);
		
		// Legal Recommendations ComboBox
		String[] listRecom = {
			"Recommendation 1", 
			"Recommendation 2", 
			"Recommendation 3", 
			"Recommendation 4", 
			"Recommendation 5"
		};
		cbbRecom = new JComboBox(listRecom);
		cbbRecom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbRecom.setBounds(435, 740, 288, 38);
		contentPane.add(cbbRecom);
		
		// Legal Recommendation Accept
		chAccepted = new JCheckBox("Accepted");
		chAccepted.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chAccepted.setBounds(435, 787, 277, 25);
		contentPane.add(chAccepted);
		
		// Legal Recommendation Add Button
		btnRAdd = new JButton("<< Add");
		btnRAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRecom();
			}
		});
		btnRAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRAdd.setBounds(441, 834, 97, 25);
		contentPane.add(btnRAdd);
		
		// Legal Recommendation Side Effects Button
		btnREffects = new JButton("Side-Effects");
		btnREffects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getEffect(cbbRecom.getSelectedIndex());
			}
		});
		btnREffects.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnREffects.setBounds(555, 834, 117, 25);
		contentPane.add(btnREffects);
		
		// Line Separator
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 705, 785, 2);
		contentPane.add(separator_6);
		
		// Update Button
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBounds(502, 893, 97, 25);
		contentPane.add(btnUpdate);
		
		// Fill Table with appointments
		insertApp();
	}
	
	
	public void insertApp(){
		/* client.send("getApps", lawID) */
		for (int i=0;i<20;i++)
			model.addRow(new Object[]{i+1, "type"+i, (i+1)+"-Apr-14", "Yes", "T00"+i, "Yes"});
	}
	
	private void getApp(int appid){
		/* client.send("getClients", appid); */
		while(modelC.getRowCount()!=0) modelC.removeRow(0);
		modelC.addRow(new Object[]{123456, "Andreas Andreou", "Yes"});
		modelC.addRow(new Object[]{654321, "George Georgiou", "No"});
		
		
		/* client.sent("getOpinions", appid); */
		while(modelO.getRowCount()!=0) modelO.removeRow(0);
		modelO.addRow(new Object[]{1, "14-Apr-14", "Bla Bla Bla"});
		modelO.addRow(new Object[]{2, "14-Apr-14", "Bla Bla Bla"});
		
		/* client.sent("getRecom", appid); */
		while(modelR.getRowCount()!=0) modelR.removeRow(0);
		modelR.addRow(new Object[]{"14-Apr-14", "Bla Bla Bla", "Yes"});
		modelR.addRow(new Object[]{"17-Apr-14", "Bla Bla Bla", "No"});
		
	}
	
	private void getClient(int cid){
		/* client.send("getClient", cid); */
		this.CID.setText( Integer.toString(cid) );
		this.CName.setText("Andreas");
		this.CSurname.setText("Andreou");
		this.CFlag.setSelected(true);
	}
	
	private void editClient(){
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
	
	private void saveClient(int cid){
		/* update client */
		JOptionPane.showMessageDialog(null, "Changes were saved!");
	}
	
	private void getOpinion(int oid){
		/* getOpinion */
		this.inOpinion.setText("Bla Bla Bla\nBla Bla Bla\nBla Bla Bla");
	}
	
	private void newOpinion(){
		if (this.btnONew.getText().equals("+")){
			this.btnONew.setText("X");
			this.tOpinion=this.inOpinion.getText();
			this.inOpinion.setText("");
		}else{
			this.btnONew.setText("+");
			this.inOpinion.setText(this.tOpinion);
		}
		inOpinion.setEditable(!inOpinion.isEditable());
		tableOpinions.setEnabled(!tableOpinions.isEnabled());
		btnOShow.setEnabled(!btnOShow.isEnabled());
		btnOAdd.setEnabled(!btnOAdd.isEnabled());
	}
	
	private void getEffect(int rid){
		/* getSideEffects */
		JOptionPane.showMessageDialog(null, "Side-Effects: N/A");
	}
	
	private void addRecom(){
		String recom = (String) this.cbbRecom.getSelectedItem();
		boolean sel = this.chAccepted.isSelected();
		modelR.addRow(new Object[]{"N/A", recom, sel});
	}
	
	public void kill(){
		if (comm!=null && comm.isVisible()) comm.dispose();
		dispose();
	}
}

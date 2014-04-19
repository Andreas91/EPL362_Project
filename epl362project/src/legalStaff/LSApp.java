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
	private String tSurname;			// Temporary stores Client's Surname if edit is cancel
	private int Case_ID;				// 
	
	private JButton btnOp;				// 
	private JButton btnRec;				// 
	private JButton btnUpdate;			// Update Transaction Button
	
	private LSOpinions lso;				// 
	private LSRecom lsr;				// 
	private JButton btnClose;
	

	/**
	 * Create the frame.
	 */
	public LSApp() {
		setTitle("My Appointments");
		setBounds(550, 100, 803, 690);
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
		String[] colC = { "ID", "Name", "Flag" };
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
					Object o = modelC.getValueAt(tableClients.getSelectedRow(), 0);
					getClient((int) o);
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

		// Line Separator
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(451, 303, 2, 188);
		contentPane.add(separator_3);

		// Update Button
		btnUpdate = new JButton("UPDATE");
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
				if (tableApp.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null,"Select an appointment first!");
				} else {
					if (lsr!=null && lsr.isVisible()) lsr.dispose();
					lsr = new LSRecom(Case_ID);
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

	public void insertApp() {
		/* client.send("getApps", lawID) */
		for (int i = 0; i < 20; i++)
			model.addRow(new Object[] {i+1, "type"+i, (i+1)+"-Apr-14", "Yes", 100+i, "Yes"});
	}

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
	
	private void clearClient(){
		this.CID.setText("N/A");
		this.CName.setText("N/A");
		this.CSurname.setText("N/A");
		this.CFlag.setSelected(false);
		while (this.modelC.getRowCount()!=0) this.modelC.removeRow(0);
	}
	
	private void getApp(int appid) {
		/* client.send("getClients", appid); */
		while (modelC.getRowCount() != 0) modelC.removeRow(0);
		modelC.addRow(new Object[] { 123456, "Andreas Andreou", "Yes" });
		modelC.addRow(new Object[] { 654321, "George Georgiou", "No" });
	}

	private void getClient(int cid) {
		/* client.send("getClient", cid); */
		this.CID.setText(Integer.toString(cid));
		this.CName.setText("Andreas");
		this.CSurname.setText("Andreou");
		this.CFlag.setSelected(true);
	}

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

	private void saveClient(int cid) {
		/* update client */
		JOptionPane.showMessageDialog(null, "Changes were saved!");
	}

	public void kill() {
		if (lso!=null && lso.isVisible()) lso.dispose();
		if (lsr!=null && lsr.isVisible()) lsr.dispose();
		if (comm!= null && comm.isVisible()) comm.dispose();
		dispose();
	}
}

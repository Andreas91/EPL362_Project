package receptionist;

import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RApp extends JFrame {

	private JPanel contentPane;
	private JTextField txtBranch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JTable tableApp;
	private JTable tableApp2;
	JSpinner spDate;
	JRadioButton rbScheduled;
	JRadioButton rbDropin;
	@SuppressWarnings("rawtypes")
	JComboBox cbLawyer;
	@SuppressWarnings("rawtypes")
	JComboBox cbCase;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RApp() {
		setTitle("Appointment Diary");
		setResizable(false);
		setBounds(100, 100, 712, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Schedule Appointment");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(228, 28, 240, 50);
		contentPane.add(label);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 93, 708, 2);
		contentPane.add(separator);

		JLabel label_1 = new JLabel("Date:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(31, 131, 46, 14);
		contentPane.add(label_1);

		spDate = new JSpinner();
		spDate.setModel(new SpinnerDateModel(new Date(1398546000000L), null,
				null, Calendar.DAY_OF_YEAR));
		spDate.setBounds(102, 130, 169, 20);
		contentPane.add(spDate);

		JLabel label_2 = new JLabel("Branch:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(311, 131, 84, 14);
		contentPane.add(label_2);

		txtBranch = new JTextField();
		txtBranch.setText("N/A");
		txtBranch.setColumns(10);
		txtBranch.setBounds(383, 130, 60, 20);
		contentPane.add(txtBranch);

		JLabel label_3 = new JLabel("Type:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_3.setBounds(495, 128, 46, 20);
		contentPane.add(label_3);

		rbScheduled = new JRadioButton("Scheduled");
		buttonGroup.add(rbScheduled);
		rbScheduled.setBounds(562, 129, 109, 23);
		contentPane.add(rbScheduled);

		rbDropin = new JRadioButton("Drop-In");
		buttonGroup.add(rbDropin);
		rbDropin.setBounds(562, 155, 109, 23);
		contentPane.add(rbDropin);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 190, 708, 2);
		contentPane.add(separator_1);

		JLabel label_4 = new JLabel("Involved Clients:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_4.setBounds(31, 203, 133, 14);
		contentPane.add(label_4);

		String[] col = { "Client ID", "Name", "Surname", "Flagged" };
		Object[][] data = {};
		model = new DefaultTableModel(data, col);

		tableApp = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		JScrollPane scrollPane = new JScrollPane(tableApp);
		scrollPane.setBounds(31, 247, 240, 136);
		contentPane.add(scrollPane);
		

		String[] col2 = { "Client ID", "Name", "Surname", "Flagged" };
		Object[][] data2 = {};
		model2 = new DefaultTableModel(data2, col2);

		tableApp2 = new JTable(model2) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		JScrollPane scrollPane_1 = new JScrollPane(tableApp2);
		scrollPane_1.setBounds(415, 247, 240, 136);
		contentPane.add(scrollPane_1);

		JButton btnAdd = new JButton(">> Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tableApp.getSelectedRowCount() < 1)
					JOptionPane.showMessageDialog(null, "Select a client first!");
				else {
					int[] selection = tableApp.getSelectedRows();
					for (int j = selection.length-1;j>=0;j--) {
						int i=selection[j];
						model2.addRow(new Object[] { model.getValueAt(i, 0),
								model.getValueAt(i, 1), model.getValueAt(i, 2),
								model.getValueAt(i, 3) });
						model.removeRow(i);
					}
					
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(281, 283, 118, 23);
		contentPane.add(btnAdd);

		JButton btnRemove = new JButton("<< Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tableApp2.getSelectedRowCount() < 1)
					JOptionPane.showMessageDialog(null, "Select a client first!");
				else {
					int[] selection = tableApp2.getSelectedRows();
					for (int j = selection.length-1;j>=0;j--) {
						int i=selection[j];
						model.addRow(new Object[] { model2.getValueAt(i, 0),
								model2.getValueAt(i, 1),
								model2.getValueAt(i, 2),
								model2.getValueAt(i, 3) });
						model2.removeRow(i);
					}
				}
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemove.setBounds(281, 317, 118, 23);
		contentPane.add(btnRemove);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 394, 708, 2);
		contentPane.add(separator_2);

		JLabel label_5 = new JLabel("Attending Lawyer:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_5.setBounds(31, 407, 155, 20);
		contentPane.add(label_5);

		

		JLabel label_6 = new JLabel("Concerning Case:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_6.setBounds(415, 407, 155, 20);
		contentPane.add(label_6);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 472, 708, 2);
		contentPane.add(separator_3);

		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date apDate = (Date) spDate.getValue();
			    java.sql.Timestamp sqlDate = new java.sql.Timestamp(apDate.getTime());
				int bid;
				try {
					bid = Integer.parseInt(txtBranch.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid Branch ID!");
					return;
				}
				boolean scheduled;
				if(rbScheduled.isSelected())
					scheduled = true;
				else if(rbDropin.isSelected())
					scheduled = false;
				else{
					JOptionPane.showMessageDialog(null, "Appointment type not specified!");
					return;
				}
				if(tableApp2.getRowCount()==0){
					JOptionPane.showMessageDialog(null, "No clients selected!");
					return;
				}
				if(cbLawyer.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "No lawyer selected!");
					return;
				}
				if(cbCase.getSelectedIndex()==0){
					JOptionPane.showMessageDialog(null, "No case selected!");
					return;
				}
				String getLUser = "SELECT USERNAME FROM dbo.LAWER WHERE LID="+(cbLawyer.getSelectedItem().toString().split("-")[0]);
				Object[][] lUser = (Object[][]) client.client.send(getLUser);
				if(lUser.length!=2){
					JOptionPane.showMessageDialog(null, "Error selecting lawyer from DB!");
					return;
				}
				String username = (String)lUser[1][0];
				// Insert in table Appointment
				String str1 = "INSERT INTO dbo.APPOINTMENT(BID,ADATE,SCHEDULED) VALUES("+bid+",'"+sqlDate+"',"+(scheduled?1:0)+")";
				if(!(boolean)client.client.send(str1)){
					JOptionPane.showMessageDialog(null, "Error inserting appointment!");
					return;
				}
				//Get this appointment's id
				String getid = "SELECT AID FROM dbo.APPOINTMENT ORDER BY AID DESC";
				Object[][] rs = (Object[][]) client.client.send(getid);
				int apid = (int)rs[1][0];
				//Insert in table Meeting
				for(int i=0;i<tableApp2.getRowCount();i++){
					String str2 = "INSERT INTO dbo.MEETING(AID,CID,LUSER,CASEID) VALUES("+apid+","+tableApp2.getValueAt(i, 0)+",'"+username+"',"+cbCase.getSelectedItem()+")";
					if(!(boolean)client.client.send(str2)){
						JOptionPane.showMessageDialog(null, "Error inserting meeting!");
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "Appointment inserted!");
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setBounds(156, 485, 141, 38);
		contentPane.add(btnSave);

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoSmall.png"));
		logo.setBounds(10, 11, 86, 71);
		contentPane.add(logo);

		JLabel lblExistingClients = new JLabel("Existing clients:");
		lblExistingClients.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblExistingClients.setBounds(31, 228, 118, 14);
		contentPane.add(lblExistingClients);

		JLabel lblAttendingClients = new JLabel("Attending clients:");
		lblAttendingClients.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAttendingClients.setBounds(415, 229, 126, 14);
		contentPane.add(lblAttendingClients);

		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				spDate.setValue(new Date(1398546000000L));
				txtBranch.setText("N/A");
				buttonGroup.clearSelection();
				while(tableApp2.getRowCount()>0){
						model.addRow(new Object[] { model2.getValueAt(0, 0),
								model2.getValueAt(0, 1),
								model2.getValueAt(0, 2),
								model2.getValueAt(0, 3) });
						model2.removeRow(0);
					
				}
				cbLawyer.setSelectedIndex(0);
				cbCase.setSelectedIndex(0);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(400, 486, 141, 38);
		contentPane.add(btnClear);
	}
	
	public void initialize(){
		// Get clients
		String str = "SELECT DISTINCT CID,FNAME,LNAME,FLAG FROM dbo.CLIENT";
		Object[][] rs = (Object[][]) client.client.send(str);
		while (model.getRowCount() != 0)
			model.removeRow(0);
		if (rs.length < 2)
			JOptionPane.showMessageDialog(null, "No clients exist in DB!");
		else
			for (int i = 1; i < rs.length; i++) {
				model.addRow(new Object[] { rs[i][0], rs[i][1], rs[i][2],
						rs[i][3] });
			}
		// Get lawyers
		Object[][] rsl = (Object[][]) client.client
				.send("SELECT LID,FNAME,LNAME FROM dbo.LAWER");
		String[] listLawyer = new String[rsl.length];
		listLawyer[0] = "--Select lawyer--";
		for (int i = 1; i < listLawyer.length; i++)
			listLawyer[i] = rsl[i][0].toString() + " - " + rsl[i][1] + " "
					+ rsl[i][2];
		cbLawyer = new JComboBox(listLawyer);
		cbLawyer.setBounds(31, 438, 238, 23);
		contentPane.add(cbLawyer);
		
		// Get cases
		Object[][] rsc = (Object[][]) client.client
				.send("SELECT CASEID FROM dbo.CASES");
		String[] listCase = new String[rsc.length];
		listCase[0] = "--Select case--";
		for (int i = 1; i < listCase.length; i++)
			listCase[i] = rsc[i][0].toString();

		cbCase = new JComboBox(listCase);
		cbCase.setBounds(415, 438, 240, 23);
		contentPane.add(cbCase);
	}
}

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
	private JTable table;				// Table for Appointments List
	private JButton btnOpen;			// Open an appointment Button
	
	private JTextField CID;				// Client's ID
	private JTextField CName;			// Client's Name
	private JCheckBox CFlag;			// Client's Flag
	private JButton btnCEdit;			// Client Edit/Cancel Button
	private JButton btnCSave;			// Save Changes Button
	private JButton btnCCancel;			// Cancel Changes Button
	private JButton btnCCom;			// Client Comments Button
	
	private boolean tFlag;				// Temporary stores Client's Flag if edit is cancel
	private String tName;				// Temporary stores Client's Name if edit is cancel
	
	private Comments comm;				// Comment window for a client

	/**
	 * Create the frame.
	 */
	public LSApp() {
		setTitle("My Appointments");
		setBounds(550, 100, 615, 750);
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
		lblNewLabel.setBounds(103, 13, 317, 55);
		contentPane.add(lblNewLabel);
		
		// Line Separator
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 85, 597, 2);
		contentPane.add(separator);
		
		// Appointments Table
		String[] col = {"A/a", "Type", "Date/Time", "Client ID", "Scheduled"};
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		
		// Scroll Pane for Appointments Table
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(15, 100, 570, 144);
		contentPane.add(scrollPane);
		
		// Button that opens a selected appointment
		btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount()!=1){
					JOptionPane.showMessageDialog(null, "Select a single row to see details!");
				}
				else{
					Object o = model.getValueAt(table.getSelectedRow(), 0);
					int aid = Integer.parseInt(o.toString());
					
					/* Get appointment's info */
					
					System.out.println(aid);
				}
			}
		});
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOpen.setBounds(12, 252, 97, 25);
		contentPane.add(btnOpen);
		
		// Line Separator
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 290, 597, 2);
		contentPane.add(separator_1);
		
		// Client's Info Title
		JLabel lblClientsInfo = new JLabel("Client's Info:");
		lblClientsInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblClientsInfo.setBounds(10, 301, 119, 30);
		contentPane.add(lblClientsInfo);
		
		// Client's ID Label
		JLabel lblCID = new JLabel("ID:");
		lblCID.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCID.setBounds(15, 336, 42, 25);
		contentPane.add(lblCID);
		
		// Client's ID
		CID = new JTextField();
		CID.setText("2");
		CID.setEditable(false);
		CID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CID.setBounds(69, 337, 116, 22);
		contentPane.add(CID);
		CID.setColumns(10);
		
		// Client's Flag Label
		JLabel lblFlag = new JLabel("Flag:");
		lblFlag.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFlag.setBounds(199, 336, 42, 25);
		contentPane.add(lblFlag);
		
		// Client's Flag
		CFlag = new JCheckBox("Illegal Activities");
		CFlag.setEnabled(false);
		CFlag.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CFlag.setBounds(249, 337, 142, 25);
		contentPane.add(CFlag);
		
		// Client's Name Label
		JLabel lblCName = new JLabel("Name:");
		lblCName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCName.setBounds(15, 370, 57, 25);
		contentPane.add(lblCName);
		
		// Client's Name
		CName = new JTextField();
		CName.setText("N/A");
		CName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CName.setEditable(false);
		CName.setColumns(10);
		CName.setBounds(79, 371, 305, 22);
		contentPane.add(CName);
		
		// Client Edit/Cancel Button
		btnCEdit = new JButton("Edit");
		btnCEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount()!=1){
					JOptionPane.showMessageDialog(null, "Select a single row to see details!");
				}
				else{
					btnOpen.doClick();
					tFlag=CFlag.isSelected();
					tName=CName.getText();
					editClient();
				}
				
			}
		});
		btnCEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCEdit.setBounds(408, 316, 73, 25);
		contentPane.add(btnCEdit);
		
		// Save Changes Button
		btnCSave = new JButton("Save");
		btnCSave.setEnabled(false);
		btnCSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCSave.setBounds(408, 349, 73, 25);
		contentPane.add(btnCSave);
		
		// Cancel Changes Button
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
		btnCCancel.setBounds(490, 350, 95, 25);
		contentPane.add(btnCCancel);
		
		// Client Comments Button
		btnCCom = new JButton("Comments");
		btnCCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CID.getText().equals("N/A")){
					JOptionPane.showMessageDialog(null, "Select a client to see/add comment(s)!");
				}
				else{
					int cid = Integer.valueOf(CID.getText());
					if (comm!=null && comm.isVisible()) comm.dispose();
					comm = new Comments(cid);
					comm.setVisible(true);
				}		
			}
		});
		btnCCom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCCom.setBounds(408, 382, 177, 25);
		contentPane.add(btnCCom);
		
		// Line Separator
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 420, 597, 2);
		contentPane.add(separator_2);
		
		JLabel lblConsultation = new JLabel("Consultation:");
		lblConsultation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblConsultation.setBounds(10, 430, 119, 30);
		contentPane.add(lblConsultation);
		
		JLabel lblPrevious = new JLabel("Previous:");
		lblPrevious.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblPrevious.setBounds(301, 430, 119, 30);
		contentPane.add(lblPrevious);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(274, 420, 2, 115);
		contentPane.add(separator_3);
		
		JButton button = new JButton("<<");
		button.setBounds(247, 548, 57, 25);
		contentPane.add(button);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(274, 586, 2, 119);
		contentPane.add(separator_4);
		
		// Fill Table with appointments
		insertApp();
	}
	
	
	public void insertApp(){
		for (int i=0;i<20;i++)
			model.addRow(new Object[]{i+1, "type"+i, "14-Apr-14", 983740+i, "Yes"});
	}
	
	public void editClient(){
		// Enable/Disable Input
		table.setEnabled(!table.isEnabled());
		btnOpen.setEnabled(!btnOpen.isEnabled());
		CName.setEditable(!CName.isEditable());
		CFlag.setEnabled(!CFlag.isEnabled());
		
		
		// Enable/Disable Buttons
		btnCEdit.setEnabled(!btnCEdit.isEnabled());
		btnCSave.setEnabled(!btnCSave.isEnabled());
		btnCCancel.setEnabled(!btnCCancel.isEnabled());
		btnCCom.setEnabled(!btnCCom.isEnabled());
	}
	
	public void kill(){
		if (comm.isVisible()) comm.dispose();
		dispose();
	}
}

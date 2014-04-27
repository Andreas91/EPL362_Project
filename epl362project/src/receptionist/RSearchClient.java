package receptionist;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class RSearchClient extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTextField txtSearchSurname;
	private JTextField txtSearchID;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTable tableApp;

	/**
	 * Create the frame.
	 */
	public RSearchClient() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Search Client");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(239, 36, 209, 25);
		contentPane.add(label);

		String[] col = { "Client","A/a", "Branch ID", "Case ID", "Date/Time" };
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		
		tableApp = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoSmall.png"));
		logo.setBounds(10, 11, 86, 71);
		contentPane.add(logo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 95, 779, 2);
		contentPane.add(separator);
		
		JLabel label_1 = new JLabel("Search Using Surname:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(10, 108, 180, 19);
		contentPane.add(label_1);
		
		txtSearchSurname = new JTextField();
		txtSearchSurname.setColumns(10);
		txtSearchSurname.setBounds(10, 138, 180, 20);
		contentPane.add(txtSearchSurname);
		
		JButton btnSearchSurname = new JButton("Search");
		btnSearchSurname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearchSurname.setBounds(10, 169, 180, 23);
		contentPane.add(btnSearchSurname);
		
		JLabel label_2 = new JLabel("Search Using ID:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(458, 108, 171, 19);
		contentPane.add(label_2);
		
		txtSearchID = new JTextField();
		txtSearchID.setColumns(10);
		txtSearchID.setBounds(458, 138, 171, 20);
		contentPane.add(txtSearchID);
		
		JButton btnSearchID = new JButton("Search");
		btnSearchID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearchID.setBounds(458, 169, 171, 23);
		contentPane.add(btnSearchID);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 211, 779, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(319, 93, 1, 120);
		contentPane.add(separator_2);
		
		JLabel label_3 = new JLabel("Client's Info:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_3.setBounds(10, 238, 119, 30);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("ID:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_4.setBounds(10, 277, 42, 25);
		contentPane.add(label_4);
		
		txtID = new JTextField();
		txtID.setText("N/A");
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBounds(54, 278, 86, 22);
		contentPane.add(txtID);
		
		JLabel label_5 = new JLabel("Flag:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_5.setBounds(160, 277, 42, 25);
		contentPane.add(label_5);
		
		final JCheckBox chbFlag = new JCheckBox("");
		chbFlag.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chbFlag.setEnabled(false);
		chbFlag.setBounds(208, 277, 49, 25);
		contentPane.add(chbFlag);
		
		JLabel label_6 = new JLabel("Name:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_6.setBounds(10, 311, 57, 25);
		contentPane.add(label_6);
		
		txtName = new JTextField();
		txtName.setText("N/A");
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setEditable(false);
		txtName.setColumns(10);
		txtName.setBounds(95, 312, 130, 22);
		contentPane.add(txtName);
		
		JLabel label_7 = new JLabel("Surname:");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_7.setBounds(10, 339, 86, 25);
		contentPane.add(label_7);
		
		txtSurname = new JTextField();
		txtSurname.setText("N/A");
		txtSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSurname.setEditable(false);
		txtSurname.setColumns(10);
		txtSurname.setBounds(95, 340, 130, 22);
		contentPane.add(txtSurname);
		
		JLabel label_8 = new JLabel("Scheduled Appointments:");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_8.setBounds(260, 224, 188, 19);
		contentPane.add(label_8);
		
		JScrollPane scrollPane = new JScrollPane(tableApp);
		scrollPane.setBounds(263, 249, 506, 87);
		contentPane.add(scrollPane);
		
		JLabel label_9 = new JLabel("Mark As:");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_9.setBounds(260, 356, 71, 20);
		contentPane.add(label_9);
				
		JButton btnAttend = new JButton("Attended");
		btnAttend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableApp.getSelectedRowCount() < 1)
					JOptionPane.showMessageDialog(null,"Select an appointment first!");
				else{
					for(int i=0;i<tableApp.getSelectedRowCount();i++){
						String str = "UPDATE dbo.MEETING SET ATTENDED = 1 WHERE AID="+tableApp.getValueAt(i, 1)+" AND CID="+tableApp.getValueAt(i, 0)+" AND CASEID="+tableApp.getValueAt(i,3);
						if((boolean)client.client.send(str))
							JOptionPane.showMessageDialog(null,"Appointments marked as attended!");
						else
							JOptionPane.showMessageDialog(null,"Problem communicating with DB");
					}
				}
			}
		});
		btnAttend.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAttend.setBounds(341, 355, 107, 23);
		contentPane.add(btnAttend);
		
		JButton btnMiss = new JButton("Not Attended");
		btnMiss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableApp.getSelectedRowCount() < 1)
					JOptionPane.showMessageDialog(null,"Select an appointment first!");
				else{
					for(int i=0;i<tableApp.getSelectedRowCount();i++){
						String str = "UPDATE dbo.MEETING SET ATTENDED = 0 WHERE AID="+tableApp.getValueAt(i, 1)+" AND CID="+tableApp.getValueAt(i, 0)+" AND CASEID="+tableApp.getValueAt(i,3);
						if((boolean)client.client.send(str))
							JOptionPane.showMessageDialog(null,"Appointments marked as attended!");
						else
							JOptionPane.showMessageDialog(null,"Problem communicating with DB");
					}
				}
			}
		});
		btnMiss.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnMiss.setBounds(458, 355, 119, 23);
		contentPane.add(btnMiss);
		
		btnSearchSurname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lname;
				if ((lname = txtSearchSurname.getText()).length() == 0)
					JOptionPane.showMessageDialog(null,
							"Surname field is empty!");
				else {
					String str = "SELECT DISTINCT CID, FNAME, LNAME, FLAG FROM dbo.CLIENT WHERE LNAME = '"
							+ lname + "' AND DELETED=0";
					Object[][] rs = (Object[][]) client.client.send(str);
					if (rs.length < 2)
						JOptionPane
								.showMessageDialog(null,
										"No client matches search criteria, either client does not exist or is deleted");
					else {
						txtID.setText("" + (int) rs[1][0]);
						txtName.setText((String) rs[1][1]);
						txtSurname.setText((String) rs[1][2]);
						chbFlag.setSelected((boolean) rs[1][3]);
						String str2 = "SELECT DISTINCT CID,A.AID,BID,CASEID,ADATE FROM dbo.APPOINTMENT A JOIN dbo.MEETING M ON A.AID=M.AID WHERE M.CID="+(int)rs[1][0];
						Object[][] rs2 = (Object[][]) client.client.send(str2);
						while (model.getRowCount() != 0) model.removeRow(0);
						if(rs2.length>=2){
							for (int i=1;i<rs.length;i++){
								model.addRow(new Object[] {rs2[i][0], rs2[i][1], rs2[i][2], rs2[i][3], rs2[i][4]});
							}
						}
					}
				}
			}
		});

		btnSearchID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id;
				if ((id = txtSearchID.getText()).length() == 0)
					JOptionPane.showMessageDialog(null, "ID field is empty!");
				else {
					String str = "SELECT DISTINCT CID, FNAME, LNAME, FLAG FROM dbo.CLIENT WHERE CID = "
							+ Integer.parseInt(id) + " AND DELETED=0";
					Object[][] rs = (Object[][]) client.client.send(str);
					if (rs.length < 2)
						JOptionPane
								.showMessageDialog(null,
										"No client matches search criteria, either client does not exist or is deleted");
					else {
						txtID.setText("" + (int) rs[1][0]);
						txtName.setText((String) rs[1][1]);
						txtSurname.setText((String) rs[1][2]);
						chbFlag.setSelected((boolean) rs[1][3]);
						String str2 = "SELECT DISTINCT CID,A.AID,BID,CASEID,ADATE FROM dbo.APPOINTMENT A JOIN dbo.MEETING M ON A.AID=M.AID WHERE M.CID="+(int)rs[1][0];
						Object[][] rs2 = (Object[][]) client.client.send(str2);
						while (model.getRowCount() != 0) model.removeRow(0);
						if(rs2.length>=2){
							for (int i=1;i<rs.length;i++){
								model.addRow(new Object[] {rs2[i][0], rs2[i][1], rs2[i][2], rs2[i][3], rs2[i][4]});
							}
						}
					}
				}
			}
		});
	}
}

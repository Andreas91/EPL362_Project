package management;

import java.awt.Font;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class HMBranchReport extends JFrame {

	private JPanel contentPane;
	private JTextField txtClientsMon;
	private JTextField txtClientsTue;
	private JTextField txtClientsWed;
	private JTextField txtClientsThu;
	private JTextField txtClientsFri;
	private JTextField txtClientsTotal;
	private JTextField txtCaseClients;
	private JTextField txtRecomCount;
	private JLabel lblDate1;
	private JLabel lblDate2;
	private JLabel lblDate3;
	private JLabel lblDate4;
	private JLabel lblDate5;
	private DefaultTableModel model;
	private JTable tableApp;
	@SuppressWarnings("rawtypes")
	private JComboBox cbCaseTypes;
	@SuppressWarnings("rawtypes")
	private JComboBox cbRecom;

	/**
	 * Create the frame.
	 */
	public HMBranchReport() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 703, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Weekly Branch Report");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(102, 11, 167, 19);
		contentPane.add(label);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 41, 697, 4);
		contentPane.add(separator);

		JLabel lblDay = new JLabel("Mon");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDay.setBounds(10, 81, 46, 14);
		contentPane.add(lblDay);

		lblDate1 = new JLabel("Date1");
		lblDate1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDate1.setBounds(54, 81, 64, 14);
		contentPane.add(lblDate1);

		JLabel lblClientsAttended = new JLabel("Clients Attended:");
		lblClientsAttended.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClientsAttended.setBounds(132, 56, 97, 14);
		contentPane.add(lblClientsAttended);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(121, 41, 2, 208);
		contentPane.add(separator_1);

		txtClientsMon = new JTextField();
		txtClientsMon.setEditable(false);
		txtClientsMon.setBounds(132, 78, 86, 20);
		contentPane.add(txtClientsMon);
		txtClientsMon.setColumns(10);

		JLabel lblDay_1 = new JLabel("Tue");
		lblDay_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDay_1.setBounds(10, 106, 46, 14);
		contentPane.add(lblDay_1);

		lblDate2 = new JLabel("Date2");
		lblDate2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDate2.setBounds(54, 106, 64, 14);
		contentPane.add(lblDate2);

		JLabel lblDay_2 = new JLabel("Wed");
		lblDay_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDay_2.setBounds(10, 131, 46, 14);
		contentPane.add(lblDay_2);

		JLabel lblDay_3 = new JLabel("Thu");
		lblDay_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDay_3.setBounds(10, 156, 46, 14);
		contentPane.add(lblDay_3);

		JLabel lblFriday = new JLabel("Fri");
		lblFriday.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFriday.setBounds(10, 181, 46, 14);
		contentPane.add(lblFriday);

		lblDate3 = new JLabel("Date3");
		lblDate3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDate3.setBounds(54, 131, 64, 14);
		contentPane.add(lblDate3);

		lblDate4 = new JLabel("Date4");
		lblDate4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDate4.setBounds(54, 156, 64, 14);
		contentPane.add(lblDate4);

		lblDate5 = new JLabel("Date5");
		lblDate5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDate5.setBounds(54, 181, 64, 14);
		contentPane.add(lblDate5);

		// use current date as basis for labels
		DateFormat today = new SimpleDateFormat("E");
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date dateobj = new Date();

		// Mon - Thu display last week's stats
		if (today.format(dateobj).compareToIgnoreCase("mon") == 0) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			lblDate1.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate2.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate3.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate4.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate5.setText(df.format(cal.getTime()));
		} else if (today.format(dateobj).compareToIgnoreCase("tue") == 0) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -8);
			lblDate1.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate2.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate3.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate4.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate5.setText(df.format(cal.getTime()));
		} else if (today.format(dateobj).compareToIgnoreCase("wed") == 0) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -9);
			lblDate1.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate2.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate3.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate4.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate5.setText(df.format(cal.getTime()));
		} else if (today.format(dateobj).compareToIgnoreCase("thu") == 0) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -10);
			lblDate1.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate2.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate3.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate4.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate5.setText(df.format(cal.getTime()));
		} else if (today.format(dateobj).compareToIgnoreCase("fri") == 0) {
			// Fri, Sat, Sun display this week's stats
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 0);
			lblDate5.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate4.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate3.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate2.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate1.setText(df.format(cal.getTime()));
		} else if (today.format(dateobj).compareToIgnoreCase("sat") == 0) {
			// Fri, Sat, Sun display this week's stats
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			lblDate5.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate4.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate3.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate2.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate1.setText(df.format(cal.getTime()));
		} else if (today.format(dateobj).compareToIgnoreCase("sun") == 0) {
			// Fri, Sat, Sun display this week's stats
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -2);
			lblDate5.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate4.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate3.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate2.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate1.setText(df.format(cal.getTime()));
		}

		txtClientsTue = new JTextField();
		txtClientsTue.setEditable(false);
		txtClientsTue.setColumns(10);
		txtClientsTue.setBounds(132, 103, 86, 20);
		contentPane.add(txtClientsTue);

		txtClientsWed = new JTextField();
		txtClientsWed.setEditable(false);
		txtClientsWed.setColumns(10);
		txtClientsWed.setBounds(132, 128, 86, 20);
		contentPane.add(txtClientsWed);

		txtClientsThu = new JTextField();
		txtClientsThu.setEditable(false);
		txtClientsThu.setColumns(10);
		txtClientsThu.setBounds(132, 153, 86, 20);
		contentPane.add(txtClientsThu);

		txtClientsFri = new JTextField();
		txtClientsFri.setEditable(false);
		txtClientsFri.setColumns(10);
		txtClientsFri.setBounds(132, 178, 86, 20);
		contentPane.add(txtClientsFri);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 209, 243, 4);
		contentPane.add(separator_2);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotal.setBounds(65, 222, 46, 14);
		contentPane.add(lblTotal);

		txtClientsTotal = new JTextField();
		txtClientsTotal.setEditable(false);
		txtClientsTotal.setColumns(10);
		txtClientsTotal.setBounds(132, 220, 86, 20);
		contentPane.add(txtClientsTotal);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(241, 41, 2, 208);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 247, 697, 2);
		contentPane.add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 250, 697, 4);
		contentPane.add(separator_5);

		JLabel lblCaseTypes = new JLabel("Case Types:");
		lblCaseTypes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCaseTypes.setBounds(10, 260, 76, 14);
		contentPane.add(lblCaseTypes);

		JButton btnShowClients = new JButton("Clients Involved");
		btnShowClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbCaseTypes.getSelectedIndex() == 0){
					JOptionPane.showMessageDialog(null, "Select case type!");
					return;
				}
				String q = "SELECT COUNT(DISTINCT CID) FROM dbo.CASES C JOIN dbo.MEETING M ON C.CASEID = M.CASEID WHERE CASE_TYPE = '" + cbCaseTypes.getSelectedItem() + "'";
				Object[][] res = (Object[][])client.client.send(q);
				if(res.length < 2){
					JOptionPane.showMessageDialog(null, "Internal processing error!");
					return;
				}
				int count = Integer.parseInt(res[1][0].toString());
				txtCaseClients.setText("" + count);
			}
		});
		btnShowClients.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowClients.setBounds(10, 316, 151, 23);
		contentPane.add(btnShowClients);

		txtCaseClients = new JTextField();
		txtCaseClients.setEditable(false);
		txtCaseClients.setBounds(183, 317, 60, 20);
		contentPane.add(txtCaseClients);
		txtCaseClients.setColumns(10);

		JLabel lblLegalRecommendations = new JLabel("Legal Recommendations:");
		lblLegalRecommendations.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLegalRecommendations.setBounds(408, 260, 151, 14);
		contentPane.add(lblLegalRecommendations);

		JButton btnShowRate = new JButton("Times Given");
		btnShowRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbRecom.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Select legal recommendation!");
					return;
				}
				String str = "SELECT COUNT(LRID) FROM dbo.DISPUTE WHERE LRID = " + cbRecom.getSelectedItem().toString().split(" -")[0];
				Object[][] res = (Object[][]) client.client.send(str);
				if (res.length < 2) {
					JOptionPane.showMessageDialog(null, "Internal processing error!");
					return;
				}
				int count = Integer.parseInt(res[1][0].toString());
				txtRecomCount.setText("" + count);
			}
		});
		btnShowRate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowRate.setBounds(408, 316, 151, 23);
		contentPane.add(btnShowRate);

		txtRecomCount = new JTextField();
		txtRecomCount.setEditable(false);
		txtRecomCount.setBounds(581, 317, 60, 20);
		contentPane.add(txtRecomCount);
		txtRecomCount.setColumns(10);

		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(243, 41, 2, 208);
		contentPane.add(separator_7);

		String[] col = { "Client ID", "Name", "Surname", "Flagged",
				"Opinions Given" };
		Object[][] data = {};
		model = new DefaultTableModel(data, col);

		tableApp = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		JScrollPane scrollPane = new JScrollPane(tableApp);
		scrollPane.setBounds(253, 56, 434, 157);
		contentPane.add(scrollPane);

		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(319, 247, 2, 114);
		contentPane.add(separator_6);

		JButton btnShowClients_1 = new JButton("Show Clients");
		btnShowClients_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Display clients with opinions
				String str = "SELECT DISTINCT CID,FNAME,LNAME,FLAG FROM dbo.CLIENT WHERE DELETED = 0";
				Object[][] rs = (Object[][]) client.client.send(str);
				while (model.getRowCount() != 0)
					model.removeRow(0);
				if (rs.length < 2)
					JOptionPane.showMessageDialog(null,
							"No clients exist in DB!");
				else {
					for (int i = 1; i < rs.length; i++) {
						String str2 = "SELECT COUNT(DISTINCT LOID) FROM dbo.LEGAL_OPINION LO JOIN dbo.MEETING M ON LO.CASEID = M.CASEID WHERE CID = "
								+ rs[i][0].toString();
						Object[][] rs2 = (Object[][]) client.client.send(str2);
						int count = Integer.parseInt(rs2[1][0].toString());
						model.addRow(new Object[] { rs[i][0], rs[i][1],
								rs[i][2], rs[i][3], count });
						// Pause a bit because socket exceptions occur
						try {
							Thread.sleep(10);
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
					}
				}
			}
		});
		btnShowClients_1.setBounds(253, 219, 115, 23);
		contentPane.add(btnShowClients_1);
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public void initialize(int bid) {
		// Display clients attended
		Date[] dates = new Date[5];
		dates[0] = new Date(lblDate1.getText());
		dates[1] = new Date(lblDate2.getText());
		dates[2] = new Date(lblDate3.getText());
		dates[3] = new Date(lblDate4.getText());
		dates[4] = new Date(lblDate5.getText());
		Timestamp[] t = new Timestamp[5];
		try {
			for (int i = 0; i < t.length; i++) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String day = df.format(dates[i]);
				Date curday = df.parse(day);
				long time = curday.getTime();
				t[i] = new Timestamp(time);
			}
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Error parsing system date!");
			return;
		}
		String[] q = new String[5];
		Object[][][] result = new Object[5][][];
		int[] c = new int[5];
		for (int i = 0; i < c.length; i++) {
			q[i] = "SELECT COUNT(DISTINCT CID) FROM dbo.MEETING M JOIN dbo.APPOINTMENT A ON M.AID = A.AID WHERE BID = "
					+ bid
					+ " AND DATEDIFF(day,ADATE,'"
					+ t[i].toString()
					+ "') = 0 AND ATTENDED = 1";
			result[i] = (Object[][]) client.client.send(q[i]);
			c[i] = Integer.parseInt(result[0][1][0].toString());
		}
		txtClientsMon.setText("" + c[0]);
		txtClientsTue.setText("" + c[1]);
		txtClientsWed.setText("" + c[2]);
		txtClientsThu.setText("" + c[3]);
		txtClientsFri.setText("" + c[4]);
		int sum = 0;
		for (int i : c)
			sum += i;
		txtClientsTotal.setText("" + sum);

		// Display case types
		String qr = "SELECT DISTINCT CASE_TYPE FROM dbo.CASES";
		Object[][] rsc = (Object[][]) client.client.send(qr);
		String[] listCase = new String[rsc.length];
		listCase[0] = "--Select case type--";
		for (int i = 1; i < listCase.length; i++)
			listCase[i] = rsc[i][0].toString();
		cbCaseTypes = new JComboBox(listCase);
		cbCaseTypes.setBounds(10, 285, 233, 20);
		contentPane.add(cbCaseTypes);

		// Get lawyers
		Object[][] rsl = (Object[][]) client.client
				.send("SELECT LRID,DESCRIPTION FROM dbo.LEGAL_RECOMMENDATION");
		String[] listRecom = new String[rsl.length];
		listRecom[0] = "--Select recommendation--";
		for (int i = 1; i < listRecom.length; i++)
			listRecom[i] = rsl[i][0].toString() + " - " + rsl[i][1];
		cbRecom = new JComboBox(listRecom);
		cbRecom.setBounds(408, 285, 233, 20);
		contentPane.add(cbRecom);
	}
}

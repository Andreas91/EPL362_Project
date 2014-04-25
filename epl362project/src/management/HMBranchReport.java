package management;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HMBranchReport extends JFrame {

	private JPanel contentPane;
	private JTextField txtClientsMon;
	private JTextField txtClientsTue;
	private JTextField txtClientsWed;
	private JTextField txtClientsThu;
	private JTextField txtClientsFri;
	private JTextField txtClientsTotal;
	private JTextField txtOpinionsMon;
	private JTextField txtOpinionsTue;
	private JTextField txtOpinionsWed;
	private JTextField txtOpinionsThu;
	private JTextField txtOpinionsFri;
	private JTextField txtOpinionsTotal;
	private JTextField txtCaseClients;
	private JTextField txtRecomCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HMBranchReport frame = new HMBranchReport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HMBranchReport() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Weekly Branch Report");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(102, 11, 167, 19);
		contentPane.add(label);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 41, 356, 2);
		contentPane.add(separator);

		JLabel lblDay = new JLabel("Mon");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDay.setBounds(10, 81, 46, 14);
		contentPane.add(lblDay);

		JLabel lblDate1 = new JLabel("Date1");
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

		JLabel lblDate2 = new JLabel("Date2");
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

		JLabel lblDate3 = new JLabel("Date3");
		lblDate3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDate3.setBounds(54, 131, 64, 14);
		contentPane.add(lblDate3);

		JLabel lblDate4 = new JLabel("Date4");
		lblDate4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDate4.setBounds(54, 156, 64, 14);
		contentPane.add(lblDate4);

		JLabel lblDate5 = new JLabel("Date5");
		lblDate5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDate5.setBounds(54, 181, 64, 14);
		contentPane.add(lblDate5);

		// use current date as basis for labels
		DateFormat today = new SimpleDateFormat("E");
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date dateobj = new Date();

		if (today.format(dateobj).compareToIgnoreCase("mon") == 0) {
			Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, 0);
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
	        cal.add(Calendar.DATE, 0);
			lblDate2.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate1.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 2);
			lblDate3.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate4.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate5.setText(df.format(cal.getTime()));
		} else if (today.format(dateobj).compareToIgnoreCase("wed") == 0) {
			Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, 0);
			lblDate3.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate2.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate1.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 3);
			lblDate4.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
			lblDate5.setText(df.format(cal.getTime()));
		} else if (today.format(dateobj).compareToIgnoreCase("thu") == 0) {
			Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, 0);
			lblDate4.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate3.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate2.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
			lblDate1.setText(df.format(cal.getTime()));
			cal.add(Calendar.DATE, 4);
			lblDate5.setText(df.format(cal.getTime()));
		} else if (today.format(dateobj).compareToIgnoreCase("fri") == 0) {
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
		separator_2.setBounds(0, 209, 356, 2);
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

		JLabel lblOpinionsGiven = new JLabel("Opinions Given:");
		lblOpinionsGiven.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOpinionsGiven.setBounds(253, 56, 92, 14);
		contentPane.add(lblOpinionsGiven);

		txtOpinionsMon = new JTextField();
		txtOpinionsMon.setEditable(false);
		txtOpinionsMon.setBounds(253, 78, 86, 20);
		contentPane.add(txtOpinionsMon);
		txtOpinionsMon.setColumns(10);

		txtOpinionsTue = new JTextField();
		txtOpinionsTue.setEditable(false);
		txtOpinionsTue.setBounds(253, 103, 86, 20);
		contentPane.add(txtOpinionsTue);
		txtOpinionsTue.setColumns(10);

		txtOpinionsWed = new JTextField();
		txtOpinionsWed.setEditable(false);
		txtOpinionsWed.setBounds(253, 128, 86, 20);
		contentPane.add(txtOpinionsWed);
		txtOpinionsWed.setColumns(10);

		txtOpinionsThu = new JTextField();
		txtOpinionsThu.setEditable(false);
		txtOpinionsThu.setBounds(253, 153, 86, 20);
		contentPane.add(txtOpinionsThu);
		txtOpinionsThu.setColumns(10);

		txtOpinionsFri = new JTextField();
		txtOpinionsFri.setEditable(false);
		txtOpinionsFri.setBounds(253, 178, 86, 20);
		contentPane.add(txtOpinionsFri);
		txtOpinionsFri.setColumns(10);

		txtOpinionsTotal = new JTextField();
		txtOpinionsTotal.setEditable(false);
		txtOpinionsTotal.setBounds(253, 220, 86, 20);
		contentPane.add(txtOpinionsTotal);
		txtOpinionsTotal.setColumns(10);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 247, 356, 2);
		contentPane.add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 250, 356, 2);
		contentPane.add(separator_5);

		JLabel lblCaseTypes = new JLabel("Case Types:");
		lblCaseTypes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCaseTypes.setBounds(10, 260, 76, 14);
		contentPane.add(lblCaseTypes);

		JComboBox cbCaseTypes = new JComboBox();
		cbCaseTypes.setBounds(10, 281, 133, 20);
		contentPane.add(cbCaseTypes);

		JButton btnShowClients = new JButton("Clients Involved");
		btnShowClients.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowClients.setBounds(152, 280, 117, 23);
		contentPane.add(btnShowClients);

		txtCaseClients = new JTextField();
		txtCaseClients.setEditable(false);
		txtCaseClients.setBounds(279, 281, 60, 20);
		contentPane.add(txtCaseClients);
		txtCaseClients.setColumns(10);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 312, 356, 2);
		contentPane.add(separator_6);

		JLabel lblLegalRecommendations = new JLabel("Legal Recommendations:");
		lblLegalRecommendations.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLegalRecommendations.setBounds(10, 325, 151, 14);
		contentPane.add(lblLegalRecommendations);

		JComboBox cbRecom = new JComboBox();
		cbRecom.setBounds(10, 350, 133, 20);
		contentPane.add(cbRecom);

		JButton btnShowRate = new JButton("Times Given");
		btnShowRate.setBounds(152, 349, 117, 23);
		contentPane.add(btnShowRate);

		txtRecomCount = new JTextField();
		txtRecomCount.setEditable(false);
		txtRecomCount.setBounds(279, 350, 60, 20);
		contentPane.add(txtRecomCount);
		txtRecomCount.setColumns(10);
	}
}

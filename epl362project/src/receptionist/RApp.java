package receptionist;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.ButtonGroup;

public class RApp extends JFrame {

	private JPanel contentPane;
	private JTextField txtBranch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	/**
	 * Create the frame.
	 */
	public RApp() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Schedule Appointment");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(239, 0, 240, 50);
		contentPane.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 56, 708, 2);
		contentPane.add(separator);
		
		JLabel label_1 = new JLabel("Date:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(31, 69, 46, 14);
		contentPane.add(label_1);
		
		JSpinner spDate = new JSpinner();
		spDate.setModel(new SpinnerDateModel(new Date(1398546000000L), null, null, Calendar.DAY_OF_YEAR));
		spDate.setBounds(100, 68, 169, 20);
		contentPane.add(spDate);
		
		JLabel label_2 = new JLabel("Branch:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(315, 69, 84, 14);
		contentPane.add(label_2);
		
		txtBranch = new JTextField();
		txtBranch.setText("N/A");
		txtBranch.setColumns(10);
		txtBranch.setBounds(389, 68, 60, 20);
		contentPane.add(txtBranch);
		
		JLabel label_3 = new JLabel("Type:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_3.setBounds(503, 69, 46, 20);
		contentPane.add(label_3);
		
		JRadioButton rbScheduled = new JRadioButton("Scheduled");
		buttonGroup.add(rbScheduled);
		rbScheduled.setBounds(573, 70, 109, 23);
		contentPane.add(rbScheduled);
		
		JRadioButton rbDropin = new JRadioButton("Drop-In");
		buttonGroup.add(rbDropin);
		rbDropin.setBounds(573, 96, 109, 23);
		contentPane.add(rbDropin);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 133, 708, 2);
		contentPane.add(separator_1);
		
		JLabel label_4 = new JLabel("Involved Clients:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_4.setBounds(31, 146, 133, 14);
		contentPane.add(label_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 171, 240, 136);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(415, 171, 240, 136);
		contentPane.add(scrollPane_1);
		
		JButton btnAdd = new JButton(">> Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(281, 199, 118, 23);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("<< Remove");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemove.setBounds(281, 244, 118, 23);
		contentPane.add(btnRemove);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 346, 708, 2);
		contentPane.add(separator_2);
		
		JLabel label_5 = new JLabel("Attending Lawyer:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_5.setBounds(31, 359, 155, 20);
		contentPane.add(label_5);
		
		JComboBox cbLawyer = new JComboBox();
		cbLawyer.setBounds(31, 390, 238, 23);
		contentPane.add(cbLawyer);
		
		JLabel label_6 = new JLabel("Concerning Case:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_6.setBounds(415, 359, 155, 20);
		contentPane.add(label_6);
		
		JComboBox cbCase = new JComboBox();
		cbCase.setBounds(415, 390, 240, 23);
		contentPane.add(cbCase);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 436, 708, 2);
		contentPane.add(separator_3);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setBounds(273, 448, 141, 38);
		contentPane.add(btnSave);
	}
}

package receptionist;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class RCApp extends JFrame{

	private JFrame frame;
	private JTextField txtNa;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the application.
	 */
	public RCApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 714, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblScheduleAppointment = new JLabel("Schedule Appointment");
		lblScheduleAppointment.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblScheduleAppointment.setBounds(239, 11, 240, 50);
		frame.getContentPane().add(lblScheduleAppointment);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 67, 708, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDate.setBounds(31, 80, 46, 14);
		frame.getContentPane().add(lblDate);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1398027600000L), null, null, Calendar.DAY_OF_YEAR));
		spinner.setBounds(100, 79, 169, 20);
		frame.getContentPane().add(spinner);
		
		JLabel lblBranch = new JLabel("Branch:");
		lblBranch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBranch.setBounds(315, 80, 84, 14);
		frame.getContentPane().add(lblBranch);
		
		txtNa = new JTextField();
		txtNa.setText("N/A");
		txtNa.setBounds(389, 79, 60, 20);
		frame.getContentPane().add(txtNa);
		txtNa.setColumns(10);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblType.setBounds(503, 80, 46, 20);
		frame.getContentPane().add(lblType);
		
		JRadioButton rdbtnScheduled = new JRadioButton("Scheduled");
		buttonGroup.add(rdbtnScheduled);
		rdbtnScheduled.setBounds(573, 81, 109, 23);
		frame.getContentPane().add(rdbtnScheduled);
		
		JRadioButton rdbtnDropin = new JRadioButton("Drop-In");
		buttonGroup.add(rdbtnDropin);
		rdbtnDropin.setBounds(573, 107, 109, 23);
		frame.getContentPane().add(rdbtnDropin);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 144, 708, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblInvolvedClients = new JLabel("Involved Clients:");
		lblInvolvedClients.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInvolvedClients.setBounds(31, 157, 133, 14);
		frame.getContentPane().add(lblInvolvedClients);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 182, 240, 136);
		frame.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(415, 182, 240, 136);
		frame.getContentPane().add(scrollPane_1);
		
		JButton btnAdd = new JButton(">> Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(281, 210, 118, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton button = new JButton("<< Remove");
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(281, 255, 118, 23);
		frame.getContentPane().add(button);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 357, 708, 2);
		frame.getContentPane().add(separator_2);
		
		JLabel lblAttendingLawyer = new JLabel("Attending Lawyer:");
		lblAttendingLawyer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAttendingLawyer.setBounds(31, 370, 155, 20);
		frame.getContentPane().add(lblAttendingLawyer);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(31, 401, 238, 23);
		frame.getContentPane().add(comboBox);
		
		JLabel lblConcerningCase = new JLabel("Concerning Case:");
		lblConcerningCase.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConcerningCase.setBounds(415, 370, 155, 20);
		frame.getContentPane().add(lblConcerningCase);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(415, 401, 240, 23);
		frame.getContentPane().add(comboBox_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 447, 708, 2);
		frame.getContentPane().add(separator_3);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setBounds(273, 459, 141, 38);
		frame.getContentPane().add(btnSave);
	}
}

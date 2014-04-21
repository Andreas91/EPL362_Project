package receptionist;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class RCSearchClient extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the application.
	 */
	public RCSearchClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 510, 392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSearchClient = new JLabel("Search Client");
		lblSearchClient.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSearchClient.setBounds(174, 11, 209, 25);
		frame.getContentPane().add(lblSearchClient);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 49, 600, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblSearchUsingSurname = new JLabel("Search Using Surname:");
		lblSearchUsingSurname.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearchUsingSurname.setBounds(10, 62, 180, 19);
		frame.getContentPane().add(lblSearchUsingSurname);
		
		textField = new JTextField();
		textField.setBounds(10, 92, 180, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSearchSurname = new JButton("Search");
		btnSearchSurname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearchSurname.setBounds(10, 123, 180, 23);
		frame.getContentPane().add(btnSearchSurname);
		
		JLabel lblSearchUsingId = new JLabel("Search Using ID:");
		lblSearchUsingId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearchUsingId.setBounds(305, 62, 171, 19);
		frame.getContentPane().add(lblSearchUsingId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(305, 92, 171, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSearchID = new JButton("Search");
		btnSearchID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearchID.setBounds(305, 124, 171, 23);
		frame.getContentPane().add(btnSearchID);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 165, 494, 2);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(244, 48, 1, 120);
		frame.getContentPane().add(separator_2);
		
		JLabel label = new JLabel("Client's Info:");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(10, 192, 119, 30);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("ID:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(10, 231, 42, 25);
		frame.getContentPane().add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setText("N/A");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(54, 232, 86, 22);
		frame.getContentPane().add(textField_2);
		
		JLabel label_2 = new JLabel("Flag:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(160, 231, 42, 25);
		frame.getContentPane().add(label_2);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		checkBox.setEnabled(false);
		checkBox.setBounds(208, 231, 49, 25);
		frame.getContentPane().add(checkBox);
		
		JLabel label_3 = new JLabel("Name:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_3.setBounds(10, 265, 57, 25);
		frame.getContentPane().add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setText("N/A");
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(95, 266, 130, 22);
		frame.getContentPane().add(textField_3);
		
		JLabel label_4 = new JLabel("Surname:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_4.setBounds(10, 293, 86, 25);
		frame.getContentPane().add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setText("N/A");
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(95, 294, 130, 22);
		frame.getContentPane().add(textField_4);
		
		JLabel lblScheduledAppointments = new JLabel("Scheduled Appointments:");
		lblScheduledAppointments.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblScheduledAppointments.setBounds(260, 178, 188, 19);
		frame.getContentPane().add(lblScheduledAppointments);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(263, 203, 188, 87);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblMarkAs = new JLabel("Mark As:");
		lblMarkAs.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMarkAs.setBounds(260, 310, 71, 20);
		frame.getContentPane().add(lblMarkAs);
		
		JButton btnAttended = new JButton("Attended");
		btnAttended.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAttended.setBounds(341, 295, 107, 23);
		frame.getContentPane().add(btnAttended);
		
		JButton btnNotAttended = new JButton("Not Attended");
		btnNotAttended.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNotAttended.setBounds(341, 323, 107, 23);
		frame.getContentPane().add(btnNotAttended);
	}
}

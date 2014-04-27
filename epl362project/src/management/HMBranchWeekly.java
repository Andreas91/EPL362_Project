package management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class HMBranchWeekly extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JFrame br = new HMBranchReport();

	/**
	 * Create the frame.
	 */
	public HMBranchWeekly(String user) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 113);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWeeklyBranchReport = new JLabel("Weekly Branch Report");
		lblWeeklyBranchReport.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWeeklyBranchReport.setBounds(66, 11, 167, 19);
		contentPane.add(lblWeeklyBranchReport);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 41, 298, 2);
		contentPane.add(separator);
		
		JLabel lblBranchId = new JLabel("Branch ID:");
		lblBranchId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBranchId.setBounds(10, 54, 73, 14);
		contentPane.add(lblBranchId);
		
		textField = new JTextField();
		textField.setBounds(93, 52, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnShow = new JButton("SHOW");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				br.setVisible(true);
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnShow.setBounds(189, 50, 89, 23);
		contentPane.add(btnShow);
	}
}

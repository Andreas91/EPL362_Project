package management;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class HMBranchWeekly extends JFrame {

	private JPanel contentPane;
	private JTextField txtBID;
	private HMBranchReport br = new HMBranchReport();

	/**
	 * Create the frame.
	 */
	public HMBranchWeekly(String user) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 304, 138);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWeeklyBranchReport = new JLabel("Weekly Branch Report");
		lblWeeklyBranchReport.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWeeklyBranchReport.setBounds(63, 32, 167, 19);
		contentPane.add(lblWeeklyBranchReport);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 62, 298, 2);
		contentPane.add(separator);
		
		JLabel lblBranchId = new JLabel("Branch ID:");
		lblBranchId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBranchId.setBounds(10, 75, 73, 14);
		contentPane.add(lblBranchId);
		
		txtBID = new JTextField();
		txtBID.setBounds(93, 75, 86, 20);
		contentPane.add(txtBID);
		txtBID.setColumns(10);
		
		JButton btnShow = new JButton("SHOW");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String branch = txtBID.getText();
				int bid = 0;
				if(branch.length()==0){
					JOptionPane.showMessageDialog(null, "Insert Branch ID!");
					return;
				}
				try{
					bid = Integer.parseInt(branch);
				} catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Invalid Branch ID!");
					return;
				}
				br.setVisible(true);
				br.initialize(bid);
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnShow.setBounds(189, 73, 89, 23);
		contentPane.add(btnShow);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 298, 21);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnFile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (br.isVisible())
					br.dispose();
				client.Login login = new client.Login();
				login.setVisible(true);
				dispose();
			}
		});
		mntmLogout.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnFile.add(mntmLogout);
	}
}

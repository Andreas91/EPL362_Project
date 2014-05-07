/**
 * Copyright 2014 Maria Christodoulou
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

/**
 * The management viewpoint, allowing managers to view a weekly report for a 
 * specific branch.
 * @author Maria Christodoulou
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class HMBranchWeekly extends JFrame {

	private JPanel contentPane;
	private JTextField txtBID;							// Branch ID
	private HMBranchReport br = new HMBranchReport();	// Branch Report viewpoint

	/**
	 * Class constructor, creates the frame.
	 * @param user Username of the logged in user
	 */
	public HMBranchWeekly(String user) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 304, 138);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Title
		JLabel lblWeeklyBranchReport = new JLabel("Weekly Branch Report");
		lblWeeklyBranchReport.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWeeklyBranchReport.setBounds(63, 32, 167, 19);
		contentPane.add(lblWeeklyBranchReport);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 62, 298, 2);
		contentPane.add(separator);
		
		// Label
		JLabel lblBranchId = new JLabel("Branch ID:");
		lblBranchId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBranchId.setBounds(10, 75, 73, 14);
		contentPane.add(lblBranchId);
		
		// Text box for Branch ID
		txtBID = new JTextField();
		txtBID.setBounds(93, 75, 86, 20);
		contentPane.add(txtBID);
		txtBID.setColumns(10);
		
		// Displays report based on Branch ID
		JButton btnShow = new JButton("SHOW");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Ignore action if frame is already visible
				if (br.isVisible())
					return;
				// Recreate frame (due to appearance issues)
				br.dispose();
				br = new HMBranchReport();
				// Get Branch ID from text box
				String branch = txtBID.getText();
				int bid = 0;
				if(branch.length()==0){
					JOptionPane.showMessageDialog(null, "Insert Branch ID!");
					return;
				}
				try {
					bid = Integer.parseInt(branch);
				} catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Invalid Branch ID!");
					return;
				}
				br.setVisible(true);
				// Send Branch ID to frame initializer
				br.initialize(bid);
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnShow.setBounds(189, 73, 89, 23);
		contentPane.add(btnShow);
		
		// Menu for logging out, since X is disabled
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 298, 21);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnFile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Dispose all screens and logout user
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

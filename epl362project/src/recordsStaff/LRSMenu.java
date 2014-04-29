/**
 * Copyright 2014 Andreas K. Andreou
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
package recordsStaff;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * The Menu class, gives the records staff access to his viewpoints.  
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LRSMenu extends JFrame {

	private JPanel contentPane;
	private String username = null;		// Staff's username
	private LRSClients lrsc = null;		// Clients Page
	private LRSNewClient lrsnc = null;	// New Client Page
	private LRSAccounts lrsa = null;	// Accounts Page
	private LRSNewAccount lrsna = null;	// New Account Page
	private LRSRequests lrsr = null;	// Requests Page
	private LRSHistory lrsh = null;		// History of Transactions Page

	/**
	 * Class constructor. It creates the frame
	 * for the menu.
	 * @param user staff's username.
	 */
	public LRSMenu(String user) {
		setTitle("Menu: "+user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 362);
		this.username=user;	
		
		// Menu Bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// File Option in Menu Bar
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnFile);
		
		// Logout Option in Menu Bar
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lrsc!=null && lrsc.isVisible()) lrsc.dispose();
				if (lrsnc!=null && lrsnc.isVisible()) lrsnc.dispose();
				if (lrsa!=null && lrsa.isVisible()) lrsa.dispose();
				if (lrsna!=null && lrsna.isVisible()) lrsna.dispose();
				if (lrsr!=null && lrsr.isVisible()) lrsr.dispose(); 
				if (lrsh!=null && lrsh.isVisible()) lrsh.dispose(); 
				client.Login login = new client.Login();
				login.setVisible(true);
				dispose();
			}
		});
		mntmLogout.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnFile.add(mntmLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// App Logo
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoSmall.png"));
		logo.setBounds(12, 13, 86, 71);
		contentPane.add(logo);
		
		// Title
		JLabel lblNewLabel = new JLabel("L.R.S Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(145, 13, 180, 55);
		contentPane.add(lblNewLabel);
		
		// Requests Page Button
		JButton btnReq = new JButton("Requests");
		btnReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lrsr!=null && lrsr.isVisible()) lrsr.dispose();
				lrsr = new LRSRequests(username);
				lrsr.setVisible(true);
			}
		});
		btnReq.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReq.setBounds(12, 236, 180, 42);
		contentPane.add(btnReq);
		
		// Accounts Page Button
		JButton btnAccounts = new JButton("Accounts");
		btnAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lrsa!=null && lrsa.isVisible()) lrsa.dispose();
				lrsa = new LRSAccounts(username);
				lrsa.setVisible(true);
			}
		});
		btnAccounts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAccounts.setBounds(12, 166, 180, 42);
		contentPane.add(btnAccounts);
		
		// New Account Page Button
		JButton btnNewAccount = new JButton("New Account");
		btnNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lrsna!=null && lrsna.isVisible()) lrsna.dispose();
				lrsna = new LRSNewAccount();
				lrsna.setVisible(true);
			}
		});
		btnNewAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewAccount.setBounds(229, 166, 180, 42);
		contentPane.add(btnNewAccount);
		
		// Clients Page Button
		JButton btnClients = new JButton("Clients");
		btnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lrsc!=null && lrsc.isVisible()) lrsc.dispose();
				lrsc = new LRSClients(username);
				lrsc.setVisible(true);
			}
		});
		btnClients.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClients.setBounds(12, 97, 180, 42);
		contentPane.add(btnClients);
		
		// History Page Button
		JButton btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lrsh!=null && lrsh.isVisible()) lrsh.dispose();
				lrsh = new LRSHistory(username);
				lrsh.setVisible(true);
			}
		});
		btnHistory.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHistory.setBounds(229, 236, 180, 42);
		contentPane.add(btnHistory);
		
		// New Client Button
		JButton btnNewClient = new JButton("New Client");
		btnNewClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lrsnc!=null && lrsnc.isVisible()) lrsnc.dispose();
				lrsnc = new LRSNewClient();
				lrsnc.setVisible(true);
			}
		});
		btnNewClient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewClient.setBounds(229, 97, 180, 42);
		contentPane.add(btnNewClient);
		
	}
}

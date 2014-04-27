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
package legalStaff;

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
 * The Menu class, gives the lawyer access to his appointments and cases.  
 * @author Andreas Andreou
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LSMenu extends JFrame {

	private JPanel contentPane;
	private LSApp lsa;				// Appointments viewpoint
	private LSClients lsc;			// Clients viewpoint
	private LSNewCase lsnc;			// New Case viewpoint
	private String username = null;	// Lawyers username

	/**
	 * Class constructor. It creates the frame
	 * for the menu.
	 * @param user lawyer's username.
	 */
	public LSMenu(String user) {
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
				if (lsa!=null && lsa.isVisible()) lsa.kill();
				if (lsc!=null && lsc.isVisible()) lsc.kill();
				if (lsnc!=null && lsnc.isVisible()) lsnc.dispose(); 
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
		JLabel lblNewLabel = new JLabel("Legal Staff's Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(110, 21, 219, 55);
		contentPane.add(lblNewLabel);
		
		// Appointments Page Button
		JButton btnApp = new JButton("My Appointments");
		btnApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lsa!=null && lsa.isVisible()) lsa.kill();
				lsa = new LSApp(username);
				lsa.setVisible(true);
			}
		});
		btnApp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnApp.setBounds(110, 102, 180, 42);
		contentPane.add(btnApp);
		
		// Clients Page Button
		JButton btnClients = new JButton("My Clients");
		btnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lsc!=null && lsc.isVisible()) lsc.kill();
				lsc = new LSClients(username);
				lsc.setVisible(true);
			}
		});
		btnClients.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClients.setBounds(110, 166, 180, 42);
		contentPane.add(btnClients);
		
		// New Case Button
		JButton btnNewCase = new JButton("New Case");
		btnNewCase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lsnc!=null && lsnc.isVisible()) lsnc.dispose();
				lsnc = new LSNewCase();
				lsnc.setVisible(true);
			}
		});
		btnNewCase.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewCase.setBounds(110, 224, 180, 42);
		contentPane.add(btnNewCase);
		
	}
}

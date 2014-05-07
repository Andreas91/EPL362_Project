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

package receptionist;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Menu allowing receptionists to access various actions within the receptionist 
 * viewpoint.
 * @author Maria Christodoulou
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class RMenu extends JFrame {

	private JPanel contentPane;
	private RApp app = new RApp();						// Appointments screen
	private RSearchClient rsc = new RSearchClient();	// Search screen

	/**
	 * Class constructor, creates the frame.
	 * @param user Username of the logged in user
	 */
	public RMenu(String user) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 442, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Menu to allow logout, since X is disabled
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 426, 24);
		contentPane.add(menuBar);

		JMenu menu = new JMenu("File");
		menu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("Logout");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Dispose all frames and returns user to login screen
				if (app.isVisible())
					app.dispose();
				if (rsc.isVisible())
					rsc.dispose();
				client.Login login = new client.Login();
				login.setVisible(true);
				dispose();
			}
		});
		menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menu.add(menuItem);

		// App logo
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoSmall.png"));
		logo.setBounds(12, 37, 86, 71);
		contentPane.add(logo);

		// Title
		JLabel label_1 = new JLabel("Receptionist's Menu");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setBounds(108, 42, 222, 55);
		contentPane.add(label_1);

		// Opens a RApp() frame
		JButton button = new JButton("Schedule Appointments");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!app.isActive()) {
					app.setVisible(true);
					app.initialize();
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBounds(94, 126, 205, 42);
		contentPane.add(button);

		// Opens a RSearchClient() frame
		JButton button_1 = new JButton("Search Client");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!rsc.isActive())
					rsc.setVisible(true);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_1.setBounds(94, 190, 205, 42);
		contentPane.add(button_1);

		JLabel label_2 = new JLabel("");
		label_2.setBounds(10, 35, 86, 71);
		contentPane.add(label_2);
	}
}

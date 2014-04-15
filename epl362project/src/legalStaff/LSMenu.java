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

@SuppressWarnings("serial")
public class LSMenu extends JFrame {

	private JPanel contentPane;
	private LSApp lsa = new LSApp();

	/**
	 * Create the frame.
	 */
	public LSMenu() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 331);
		
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
				if (lsa.isVisible()) lsa.kill();
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
		JLabel lblNewLabel = new JLabel("Legan Staff's Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(110, 21, 317, 55);
		contentPane.add(lblNewLabel);
		
		// Appointments Page Button
		JButton btnApp = new JButton("My Appointments");
		btnApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!lsa.isActive())
					lsa.setVisible(true);
			}
		});
		btnApp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnApp.setBounds(110, 102, 180, 42);
		contentPane.add(btnApp);
		
		// Clients Page Button
		JButton btnClients = new JButton("My Clients");
		btnClients.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClients.setBounds(110, 166, 180, 42);
		contentPane.add(btnClients);
		
	}
}

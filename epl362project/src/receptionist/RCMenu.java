package receptionist;

import java.awt.Font;
import java.awt.Window;
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
public class RCMenu extends JFrame {

	private JPanel contentPane;
	private RCApp rca = new RCApp();
	private RCSearchClient rcs = new RCSearchClient();

	/**
	 * Create the frame.
	 */
	public RCMenu() {
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
				if (rca.isVisible()) rca.dispose();
				if (rcs.isVisible()) rcs.dispose();
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
		JLabel lblReceptionistsMenu = new JLabel("Receptionist's Menu");
		lblReceptionistsMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblReceptionistsMenu.setBounds(94, 21, 222, 55);
		contentPane.add(lblReceptionistsMenu);
		
		// Appointments Page Button
		JButton btnApp = new JButton("Schedule Appointments");
		btnApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!rca.isActive())
					rca.setVisible(true);
			}
		});
		btnApp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnApp.setBounds(94, 102, 205, 42);
		contentPane.add(btnApp);
		
		// Clients Page Button
		JButton btnClients = new JButton("Search Client");
		btnClients.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if (!rcs.isActive())
					rcs.setVisible(true);
			}
		});
		btnClients.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClients.setBounds(94, 166, 205, 42);
		contentPane.add(btnClients);
		
	}
}

package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LegalStaff extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public LegalStaff() {
		
		// Create Frame
		setTitle("Legal Staff");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 485);
		
		// Create Menu Bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Add 'File' to Menu Bar
		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		// Add 'Logout' to Menu's File
		JMenuItem mntmNewMenuItem = new JMenuItem("Logout");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem);
		
		// Add 'About' to Menu Bar
		JMenu mnAbout = new JMenu("About");
		mnAbout.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnAbout);
		
		// Add 'About Us' to Menu's About
		JMenuItem mntmAboutUs = new JMenuItem("About Us");
		mntmAboutUs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnAbout.add(mntmAboutUs);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// App Logo
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoSmall.png"));
		logo.setBounds(5, 5, 86, 71);
		contentPane.add(logo);
		
		// Viewpoint's Title
		JLabel lblLegalStaffViewpoint = new JLabel("Legal Staff Viewpoint");
		lblLegalStaffViewpoint.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLegalStaffViewpoint.setBounds(92, 13, 216, 44);
		contentPane.add(lblLegalStaffViewpoint);
		
		// Line Separator
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 84, 432, 2);
		contentPane.add(separator);
		
	}
}

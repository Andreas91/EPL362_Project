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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class LegalStaff extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTextField inName;
	private JTextField inSurname;
	private JCheckBox chckbxFlag;
	private JButton btnSaveName;
	private JButton btnEditName;
	

	/**
	 * Create the frame.
	 */
	public LegalStaff() {
		
		// Create Frame
		setTitle("Legal Staff");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 794);
		
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
		separator.setBounds(0, 83, 502, 3);
		contentPane.add(separator);
		
		// Search Field
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSearch.setText("search client...");
		txtSearch.setBounds(10, 110, 455, 44);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		// Search Button
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(12, 167, 97, 25);
		contentPane.add(btnNewButton);
		
		// Line Separator
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 205, 502, 3);
		contentPane.add(separator_1);
		
		// Name Label
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(5, 281, 59, 30);
		contentPane.add(lblName);
		
		// Name TextField		
		inName = new JTextField();
		inName.setEditable(false);
		inName.setText("Client's Name");
		inName.setFont(new Font("Tahoma", Font.ITALIC, 16));
		inName.setBounds(116, 278, 216, 37);
		contentPane.add(inName);
		inName.setColumns(10);
		
		// Surname Label
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSurname.setBounds(5, 321, 99, 30);
		contentPane.add(lblSurname);
				
		// Surname TextField
		inSurname = new JTextField();
		inSurname.setText("Client's Surname");
		inSurname.setFont(new Font("Tahoma", Font.ITALIC, 16));
		inSurname.setEditable(false);
		inSurname.setColumns(10);
		inSurname.setBounds(116, 318, 216, 37);
		contentPane.add(inSurname);
		
		// Flag Label
		JLabel lblFlag = new JLabel("Flag:");
		lblFlag.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFlag.setBounds(7, 364, 59, 30);
		contentPane.add(lblFlag);
		
		// Flag CheckBox
		chckbxFlag = new JCheckBox("Yes");
		chckbxFlag.setFont(new Font("Tahoma", Font.ITALIC, 16));
		chckbxFlag.setSelected(true);
		chckbxFlag.setEnabled(false);
		chckbxFlag.setBounds(116, 367, 113, 25);
		contentPane.add(chckbxFlag);
		
		// Edit Button
		btnEditName = new JButton("Edit");
		btnEditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inName.setEditable(!inName.isEditable());
				inSurname.setEditable(!inSurname.isEditable());
				chckbxFlag.setEnabled(!chckbxFlag.isEnabled());
				btnSaveName.setEnabled(!btnSaveName.isEnabled());
				if (btnEditName.getText().equals("Edit"))
					btnEditName.setText("Cancel");
				else
					btnEditName.setText("Edit");
			}
		});
		btnEditName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditName.setBounds(113, 221, 108, 25);
		contentPane.add(btnEditName);
		
		// Save Button
		btnSaveName = new JButton("Save");
		btnSaveName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inName.setEditable(false);
				inSurname.setEditable(false);
				chckbxFlag.setEnabled(false);
				btnSaveName.setEnabled(false);
				btnEditName.setText("Edit");
			}
		});
		btnSaveName.setEnabled(false);
		btnSaveName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSaveName.setBounds(233, 221, 97, 25);
		contentPane.add(btnSaveName);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 408, 502, 3);
		contentPane.add(separator_2);
		
		JLabel lblComment = new JLabel("Comments:");
		lblComment.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblComment.setBounds(5, 419, 99, 30);
		contentPane.add(lblComment);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setBounds(10, 462, 480, 197);
		contentPane.add(textArea);
		
		JButton btnAddComment = new JButton("Add Comment");
		btnAddComment.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddComment.setBounds(10, 672, 185, 37);
		contentPane.add(btnAddComment);
		
	}
}

package legalStaff;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Comments extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;	// Table model for comments
	private JTable table;				// Table for comments
	private JTextArea input;			// Text area for displaying/writing comments
	
	private JButton btnShow;			// Show comment button
	private JButton btnNew;				// New comment button
	private JButton btnDelete;			// Delete comment button
	private JButton btnSave;			// Save comment button
	private JButton btnCancel;			// Cancel comment button
	

	/**
	 * Class constructor. It creates the frame for the
	 * comments of the given client.
	 * @param cid clint's id.
	 */
	public Comments(int cid) {
		setTitle("Comments");
		setBounds(39, 431, 511, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Comments Table
		String[] col = {"A/a", "Lawer", "Date/Time"};
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		
		// Scroll Pane for Comments Table
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 13, 469, 160);
		contentPane.add(scrollPane);
		
		// Show comment button
		btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount()!=1){
					JOptionPane.showMessageDialog(null, "Select a single row to see comment!");
				}
				else{
					
					/* Get description for selected comment */
					
					input.setText("Bla bla bla");
				}
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShow.setBounds(12, 182, 97, 25);
		contentPane.add(btnShow);
		
		// New comment button
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeState();
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNew.setBounds(116, 183, 97, 25);
		contentPane.add(btnNew);
		
		// Delete a comment
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRowCount()!=1){
					JOptionPane.showMessageDialog(null, "Select a single row to delete!");
				}
				else{
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this comment?");
					if (option==0){
						int row = table.getSelectedRow();
						Object o = model.getValueAt(row, 0);
						int comID = Integer.parseInt(o.toString());
						
						/* Delete Comment from DB*/
						
						model.removeRow(row);
						JOptionPane.showMessageDialog(null, "Comment "+comID+" deleted!");
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(225, 183, 97, 25);
		contentPane.add(btnDelete);
		
		// Scroll Pane for the new comment
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 221, 469, 155);
		contentPane.add(scrollPane_1);
				
		// Text input for displaying/writing comments
		input = new JTextArea();
		input.setEditable(false);
		input.setFont(new Font("Monospaced", Font.PLAIN, 14));
		scrollPane_1.setViewportView(input);
		
		// Save comment button
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (input.getText().length()==0){
					JOptionPane.showMessageDialog(null, "You havn't wrote anything");
				}
				else{
					/* Add new comment to DB */
					JOptionPane.showMessageDialog(null, "New comment added!");
					changeState();
				}
			}
		});
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(12, 389, 97, 25);
		contentPane.add(btnSave);
		
		// Cancel new comment button
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeState();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setEnabled(false);
		btnCancel.setBounds(116, 390, 97, 25);
		contentPane.add(btnCancel);
		
		// Insert Comments for the selected client
		insertCom();
	}
	
	private void insertCom(){
		
		/* Get comments from db */
		
		for (int i=1;i<11;i++)
			model.addRow(new Object[]{i, "Name"+i+" Surname"+i, "15/4/14 "+i+"0:00"});
	}
	
	private void changeState(){
		input.setText("");
		btnShow.setEnabled(!btnShow.isEnabled());
		btnNew.setEnabled(!btnNew.isEnabled());
		btnDelete.setEnabled(!btnDelete.isEnabled());
		btnSave.setEnabled(!btnSave.isEnabled());
		btnCancel.setEnabled(!btnCancel.isEnabled());
		table.setEnabled(!table.isEnabled());
		input.setEditable(!input.isEditable());
	}
}

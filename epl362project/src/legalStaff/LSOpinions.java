package legalStaff;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LSOpinions extends JFrame {

	private JPanel contentPane;
	private JTable table;				// Legal Opinions Table
	private DefaultTableModel model;	// Table model for Legal Opinions
	private JTextArea inOpinion;		// Legal Opinion Text Area
	private JButton btnShow;			// Legal Opinion Show Button
	private JButton btnNew;				// New Legal Opinion Button
	private JButton btnAdd;				// Add Legal Opinion Button
	private String tOpinion;			// Temporary stores Opinion if new is cancel
	private int caseID;					// Case ID to load the Legal Opinions for
	
	/**
	 * Create the frame.
	 */
	public LSOpinions(int caseID) {
		setTitle("Case: "+caseID);
		setBounds(100, 100, 439, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.caseID=caseID;
		
		// Legal Opinion Title
		JLabel label = new JLabel("Legal Opinion:");
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		label.setBounds(12, 36, 300, 30);
		contentPane.add(label);
		
		// Legal Opinion Table
		String[] col = {"A/a", "Data", "Description"};
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
				
		// Scroll Pane for Legal Opinions
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 67, 395, 144);
		contentPane.add(scrollPane);
		
		// Show Legal Opinion Button
		btnShow = new JButton("v Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null, "Select an opinion to show!");
				} else {
					Object o = model.getValueAt(table.getSelectedRow(), 0);
					getOpinion((int) o);
				}
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShow.setBounds(52, 224, 97, 25);
		contentPane.add(btnShow);
		
		// New Legal Opinion Button
		btnNew = new JButton("+");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newOpinion();
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNew.setBounds(161, 224, 97, 25);
		contentPane.add(btnNew);
		
		// Add new Legal Opinion Button
		btnAdd = new JButton("^ Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setEnabled(false);
		btnAdd.setBounds(266, 224, 97, 25);
		contentPane.add(btnAdd);
		
		// Legal Opinion Text Area
		inOpinion = new JTextArea();
		inOpinion.setEditable(false);
		inOpinion.setBounds(12, 262, 395, 156);
		contentPane.add(inOpinion);
		
		// Insert Legal Opinions about the given case
		insertOpinions();
	}
	
	private void insertOpinions(){
		
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		/* Select from DB this.caseID */
		
		// Add data to the table
		model.addRow(new Object[] { 1, "14-Apr-14", "Bla Bla Bla" });
		model.addRow(new Object[] { 2, "14-Apr-14", "Bla Bla Bla" });
	}
	
	private void getOpinion(int oid) {
		/* getOpinion from DB */
		this.inOpinion.setText("Bla Bla Bla\nBla Bla Bla\nBla Bla Bla");
	}
	
	private void newOpinion() {
		if (this.btnNew.getText().equals("+")) {
			this.btnNew.setText("X");
			this.tOpinion = this.inOpinion.getText();
			this.inOpinion.setText("");
		} else {
			this.btnNew.setText("+");
			this.inOpinion.setText(this.tOpinion);
		}
		inOpinion.setEditable(!inOpinion.isEditable());
		table.setEnabled(!table.isEnabled());
		btnShow.setEnabled(!btnShow.isEnabled());
		btnAdd.setEnabled(!btnAdd.isEnabled());
	}
}

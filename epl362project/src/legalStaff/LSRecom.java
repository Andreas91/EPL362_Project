package legalStaff;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;

import client.client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial","rawtypes","unchecked"})
public class LSRecom extends JFrame {

	private JPanel contentPane;
	private JTable table;				// Legal Recommendation Table
	private DefaultTableModel model;	// Table model for Legal Recommendation
	private JComboBox cbbRec;			// 
	private JCheckBox chAccepted;		// 
	private JButton btnAdd;				// 
	private JButton btnEffects;			// 
	private int caseID;					// 

	/**
	 * Create the frame.
	 */
	public LSRecom(int caseid) {
		setTitle("Case: "+caseID);
		setBounds(100, 100, 494, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.caseID=caseid;
		
		// Legal Recommendations Title
		JLabel label = new JLabel("Legal Recom.:");
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		label.setBounds(19, 13, 164, 30);
		contentPane.add(label);
		
		// Legal Recommendation Table
		String[] col = {"Rec.ID", "Date", "Description", "Accepted" };
		Object[][] data = {};
		model = new DefaultTableModel(data, col);
		table = new JTable(model){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};	
		
		// Scroll Pane for Legal Recommendation
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 45, 452, 207);
		contentPane.add(scrollPane);
		
		// Legal Recommendations ComboBox
		Object[][] rs = (Object[][]) client.send("SELECT LRID,[DESCRIPTION] FROM dbo.LEGAL_RECOMMENDATION");
		String[] listRecom = new String[rs.length-1];
		for (int i=0;i<listRecom.length;i++){
			listRecom[i]=rs[i+1][1].toString();
		}
		cbbRec = new JComboBox(listRecom);
		cbbRec.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbRec.setBounds(12, 306, 452, 38);
		contentPane.add(cbbRec);
		
		// 
		chAccepted = new JCheckBox("Accepted");
		chAccepted.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chAccepted.setBounds(12, 353, 277, 25);
		contentPane.add(chAccepted);
		
		// 
		btnAdd = new JButton("^ Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRec();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(12, 265, 97, 25);
		contentPane.add(btnAdd);
		
		// 
		btnEffects = new JButton("v Side-Effects");
		btnEffects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getEffect();
			}
		});
		btnEffects.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEffects.setBounds(121, 265, 130, 25);
		contentPane.add(btnEffects);
		
		insertRec();
	}
	
	private void insertRec(){
		// Clear Table
		while (model.getRowCount() != 0) model.removeRow(0);
		
		/* Select...this.caseID */
		
		model.addRow(new Object[] {1, "14-Apr-14", "Recommendation 1", false});
		model.addRow(new Object[] {2, "14-Apr-14", "Recommendation 2", true});
		
	}
	
	private void addRec(){
		int recid = this.cbbRec.getSelectedIndex() + 1;
		String rec = (String) this.cbbRec.getSelectedItem();
		boolean acc = this.chAccepted.isSelected();
		
		/* Add to DB */
		
		model.addRow(new Object[] {recid, "18-Apr-14", rec, acc});
	}
	
	private void getEffect(){
		int recid = this.cbbRec.getSelectedIndex() + 1;
		
		/* Get side-effect from DB */
		
		JOptionPane.showMessageDialog(null,"Side-Effect("+recid+"):\nN/A");
	}
}

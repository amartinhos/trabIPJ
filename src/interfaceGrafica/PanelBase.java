package interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.*;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class PanelBase extends JPanel{

	private JTextField text, comment;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JPanel panel;

	PanelBase() {

		this.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setVisible(true);
		panel.setLayout(new GridLayout(5, 1));

		text = new JTextField();
		comment = new JTextField();
		comment.setEditable(false);
		comment.setBorder(null);
		comment.setBackground(Color.BLACK);
		comment.setForeground(Color.RED);

		model = new DefaultTableModel();
		table = new JTable(model) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		

		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		scroll = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		add(panel, BorderLayout.WEST);
		add(scroll);
		add(comment, BorderLayout.SOUTH);
		add(text, BorderLayout.NORTH);
	}

	public void addActionTable(ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);
	}
	
	public void addListenerText(ActionListener listener){
		text.addActionListener(listener);
	}
	
	public void addMouseTable(MouseAdapter listener) {
		table.addMouseListener(listener);
	}
	
	public String getText(){
		return text.getText();
	}
	
	public void addButton(JButton button){
		panel.add(button);
	}
	
	public void setComment(String comment){
		this.comment.setText(comment);
	}
	
	public void addColumn(String s){
		model.addColumn(s);
	}
	
	public void addRow(String[] s){
		model.addRow(s);
	}
	
	public void emptyModel(){
		model.setRowCount(0);
	}
	
	public int getSelectedRow(){
		return table.getSelectedRow();
	}

	public boolean isModelEmpty(){
		if (model.getRowCount()==0) return true;
		else  return false;
	}
	
}

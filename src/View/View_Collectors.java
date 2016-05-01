package View;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class View_Collectors extends Exit_class{
	private JFrame collectors_frame;
	private String[] columnNames = {"Name","Age"};
	private JTable table;
	JScrollPane scrp_table = new JScrollPane();
	public JButton getBtn_auction() {
		return btn_auction;
	}
	public void setBtn_auction(JButton btn_auction) {
		this.btn_auction = btn_auction;
	}
	private JButton btn_Add;
	private JButton btn_auction;
	private JLabel lblName;
	private JTextField txf_name;
	private JLabel lblAge;
	private DefaultTableModel model;

	private JTextField txf_age;
	public View_Collectors(){
		collectors_frame = new JFrame("Collectors");
		collectors_frame.setSize(600,600);
		collectors_frame.getContentPane().setLayout(null);
		collectors_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		collectors_frame.setLocation(dim.width/2-collectors_frame.getSize().width/2, dim.height/2-collectors_frame.getSize().height/2);
		
		JScrollPane scrp_table = new JScrollPane();
		scrp_table.setSize(175, 500);
		scrp_table.setLocation(395, 11);
		collectors_frame.getContentPane().add(scrp_table);
		
		model = new DefaultTableModel();
		for (int i = 0; i < columnNames.length; i++) {
			model.addColumn(columnNames[i]);
		}
		table = new JTable(model);
		scrp_table.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(	250,100));
		table.setFillsViewportHeight(true);
		
		btn_Add = new JButton("ADD");
		btn_Add.setBounds(10, 11, 117, 40);
		collectors_frame.getContentPane().add(btn_Add);
		
		btn_auction = new JButton("Proceed");
		btn_auction.setBounds(10, 62, 117, 40);
		collectors_frame.getContentPane().add(btn_auction);

		btn_exit.setBounds(10, 515, 117, 35);
		collectors_frame.getContentPane().add(btn_exit);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(216, 24, 46, 14);
		collectors_frame.getContentPane().add(lblName);
		
		txf_name = new JTextField();
		txf_name.setBounds(272, 21, 101, 20);
		collectors_frame.getContentPane().add(txf_name);
		txf_name.setColumns(10);
		
		lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAge.setBounds(216, 62, 46, 20);
		collectors_frame.getContentPane().add(lblAge);
		
		txf_age = new JTextField();
		txf_age.setBounds(272, 62, 101, 20);
		collectors_frame.getContentPane().add(txf_age);
		txf_age.setColumns(10);
		//view_collectors.setVisible(true);
		
	}
	public JFrame getCollectors_frame() {
		return collectors_frame;
	}
	public void setCollectors_frame(JFrame collectors_frame) {
		this.collectors_frame = collectors_frame;
	}
	public String[] getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public JButton getBtn_Add() {
		return btn_Add;
	}
	public void setBtn_Add(JButton btn_Add) {
		this.btn_Add = btn_Add;
	}
	public JButton getBtnViewDetails() {
		return btn_auction;
	}
	public void setBtnViewDetails(JButton btnViewDetails) {
		this.btn_auction = btnViewDetails;
	}

	public JTextField getTxf_name() {
		return txf_name;
	}
	public void setTxf_name(JTextField txf_name) {
		this.txf_name = txf_name;
	}
	public JTextField getTxf_age() {
		return txf_age;
	}
	public void setTxf_age(JTextField txf_age) {
		this.txf_age = txf_age;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	public void addAddCollectorListener(ActionListener listen){
		this.btn_Add.addActionListener(listen);
	}
	public void SelectCollectorListener(ActionListener listen){
		this.btn_auction.addActionListener(listen);
	}

}

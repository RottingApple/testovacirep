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

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class View_Auctions extends Exit_class{
	private JFrame auctions_frame;
	private JTable table;
	private String[] columnNames = {"Event Name","Creator","Galery","State"};
	private DefaultTableModel model;
	private JButton btnAddAuction;
	private JButton btnViewDetails;
	private JButton btn_endAuction;
	private JLabel lblGalery;
	private JComboBox<String> comboBox_Galery;
	private JLabel lblEventName;
	private JTextField txf_EvnName;
	private JLabel lblCreator;
	private JTextField txf_Creator;
	private JLabel label;
	private JLabel lblAmountOfPieces;
	private JTextField txf_amountpiec;
	private JLabel lblTotalValueOf;
	private JTextField txf_value;
	private JButton btn_Calculate;
	public View_Auctions(){
		auctions_frame = new JFrame("Auctions");
		auctions_frame.setSize(1000,600);
		auctions_frame.getContentPane().setLayout(null);
		auctions_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		auctions_frame.setLocation(dim.width/2-auctions_frame.getSize().width/2, dim.height/2-auctions_frame.getSize().height/2);
		
		model = new DefaultTableModel();
		for (int i = 0; i < columnNames.length; i++) {
			model.addColumn(columnNames[i]);
		}
		table = new JTable(model);
		JScrollPane scrp_table = new JScrollPane();
		scrp_table.setSize(490, 500);
		scrp_table.setLocation(484, 0);
		auctions_frame.getContentPane().add(scrp_table);
		
		scrp_table.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(	250,100));
		table.setFillsViewportHeight(true);
		
		btnAddAuction = new JButton("Add Auction");
		btnAddAuction.setBounds(10, 11, 113, 45);
		auctions_frame.getContentPane().add(btnAddAuction);
		
		btnViewDetails = new JButton("View Details");
		btnViewDetails.setBounds(10, 67, 113, 45);
		auctions_frame.getContentPane().add(btnViewDetails);
		
		btn_endAuction = new JButton("End Auction");
		btn_endAuction.setBounds(10, 123, 113, 45);
		auctions_frame.getContentPane().add(btn_endAuction);
		
		btn_exit.setBounds(10,510, 128, 45);
		auctions_frame.getContentPane().add(btn_exit);
		
		lblGalery = new JLabel("Galery");
		lblGalery.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGalery.setBounds(222, 22, 46, 17);
		auctions_frame.getContentPane().add(lblGalery);
		
		comboBox_Galery = new JComboBox<String>();
		comboBox_Galery.setBounds(309, 22, 123, 20);
		auctions_frame.getContentPane().add(comboBox_Galery);
		
		lblEventName = new JLabel("Event Name");
		lblEventName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEventName.setBounds(222, 68, 75, 17);
		auctions_frame.getContentPane().add(lblEventName);
		
		txf_EvnName = new JTextField();
		txf_EvnName.setBounds(309, 68, 123, 20);
		auctions_frame.getContentPane().add(txf_EvnName);
		txf_EvnName.setColumns(10);
		
		lblCreator = new JLabel("Creator");
		lblCreator.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCreator.setBounds(222, 119, 64, 17);
		auctions_frame.getContentPane().add(lblCreator);
		
		txf_Creator = new JTextField();
		txf_Creator.setBounds(309, 119, 123, 20);
		auctions_frame.getContentPane().add(txf_Creator);
		txf_Creator.setColumns(10);
		
		label = new JLabel("");
		label.setBounds(309, 297, 46, 14);
		auctions_frame.getContentPane().add(label);
		
		lblAmountOfPieces = new JLabel("Amount of Pieces Sold");
		lblAmountOfPieces.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmountOfPieces.setBounds(273, 288, 151, 45);
		auctions_frame.getContentPane().add(lblAmountOfPieces);
		
		txf_amountpiec = new JTextField();
		txf_amountpiec.setBounds(309, 334, 70, 20);
		auctions_frame.getContentPane().add(txf_amountpiec);
		txf_amountpiec.setColumns(10);
		
		lblTotalValueOf = new JLabel("Total Value of Pieces Sold");
		lblTotalValueOf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalValueOf.setBounds(260, 365, 172, 26);
		auctions_frame.getContentPane().add(lblTotalValueOf);
		
		txf_value = new JTextField();
		txf_value.setBounds(270, 402, 151, 20);
		auctions_frame.getContentPane().add(txf_value);
		txf_value.setColumns(10);
		
		btn_Calculate = new JButton("Calculate");
		btn_Calculate.setBounds(300, 430, 89, 45);
		auctions_frame.getContentPane().add(btn_Calculate);
		
		//view_auctions.setVisible(true);
	}
	public JFrame getAuctions_frame() {
		return auctions_frame;
	}
	public void setAuctions_frame(JFrame auctions_frame) {
		this.auctions_frame = auctions_frame;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public String[] getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	public JButton getBtnAddAuction() {
		return btnAddAuction;
	}
	public void setBtnAddAuction(JButton btnAddAuction) {
		this.btnAddAuction = btnAddAuction;
	}
	public JButton getBtnViewDetails() {
		return btnViewDetails;
	}
	public void setBtnViewDetails(JButton btnViewDetails) {
		this.btnViewDetails = btnViewDetails;
	}
	public JButton getBtnDeleteAuction() {
		return btn_endAuction;
	}
	public void setBtnDeleteAuction(JButton btnDeleteAuction) {
		this.btn_endAuction = btnDeleteAuction;
	}
	public JComboBox<String> getComboBox_Galery() {
		return comboBox_Galery;
	}
	public void setComboBox_Galery(JComboBox<String> comboBox_Galery) {
		this.comboBox_Galery = comboBox_Galery;
	}
	public JTextField getTxf_EvnName() {
		return txf_EvnName;
	}
	public void setTxf_EvnName(JTextField txf_EvnName) {
		this.txf_EvnName = txf_EvnName;
	}
	public JTextField getTxf_Creator() {
		return txf_Creator;
	}
	public void setTxf_Creator(JTextField txf_Creator) {
		this.txf_Creator = txf_Creator;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public JTextField getTxf_amountpiec() {
		return txf_amountpiec;
	}
	public void setTxf_amountpiec(JTextField txf_amountpiec) {
		this.txf_amountpiec = txf_amountpiec;
	}
	public JTextField getTxf_value() {
		return txf_value;
	}
	public void setTxf_value(JTextField txf_value) {
		this.txf_value = txf_value;
	}
	public JButton getBtn_Calculate() {
		return btn_Calculate;
	}
	public void setBtn_Calculate(JButton btn_Calculate) {
		this.btn_Calculate = btn_Calculate;
	}
	public void addAddAuctionListener(ActionListener listen){
		this.btnAddAuction.addActionListener(listen);
	}
	public void addENdAuctionListener(ActionListener listen){
		this.btn_endAuction.addActionListener(listen);
	}
	public void addCalculateListener(ActionListener listen){
		this.btn_Calculate.addActionListener(listen);
	}
	public void addListener(ActionListener listen){
		this.btn_Calculate.addActionListener(listen);
	}
	public void addDetailListener(ActionListener listen){
		this.btnViewDetails.addActionListener(listen);
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
}

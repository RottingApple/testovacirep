package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class View_Collectors_Det extends Exit_class {
	private JFrame colldet_frame;
	private JTable table;
	
	private String[] columnNames = {"Name","Category","Created","Value","ID"};
	private JButton btn_buy;
	private DefaultTableModel model;;

	private JComboBox<String> comboBox_Auction;
	private JButton btn_select;
	
	public View_Collectors_Det(){
		colldet_frame = new JFrame("Buy_Items");
		colldet_frame.setSize(820,600);
		colldet_frame.getContentPane().setLayout(null);
		colldet_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		colldet_frame.setLocation(dim.width/2-colldet_frame.getSize().width/2, dim.height/2-colldet_frame.getSize().height/2);
		
		JScrollPane scrp_table = new JScrollPane();
		scrp_table.setSize(500, 480);
		scrp_table.setLocation(180, 0);
		colldet_frame.getContentPane().add(scrp_table);
		model = new DefaultTableModel();
		for (int i = 0; i < columnNames.length; i++) {
			model.addColumn(columnNames[i]);
		}
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_buy.setVisible(true);
				btn_buy.setBounds(690, (int)MouseInfo.getPointerInfo().getLocation().getY()-50,100,50);
			}
		});
		scrp_table.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(	250,100));
		table.setFillsViewportHeight(true);
		
		JLabel lblSelectAuction = new JLabel("Select Auction");
		lblSelectAuction.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectAuction.setBounds(24, 11, 118, 37);
		colldet_frame.getContentPane().add(lblSelectAuction);
		
		comboBox_Auction = new JComboBox<String>();
		comboBox_Auction.setBounds(24, 70, 103, 20);
		colldet_frame.getContentPane().add(comboBox_Auction);
		
		btn_buy = new JButton("Buy");
		btn_buy.setBounds(690, 44, 89, 23);
		colldet_frame.getContentPane().add(btn_buy);
		
		btn_select = new JButton("Select");
		btn_select.setBounds(34, 118, 89, 23);
		colldet_frame.getContentPane().add(btn_select);
		btn_buy.setVisible(false);
		
		btn_exit.setBounds(10, 510, 120, 45);
		colldet_frame.getContentPane().add(btn_exit);
		//colldet_frame.setVisible(true);
	}
	public JFrame getColldet_frame() {
		return colldet_frame;
	}
	public void setColldet_frame(JFrame colldet_frame) {
		this.colldet_frame = colldet_frame;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public JButton getBtn_buy() {
		return btn_buy;
	}
	public void setBtn_buy(JButton btn_buy) {
		this.btn_buy = btn_buy;
	}
	public JComboBox<String> getComboBox_Auction() {
		return comboBox_Auction;
	}
	public void setComboBox_Auction(JComboBox<String> comboBox_Auction) {
		this.comboBox_Auction = comboBox_Auction;
	}
	public JButton getBtn_select() {
		return btn_select;
	}
	public void setBtn_select(JButton btn_select) {
		this.btn_select = btn_select;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	public void addSelectListener(ActionListener listen){
		this.btn_select.addActionListener(listen);
	}
	public void addBuyListener(ActionListener listen){
		this.btn_buy.addActionListener(listen);
	}
}

package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class View_Auction_Det extends Exit_class{
	private JFrame auctdet_frame;
	private JTable table;
	private DefaultTableModel model;
	private String[] columnNames = {"Collectors Name","Art Piece","Value"};
	public View_Auction_Det(){
		auctdet_frame = new JFrame("Auctions Details");
		auctdet_frame.setSize(500,600);
		auctdet_frame.getContentPane().setLayout(null);
		auctdet_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		auctdet_frame.setLocation(dim.width/2-auctdet_frame.getSize().width/2, dim.height/2-auctdet_frame.getSize().height/2);
		
		model = new DefaultTableModel();
		for (int i = 0; i < columnNames.length; i++) {
			model.addColumn(columnNames[i]);
		}
		table = new JTable(model);
		JScrollPane scrp_table = new JScrollPane();
		scrp_table.setSize(500, 500);
		scrp_table.setLocation(0, 0);
		auctdet_frame.getContentPane().add(scrp_table);
		
		scrp_table.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(	250,100));
		table.setFillsViewportHeight(true);
		
		btn_exit.setBounds(200, 511, 114, 39);
		auctdet_frame.getContentPane().add(btn_exit);
				
	}
	public JFrame getAuctdet_frame() {
		return auctdet_frame;
	}
	public void setAuctdet_frame(JFrame auctdet_frame) {
		this.auctdet_frame = auctdet_frame;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
}

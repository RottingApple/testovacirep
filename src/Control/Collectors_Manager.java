package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;


import Model.Art_Piece;
import Model.Auction;
import Model.Collector;
import Model.Model_Lists;
import Model.Transaction;
import View.View_Error;
import View.View_Manager;

public class Collectors_Manager extends Database_Manager{
	private int offset = 0;
	Connection con;
	View_Manager view_manager;
	Model_Lists list = Model_Lists.getInstance();
	private boolean populate = false;
	public Collectors_Manager(Connection con, View_Manager view_manager) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.view_manager = view_manager;
	}

	public void setbuttonlisteners(){
		if(view_manager.getView_collectors().getBtn_Add().getActionListeners().length == 0){
			view_manager.getView_collectors().addAddCollectorListener(new AddCollector());
			view_manager.getView_collectors().SelectCollectorListener(new SelectCollector());
			}
	}

	public void populateCollectors() {
		// TODO Auto-generated method stub
		LinkedList cmb_filler = new LinkedList();
		for (int i = view_manager.getView_collectors().getModel().getRowCount()-1; i>= 0 ; i--) {
			view_manager.getView_collectors().getModel().removeRow(i);
		}
		cmb_filler = selectQuery(con,"Select * from collectors","get_collectors");
		HashMap<Integer,Collector> collectors = new HashMap<Integer,Collector>();
		for (int i = 0; i < cmb_filler.size(); i++) {
			Collector collector =(Collector) cmb_filler.get(i);
			collectors.put(i, collector);
			view_manager.getView_collectors().getModel().addRow(new Object[]{collector.getName(),collector.getAge()});
		}
		list.setCollectors(collectors);
		//System.out.println("Velskot je "+auctions.size());
	}
	

	private void populatedetails() {
		// TODO Auto-generated method stub
		if(populate == false){
		LinkedList cmb_filler = new LinkedList();
		cmb_filler = selectQuery(con, "Select name, auction_id from auctions where ended = false", "get_auctions");
		HashMap<Integer,Auction>auctions = new HashMap<Integer,Auction>();
		for (int i = 0; i < cmb_filler.size(); i++) {
			Auction auction = (Auction) cmb_filler.get(i);
			view_manager.getView_coll_det().getComboBox_Auction().addItem(auction.getName());
			auctions.put(i, auction);
		}
		view_manager.getView_coll_det().addSelectListener(new SelectedAuction());
		view_manager.getView_coll_det().addBuyListener(new BuyItem());
		list.setAuctions(auctions);
		populate = true;
		}
	}
	private void populateDetailstable(){
		LinkedList cmb_filler = new LinkedList();
		for (int i = view_manager.getView_coll_det().getModel().getRowCount()-1; i>= 0 ; i--) {
			view_manager.getView_coll_det().getModel().removeRow(i);
		}
		cmb_filler = selectQuery(con,"Select * from art_pieces","get_pieces");
		for (int i = 0; i < cmb_filler.size(); i++) {
			Art_Piece piece =(Art_Piece) cmb_filler.get(i);
			view_manager.getView_coll_det().getModel().addRow(new Object[]{piece.getName(),piece.getStyle_ref(),piece.getCreationdate(),piece.getValue(),piece.getPiece_id()});
		}
	}
	@Override
	PreparedStatement setvalues(PreparedStatement statement, Object insertobj, String type) {
		// TODO Auto-generated method stub
		try {
			if(type == "insert_collectors"){
				Collector collector = (Collector) insertobj;
				statement.setString(1, collector.getName());
				statement.setInt(2,collector.getAge());
				return statement;
			}
			if(type == "insert_transaction"){
				Transaction transaction = (Transaction) insertobj;
				statement.setInt(1, transaction.getCollector_ref());
				statement.setInt(3, transaction.getArtpiece_ref());
				statement.setInt(2, transaction.getAuction_ref());
				return statement;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		
		return null;
	}

	@Override
	Object processrow(ResultSet rs, String type) {
		try {
		if(type == "get_collectors"){
			return new Collector(rs.getString("name"),rs.getInt("collector_id"),rs.getInt("age"));
		}
		if(type == "get_pieces"){
			return new Art_Piece(rs.getInt("piece_id"),rs.getString("style_ref"),rs.getString("name"),rs.getString("creation_date"),rs.getDouble("value"),null);
		}
		if(type == "get_auctions"){
			return new Auction(rs.getInt("auction_id"),rs.getString("name"));
		}
		else{
				return new String(rs.getString(type));
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	return rs;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public class AddCollector implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try{
			ArrayList collectors = new ArrayList<Collector>();
			Collector collector = new Collector(view_manager.getView_collectors().getTxf_name().getText(),Integer.parseInt(view_manager.getView_collectors().getTxf_age().getText()));
			collectors.add(collector);
			insertQuery(con, "INSERT into collectors(name,age) VALUES (?,?)", "insert_collectors", collectors);
			commitQuery(con);
			}catch(NumberFormatException exp){
				View_Error error = new View_Error();
			}
		}
		
	}
	
	public class SelectCollector implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try{
			list.setSelectedcoll_id(list.getCollectors().get(view_manager.getView_collectors().getTable().getSelectedRow()).getCollector_id());
			populatedetails();
			System.out.println("Collectors is je "+list.getSelectedcoll_id());
			view_manager.getView_coll_det().getColldet_frame().setVisible(true);
			}catch(NumberFormatException | NullPointerException exp){
				View_Error error = new View_Error();
			}
		}
	
	}
	
	public class SelectedAuction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try{
			int selected_row = view_manager.getView_coll_det().getComboBox_Auction().getSelectedIndex();
			int auction_id = list.getAuctions().get(selected_row).getAuction_id();
			System.out.println("Vybral si id :"+auction_id);
			LinkedList cmb_filler = new LinkedList();
			for (int i = view_manager.getView_coll_det().getModel().getRowCount()-1; i>= 0 ; i--) {
				view_manager.getView_coll_det().getModel().removeRow(i);
			}
			cmb_filler = selectQuery(con,"Select pieces.* from auctions a join galeries gal on gal.galerie_id = a.galery_ref join artpiece_location artloc on gal.galerie_id = artloc.galerie_ref join art_pieces pieces on pieces.piece_id = artloc.artpiece_ref where artloc.ended is null and a.ended = false and a.auction_id ="+auction_id,"get_pieces");
			for (int i = 0; i < cmb_filler.size(); i++) {
				Art_Piece piece =(Art_Piece) cmb_filler.get(i);
				view_manager.getView_coll_det().getModel().addRow(new Object[]{piece.getName(),piece.getStyle_ref(),piece.getCreationdate(),piece.getValue(),piece.getPiece_id()});
			}
		}catch(NumberFormatException exp){
			View_Error error = new View_Error();
		}
		}
		
	}
	
	public class BuyItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try{
			System.out.println("Predavam");
			int selected_row = view_manager.getView_coll_det().getTable().getSelectedRow();
			int piece_id = (int)view_manager.getView_coll_det().getModel().getValueAt(selected_row, 4);
			int collector_id = list.getSelectedcoll_id();
			int selected_auction_row = view_manager.getView_coll_det().getComboBox_Auction().getSelectedIndex();
			int auction_id = list.getAuctions().get(selected_auction_row).getAuction_id();
			System.out.println("Collector id je "+collector_id);
			System.out.println("Auction id je "+auction_id);
			System.out.println("Artpiece_id je "+piece_id);
			ArrayList transactions = new ArrayList<Transaction>();
			Transaction transaction = new Transaction(auction_id,piece_id,collector_id);
			transactions.add(transaction);
			Calendar cal = Calendar.getInstance();
			Timestamp stamp = new Timestamp(cal.getTimeInMillis());
			insertQuery(con, "INSERT into transaction (collector_ref,auction_ref,art_piece_ref) VALUES (?,?,?)", "insert_transaction", transactions);
			deleteUpdateQuery(con, "UPDATE artpiece_location SET ended = '"+stamp+"' WHERE artpiece_ref = "+piece_id+"and ended is null");
			commitQuery(con);
			}catch(NumberFormatException exp){
				View_Error error = new View_Error();
			}
		}
	}
	

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}

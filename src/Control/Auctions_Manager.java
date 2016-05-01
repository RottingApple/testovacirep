package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;

import Model.Auction;
import Model.Bought;
import Model.Model_Lists;
import View.View_Error;
import View.View_Manager;

public class Auctions_Manager extends Database_Manager{
	private int offset = 0;
	private Model_Lists list;
	private Connection con;
	private View_Manager view_manager;
	Auctions_Manager(Connection con,View_Manager view_manager){
		this.list = Model_Lists.getInstance();
		this.con = con;
		this.view_manager = view_manager;
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void populateAuctions(Connection con){
		LinkedList cmb_filler = new LinkedList();
		for (int i = view_manager.getView_auctions().getModel().getRowCount()-1; i>= 0 ; i--) {
			view_manager.getView_auctions().getModel().removeRow(i);
		}
		cmb_filler = selectQuery(con, "Select name from galeries", "name");
		view_manager.getView_auctions().getComboBox_Galery().setModel(new DefaultComboBoxModel(cmb_filler.toArray()));
		cmb_filler = selectQuery(con,"Select * from auctions a join (select gal.name as galname,gal.galerie_id from galeries gal) gal on gal.galerie_id = a.galery_ref order by gal.galerie_id","get_auctions");
		HashMap<Integer,Auction> auctions = new HashMap<Integer,Auction>();
		for (int i = 0; i < cmb_filler.size(); i++) {
			Auction auction =(Auction) cmb_filler.get(i);
			auctions.put(i, auction);
			//"Event Name","Creator","Galery","State"
			view_manager.getView_auctions().getModel().addRow(new Object[]{auction.getName(),auction.getCreator(),auction.getGalery(),auction.isEnded()});
		}
		//System.out.println("Velskot je "+auctions.size());
		list.setAuctions(auctions);
	}
	
	public class ShowTransactions implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try{
			int selected_auct = view_manager.getView_auctions().getTable().getSelectedRow();
			int auction_id = list.getAuctions().get(selected_auct).getAuction_id();
			LinkedList cmb_filler = new LinkedList();
			cmb_filler = selectQuery(con,"Select artp.name as artpname, artp.value, col.name as colname from auctions auc  join transaction trans on trans.auction_ref = auc.auction_id join collectors col on col.collector_id = trans.collector_ref join art_pieces artp on artp.piece_id = trans.art_piece_ref where auc.auction_id ="+auction_id,"get_bought");
			for (int i = view_manager.getView_auction_det().getModel().getRowCount()-1; i>= 0 ; i--) {
				view_manager.getView_auction_det().getModel().removeRow(i);
			}
			for (int i = 0; i < cmb_filler.size(); i++) {
				Bought bought = (Bought) cmb_filler.get(i);
				view_manager.getView_auction_det().getModel().addRow(new Object[]{bought.getCollector(),bought.getArtpiece(),bought.getValue()});
			}
			view_manager.getView_auction_det().getAuctdet_frame().setVisible(true);
		
		}catch(NumberFormatException | NullPointerException exp){
			View_Error error = new View_Error();
		}
		}
	}
	public class CreateAuction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try{
			int selected_gal = view_manager.getView_auctions().getComboBox_Galery().getSelectedIndex();
			int galerie_id = list.getGaleries().get(selected_gal).getGalerie_id();
			ArrayList insertauction = new ArrayList<Auction>();
			if(view_manager.getView_auctions().getTxf_Creator().getText().length() == 0 || view_manager.getView_auctions().getTxf_EvnName().getText().length() == 0)
				throw new NumberFormatException();
			Auction auction = new Auction(1,false,view_manager.getView_auctions().getTxf_Creator().getText(),view_manager.getView_auctions().getTxf_EvnName().getText(),galerie_id);
			insertauction.add(auction);
			insertQuery(con, "INSERT INTO auctions(galery_ref,name,ended,creator) VALUES (?,?,?,?)", "insert_auction", insertauction);
			commitQuery(con);
			}catch(NumberFormatException exp){
				View_Error error = new View_Error();
			}
		}
		
	}
	
	public class EndAuction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try{
			int selected_auct = view_manager.getView_auctions().getTable().getSelectedRow();
			int auction_id = list.getAuctions().get(selected_auct).getAuction_id();
			System.out.println("Auction id je "+auction_id);
			deleteUpdateQuery(con, "UPDATE auctions SET ended = true WHERE auction_id = "+auction_id);
			commitQuery(con);
			}catch(NumberFormatException | NullPointerException exp){
				View_Error error = new View_Error();
			}
		}
		
	}
	public class Calculate implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try{
			int selected_auct = view_manager.getView_auctions().getTable().getSelectedRow();
			int auction_id = list.getAuctions().get(selected_auct).getAuction_id();
			System.out.println("Auction id je "+auction_id);
			LinkedList value=selectQuery(con, "Select sum(artp.value) from auctions auction join transaction trans on trans.auction_ref = auction.auction_id join art_pieces artp on artp.piece_id = trans.art_piece_ref where auction.auction_id = "+auction_id+" group by auction.name", "get_value");
			if(value.size() == 0){
				view_manager.getView_auctions().getTxf_value().setText("0");
			}
			else{
				int valuee = (int) value.get(0);
				view_manager.getView_auctions().getTxf_value().setText(String.valueOf(valuee));
			}
			value=selectQuery(con, "Select count(artp.value) from auctions auction join transaction trans on trans.auction_ref = auction.auction_id join art_pieces artp on artp.piece_id = trans.art_piece_ref where auction.auction_id = "+auction_id+" group by auction.name", "get_amount");
			if(value.size() == 0){
				view_manager.getView_auctions().getTxf_amountpiec().setText("0");
			}
			else{
				int valuee = (int) value.get(0);
				view_manager.getView_auctions().getTxf_amountpiec().setText(String.valueOf(valuee));
			}
			}catch(NumberFormatException | NullPointerException exp){
				View_Error error = new View_Error();
			}

		}
		
	}
	
	PreparedStatement setvalues(PreparedStatement statement, Object insertobj, String type) {
			try {
				if(type == "insert_auction"){
					Auction auction = (Auction) insertobj;
				statement.setInt(1, auction.getGalerie_ref());
				statement.setString(2,auction.getName());
				statement.setBoolean(3, auction.isEnded());
				statement.setString(4, auction.getCreator());
				return statement;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	Object processrow(ResultSet rs, String type) {
		try {
		if(type == "get_auctions"){
			return new Auction(rs.getInt("auction_id"),rs.getBoolean("ended"),rs.getString("creator"),rs.getString("name"),rs.getInt("galery_ref"),rs.getString("galname"));
		}
		
		if(type == "get_value"){
			return rs.getInt("sum");
		}
		if(type == "get_amount"){
			return rs.getInt("count");
		}
		if(type == "get_bought"){
			return new Bought(rs.getString("artpname"),rs.getString("colname"),rs.getFloat("value"));		
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

	public void setButtonListeners() {
		// TODO Auto-generated method stub
		if(view_manager.getView_auctions().getBtnAddAuction().getActionListeners().length == 0){
		view_manager.getView_auctions().addAddAuctionListener(new CreateAuction());
		view_manager.getView_auctions().addENdAuctionListener(new EndAuction());
		view_manager.getView_auctions().addCalculateListener(new Calculate());
		view_manager.getView_auctions().addDetailListener(new ShowTransactions());

		}
	}

	public int getOffset() {
		return offset;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}

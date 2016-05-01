package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import Model.Art_Piece;
import Model.Author;
import Model.Galerie;
import Model.Model_Lists;
import Model.PieceInGalery;
import Model.WorkedOn;
import View.View_Error;
import View.View_Manager;

public class Main_Control {
	private int offset = 0;
	View_Manager view_manager;
	Connection con;
	ArtPiece_Manager artman;
	Auctions_Manager auctman;
	Collectors_Manager colman;
	Model_Lists modellists;
	String filter_string;
	Client client;
	public Main_Control(View_Manager view_manager){
		LinkedList cmb_filler;
		this.view_manager = view_manager;
		this.view_manager.getView_art_pcs();
		modellists = Model_Lists.getInstance();
		setButtonListeners();
		this.artman = new ArtPiece_Manager();
		con = artman.getConnection();
		this.auctman = new Auctions_Manager(con,view_manager);
		this.colman = new Collectors_Manager(con,view_manager);
		getAuthorsGaleries();
		filter_string = new String("");
		Client clientt = artman.elasticConnect();
		modellists.setElasticclient(clientt);
		this.client = clientt;
		new AutoCompleteBox(view_manager.getView_art_pcs().getView_window(),view_manager.getView_art_pcs().getTextField_3());
		//prvy_a_posledny(client, con);
		///////////////uzatvaram connection
		//artman.closeConnection(con);
	}
	
	public void getAuthorsGaleries() {
		LinkedList cmb_filler;
		cmb_filler = artman.selectQuery(con, "SELECT * from authors","get_authors");
		for (int i = 0; i < cmb_filler.size(); i++) {
			Author author = (Author)cmb_filler.get(i);
			modellists.getAuthors().put(i,author);
		}
		cmb_filler = artman.selectQuery(con, "SELECT * from galeries","get_galeries");
		for (int i = 0; i < cmb_filler.size(); i++) {
		Galerie gal = (Galerie)cmb_filler.get(i);
			modellists.getGaleries().put(i,gal);
		}
		
	}

	void setButtonListeners(){
		view_manager.getView_menu().addPiecesListener(new ShowScreen(view_manager.getView_art_pcs().getView_window(),1));
		view_manager.getView_menu().addAuctionsListener(new ShowScreen(view_manager.getView_auctions().getAuctions_frame(),2));
		view_manager.getView_menu().addCollectorsListener(new ShowScreen(view_manager.getView_collectors().getCollectors_frame(),3));
		view_manager.getView_menu().addExitListener(new Exit());
		view_manager.getView_art_pcs().addExitListener(new ExitScreen(view_manager.getView_menu().getView_menu(),view_manager.getView_art_pcs().getView_window()));
		view_manager.getView_art_pcs().addAddListener(new AddPiece());
		view_manager.getView_art_pcs().addAuthorListener(new AddAuthor());
		view_manager.getView_art_pcs().addEditListener(new UpdatePiece());
		view_manager.getView_art_pcs().addRemoveListener(new RemovePiece());
		view_manager.getView_art_pcs().addNextPageListener(new NextPage());
		view_manager.getView_art_pcs().addFilterListener(new Filter());
		view_manager.getView_art_pcs().addSearchListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				offset = 0;
				String temp = view_manager.getView_art_pcs().getTextField_3().getText();
				String art_piece = temp.substring(0, temp.length()-1);
				//System.out.println(art_piece+"g");
				LinkedList cmb_filler;
				String filter = new String("where artp.name = '"+art_piece+"' ");
				cmb_filler = artman.selectQuery(con, "SELECT artp.name, artp.value::numeric::float8, artp.piece_id, artp.style_ref, artp.creation_date, artl.ended, gal.name as galname from art_pieces artp left join (SELECT al.artpiece_ref,max(al.artloc_id)FROM artpiece_location al group by al.artpiece_ref) ppp on ppp.artpiece_ref = artp.piece_id left join artpiece_location artl on artl.artloc_id = ppp.max left join galeries gal on gal.galerie_id = artl.galerie_ref "+filter+"OFFSET "+offset+"Limit 100","jtable");
				printtable(cmb_filler);
				view_manager.getView_art_pcs().getTextField_3().setText("");
			}
		});
		view_manager.getView_auctions().addExitListener(new ExitScreen(view_manager.getView_menu().getView_menu(),view_manager.getView_auctions().getAuctions_frame()));
		view_manager.getView_collectors().addExitListener(new ExitScreen(view_manager.getView_menu().getView_menu(),view_manager.getView_collectors().getCollectors_frame()));
		view_manager.getView_coll_det().addExitListener(new ExitScreen(view_manager.getView_coll_det().getColldet_frame()));
		view_manager.getView_auction_det().addExitListener(new ExitScreen(view_manager.getView_auction_det().getAuctdet_frame()));
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	void populatePieces(){
		LinkedList cmb_filler = new LinkedList();
		for (Author author : modellists.getAuthors().values()) {
			//System.out.println("Pridavam autora" + author.getAuthor_id());
			cmb_filler.add(author.getFirstname()+" "+author.getLastname());
		}
		view_manager.getView_art_pcs().getComboBox_Authors().setModel(new DefaultComboBoxModel(cmb_filler.toArray()));
		cmb_filler = artman.selectQuery(con, "SELECT name from galeries","name");
		view_manager.getView_art_pcs().getComboBox_Galery().setModel(new DefaultComboBoxModel(cmb_filler.toArray()));
		view_manager.getView_art_pcs().getComboBox_cat().setModel(new DefaultComboBoxModel(cmb_filler.toArray()));
		view_manager.getView_art_pcs().getComboBox_cat().addItem("");
		view_manager.getView_art_pcs().getComboBox_cat().setSelectedItem("");
		cmb_filler = artman.selectQuery(con, "SELECT * from art_style","style");
		view_manager.getView_art_pcs().getComboBox_Category().setModel(new DefaultComboBoxModel(cmb_filler.toArray()));
		view_manager.getView_art_pcs().getComboBox_style().setModel(new DefaultComboBoxModel(cmb_filler.toArray()));
		view_manager.getView_art_pcs().getComboBox_style().addItem("");
		view_manager.getView_art_pcs().getComboBox_style().setSelectedItem("");
		cmb_filler = artman.selectQuery(con, "SELECT artp.name, artp.value::numeric::float8, artp.piece_id, artp.style_ref, artp.creation_date, artl.ended, gal.name as galname from art_pieces artp left join (SELECT al.artpiece_ref,max(al.artloc_id)FROM artpiece_location al group by al.artpiece_ref) ppp on ppp.artpiece_ref = artp.piece_id left join artpiece_location artl on artl.artloc_id = ppp.max left join galeries gal on gal.galerie_id = artl.galerie_ref "+filter_string+"OFFSET "+offset+"Limit 100","jtable");
		printtable(cmb_filler);
		modellists.setArtpieces(cmb_filler);
	}
	
	void populateAuctions(){
		auctman.populateAuctions(con);
		auctman.setButtonListeners();
	}
	
	void populateCollectors(){
		colman.populateCollectors();
		colman.setbuttonlisteners();
	}
	void printtable(LinkedList pieces){
		for (int i = view_manager.getView_art_pcs().getModel().getRowCount()-1; i>= 0 ; i--) {
			view_manager.getView_art_pcs().getModel().removeRow(i);
		}
		for (int i = 0; i < pieces.size(); i++) {
			Art_Piece piece = (Art_Piece) pieces.get(i);
			view_manager.getView_art_pcs().getModel().addRow(new Object[]{piece.getName(),piece.getStyle_ref(),piece.getCreationdate(),piece.getGalery(),piece.getValue(),piece.getPiece_id()});
		}
	}
	public class Exit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			artman.closeConnection(con);
			artman.CloseElastic(client);
			System.exit(0);
		}
		
	}
	public class ShowScreen implements ActionListener{
		JFrame screen;
		int screennumber;
		ShowScreen(JFrame screen, int screennumber){
			this.screen = screen;
			this.screennumber = screennumber;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			screen.setVisible(true);
			switch(screennumber){
			case 1:
				populatePieces();
				break;
			case 2:
				populateAuctions();
				break;
			case 3:
				populateCollectors();
				break;
			}
		}
		
	}
	public class ExitScreen implements ActionListener{
		JFrame showscreen;
		JFrame exitscreen;
		ExitScreen(JFrame showscreen,JFrame exitscreen){
			this.showscreen = showscreen;
			this.exitscreen = exitscreen;
		}
		ExitScreen(JFrame exitscreen){
			this.exitscreen = exitscreen;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(showscreen != null)
			showscreen.setVisible(true);
			exitscreen.setVisible(false);
		}
		
	}
	private class AddAuthor implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Author author =(Author) modellists.getAuthors().get(view_manager.getView_art_pcs().getComboBox_Authors().getSelectedIndex());
		//	System.out.println("Zvolil si si tohto:"+author.getFirstname() +author.getLastname()+author.getBorn());
			modellists.getSelectedauthors().add(author.getAuthor_id());
		}
	}
	public class AddPiece implements ActionListener{

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try{
			ArrayList<Author> authors = new ArrayList<Author>();
			Double price = Double.valueOf(view_manager.getView_art_pcs().getTextField_value().getText());
			String name = view_manager.getView_art_pcs().getTextField_name().getText();
			String style_ref = view_manager.getView_art_pcs().getComboBox_Category().getSelectedItem().toString();
			String created = view_manager.getView_art_pcs().getTextField_date().getText();
			int galery_num = view_manager.getView_art_pcs().getComboBox_Galery().getSelectedIndex();
			String galery = view_manager.getView_art_pcs().getComboBox_Galery().getSelectedItem().toString();
			Art_Piece artpiece = new Art_Piece(1,style_ref,name,created,price,galery);
			artpiece.setGalery_id(galery_num);
			//System.out.println("Meno: "+name+" cena: "+price+" style_ref: "+style_ref+" created: "+created+" galerynum: "+galery_num);
			@SuppressWarnings("rawtypes")
			ArrayList listt = new ArrayList<Art_Piece>();
			listt.add(artpiece);
			int piece_id = (int) artman.insertQuery(con, "INSERT Into art_pieces (name, creation_date, value, style_ref) VALUES (?,?,?,?) returning piece_id", "piece",listt);			
		//	System.out.println("Pridanych autorov je :"+modellists.getSelectedauthors().size());
		//	System.out.println("Idecko pridane je "+piece_id);
			ArrayList worklist = new ArrayList<WorkedOn>();
			for (int number_id : modellists.getSelectedauthors()) {
				WorkedOn work = new WorkedOn(number_id,piece_id);
				worklist.add(work);
			//	System.out.println("Autorove idcka su ");
			}
			artman.insertQuery(con, "INSERT INTO workedon (author_ref,artpiece_ref) VALUES (?,?)", "piece_author",worklist );
			ArrayList lister = new ArrayList<PieceInGalery>();
			int galerie_id = modellists.getGaleries().get(view_manager.getView_art_pcs().getComboBox_Galery().getSelectedIndex()).getGalerie_id();
			lister.add(new PieceInGalery(Calendar.getInstance(),piece_id,galerie_id));
			artman.insertQuery(con, "INSERT INTO artpiece_location (galerie_ref,artpiece_ref,created) VALUES (?,?,?)", "piece_galery",lister );
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("name",name);
			json.put("suggest",name);
			IndexResponse response = client.prepareIndex("dbsprojekt", "artpieces",Integer.toString(piece_id))
			        .setSource(json)
			        .get();
			artman.commitQuery(con);
			modellists.getSelectedauthors().removeAll(modellists.getSelectedauthors());
			}catch(NumberFormatException | NullPointerException exp){
				View_Error error = new View_Error();
			}
		}
		
	}
	
	public class RemovePiece implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try{
			int rownumber = view_manager.getView_art_pcs().getTable().getSelectedRow();
			//System.out.println("IDem mazat piece");
			int piece_id = (int) view_manager.getView_art_pcs().getTable().getModel().getValueAt(rownumber, 5);
			artman.deleteUpdateQuery(con, "DELETE from art_pieces where piece_id = "+piece_id);
			DeleteResponse response = client.prepareDelete("dbsprojekt", "artpieces",Integer.toString(piece_id)).get();
			artman.commitQuery(con);
			}catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException exp){
				View_Error error = new View_Error();
			}
		}
	}
	public class UpdatePiece implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try{
			int columnnumber = view_manager.getView_art_pcs().getTable().getSelectedRow();
			String table_galery = (String) view_manager.getView_art_pcs().getTable().getModel().getValueAt(columnnumber, 3);
			String sel_galery = (String)view_manager.getView_art_pcs().getComboBox_Galery().getSelectedItem();
			int piece_id = (int) view_manager.getView_art_pcs().getTable().getModel().getValueAt(columnnumber, 5);
			//System.out.println(sel_galery);
			//System.out.println(table_galery);
			//System.out.println("Selektnute idcko je: "+piece_id);
			if(! sel_galery.equals(table_galery)){
				ArrayList list = new ArrayList<Integer>();
				list.add(10);
				Calendar cal = Calendar.getInstance();
				Timestamp stamp = new Timestamp(cal.getTimeInMillis());
				int locid = 0;
				 locid = (int) artman.insertQuery(con, "UPDATE artpiece_location artl SET ended = '"+stamp+"' WHERE artl.artpiece_ref = "+piece_id+"and artl.ended is null returning artl.artloc_id", "update_query",list);
					ArrayList lister = new ArrayList<PieceInGalery>();
					int galerie_id = modellists.getGaleries().get(view_manager.getView_art_pcs().getComboBox_Galery().getSelectedIndex()).getGalerie_id();
					lister.add(new PieceInGalery(Calendar.getInstance(),piece_id,galerie_id));
					artman.insertQuery(con, "INSERT INTO artpiece_location (galerie_ref,artpiece_ref,created) VALUES (?,?,?)", "piece_galery",lister );
				artman.commitQuery(con);
			}
		}catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException exp){
			View_Error error = new View_Error();
		}
		}
	}
	public class NextPage implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			nextpage();
		}
		public void nextpage(){
			offset+=20;
			LinkedList cmb_filler = new LinkedList();
			cmb_filler = artman.selectQuery(con, "SELECT artp.name, artp.value::numeric::float8, artp.piece_id, artp.style_ref, artp.creation_date, artl.ended, gal.name as galname from art_pieces artp left join (SELECT al.artpiece_ref,max(al.artloc_id)FROM artpiece_location al group by al.artpiece_ref) ppp on ppp.artpiece_ref = artp.piece_id left join artpiece_location artl on artl.artloc_id = ppp.max left join galeries gal on gal.galerie_id = artl.galerie_ref "+filter_string+"OFFSET "+offset+"Limit 20","jtable");
			printtable(cmb_filler);
			if(view_manager.getView_art_pcs().getTable().getModel().getRowCount() == 0){
				offset = 0;
				cmb_filler = artman.selectQuery(con, "SELECT artp.name, artp.value::numeric::float8, artp.piece_id, artp.style_ref, artp.creation_date, artl.ended, gal.name as galname from art_pieces artp left join (SELECT al.artpiece_ref,max(al.artloc_id)FROM artpiece_location al group by al.artpiece_ref) ppp on ppp.artpiece_ref = artp.piece_id left join artpiece_location artl on artl.artloc_id = ppp.max left join galeries gal on gal.galerie_id = artl.galerie_ref "+filter_string+"OFFSET "+offset+"Limit 20","jtable");
				printtable(cmb_filler);
			}
		}
	}
	
	public class RefreshConnection implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				if(con.isClosed()){
					con = artman.getConnection();
					auctman.setCon(con);
					colman.setCon(con);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public class Filter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int value=0;
			StringBuilder builder = new StringBuilder("");
			String category = (String) view_manager.getView_art_pcs().getComboBox_style().getSelectedItem();
			String galery = (String) view_manager.getView_art_pcs().getComboBox_cat().getSelectedItem();
			if(view_manager.getView_art_pcs().getTextField_fvalue().getText().length() == 0)
				value = 0;
			else
				 value = Integer.parseInt(view_manager.getView_art_pcs().getTextField_fvalue().getText());
			if(category != "" || galery != "" || value != 0 ){
				builder.append("WHERE artl.ended is null and ");
				if(category != ""){
					builder.append("artp.style_ref = '"+category+"'");
					if(galery != "" || value != 0)
						builder.append(" and ");
				}
				if(galery != ""){
					builder.append("gal.name = '"+galery+"'");
					if(value != 0)
						builder.append(" and ");
				}
				if(value != 0)
					builder.append("artp.value > "+value);
				//System.out.println("asi to funguje");
				builder.append(" ");
				System.out.println(builder.toString());
			}
				filter_string = builder.toString();
				NextPage next = new NextPage();
				next.nextpage();
		}
	}
	
	public void postgresToElastic(Client client,Connection con){
		LinkedList cmb_filler,druhy_filler;
		cmb_filler = artman.selectQuery(con, "SELECT name from art_pieces","name");
		druhy_filler= artman.selectQuery(con, "SELECT piece_id from art_pieces","piece_id");
		for (int i = 0; i < cmb_filler.size(); i++) {
		//	System.out.println("Meno: "+cmb_filler.get(i));
			Map<String, Object> json = new HashMap<String, Object>();
			String id = druhy_filler.get(i).toString();
			json.put("name",cmb_filler.get(i));
			json.put("suggest",cmb_filler.get(i));
			IndexResponse response = client.prepareIndex("dbsprojekt", "artpieces",id)
			        .setSource(json)
			        .get();
		}
	}
	
}

package main;

import Control.ArtPiece_Manager;
import Control.Database_Manager;
import Control.Main_Control;
import Control.AutoCompleteBox;
import View.View_Collectors_Det;
import View.View_Error;
import View.View_Art_Pieces;
import View.View_Auction_Det;
import View.View_Auctions;
import View.View_Collectors;
import View.View_Manager;
import View.View_Menu;

public class Main {

	public static void main(String[] args) {
		View_Art_Pieces view_art_pieces = null;
		View_Menu view_menu = new View_Menu();
		view_art_pieces = new View_Art_Pieces(view_menu.getView_menu());
		View_Auctions view_auctions = new View_Auctions();
		View_Auction_Det view_au_det = new View_Auction_Det();
		View_Collectors view_collectors = new View_Collectors();
	    View_Collectors_Det view_col_details = new View_Collectors_Det();
		View_Manager view_manager = new View_Manager(view_menu,view_art_pieces,view_auctions,view_au_det,view_col_details,view_collectors);
		// TODO Auto-generated method stub
	//	System.out.println("Hello world");
		Main_Control control = new Main_Control(view_manager);
	}

}

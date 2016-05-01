package View;

public class View_Manager {
	private View_Menu view_menu;
	private View_Art_Pieces view_art_pcs;
	private View_Auctions view_auctions;
	private View_Auction_Det view_auction_det;
	private View_Collectors view_collectors;
	private View_Collectors_Det view_coll_det;
	public View_Manager(View_Menu view_menu, View_Art_Pieces art_pcs,View_Auctions auctions,View_Auction_Det view_auction_det,View_Collectors_Det col_detail, View_Collectors view_collectors){
		this.view_menu = view_menu;
		this.view_art_pcs = art_pcs;
		this.view_auctions = auctions;
		this.view_auction_det = view_auction_det;
		this.view_collectors = view_collectors;
		this.view_coll_det = col_detail;
	}
	public View_Menu getView_menu() {
		return view_menu;
	}
	public void setView_menu(View_Menu view_menu) {
		this.view_menu = view_menu;
	}
	public View_Art_Pieces getView_art_pcs() {
		return view_art_pcs;
	}
	public void setView_art_pcs(View_Art_Pieces view_art_pcs) {
		this.view_art_pcs = view_art_pcs;
	}
	public View_Auctions getView_auctions() {
		return view_auctions;
	}
	public void setView_auctions(View_Auctions view_auctions) {
		this.view_auctions = view_auctions;
	}
	public View_Auction_Det getView_auction_det() {
		return view_auction_det;
	}
	public void setView_auction_det(View_Auction_Det view_auction_det) {
		this.view_auction_det = view_auction_det;
	}
	public View_Collectors getView_collectors() {
		return view_collectors;
	}
	public void setView_collectors(View_Collectors view_collectors) {
		this.view_collectors = view_collectors;
	}
	public View_Collectors_Det getView_coll_det() {
		return view_coll_det;
	}
	public void setView_coll_det(View_Collectors_Det view_coll_det) {
		this.view_coll_det = view_coll_det;
	}
}

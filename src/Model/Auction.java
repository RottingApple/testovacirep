package Model;

public class Auction {
	private int auction_id;
	private boolean ended;
	private String creator;
	private String name;
	private int galerie_ref;
	private String galery;
	public Auction(int auction_id, boolean ended, String creator, String name, int galerie_ref) {
		this.auction_id = auction_id;
		this.ended = ended;
		this.creator = creator;
		this.name = name;
		this.galerie_ref = galerie_ref;
	}
	
	
	public Auction(int auction_id, boolean ended, String creator, String name, int galerie_ref, String galery) {
		this.auction_id = auction_id;
		this.ended = ended;
		this.creator = creator;
		this.name = name;
		this.galerie_ref = galerie_ref;
		this.galery = galery;
	}
	public Auction(int auction_id,String name){
		this.auction_id = auction_id;
		this.name = name;
	}

	public int getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(int auction_id) {
		this.auction_id = auction_id;
	}
	public boolean isEnded() {
		return ended;
	}
	public void setEnded(boolean ended) {
		this.ended = ended;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGalerie_ref() {
		return galerie_ref;
	}
	public void setGalerie_ref(int galerie_ref) {
		this.galerie_ref = galerie_ref;
	}
	public String getGalery() {
		return galery;
	}
	public void setGalery(String galery) {
		this.galery = galery;
	}
	
}

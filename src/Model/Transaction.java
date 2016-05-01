package Model;

public class Transaction {
	private int auction_ref;
	private int artpiece_ref;
	private int collector_ref;
	public Transaction(int auction_ref, int artpiece_ref, int collector_ref) {
		this.auction_ref = auction_ref;
		this.artpiece_ref = artpiece_ref;
		this.collector_ref = collector_ref;
	}
	public int getAuction_ref() {
		return auction_ref;
	}
	public void setAuction_ref(int auction_ref) {
		this.auction_ref = auction_ref;
	}
	public int getArtpiece_ref() {
		return artpiece_ref;
	}
	public void setArtpiece_ref(int artpiece_ref) {
		this.artpiece_ref = artpiece_ref;
	}
	public int getCollector_ref() {
		return collector_ref;
	}
	public void setCollector_ref(int collector_ref) {
		this.collector_ref = collector_ref;
	}
	
}

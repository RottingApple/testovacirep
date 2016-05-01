package Model;

public class WorkedOn {
	private int author_ref;
	private int piece_ref;
	public int getAuthor_ref() {
		return author_ref;
	}
	public void setAuthor_ref(int author_ref) {
		this.author_ref = author_ref;
	}
	public int getPiece_ref() {
		return piece_ref;
	}
	public void setPiece_ref(int piece_ref) {
		this.piece_ref = piece_ref;
	}
	public WorkedOn(int author_ref, int piece_ref) {
		this.author_ref = author_ref;
		this.piece_ref = piece_ref;
	}
	
}

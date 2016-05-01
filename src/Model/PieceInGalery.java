package Model;

import java.util.Calendar;

public class PieceInGalery {
	private Calendar putdate;
	private Calendar pulldate;
	private int piece_ref;
	private int galery_ref;
	
	public PieceInGalery(Calendar putdate, int piece_ref, int galery_ref) {
		this.putdate = putdate;
		this.piece_ref = piece_ref;
		this.galery_ref = galery_ref;
	}

	public Calendar getPutdate() {
		return putdate;
	}

	public void setPutdate(Calendar putdate) {
		this.putdate = putdate;
	}

	public Calendar getPulldate() {
		return pulldate;
	}

	public void setPulldate(Calendar pulldate) {
		this.pulldate = pulldate;
	}

	public int getPiece_ref() {
		return piece_ref;
	}
	public void setPiece_ref(int piece_ref) {
		this.piece_ref = piece_ref;
	}
	public int getGalery_ref() {
		return galery_ref;
	}
	public void setGalery_ref(int galery_ref) {
		this.galery_ref = galery_ref;
	}
	
}

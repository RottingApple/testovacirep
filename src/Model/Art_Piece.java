package Model;

import java.util.ArrayList;

public class Art_Piece {
	private int piece_id;
	private String style_ref;
	private String name;
	private String creationdate;
	private double value;
	private String galery;
	private int galery_id;
	public Art_Piece(int piece_id, String style_ref, String name, String creationdate, double value,String galery) {
		this.piece_id = piece_id;
		this.style_ref = style_ref;
		this.name = name;
		this.galery = galery;
		this.creationdate = creationdate;
		this.value = value;
	}
	public int getPiece_id() {
		return piece_id;
	}
	public void setPiece_id(int piece_id) {
		this.piece_id = piece_id;
	}
	public String getStyle_ref() {
		return style_ref;
	}
	public void setStyle_ref(String style_ref) {
		this.style_ref = style_ref;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getGalery() {
		return galery;
	}
	public void setGalery(String galery) {
		this.galery = galery;
	}
	public int getGalery_id() {
		return galery_id;
	}
	public void setGalery_id(int galery_id) {
		this.galery_id = galery_id;
	}	
}

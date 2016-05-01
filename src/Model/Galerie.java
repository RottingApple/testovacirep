package Model;

public class Galerie {
	private int galerie_id;
	private String name;
	private String place;
	private String owner;
	
	public Galerie(int galerie_id, String name, String place, String owner) {
		this.galerie_id = galerie_id;
		this.name = name;
		this.place = place;
		this.owner = owner;
	}
	public int getGalerie_id() {
		return galerie_id;
	}
	public void setGalerie_id(int galerie_id) {
		this.galerie_id = galerie_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
}

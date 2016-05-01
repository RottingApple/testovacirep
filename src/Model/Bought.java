package Model;

public class Bought {
	private String artpiece;
	private String collector;
	private float value;
	public Bought(String artpiece, String collector, float value) {
		this.artpiece = artpiece;
		this.collector = collector;
		this.value = value;
	}
	public String getArtpiece() {
		return artpiece;
	}
	public void setArtpiece(String artpiece) {
		this.artpiece = artpiece;
	}
	public String getCollector() {
		return collector;
	}
	public void setCollector(String collector) {
		this.collector = collector;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
}

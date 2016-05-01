package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.elasticsearch.client.Client;

public class Model_Lists {
	private HashMap<Integer,Author>authors;
	private ArrayList<Integer> selectedauthors;
	private HashMap<Integer,Galerie>galeries;
	private LinkedList<Art_Piece>artpieces;
	private HashMap<Integer,Auction>auctions;
	private HashMap<Integer,Collector>collectors;
	private int selectedcoll_id;
	private Client elasticclient;
	static Model_Lists instance = null;
	private Model_Lists(){
	}
	public static Model_Lists getInstance(){
		if(instance == null){
			instance = new Model_Lists();
			instance.authors = new HashMap<Integer,Author>();
			instance.galeries = new HashMap<Integer,Galerie>();
			instance.selectedauthors = new ArrayList<Integer>();
			instance.auctions = new HashMap<Integer,Auction>();
		}
		return instance;
	}
	public HashMap<Integer,Author> getAuthors() {
		return authors;
	}
	public HashMap<Integer,Galerie> getGaleries() {
		return galeries;
	}
	public void setGaleries(HashMap<Integer,Galerie> galeries) {
		this.galeries = galeries;
	}
	public ArrayList<Integer> getSelectedauthors() {
		return selectedauthors;
	}
	public void setSelectedauthors(ArrayList<Integer> selectedauthors) {
		this.selectedauthors = selectedauthors;
	}
	public LinkedList<Art_Piece> getArtpieces() {
		return artpieces;
	}
	public void setArtpieces(LinkedList<Art_Piece> artpieces) {
		this.artpieces = artpieces;
	}
	public HashMap<Integer,Auction> getAuctions() {
		return auctions;
	}
	public void setAuctions(HashMap<Integer,Auction> auctions) {
		this.auctions = auctions;
	}
	public HashMap<Integer,Collector> getCollectors() {
		return collectors;
	}
	public void setCollectors(HashMap<Integer,Collector> collectors) {
		this.collectors = collectors;
	}
	public int getSelectedcoll_id() {
		return selectedcoll_id;
	}
	public void setSelectedcoll_id(int selectedcoll_id) {
		this.selectedcoll_id = selectedcoll_id;
	}
	public Client getElasticclient() {
		return elasticclient;
	}
	public void setElasticclient(Client elasticclient) {
		this.elasticclient = elasticclient;
	}
}

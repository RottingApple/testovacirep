package Control;

import java.awt.List;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

import javax.swing.JTable;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion.Entry;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion.Entry.Option;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;

public abstract class Database_Manager {
	
	public Connection getConnection(){
		 Connection con = null;
		 Properties con_props = new Properties();
		 con_props.put("user", "postgres");
		 con_props.put("password", "petis");
		 String con_string = "jdbc:postgresql://localhost:5432/DBS_Projekt";
		 try {
			con = DriverManager.getConnection(con_string,con_props);
		//	System.out.println("Hura podarilo sa pripojit");
			 return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
	
	public void closeConnection(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void commitQuery(Connection con){
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			rollbackQuery(con);
			e.printStackTrace();
		}
	}
	public void rollbackQuery(Connection con){
		try {
			System.err.print("The Error has accured the transaction is being rolled back");
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LinkedList  selectQuery(Connection con,String stringquery,String type){
		ResultSet rs=null;
		//System.out.println(stringquery);
		LinkedList result = new LinkedList();
		Statement statement=null;
		try {
				statement = con.createStatement();
				rs = statement.executeQuery(stringquery);
			
				while(rs.next()){
					result.add(processrow(rs,type));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		    try { 
		    	statement.close();
				rs.close();
		    } catch (Exception e) { /* ignored */ }

		}
		return result;
		
	}
	public Object insertQuery(Connection con,String stringquery,String type,ArrayList<Object> insertlist){
		PreparedStatement statement = null;
		try {
			//System.out.println(stringquery);
			con.setAutoCommit(false);
			statement = (PreparedStatement) con.prepareStatement(stringquery);
			for (int i = 0; i < insertlist.size(); i++) {
				//System.out.println("som v cykle");
				statement = setvalues(statement,insertlist.get(i),type);
				if(statement == null){
					System.out.println("Nieco sa zle stalo :(");
					throw new SQLException("Nieco sa zle stalo :(");
				}
				else{
					Object result;
					statement.execute();
					if(stringquery.contains("returning")){
					ResultSet last_update = statement.getResultSet();
					if(last_update.next()){
						result = last_update.getObject(1);
						statement.close();
						last_update.close();
						return result;
					}
					else
						return 0;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (con != null) {
				e.printStackTrace();
	                rollbackQuery(con);
			}
		}finally {
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}
		return null;
	}
	
	public void deleteUpdateQuery(Connection con,String stringquery){
		Statement statement = null;
		try {
			con.setAutoCommit(false);
			statement = con.createStatement();
			statement.executeUpdate(stringquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (con != null) {
				e.printStackTrace();
	                rollbackQuery(con);
			}
		}finally {
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}
	}
	
	public Client elasticConnect(){
		try {
			Settings settings = Settings.settingsBuilder()
			        .put("cluster.name", "elasticsearch").build();
			Client client = TransportClient.builder().settings(settings).build()
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		return client;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}
	
	public void CloseElastic(Client client){
		client.close();
	}
	
	public static ArrayList<String> Elasticsearch(Client client,String typedWord){
		CompletionSuggestionBuilder compBuilder = new CompletionSuggestionBuilder("complete"); 
		compBuilder.text(typedWord);
		compBuilder.field("suggest");
		ArrayList<String> results = null;
		SearchResponse response = client.prepareSearch("dbsprojekt").setQuery(QueryBuilders.matchAllQuery())
				.addSuggestion(compBuilder)
				.setTypes("artpieces").execute().actionGet();
		//System.out.println(response.get);
		/*for(SearchHit hit: response.getHits().getHits()){
			//String name = hit.field("first_name").getValue();
			//System.out.println("Meno je : "+name);
			//System.out.println("Dostal som odpoved");
			System.out.println("bubu");
			
		}*/
		  CompletionSuggestion compSuggestion = response.getSuggest().getSuggestion("complete");
		  
		  java.util.List<CompletionSuggestion.Entry> entryList = compSuggestion.getEntries();
	        if(entryList != null) {
	            CompletionSuggestion.Entry entry = entryList.get(0);
	            java.util.List<CompletionSuggestion.Entry.Option> options =entry.getOptions();
	            if(options != null)  {
	            	results = new ArrayList<String>();
	            	for (CompletionSuggestion.Entry.Option option : options)
	            		 results.add(option.getText().string()); 
	            }
	        } 
	        return results;
	}
	
	abstract PreparedStatement setvalues(PreparedStatement statement,Object insertobj,String type);
	abstract Object processrow(ResultSet rs,String type);
}
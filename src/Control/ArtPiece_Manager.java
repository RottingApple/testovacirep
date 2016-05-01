package Control;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.elasticsearch.client.Client;

import Model.Art_Piece;
import Model.Author;
import Model.Galerie;
import Model.PieceInGalery;
import Model.WorkedOn;

public class ArtPiece_Manager extends Database_Manager{
	
	@Override
	Object processrow(ResultSet rs,String type) {
		// TODO Auto-generated method stub
			try {
				if(type == "get_authors"){
					return new Author(rs.getInt("author_id"),rs.getString("firstname"),rs.getString("secondname"),rs.getString("nationality"),rs.getString("born") );
				}
					else
				if(type == "jtable"){
				//	System.out.println(rs.getInt("piece_id")+rs.getString("name"));
					if(rs.getString("ended") == null){
						return new Art_Piece(rs.getInt("piece_id"),rs.getString("style_ref"),rs.getString("name"),rs.getString("creation_date"),rs.getFloat("value"),rs.getString("galname"));
					}
					else{
						return new Art_Piece(rs.getInt("piece_id"),rs.getString("style_ref"),rs.getString("name"),rs.getString("creation_date"),rs.getFloat("value"),"Sold");

					}
				}
				else
				if(type == "get_galeries"){
						return new Galerie(rs.getInt("galerie_id"),rs.getString("name"),rs.getString("place"),rs.getString("owner") );
				}
				else{
					return new String(rs.getString(type));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return rs;
	}

	@Override
	PreparedStatement setvalues(PreparedStatement statement,Object insertobj, String type) {
		int[] dotsplacement;
		try {
			if(type == "piece"){
				Art_Piece artpiece = (Art_Piece) insertobj;
				String date = artpiece.getCreationdate();
				statement.setString(1, artpiece.getName());
				Calendar cal = Calendar.getInstance();
				dotsplacement = getdots(date);
				System.out.println("Den: "+date.substring(0, dotsplacement[0])+"Mesiac "+date.substring(dotsplacement[0]+1,dotsplacement[1])+"Rok "+date.substring(dotsplacement[1]+1,date.length()));
				cal.set(Integer.parseInt(date.substring(dotsplacement[1]+1,date.length())), Integer.parseInt(date.substring(dotsplacement[0]+1,dotsplacement[1])), Integer.parseInt(date.substring(0, dotsplacement[0])));
				Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
				statement.setTimestamp(2,timestamp);
				statement.setFloat(3, (float)artpiece.getValue());
				statement.setString(4, artpiece.getStyle_ref());
				return statement;
			}
			if(type =="piece_author"){
				WorkedOn worked = (WorkedOn) insertobj;
				statement.setInt(1,worked.getAuthor_ref());
				statement.setInt(2,worked.getPiece_ref());
				return statement;
			}
			if(type =="piece_galery"){
				PieceInGalery piece = (PieceInGalery) insertobj;
				statement.setInt(1,piece.getGalery_ref());
				statement.setInt(2,piece.getPiece_ref());
				Timestamp timestamp = new Timestamp(piece.getPutdate().getTimeInMillis());
				statement.setTimestamp(3, timestamp);
				return statement;
			}
			if(type == "update_query"){
				return statement;
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	public static int[] getdots(String string){
		int[] array = new int[2];
			array[0]=string.indexOf('.');
			array[1]=string.indexOf('.',array[0]+1);
			//System.out.println("Bodky su na "+array[0]+" "+array[1]);
		return array;
	}
	
}

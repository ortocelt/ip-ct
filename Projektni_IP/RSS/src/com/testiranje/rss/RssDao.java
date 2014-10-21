package com.testiranje.rss;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;



public class RssDao {
	private static String najavljeniTestovi = "select naziv,rok_za_prijavu,datum_polaganja from test where najavljen = '1' and rok_za_prijavu>now() ";
	
	public static ArrayList<RSSEntry> najavljeniTestovi(){
		ArrayList<RSSEntry> rssList = new ArrayList<RSSEntry>();
		String bla =""; 
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(najavljeniTestovi);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()){
				RSSEntry rss = new RSSEntry();
				bla = rs.getString("naziv")
				+ " Rok za prijavu: " + rs.getDate("rok_za_prijavu").toString() +
				 " Datum polaganja: " + rs.getDate("datum_polaganja").toString();
				rss.setTitle(bla);
				rss.setCopyright("IP projekat 2014 Milos Jankovic");
				rss.setDescription("Najavljeno testiranje");
				rss.setGuid("http://localhost:8085/CentarZaTestiranjev1.0/test.xhtml");
				rss.setLanguage("Srpski");
				rss.setLink("http://localhost:8085/CentarZaTestiranjev1.0/test.xhtml");
				rss.setPubDate(RSSFeed.formatDate(Calendar.getInstance()));
				
				rssList.add(rss);				
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rssList;
		
	}

}

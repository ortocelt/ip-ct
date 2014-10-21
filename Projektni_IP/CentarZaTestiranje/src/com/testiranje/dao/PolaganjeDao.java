package com.testiranje.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PolaganjeDao {
	
	private static String insertPolaganje = "insert into polaganje (user_id,test_id,uplaceno,sifra) values(?,?,?,?) ";
	private static String provjeraPolaganja = "select * from polaganje where user_id=? and test_id=? and polagano = '0'";
	private static String brojBodova = "select sum(bodovi) as poeni from odgovor join uneseni_odgovor on odgovor.id = odgovor_id and polaganje_id = ?";

	public PolaganjeDao() {
		// TODO Auto-generated constructor stub
	}

	public static int postavljanjeSifre(String sifraTesta,int testId, int userId) {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			
			PreparedStatement st1 = conn.prepareStatement(provjeraPolaganja);
			st1.setInt(1, userId);
			st1.setInt(2, testId);
			ResultSet rs = st1.executeQuery();
			
			if(!rs.next()){
			
			PreparedStatement st = conn.prepareStatement(insertPolaganje);
			int uplaceno = 1;
			st.setInt(1,userId);
			st.setInt(2, testId);
			st.setInt(3, uplaceno);
			st.setString(4, sifraTesta);			
			st.executeUpdate();
			st.close();
			return 1;
			}
			st1.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return 0;
	}
	
	public static int brojBodova(int polaganjeId){
		
		int bodovi = -696969;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(brojBodova);
			st.setInt(1, polaganjeId);
			
			ResultSet rs = st.executeQuery();			
			if (rs.next()){
				bodovi = rs.getInt("poeni");			
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
		
		return bodovi;
		
	}

}

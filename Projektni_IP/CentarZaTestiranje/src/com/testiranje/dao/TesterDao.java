package com.testiranje.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.testiranje.bean.TesterBean;
import com.testiranje.bean.TesterBean.KandidatiNaPolaganju;
import com.testiranje.bean.TesterBean.PolaganiTestovi;
import com.testiranje.dto.Polaganje;
import com.testiranje.dto.Test;

public class TesterDao {
	private static String zavrsenaPolaganja = "SELECT a.test_id,b.naziv,b.datum_polaganja FROM polaganje as a join test as b on a.test_id = b.id group by b.id";
	private static String pregledPoPolaganju = "SELECT u.id, u.ime, u.prezime, t.naziv, t.datum_polaganja, t.potrebno_bodova,p.ostvareno_bodova,p.test_id,p.id as polaganje_id FROM `testiranje_1.1`.polaganje as p LEFT JOIN `testiranje_1.1`.test as t on p.test_id=t.id left JOIN `testiranje_1.1`.user as u ON p.user_id = u.id where p.test_id=?";

	public TesterDao() {
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<PolaganiTestovi> zavrsenaPolaganja() {
		ArrayList<PolaganiTestovi> polagano = new ArrayList<PolaganiTestovi>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(zavrsenaPolaganja);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				TesterBean tb = new TesterBean();
				//da bi instancirali podklasu neke klase
				//potrebno je prvo instancirati tu klasu
				//vvv  zatim instancu podklase vvv
				PolaganiTestovi pt = tb.new PolaganiTestovi();
				
				pt.setTest_id(rs.getInt("test_id"));
				pt.setNaziv(rs.getString("naziv"));
				pt.setDatum_polaganja(rs.getTimestamp("datum_polaganja"));
				
				polagano.add(pt);
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
		
		return polagano;
		
	}

	public static ArrayList<KandidatiNaPolaganju> pregledPoPolaganju(PolaganiTestovi p) {
		ArrayList<KandidatiNaPolaganju> kandidati = new ArrayList<KandidatiNaPolaganju>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(pregledPoPolaganju);
			st.setInt(1, p.getTest_id());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				TesterBean tb = new TesterBean();
				//da bi instancirali podklasu neke klase
				//potrebno je prvo instancirati tu klasu
				//vvv  zatim instancu podklase vvv
				KandidatiNaPolaganju kp = tb.new KandidatiNaPolaganju();
				kp.setTest_id(rs.getInt("test_id"));
				kp.setId(rs.getInt("id"));
				kp.setIme(rs.getString("ime"));
				kp.setPrezime(rs.getString("prezime"));
				kp.setNaziv(rs.getString("naziv"));
				kp.setDatum_polaganja(rs.getTimestamp("datum_polaganja"));
				kp.setPotrebno_bodova(rs.getInt("potrebno_bodova"));
				kp.setBodovi(rs.getInt("ostvareno_bodova"));		
				kp.setPolaganje_id(rs.getInt("polaganje_id"));
				kandidati.add(kp);
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
		return kandidati;		
	}	

}

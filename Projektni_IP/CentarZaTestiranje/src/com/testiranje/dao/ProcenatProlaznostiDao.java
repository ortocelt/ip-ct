package com.testiranje.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.testiranje.dto.BrojIzlazaka;
import com.testiranje.dto.ProcenatProlaznosti;

public class ProcenatProlaznostiDao {
	
	private static String brojPolozenih = "select count(*) as broj_polozenih from polaganje left join test on polaganje.test_id = test.id where polaganje.ostvareno_bodova >= test.potrebno_bodova  and test.id = ?";
	private static String brojIzlazaka = "select test.datum_polaganja, polaganje.id as polaganje_id, test.naziv, test.id as test_id,count(polaganje.user_id) as broj FROM test	LEFT outer JOIN polaganje ON test.id = polaganje.test_id group by test.id";

	public ProcenatProlaznostiDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static int brojPolozenih(int testId){
		int ret = 0;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(brojPolozenih);
			st.setInt(1, testId);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				ret = rs.getInt("broj_polozenih");
				return ret;
				}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
		
	}

	public static ArrayList<ProcenatProlaznosti> procenatProlaznosti() {
		ArrayList<ProcenatProlaznosti> procenatProlaznosti = new ArrayList<ProcenatProlaznosti>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(brojIzlazaka);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				ProcenatProlaznosti pp = new ProcenatProlaznosti();
				pp.setDatum_polaganja(rs.getTimestamp("datum_polaganja"));
				pp.setNaziv_testa(rs.getString("naziv"));
				double brojIzlazaka = rs.getInt("broj");
				double brojPolozenih = brojPolozenih(rs.getInt("test_id"));
				//double procenat = brojPolozenih/brojIzlazaka * 100;
				if (brojIzlazaka != 0.0){
					double proc = brojPolozenih/brojIzlazaka;
					NumberFormat nf = NumberFormat.getPercentInstance();
					nf.setMinimumFractionDigits(1);					
					pp.setProcenat(nf.format(proc));
				}
				
				procenatProlaznosti.add(pp);
				}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return procenatProlaznosti;
	}

}

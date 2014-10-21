package com.testiranje.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.testiranje.dto.BrojIzlazaka;
import com.testiranje.dto.Test;

public class BrojIzlazakaDao {
	private static String brojIzlazaka = "select test.naziv, test.id,count(polaganje.user_id) as broj FROM test	LEFT outer JOIN `testiranje_1.1`.polaganje ON test.id = polaganje.test_id group by test.id";

	public BrojIzlazakaDao() {
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<BrojIzlazaka> napuni() {
		// TODO Auto-generated method stub
		ArrayList<BrojIzlazaka> najavljenaPolaganja = new ArrayList<BrojIzlazaka>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(brojIzlazaka);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				BrojIzlazaka bi = new  BrojIzlazaka();
				bi.setId(rs.getInt("id"));
				bi.setNaziv(rs.getString("naziv"));
				bi.setBroj_izlazaka(rs.getInt("broj"));
				najavljenaPolaganja.add(bi);
				}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return najavljenaPolaganja;
	}
	}

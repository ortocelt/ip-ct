package com.testiranje.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.testiranje.dto.Odgovor;
import com.testiranje.dto.Pitanje;
import com.testiranje.dto.Test;
import com.testiranje.dto.User;

public class TestDao {
	
	public static String najavljeniTestovi = "select * from test where najavljen='1' and rok_za_prijavu>now()";
	private static String insertNoviTest = "insert into test (naziv,datum_polaganja,rok_za_prijavu,najavljen,max_broj_kandidata,potrebno_bodova,sifra_testa) value (?,?,?,?,?,?,?)";
	private static String pitanjaPoTestu = "select * from pitanje where test_id = ?";
	private static String odgovoriPoPitanju = "select * from odgovor where pitanje_id = ?";
	private static String insertNovoPitanje = "insert into pitanje (test_id,tekst) value (?,?)";
	private static String insertNoviOdgovor = "insert into odgovor (pitanje_id,tekst,bodovi) value (?,?,?)";
	private static String brisanjeOdgovoraPriKreiranju = "delete from odgovor where id = ?";
	private static String brisanjePitanjaPriKreiranjuOdgovori = "delete odgovor from odgovor where pitanje_id = ?";
	private static String brisanjePitanjaPriKreiranju = "delete pitanje from pitanje where id = ?";
	private static String provjeraOdgovoraKorisnikovogTesta = "select * from uneseni_odgovor where polaganje_id = ? and odgovor_id = ?";
	private static String labelaTacnostiSamoString = "select tacan from odgovor where id = ?";
	private static String danasnjiTestovi = "SELECT test.id, test.naziv,  test.datum_polaganja , test.rok_za_prijavu, test.potrebno_bodova FROM test left  join polaganje on test.id = polaganje.test_id where polaganje.user_id = ? and polaganje.polagano = '0' and polaganje.uplaceno = '1' and test.datum_polaganja between date_sub(now(),interval 10 day) and now()";
	private static String updatePolaganje = "update polaganje set polaganje.polagano = '1' where polaganje.sifra= ?";
	private static String uneseniOdgovor = "insert into uneseni_odgovor (polaganje_id,odgovor_id) value (?,?)";
	private static String ponistenOdgovor = "delete from uneseni_odgovor where polaganje_id = ? and odgovor_id = ?";
	private static String selectIdUpPol = "select id from polaganje where sifra = ?";
	private static String potrebnoBodova = "select potrebno_bodova from test where id = ?";
	//public static String najTes = "select * from test";
	public TestDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static int potrebnoBodova(int testId){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(potrebnoBodova);
			st.setInt(1, testId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				return rs.getInt("potrebno_bodova");
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
		return 0;
		
	}
	
	public static int idPolaganjaZaBrojBodova(String sifra){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(selectIdUpPol);
			
			st.setString(1, sifra);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				return rs.getInt("id");
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
		return 0;
	}
	
	public static int setPolaganje(String sifra){
		int q=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(updatePolaganje);
			
			st.setString(1, sifra);		
			q = st.executeUpdate();
			if(q!=0){
				PreparedStatement st1 = conn.prepareStatement(selectIdUpPol);
				st1.setString(1, sifra);				
				ResultSet rs = st1.executeQuery();
				if(rs.next()){
				q = rs.getInt("id");				
				}
				rs.close();
				st1.close();
			}
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return q;
		
	
	
		
	}
	
	public static ArrayList<Test> danasnjiTestovi(int userId){
		ArrayList<Test> danasnjiTestovi = new ArrayList<Test>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(TestDao.danasnjiTestovi);
			st.setInt(1, userId);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				Test test = new Test();
				test.setId(rs.getInt("id"));
				test.setNaziv(rs.getString("naziv"));
				test.setRok_za_prijavu(rs.getTimestamp("rok_za_prijavu"));
				test.setDatum_polaganja(rs.getTimestamp("datum_polaganja"));
				test.setPotrebno_bodova(rs.getInt("potrebno_bodova"));
				danasnjiTestovi.add(test);
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
		
		return danasnjiTestovi;
		
	}
	
	public static ArrayList<Test> najavljenaPolaganja(){
		ArrayList<Test> najavljenaPolaganja = new ArrayList<Test>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(najavljeniTestovi);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				Test test = new Test();
				test.setId(rs.getInt("id"));
				test.setNaziv(rs.getString("naziv"));
				test.setRok_za_prijavu(rs.getTimestamp("rok_za_prijavu"));
				test.setDatum_polaganja(rs.getTimestamp("datum_polaganja"));
				najavljenaPolaganja.add(test);
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
		
		return najavljenaPolaganja;
	}

	public static int noviTest(Test t) {
		Integer i=-1;
		ResultSet zadnjiKey;
		try {
									
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
					PreparedStatement st = conn.prepareStatement(insertNoviTest,Statement.RETURN_GENERATED_KEYS);					
															
					
					st.setString(1, t.getNaziv());
					st.setTimestamp(2, t.getDatum_polaganja());
					st.setTimestamp(3,t.getRok_za_prijavu());
					if (t.getNajavljen() == true){
					st.setInt(4, 1);
					}
					else{
					st.setBoolean(4,t.getNajavljen());
					}
					//st.setTime(5,t.getTrajanje());
					st.setInt(5, t.getMax_broj_kandidata());
					st.setInt(6,t.getPotrebno_bodova());
					st.setString(7, t.getSifra_testa());
										
					st.executeUpdate();
					zadnjiKey = st.getGeneratedKeys();
					zadnjiKey.next();
					i = zadnjiKey.getInt(1);
					st.close();
					conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
		return i;
	}

	public static int novoPitanje(Pitanje p) {
		Integer i = -1;
		ResultSet zadnjiKey;
		
		try {
													
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
					PreparedStatement st = conn.prepareStatement(insertNovoPitanje,Statement.RETURN_GENERATED_KEYS);					
															
					st.setInt(1, p.getTest_id());
					st.setString(2, p.getTekst());
										
					st.executeUpdate();
					zadnjiKey = st.getGeneratedKeys();
					zadnjiKey.next();
					i = zadnjiKey.getInt(1);
					st.close();
					conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return i;
						

		
	}

public static ArrayList<Pitanje> pitanjaPoTestu(int test){
		
		ArrayList<Pitanje> pitanja = new ArrayList<Pitanje>();
		
		try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
					PreparedStatement st= conn.prepareStatement(pitanjaPoTestu);
								
					st.setInt(1, test);
					ResultSet rs= st.executeQuery();
					
					while(rs.next()){
						
					Pitanje pitanje = new Pitanje();	
					pitanje.setId(rs.getInt("id"));
					pitanje.setTest_id(rs.getInt("test_id"));
					pitanje.setTekst(rs.getString("tekst"));
					pitanja.add(pitanje);
									
					}
					
					rs.close();
					st.close();
					conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return pitanja;
			}

public static void noviOdgovor(Odgovor o) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
		PreparedStatement st = conn.prepareStatement(insertNoviOdgovor);
		
		st.setInt(1, o.getPitanje_id());
		st.setString(2, o.getTekst());
		st.setInt(3, o.getBodovi());
		st.executeUpdate();
		st.close();
		conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static  ArrayList<Odgovor> odgovoriPoPitanju(int pitanjeId) {
	ArrayList<Odgovor> odgovori = new ArrayList<Odgovor>();
	
	try {
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
				PreparedStatement st= conn.prepareStatement(odgovoriPoPitanju);
							
				st.setInt(1, pitanjeId);
				ResultSet rs= st.executeQuery();
				
				while(rs.next()){
					
				Odgovor odgovor = new Odgovor();	
				odgovor.setId(rs.getInt("id"));
				odgovor.setPitanje_id(rs.getInt("pitanje_id"));
				odgovor.setTekst(rs.getString("tekst"));
				odgovor.setBodovi(rs.getInt("bodovi"));
				odgovor.setTacan(rs.getInt("tacan"));
				
				odgovori.add(odgovor);
								
				}
				
				rs.close();
				st.close();
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return odgovori;
}
public static void brisanjeOdgovoraPriKreiranju(Odgovor o) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
		PreparedStatement st = conn.prepareStatement(brisanjeOdgovoraPriKreiranju);
		
		st.setInt(1, o.getId());
		st.executeUpdate();
		st.close();
		conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void brisanjePitanjaPriKreiranju(Pitanje p) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
		PreparedStatement st = conn.prepareStatement(brisanjePitanjaPriKreiranjuOdgovori );
		PreparedStatement st1 = conn.prepareStatement(brisanjePitanjaPriKreiranju  );
		
		
		st.setInt(1, p.getId());
		st.executeUpdate();
		st.close();
		st1.setInt(1, p.getId());
		st1.executeUpdate();
		st1.close();
		conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static boolean provjeraOdgovoraKorisnikovogTesta(int idOdgovor,
		int polaganjeIzabranogTestaZaPregled) {
	try {
		
		//connection driver
		Class.forName("com.mysql.jdbc.Driver");
		//konekcija ka bazi
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
		
		PreparedStatement st= conn.prepareStatement(provjeraOdgovoraKorisnikovogTesta);
		st.setInt(1, polaganjeIzabranogTestaZaPregled);
		st.setInt(2, idOdgovor);
		
		ResultSet rs= st.executeQuery();
		
		if(rs.next()){
			return true;		
								
		}
			
		rs.close();
		st.close();
		conn.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
			return false;
	// TODO Auto-generated method stub
	
}

public static String labelaTacnosti(int idOdgovor,
		int polaganjeIzabranogTestaZaPregled) {
	// TODO Auto-generated method stub
	return null;
}

public static String labelaTacnostiSamoString(int idOdgovor) {
try {
	
		int tn = 0;
		//connection driver
		Class.forName("com.mysql.jdbc.Driver");
		//konekcija ka bazi
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
		
		PreparedStatement st= conn.prepareStatement(labelaTacnostiSamoString);
		st.setInt(1, idOdgovor);				
		ResultSet rs= st.executeQuery();
		
		if(rs.next()){
			 tn = rs.getInt("tacan");
			 if (tn == 1)
			 {
				 return "Odgovor tacan, treba biti obiljezen";
			 }
			 else
			 {
				 return "Odgovor netacan, ne treba biti obiljezen";
			 }
								
		}
			
		rs.close();
		st.close();
		conn.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
			return null;
}

public static void uneseniOdgovor(int polaganje, int odgovor) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
		PreparedStatement st = conn.prepareStatement(uneseniOdgovor);
		
		st.setInt(1,polaganje);
		st.setInt(2, odgovor);
		st.executeUpdate();
		st.close();
		conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void ponistenOdgovor(int polaganje, int odgovor) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
		PreparedStatement st = conn.prepareStatement(ponistenOdgovor);
		
		st.setInt(1, polaganje);
		st.setInt(2, odgovor);
		st.executeUpdate();
		st.close();
		conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

}

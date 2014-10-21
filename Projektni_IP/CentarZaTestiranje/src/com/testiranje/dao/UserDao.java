package com.testiranje.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.testiranje.dto.Test;
import com.testiranje.dto.User;

public class UserDao {
	
	private static String login = "select * from user where username=? and password=?";
	public static String registerUser = "insert into user (privilegija,ime,prezime,username,password) values(?,?,?,?,?)";
	public static String pregledUsera = "select * from user";
	private static String aktivacija = "update user set aktivan = '1' where id = ? ";
	private static String deaktivacija = "update user set aktivan = '0' where id = ? ";
	
	public UserDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static User login(String username, String password){
		User user = new User();
		try {
			
			//connection driver
			Class.forName("com.mysql.jdbc.Driver");
			//konekcija ka bazi
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			
			PreparedStatement st= conn.prepareStatement(login);
			st.setString(1, username);
			st.setString(2, password);
			
			ResultSet rs= st.executeQuery();
			
			if(rs.next()){
				user.setId(rs.getInt("id"));
				user.setPrivilegija(rs.getInt("privilegija"));					
				user.setUsername(rs.getString("username"));				
				user.setIme(rs.getString("ime"));
				user.setPrezime(rs.getString("prezime"));
				user.setAktivan(rs.getInt("aktivan"));
				
							
			}
			else
			{
				user = new User(-1,0,"","","","",0);	
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
				
		return user;
	}
	
	public static String registracijaKorisnika(User u){
		Integer i=-1;
		try {
													
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
					PreparedStatement st = conn.prepareStatement(registerUser);					
															
					st.setInt(1, u.getPrivilegija());
					st.setString(2, u.getIme());
					st.setString(3, u.getPrezime());
					st.setString(4, u.getUsername());
					st.setString(5, u.getPassword());
					
					i = st.executeUpdate();
					st.close();
					conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i != -1){
				return "Korisnik je uspjesno registrovan";
				}
				else{
					return "Greska, korisnik sa unesenim parametrima vec postoji";
				}
	}

	public static ArrayList<User> pregledUsera() {
		ArrayList<User> pregled = new ArrayList<User>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			PreparedStatement st = conn.prepareStatement(pregledUsera);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setIme(rs.getString("ime"));
				u.setPrezime(rs.getString("prezime"));
				u.setUsername(rs.getString("username"));
				u.setAktivan(rs.getInt("aktivan"));
				u.setPrivilegija(rs.getInt("privilegija"));
				pregled.add(u);
				}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pregled;
	}

	public static String registracijaTestera(User u) {
		
		Integer i=-1;
		try {
													
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
					PreparedStatement st = conn.prepareStatement(registerUser);					
															
					st.setInt(1, u.getPrivilegija());
					st.setString(2, u.getIme());
					st.setString(3, u.getPrezime());
					st.setString(4, u.getUsername());
					st.setString(5, u.getPassword());
					
					i = st.executeUpdate();
					st.close();
					conn.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i != -1){
				return "Administrator testova je uspjesno registrovan";
				}
				else{
					return "Greska, korisnik sa unesenim parametrima vec postoji";
				}
	}
	
	public static void aktivacija(int id, int aktivan){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testiranje_1.1","root","root");
			
			if(aktivan == 0){
			PreparedStatement st = conn.prepareStatement(aktivacija);
			st.setInt(1, id);			
			st.executeUpdate();			
			st.close();
			}
			else {
			PreparedStatement st = conn.prepareStatement(deaktivacija);
			st.setInt(1, id);
			st.executeUpdate();
			st.close();
			}
			
			
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deaktivacija(int id){
		
	}
	

}

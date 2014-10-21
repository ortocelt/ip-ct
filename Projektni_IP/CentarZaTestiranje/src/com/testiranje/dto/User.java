package com.testiranje.dto;

public class User {
	private int id;
	private int privilegija;	
	private String ime;
	private String prezime;
	private String username;
	private String password;
	private int aktivan;

	public User() {
		// TODO Auto-generated constructor stub
	}
	

	public User(int id, int privilegija, String ime, String prezime,
			String username, String password, int aktivan) {
		super();
		this.id = id;
		this.privilegija = privilegija;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.aktivan = aktivan;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrivilegija() {
		return privilegija;
	}

	public void setPrivilegija(int privilegija) {
		this.privilegija = privilegija;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAktivan() {
		return aktivan;
	}

	public void setAktivan(int aktivan) {
		this.aktivan = aktivan;
	}
}

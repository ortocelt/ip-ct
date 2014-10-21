package com.testiranje.util;

public class ConnectionParametrs {
	
	private String driver = "com.mysql.jdbc.Driver";
	private String konekcija = "jdbc:mysql://localhost:3306/testiranje";
	private String dbuser = "root";
	private String dbpass = "root";
	
	

	public ConnectionParametrs() {
		// TODO Auto-generated constructor stub
	}



	public String getDriver() {
		return driver;
	}



	public void setDriver(String driver) {
		this.driver = driver;
	}



	public String getKonekcija() {
		return konekcija;
	}



	public void setKonekcija(String konekcija) {
		this.konekcija = konekcija;
	}



	public String getDbuser() {
		return dbuser;
	}



	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}



	public String getDbpass() {
		return dbpass;
	}



	public void setDbpass(String dbpass) {
		this.dbpass = dbpass;
	}
	

}

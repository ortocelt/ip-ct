package com.testiranje.dto;

public class Polaganje {
	private int id;
	private int user_id;
	private int test_id;
	private int uplaceno;
	private int ostvareno_bodova;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getTest_id() {
		return test_id;
	}

	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}

	public int getUplaceno() {
		return uplaceno;
	}

	public void setUplaceno(int uplaceno) {
		this.uplaceno = uplaceno;
	}

	public int getOstvareno_bodova() {
		return ostvareno_bodova;
	}

	public void setOstvareno_bodova(int ostvareno_bodova) {
		this.ostvareno_bodova = ostvareno_bodova;
	}

	public Polaganje() {
		// TODO Auto-generated constructor stub
	}

}

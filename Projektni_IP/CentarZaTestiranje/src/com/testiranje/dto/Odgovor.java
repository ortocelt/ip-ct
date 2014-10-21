package com.testiranje.dto;

public class Odgovor {
	private int id;
	private int pitanje_id;
	private String tekst;
	private int tacan;
	private int bodovi;
	private boolean isChecked;
	
	public Odgovor() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPitanje_id() {
		return pitanje_id;
	}
	public void setPitanje_id(int pitanje_id) {
		this.pitanje_id = pitanje_id;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public int getTacan() {
		return tacan;
	}
	public void setTacan(int tacan) {
		this.tacan = tacan;
	}
	public int getBodovi() {
		return bodovi;
	}
	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}

package com.testiranje.dto;

import java.sql.Time;
import java.sql.Timestamp;

public class Test {
	private int id;
	private String naziv;
	private Timestamp datum_polaganja;
	private Timestamp rok_za_prijavu;
	private int max_broj_kandidata;
	private Time trajanje;
	private int potrebno_bodova;
	private String sifra_testa;
	private boolean najavljen;

	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Test( String naziv, Timestamp datum_polaganja,
			Timestamp rok_za_prijavu, int max_broj_kandidata,
			int potrebno_bodova, String sifra_testa, boolean najavljen) {
		super();
		//this.id = id;
		this.naziv = naziv;
		this.datum_polaganja = datum_polaganja;
		this.rok_za_prijavu = rok_za_prijavu;
		this.max_broj_kandidata = max_broj_kandidata;
		//this.trajanje = trajanje;
		this.potrebno_bodova = potrebno_bodova;
		this.sifra_testa = sifra_testa;
		this.najavljen = najavljen;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getMax_broj_kandidata() {
		return max_broj_kandidata;
	}

	public void setMax_broj_kandidata(int max_broj_kandidata) {
		this.max_broj_kandidata = max_broj_kandidata;
	}

	public Time getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Time trajanje) {
		this.trajanje = trajanje;
	}

	public int getPotrebno_bodova() {
		return potrebno_bodova;
	}

	public void setPotrebno_bodova(int potrebno_bodova) {
		this.potrebno_bodova = potrebno_bodova;
	}

	public String getSifra_testa() {
		return sifra_testa;
	}

	public void setSifra_testa(String sifra_testa) {
		this.sifra_testa = sifra_testa;
	}

	public boolean getNajavljen() {
		return najavljen;
	}

	public void setNajavljen(boolean najavljen) {
		this.najavljen = najavljen;
	}

	public Timestamp getRok_za_prijavu() {
		return rok_za_prijavu;
	}

	public void setRok_za_prijavu(Timestamp rok_za_prijavu) {
		this.rok_za_prijavu = rok_za_prijavu;
	}

	public Timestamp getDatum_polaganja() {
		return datum_polaganja;
	}

	public void setDatum_polaganja(Timestamp datum_polaganja) {
		this.datum_polaganja = datum_polaganja;
	}
	
	public void customSet(Timestamp t){
		
	}
	
	

}

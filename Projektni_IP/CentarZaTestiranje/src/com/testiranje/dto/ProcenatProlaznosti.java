package com.testiranje.dto;

import java.sql.Timestamp;

public class ProcenatProlaznosti {
	
	private Timestamp datum_polaganja;
	private String naziv_testa;
	private String procenat;
	

	public ProcenatProlaznosti() {
		// TODO Auto-generated constructor stub
	}


	public Timestamp getDatum_polaganja() {
		return datum_polaganja;
	}


	public void setDatum_polaganja(Timestamp datum_polaganja) {
		this.datum_polaganja = datum_polaganja;
	}


	public String getNaziv_testa() {
		return naziv_testa;
	}


	public void setNaziv_testa(String naziv_testa) {
		this.naziv_testa = naziv_testa;
	}


	public String getProcenat() {
		return procenat;
	}


	public void setProcenat(String procenat) {
		this.procenat = procenat;
	}


	
	
	

}

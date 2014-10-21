package com.testiranje.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.security.auth.kerberos.KerberosKey;

import com.testiranje.dao.TesterDao;
import com.testiranje.dto.Polaganje;

@ManagedBean
@SessionScoped
public class TesterBean {
	
	
	public class PolaganiTestovi{
		private int test_id;
		private String naziv;
		private Timestamp datum_polaganja;
		
		public PolaganiTestovi(){
			//zavrsenaPolaganja();
		}

		public int getTest_id() {
			return test_id;
		}

		public void setTest_id(int test_id) {
			this.test_id = test_id;
		}

		public String getNaziv() {
			return naziv;
		}

		public void setNaziv(String naziv) {
			this.naziv = naziv;
		}

		public Timestamp getDatum_polaganja() {
			return datum_polaganja;
		}

		public void setDatum_polaganja(Timestamp datum_polaganja) {
			this.datum_polaganja = datum_polaganja;
		}
		
	}
	
	public class KandidatiNaPolaganju{
		private int id;
		private int test_id;
		private String ime;
		private String prezime;
		private String naziv;
		private Timestamp datum_polaganja;
		private int bodovi;
		private int potrebno_bodova;
		private int polaganje_id;
		
		
		public KandidatiNaPolaganju(){
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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

		public String getNaziv() {
			return naziv;
		}

		public void setNaziv(String naziv) {
			this.naziv = naziv;
		}

		public Timestamp getDatum_polaganja() {
			return datum_polaganja;
		}

		public void setDatum_polaganja(Timestamp datum_polaganja) {
			this.datum_polaganja = datum_polaganja;
		}

		public int getBodovi() {
			return bodovi;
		}

		public void setBodovi(int bodovi) {
			this.bodovi = bodovi;
		}

		public int getPotrebno_bodova() {
			return potrebno_bodova;
		}

		public void setPotrebno_bodova(int potrebno_bodova) {
			this.potrebno_bodova = potrebno_bodova;
		}

		public int getTest_id() {
			return test_id;
		}
		public void setTest_id(int test_id) {
			this.test_id = test_id;
		}

		public int getPolaganje_id() {
			return polaganje_id;
		}

		public void setPolaganje_id(int polaganje_id) {
			this.polaganje_id = polaganje_id;
		}
		
	}

	ArrayList<PolaganiTestovi> polaganja = new ArrayList<PolaganiTestovi>();
	ArrayList<KandidatiNaPolaganju> kandidatiNaPolaganju = new ArrayList<KandidatiNaPolaganju>();
	
	public ArrayList<KandidatiNaPolaganju> getKandidatiNaPolaganju() {
		//pregledPoPolaganju(p);
		return kandidatiNaPolaganju;
	}
	public void setKandidatiNaPolaganju(
			ArrayList<KandidatiNaPolaganju> kandidatiNaPolaganju) {
		this.kandidatiNaPolaganju = kandidatiNaPolaganju;
	}
	public TesterBean() {
		//zavrsenaPolaganja();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<PolaganiTestovi> getPolaganja() {
		zavrsenaPolaganja();
		return polaganja;
	}
	public void setPolaganja(ArrayList<PolaganiTestovi> polaganja) {
		this.polaganja = polaganja;
	}
	
	public void zavrsenaPolaganja(){
		setPolaganja(TesterDao.zavrsenaPolaganja());
	}
	
	public String pregledPoPolaganju(PolaganiTestovi p){
		setKandidatiNaPolaganju(TesterDao.pregledPoPolaganju(p));
		return "/pages/tester/kandidatiPoPolaganju.xhtml";
	}
	
	

}

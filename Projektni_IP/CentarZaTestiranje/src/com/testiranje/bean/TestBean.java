package com.testiranje.bean;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.testiranje.bean.rss.CreateRss;
import com.testiranje.dao.PolaganjeDao;
import com.testiranje.dao.TestDao;
import com.testiranje.dto.Odgovor;
import com.testiranje.dto.Pitanje;
import com.testiranje.dto.Test;
import com.testiranje.rss.TestRSSWriter;


@ManagedBean
@SessionScoped
public class TestBean {
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	public TestBean() {
		najavljeniTestovi();
		
		String[] args = null;
		TestRSSWriter.main(args);
		//danasnjiTestovi();
	}

	public String createRss(){
		
		CreateRss rss= new CreateRss();
		
		rss.createRss();
		
		return null;
	}
	
	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}	
	
	public static String prettyFormat(String input, int indent) {
	    try {
	        Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", indent);
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	    } catch (Exception e) {
	        throw new RuntimeException(e); // simple exception handling, please review it
	    }
	}

	public static String prettyFormat(String input) {
	    return prettyFormat(input, 2);
	}
	
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	private ArrayList<Test> najavljeniTestovi = new ArrayList<Test>();
	private ArrayList<Test> danasnjiTestovi = new ArrayList<Test>();
	private Test tempTest = new Test();
	private Pitanje tempPitanje = new Pitanje();
	private Odgovor tempOdgovor = new Odgovor();
	private int zadnjiInsertovaniTest;
	private int zadnjeInsertovanoPitanje;
	private ArrayList<Pitanje> kolekcijaTempPitanja = new ArrayList<Pitanje>();
	private ArrayList<Odgovor> kolekcijaTempOdgovora = new ArrayList<Odgovor>();
	private Date tempRok;
	private Date tempDatum;
	private   ArrayList<Pitanje> pitanje = new ArrayList<Pitanje>();
	private   ArrayList<Odgovor> odgovori = new ArrayList<Odgovor>();
	boolean check = true;
	private int polaganjeIzabranogTestaZaPregled;
	private String sifra;
	private String porukaSifra = "";
	private boolean checkbox = false;
	private int updatePolaganje;
	private int idPolaganjaRezultat;
	private int poeni;
	private int potrebnoBodova;
	private int idAktuelniTest;
	
	
	public boolean getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		
		this.checkbox = checkbox;
	}

	public int getPolaganjeIzabranogTestaZaPregled() {
		return polaganjeIzabranogTestaZaPregled;
	}

	public void setPolaganjeIzabranogTestaZaPregled(
			int polaganjeIzabranogTestaZaPregled) {
		this.polaganjeIzabranogTestaZaPregled = polaganjeIzabranogTestaZaPregled;
	}

	
	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
		
	public Pitanje getTempPitanje() {
		return tempPitanje;
	}

	public void setTempPitanje(Pitanje tempPitanje) {
		this.tempPitanje = tempPitanje;
	}

	public Odgovor getTempOdgovor() {
		return tempOdgovor;
	}

	public void setTempOdgovor(Odgovor tempOdgovor) {
		this.tempOdgovor = tempOdgovor;
	}

	public int getZadnjiInsertovaniTest() {
		return zadnjiInsertovaniTest;
	}

	public void setZadnjiInsertovaniTest(int zadnjiInsertovaniTest) {
		this.zadnjiInsertovaniTest = zadnjiInsertovaniTest;
		}	
	
	public void najavljeniTestovi(){
		setNajavljeniTestovi(TestDao.najavljenaPolaganja());
		
	}

	public ArrayList<Test> getNajavljeniTestovi() {
		return najavljeniTestovi;
	}

	public void setNajavljeniTestovi(ArrayList<Test> najavljeniTestovi) {
		this.najavljeniTestovi = najavljeniTestovi;
	}

	public Test getTempTest() {
		return tempTest;
	}

	public void setTempTest(Test tempTest) {
		this.tempTest = tempTest;
	}
	
	public void dodavanjeTesta(){
		/*java.util.Date date= new java.util.Date();
		Timestamp fechaNueva = new Timestamp(date.getTime());
		Test te = new Test("izKonstruktora",fechaNueva,fechaNueva,10,10,"10",true);*/
		tempTest.setRok_za_prijavu(new Timestamp(getTempRok().getTime()));
		tempTest.setDatum_polaganja(new Timestamp(getTempDatum().getTime()));
		setZadnjiInsertovaniTest(TestDao.noviTest(tempTest));
		tempTest.setId(zadnjiInsertovaniTest);
		
	}	
	public void dodavanjePitanja(){
		
		tempPitanje.setTest_id(zadnjiInsertovaniTest);
		setZadnjeInsertovanoPitanje(TestDao.novoPitanje(tempPitanje));		
		setKolekcijaTempPitanja(TestDao.pitanjaPoTestu(tempTest.getId()));
	}
	public void dodavanjeOdgovora(){
		tempOdgovor.setPitanje_id(zadnjeInsertovanoPitanje);
		TestDao.noviOdgovor(tempOdgovor);
		
	}

	public ArrayList<Pitanje> getKolekcijaTempPitanja() {
		return kolekcijaTempPitanja;
	}

	public void setKolekcijaTempPitanja(ArrayList<Pitanje> kolekcijaTempPitanja) {
		this.kolekcijaTempPitanja = kolekcijaTempPitanja;
	}

	public int getZadnjeInsertovanoPitanje() {
		return zadnjeInsertovanoPitanje;
	}

	public void setZadnjeInsertovanoPitanje(int zadnjeInsertovanoPitanje) {
		this.zadnjeInsertovanoPitanje = zadnjeInsertovanoPitanje;
	}

	public Date getTempRok() {
		return tempRok;
	}

	public void setTempRok(Date tempRok) {
		this.tempRok = tempRok;
	}

	public Date getTempDatum() {
		return tempDatum;
	}

	public void setTempDatum(Date tempDatum) {
		this.tempDatum = tempDatum;
	}

	public ArrayList<Odgovor> getKolekcijaTempOdgovora() {
		return kolekcijaTempOdgovora;
	}
	
public ArrayList<Odgovor> getKolekcijaTempOdgovora(int pitanjeId) {
		
		for (Pitanje p : kolekcijaTempPitanja){
			if(p.getId() == pitanjeId)
			kolekcijaTempOdgovora = TestDao.odgovoriPoPitanju(p.getId()); 
		}
		
		return kolekcijaTempOdgovora;
	}	

	public void setKolekcijaTempOdgovora(ArrayList<Odgovor> kolekcijaTempOdgovora) {
		this.kolekcijaTempOdgovora = kolekcijaTempOdgovora;
	}
	public void brisanjeOdgovoraPriKreiranju(Odgovor o){
		kolekcijaTempOdgovora.remove(o);
		TestDao.brisanjeOdgovoraPriKreiranju(o);
	}
	public void brisanjePitanjaPriKreiranju(Pitanje p){
		kolekcijaTempPitanja.remove(p);
		TestDao.brisanjePitanjaPriKreiranju(p);
	}

	public  ArrayList<Pitanje> getPitanje() {
		return pitanje;
	}

	public  void setPitanje(ArrayList<Pitanje> pitanje) {
		this.pitanje = pitanje;
	}

	public  ArrayList<Odgovor> getOdgovori() {
		return odgovori;
	}

	public  void setOdgovori(ArrayList<Odgovor> odgovori) {
		this.odgovori = odgovori;
	}
	
	public  ArrayList<Odgovor> odgovoriZaPitanje(int pitanjeId) {
		//Odgovor odgovor = new Odgovor();
		for (Pitanje p : pitanje){
			if(p.getId() == pitanjeId)
			odgovori = TestDao.odgovoriPoPitanju(p.getId()); 
		}
		return odgovori;
	}
	
public void pregledTesta(int testId){		
		
		pitanje = TestDao.pitanjaPoTestu(testId);		
		//return "/tester/pregledTesta.xhtml";		
	}

public String kandidatovTest(int userId, int testId, int polagnje_id){
	setPolaganjeIzabranogTestaZaPregled(polagnje_id);
	pregledTesta(testId);
	
	return "/pages/tester/kandidatovTest.xhtml";
	
}

public String kandidatPregledTesta(){
	setPolaganjeIzabranogTestaZaPregled(getIdPolaganjaRezultat());
	pregledTesta(getIdAktuelniTest());
	return "/pages/kandidat/kandidatovTestContent.xhtml";
	
}

public  ArrayList<Odgovor> odgovoriZaPitanjeKorTest(int pitanjeId) {
	//Odgovor odgovor = new Odgovor();
	for (Pitanje p : pitanje){
		if(p.getId() == pitanjeId)
		odgovori = TestDao.odgovoriPoPitanju(p.getId()); 
	}
	return odgovori;
}

public boolean provjeraOdgovora(int idOdgovor){
	check = TestDao.provjeraOdgovoraKorisnikovogTesta(idOdgovor,polaganjeIzabranogTestaZaPregled);
	return check;
	
}

public String labelaTacnosti(int idOdgovor){
	
	return TestDao.labelaTacnosti(idOdgovor,polaganjeIzabranogTestaZaPregled);
	
}

public String labelaTacnostiSamoString(int idOdgovor){
	
	return TestDao.labelaTacnostiSamoString(idOdgovor);
	
}

public String prijavaTesta(){
	return null;
}

public ArrayList<Test> getDanasnjiTestovi() {
	return danasnjiTestovi;
}

public void setDanasnjiTestovi(ArrayList<Test> danasnjiTestovi) {
	this.danasnjiTestovi = danasnjiTestovi;
}

public String rjesavanjeTesta(int testId){
	setIdAktuelniTest(testId);
	setUpdatePolaganje(TestDao.setPolaganje(getSifra()));
	if (getUpdatePolaganje() != 0){
		pregledTesta(testId);	
		return "/pages/kandidat/rjesavanjeTesta.xhtml";
	}
	else
	{
		setPorukaSifra("Unijeli ste pogresnu sifru");
	}
	
	return null;
}

public void danasnjiTestovi(){
	setDanasnjiTestovi(TestDao.danasnjiTestovi(getUserBean().getUserId()));
}

public String danPol(){
	danasnjiTestovi();
	return "/pages/kandidat/danasnjaPolaganja.xhtml";
}

public String getSifra() {
	return sifra;
}

public void setSifra(String sifra) {
	this.sifra = sifra;
}

public String getPorukaSifra() {
	return porukaSifra;
}

public void setPorukaSifra(String porukaSifra) {
	this.porukaSifra = porukaSifra;
}



public void insertOrDelete(Odgovor o){
	if(checkbox == true){
		TestDao.uneseniOdgovor(getUpdatePolaganje(),o.getId());
	}
	if(checkbox == false){
		TestDao.ponistenOdgovor(getUpdatePolaganje(),o.getId());
	}
	
}

public String predajTest(){
	//nedostaje setovanje ostvarenih bodova u tabeli polaganje
	//TODO setovanje ostvarenih bodova u polaganju, timer
	setIdPolaganjaRezultat(TestDao.idPolaganjaZaBrojBodova(getSifra()));
	setPoeni(PolaganjeDao.brojBodova(getIdPolaganjaRezultat()));
	setPotrebnoBodova(TestDao.potrebnoBodova(idAktuelniTest));
	
	if(poeni>=potrebnoBodova){
	return "/pages/kandidat/porukaRezultati.xhtml";
	}
	else
	return "/pages/kanditat/porukaRezultatiPad.xhtml";
}

public int getUpdatePolaganje() {
	return updatePolaganje;
}

public void setUpdatePolaganje(int updatePolaganje) {
	this.updatePolaganje = updatePolaganje;
}

public int getIdPolaganjaRezultat() {
	return idPolaganjaRezultat;
}

public void setIdPolaganjaRezultat(int idPolaganjaRezultat) {
	this.idPolaganjaRezultat = idPolaganjaRezultat;
}

public int getPoeni() {
	return poeni;
}

public void setPoeni(int poeni) {
	this.poeni = poeni;
}

public int getPotrebnoBodova() {
	return potrebnoBodova;
}

public void setPotrebnoBodova(int potrebnoBodova) {
	this.potrebnoBodova = potrebnoBodova;
}

public int getIdAktuelniTest() {
	return idAktuelniTest;
}

public void setIdAktuelniTest(int idAktuelniTest) {
	this.idAktuelniTest = idAktuelniTest;
}
}



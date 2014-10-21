package com.testiranje.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class LinkoviBean {
	private String login;
	private String pocetna;
	private String registracija;
	private String najavljeniTestovi;
	private String adminSviKorisnici;
	private String adminKreiranje;
	private String adminStatistika;
	private String testerPregledPolaganja;

	public LinkoviBean() {
		// TODO Auto-generated constructor stub
		
		 setLogin("/pages/gost/loginStranica.xhtml?faces-redirect=true");
		 setPocetna("/index.xhtml");
		 setRegistracija("/pages/gost/registracijaStranica.xhtml?faces-redirect=true");
		 setNajavljeniTestovi("/pages/gost/najavljenaTestiranja.xhtml?faces-redirect=true");
		 setAdminSviKorisnici("/pages/admin/adminIndex.xhtml?faces-redirect=true");
		 setAdminKreiranje("/pages/admin/adminKreiranje.xhtml?faces-redirect=true");
		 setAdminStatistika("/pages/admin/adminStatistika.xhtml?faces-redirect=true");
		 setTesterPregledPolaganja("/pages/tester/testerPregledPolaganja.xhtml?faces-redirect=true");
	}

	public String getLogin() {		
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPocetna() {
		return pocetna;
	}

	public void setPocetna(String pocetna) {
		this.pocetna = pocetna;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public String getNajavljeniTestovi() {
		return najavljeniTestovi;
	}

	public void setNajavljeniTestovi(String najavljeniTestovi) {
		this.najavljeniTestovi = najavljeniTestovi;
	}

	public String getAdminSviKorisnici() {
		return adminSviKorisnici;
	}

	public void setAdminSviKorisnici(String adminSviKorisnici) {
		this.adminSviKorisnici = adminSviKorisnici;
	}

	public String getAdminKreiranje() {
		return adminKreiranje;
	}

	public void setAdminKreiranje(String adminKreiranje) {
		this.adminKreiranje = adminKreiranje;
	}

	public String getAdminStatistika() {
		return adminStatistika;
	}

	public void setAdminStatistika(String adminStatistika) {
		this.adminStatistika = adminStatistika;
	}

	public String getTesterPregledPolaganja() {
		return testerPregledPolaganja;
	}

	public void setTesterPregledPolaganja(String testerPregledPolaganja) {
		this.testerPregledPolaganja = testerPregledPolaganja;
	}
	
	

}

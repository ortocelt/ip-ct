package com.testiranje.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.testiranje.dao.UserDao;
import com.testiranje.dto.User;

@ManagedBean
@SessionScoped
public class UserBean {
	
	private User user = new User();
	private int userId = 0;
	private String porukaLogin = "";
	private String porukaRegister = "";
	private User noviUser = new User();
	private boolean isLoggedIn = false;
	private ArrayList<User> pregledUsera = new ArrayList<User>();
	
	

	public UserBean() {
		pregledUsera();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String login(){		
		
		try{
			
			user = UserDao.login(user.getUsername(), user.getPassword());
			
			if(user.getId()!= -1){
				if(user.getAktivan() == 0){
					setPorukaLogin("Nalog je neaktivan");
					return "/pages/gost/loginStranica.xhtml?faces-redirect=true";
				}
				setLoggedIn(true);
				setUserId(user.getId());
				
				if (user.getPrivilegija() == 1){
					// pregledUsera();
					setLoggedIn(true);
					return "/pages/admin/adminIndex.xhtml?faces-redirect=true";
				}				
				if (user.getPrivilegija() == 2){
					return "/pages/tester/testerIndex.xhtml?faces-redirect=true";
				}
				if (user.getPrivilegija() == 3){
					return "/pages/kandidat/kandidatIndex.xhtml?faces-redirect=true";
					
				}
				/*
				if(korisnik.getPrivilegija().equals("administrator")){
					
					AdministratorBean.setAdmin(AdministratorDAO.selectAdmin(korisnik.getId()));
					setLoggedin(true);
					
					return "/pages/administrator/obavjestenja.jsf?faces-redirect=true";
					
				}else if(korisnik.getPrivilegija().equals("predavac")){
					
					setLoggedin(true);
					
				}else if(korisnik.getPrivilegija().equals("student")){
					
					setLoggedin(true);*/
					
				//}
				
			}else{
				
				setPorukaLogin("Korisnicko ime/Lozinka su pogresni.");
			}
			
		}catch (Exception e) {
			
			setPorukaLogin("Doslo je do greske. Pokusajte kasnije.");
		}
		
		return null; 
	}
	
	public String logout(){
		setLoggedIn(false);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}
	
	public void pregledUsera(){
		setPregledUsera(UserDao.pregledUsera());
		
	}
	
	public void registracijaKandidata(){
		getNoviUser().setPrivilegija(3);
		porukaRegister = UserDao.registracijaKorisnika(getNoviUser());
				
	}
	public void registracijaTestera(){
		noviUser.setPrivilegija(2);
		porukaRegister = UserDao.registracijaTestera(noviUser);
		pregledUsera();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPorukaLogin() {
		return porukaLogin;
	}

	public void setPorukaLogin(String porukaLogin) {
		this.porukaLogin = porukaLogin;
	}

	public String getPorukaRegister() {
		return porukaRegister;
	}

	public void setPorukaRegister(String porukaRegister) {
		this.porukaRegister = porukaRegister;
	}

	public User getNoviUser() {
		return noviUser;
	}

	public void setNoviUser(User noviUser) {
		this.noviUser = noviUser;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public ArrayList<User> getPregledUsera() {
		return pregledUsera;
	}

	public void setPregledUsera(ArrayList<User> pregledUsera) {
		this.pregledUsera = pregledUsera;
	} 
	
	public String promjenaStatusa(int userId, int aktivan){
		UserDao.aktivacija(userId, aktivan);
		pregledUsera();
		return "/pages/admin/adminIndex.xhtml?faces-redirect=true";
		
		
	}

}

package com.testiranje.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean

public class KandidatBean {

	public KandidatBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String povratakNaKandidatMenu(){
		return "/pages/kandidat/kandidatIndex.xhtml";
	}

}

package com.testiranje.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.testiranje.dao.BrojIzlazakaDao;
import com.testiranje.dto.BrojIzlazaka;
@ManagedBean
@RequestScoped
public class BrojIzlazakaBean {

	private ArrayList<BrojIzlazaka> brojIzlazaka = new ArrayList<BrojIzlazaka>();
	public BrojIzlazakaBean() {
		// TODO Auto-generated constructor stub
		napuni();
	}
	public ArrayList<BrojIzlazaka> getBrojIzlazaka() {
		return brojIzlazaka;
	}
	public void setBrojIzlazaka(ArrayList<BrojIzlazaka> brojIzlazaka) {
		this.brojIzlazaka = brojIzlazaka;
	}
	public void napuni(){
		setBrojIzlazaka(BrojIzlazakaDao.napuni());
	}

}

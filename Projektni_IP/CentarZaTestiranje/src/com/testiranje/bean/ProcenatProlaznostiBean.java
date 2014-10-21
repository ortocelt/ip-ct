package com.testiranje.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.testiranje.dao.ProcenatProlaznostiDao;
import com.testiranje.dto.ProcenatProlaznosti;
@ManagedBean
@SessionScoped
public class ProcenatProlaznostiBean {
	private ArrayList<ProcenatProlaznosti> procenatProlaznosti = new ArrayList<ProcenatProlaznosti>();
	public ProcenatProlaznostiBean() {
		napuni();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<ProcenatProlaznosti> getProcenatProlaznosti() {
		return procenatProlaznosti;
	}
	public void setProcenatProlaznosti(ArrayList<ProcenatProlaznosti> procenatProlaznosti) {
		this.procenatProlaznosti = procenatProlaznosti;
	}
	
	public void napuni(){
		setProcenatProlaznosti(ProcenatProlaznostiDao.procenatProlaznosti());
		
	}
	
	

}

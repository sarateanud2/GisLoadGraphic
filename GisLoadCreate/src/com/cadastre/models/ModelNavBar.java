package com.cadastre.models;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ModelNavBar implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String page;
	
	
//	public ModelNavBar() {
//		this.page = "main";
//	}
	
	@PostConstruct
	public void init() {
		this.page = "main";
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
}

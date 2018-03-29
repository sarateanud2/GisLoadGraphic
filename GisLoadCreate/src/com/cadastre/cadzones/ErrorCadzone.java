package com.cadastre.cadzones;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ErrorCadzone {
	
	private List<String> cadzoneError;
	
	@PostConstruct
	public void init() {
		cadzoneError = new ArrayList<>();
		cadzoneError.add("0100");
		cadzoneError.add("0300");
		cadzoneError.add("6055");
		cadzoneError.add("1218");
		cadzoneError.add("1219");
		cadzoneError.add("1298");
		cadzoneError.add("1589");
		cadzoneError.add("5878");
		cadzoneError.add("8988");
		cadzoneError.add("0100");
		cadzoneError.add("0300");
	}

	public List<String> getCadzoneError() {
		return cadzoneError;
	}

	public void setCadzoneError(List<String> cadzoneError) {
		this.cadzoneError = cadzoneError;
	}
	
	
}

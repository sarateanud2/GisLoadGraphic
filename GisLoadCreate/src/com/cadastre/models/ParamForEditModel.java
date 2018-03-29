package com.cadastre.models;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.cadastre.objectsget.ParameterEdit;
import com.cadastre.services.ParamsForEditSerivces;

@ManagedBean(name="paramForEditModel")
@ViewScoped
public class ParamForEditModel  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<ParameterEdit> allParams;
	
	@ManagedProperty("#{paramsForEditSerivces}")
	private ParamsForEditSerivces service;

	public ParamsForEditSerivces getService() {
		return service;
	}

	public void setService(ParamsForEditSerivces service) {
		this.service = service;
	}
	
	@PostConstruct
	public void init() {
		allParams = service.fillAllParamsForEdit();
	}

	public List<ParameterEdit> getAllParams() {
		return allParams;
	}

	public void setAllParams(List<ParameterEdit> allParams) {
		this.allParams = allParams;
	}
	
	public void onRowEdit(RowEditEvent event) {
		
		FacesMessage msg = new FacesMessage(((ParameterEdit) event.getObject()).getDescription(), ((ParameterEdit) event.getObject()).getValue());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		this.service.onRowEdit(((ParameterEdit) event.getObject()).getValue(),
								((ParameterEdit) event.getObject()).getDescription(), 
								((ParameterEdit) event.getObject()).getParamNotation());
	}

}

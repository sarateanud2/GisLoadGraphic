package com.cadastre.objectsget;

import java.io.Serializable;

public class ParameterEdit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String paramNotation;
	private String value;
	private String description;
	
	public ParameterEdit() {}

	public ParameterEdit(String paramNotation, String value, String description) {
		this.paramNotation = paramNotation;
		this.value = value;
		this.description = description;
	}

	public String getParamNotation() {
		return paramNotation;
	}

	public void setParamNotation(String paramNotation) {
		this.paramNotation = paramNotation;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

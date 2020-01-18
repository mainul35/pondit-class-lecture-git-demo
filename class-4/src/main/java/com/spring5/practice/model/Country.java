package com.spring5.practice.model;

import java.io.Serializable;

public class Country implements Serializable{

	private long id;
	private String countryCode;
	private String countryName;
	
	public Country() {
		super();
	}
	
	public Country(long id, String countryCode, String countryName) {
		super();
		this.id = id;
		this.countryCode = countryCode;
		this.countryName = countryName;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	@Override
	public String toString() {
		return "Country [id=" + id + ", countryCode=" + countryCode + ", countryName=" + countryName + "]";
	}
}

package com.spring5.practice.model;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tbl_country")
public class Country implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generator")
//	@TableGenerator(name = "table_country_generator",
//			table = "country_ids",
//			pkColumnName = "seq_id",
//			valueColumnName = "seq_value")
	private long id;
	@Column(name = "country_code", unique = true, nullable = false, updatable = false)
	private String countryCode;
	@Column(name = "country_name", nullable = false)
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

package com.spring5.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.spring5.practice.exceptions.ResourceAlreadyExistsException;
import com.spring5.practice.exceptions.ResourceNotFoundException;
import com.spring5.practice.model.Country;

@Service
public class CountryService {

	private final JdbcTemplate jdbcTemplate;

	public CountryService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		String createTable = "create table if not exists country(" + "id bigint not null auto_increment PRIMARY KEY, "
				+ "country_code varchar(20), " + "country_name varchar(100)" + "); ";
		this.jdbcTemplate.execute(createTable);
	}

	public void addCountry(Country country) {
		checkCountryInList(country);
		var insertQuery = "insert into country(country_code, country_name) values(?, ?); ";
		int resultSet = jdbcTemplate.update(insertQuery, country.getCountryCode(), country.getCountryName());
		if (resultSet < 1) {
			throw new RuntimeException("Failed to create country.");
		}
	}

	public void checkCountryInList(Country c) {
		var checkCountryQuery = "select count(*) as count from country c where c.country_code = ?; ";
		Map<String, Object> map = jdbcTemplate.queryForMap(checkCountryQuery, c.getCountryCode());
		if (Integer.parseInt(map.get("count").toString()) > 0) {
			throw new ResourceAlreadyExistsException("Country with this Country code is already in DB.");
		}
	}

	public Country getCountryByCode(String countryCode) {
		var countryByCode = "select id, country_code, country_name from country where country_code = ? ; ";
		var countries = new ArrayList<Country>();
		var countryMap = jdbcTemplate.queryForMap(countryByCode, countryCode);
		var country = new Country();
		country.setId(Long.parseLong(countryMap.get("id").toString()));
		country.setCountryCode(countryMap.get("country_code").toString());
		country.setCountryName(countryMap.get("country_name").toString());
		return country;
	}

	public List<Country> getAll() {
		var allCountryQuery = "select id, country_code, country_name from country; ";
		var countries = new ArrayList<Country>();
		jdbcTemplate.queryForList(allCountryQuery).stream().forEach(countryMap -> {
			var country = new Country();
			country.setId(Long.parseLong(countryMap.get("id").toString()));
			country.setCountryCode(countryMap.get("country_code").toString());
			country.setCountryName(countryMap.get("country_name").toString());
			countries.add(country);
		});

		return countries;
	}
}

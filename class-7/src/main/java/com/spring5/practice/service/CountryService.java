package com.spring5.practice.service;

import com.spring5.practice.config.HibernateConfig;
import com.spring5.practice.exceptions.ResourceAlreadyExistsException;
import com.spring5.practice.exceptions.ResourceNotFoundException;
import com.spring5.practice.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

	private final HibernateConfig hibernateConfig;
	
	private static List<Country> countries = new ArrayList<Country>();

	private static final String[] COUNTRIES = { "BANGLADESH", "USA", "JAPAN", "NEPAL", "IRELAND", "GERMAN", "CANADA" };

	@Autowired
	public CountryService(HibernateConfig hibernateConfig) {
		this.hibernateConfig = hibernateConfig;
	}

	private void addCountry(String countryName) {
		// save the student object
		var countryObj = new Country();
		countryObj.setId(countries.size() + 1);
		countryObj.setCountryName(countryName);
		countryObj.setCountryCode(countryName.substring(0, 3));
//		countries.add(countryObj);

		// save the student object
	}

	@Transactional
	public void addCountry(Country country) {
		var session = hibernateConfig.getSession();
		var transaction = session.beginTransaction();
		country.setId(countries.size() + 1);
		session.save(country);
		transaction.commit();
	}

	public void checkCountryInList(Country c) {
		if (countries.stream().filter(country -> country.getCountryCode().equals(c.getCountryCode())).findAny()
				.isPresent()) {
			throw new ResourceAlreadyExistsException("Country already exists in list");
		}
	}

	public Country getCountryByCode(String countryCode) {
		var session = hibernateConfig.getSession();
		var transaction = session.beginTransaction();
		var query = session
				.getEntityManagerFactory()
				.createEntityManager()
				.createQuery("SELECT c from com.spring5.practice.model.Country c where c.countryCode=:countryCode", Country.class);
		query.setParameter("countryCode", countryCode);

		return query.getResultStream().findAny()
				.orElseThrow(() -> new ResourceNotFoundException("Country not found with this code"));
	}

	public List<Country> getAll() {
		var session = hibernateConfig.getSession();
		session.beginTransaction();
		var query = session
				.getEntityManagerFactory()
				.createEntityManager()
				.createQuery("SELECT c from com.spring5.practice.model.Country c ", Country.class);

		return query.getResultList();
	}
}

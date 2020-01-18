package com.spring5.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.spring5.practice.model.Country;

@Service
public class CountryService {

	private static List<Country> countries = new ArrayList<Country>();
	
	private static final String[] COUNTRIES = {
			"BANGLADESH", "USA", "JAPAN", "NEPAL", "IRELAND", "GERMAN", "CANADA"
	}; 
	
	public CountryService() {
		// TODO Auto-generated constructor stub
//		final AtomicInteger atomicId = new AtomicInteger(0);
		final AtomicReference<Integer> atomicId = new AtomicReference<Integer>();
		atomicId.set(0);
		Stream.of(COUNTRIES).forEach(country->{
			var countryObj = new Country();
			countryObj.setId(atomicId.get());
			countryObj.setCountryName(country);
			countryObj.setCountryCode(country.substring(0, 2));
			addCountry(countryObj);
			atomicId.set(atomicId.get() + 1);
		});
	}
	public void addCountry(Country country) {
		
	}
}

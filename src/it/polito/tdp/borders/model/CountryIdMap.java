package it.polito.tdp.borders.model;

import java.util.*;

public class CountryIdMap {
	
	private Map<Integer,Country> map;
	
	public CountryIdMap() {
		map = new HashMap<>();
	}
	
	public Country get(Integer id) {
		return map.get(id);
	}
	
	public Country get(Country country) {
		Country old = map.get(country.getcCode());
		if(old==null) {
			map.put(country.getcCode(), country);
			return country;
		}
		return old;
	}
	
	public void put(Integer id, Country country) {
		map.put(id, country);
	}

}
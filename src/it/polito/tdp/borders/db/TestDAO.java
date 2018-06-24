package it.polito.tdp.borders.db;

import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryIdMap;

public class TestDAO {
	
	public static void main(String[] args) {
		List<Country> list ;
		BordersDAO dao = new BordersDAO() ;
		CountryIdMap map = new CountryIdMap();
		list = dao.loadAllCountries(map,1996) ;
//		for(Country c: list) {
//			System.out.println(c);
//		}
		System.out.println(list.size());
		
		List<Border> borders = dao.loadAllBorderFromYear(map, 1996);
		
//		for(Border b: borders) {
//			System.out.println(b);
//		}
		
		System.out.println(borders.size());
	}

}

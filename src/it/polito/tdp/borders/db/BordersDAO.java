package it.polito.tdp.borders.db;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryIdMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BordersDAO {
	
	public List<Country> loadAllCountries(CountryIdMap	countryIdMap, int year) {
		
		String sql = 
				"SELECT c1.ccode,StateAbb,StateNme\n" + 
				"FROM country as c1, contiguity as c2\n" + 
				"where (c1.CCode=c2.state1no)\n" + 
				"and year<=?\n" + 
				"and conttype=1\n" + 
				"group BY StateAbb " ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, year);
			ResultSet rs = st.executeQuery() ;
			
			List<Country> list = new ArrayList<Country>() ;
			
			while( rs.next() ) {
				
				Country c = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				
				list.add(countryIdMap.get(c)) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	public List<Border> loadAllBorderFromYear(CountryIdMap countryIdMap, int year) {
		
		String sql = 
				"select state1no, state2no\n" + 
				"from contiguity\n" + 
				"where conttype=1\n" + 
				"and state1no<state2no\n" + 
				"and year<=?" ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, year);
			ResultSet rs = st.executeQuery() ;
			
			List<Border> list = new ArrayList<>() ;
			
			while( rs.next() ) {
				
				list.add(new Border(countryIdMap.get(rs.getInt("state1no")), countryIdMap.get(rs.getInt("state2no")))) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	public int minYear() {
		
		String sql = 
				"select min(year) as m from contiguity" ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			ResultSet rs = st.executeQuery() ;
			
			int result =0;
			
			if( rs.next() ) {
				result = rs.getInt("m");
			}
			
			conn.close() ;
			
			return result ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int maxYear() {
		
		String sql = 
				"select max(year) as m from contiguity" ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			ResultSet rs = st.executeQuery() ;
			
			int result =0;
			
			if( rs.next() ) {
				result = rs.getInt("m");
			}
			
			conn.close() ;
			
			return result ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
}

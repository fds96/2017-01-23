package it.polito.tdp.borders.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private CountryIdMap map;
	private List<Country> countries;
	private BordersDAO dao;
	private Graph<Country, DefaultEdge> graph;
	private int stepSimulazione;

	public Model() {
		map = new CountryIdMap();
		dao = new BordersDAO();
	}
		
	public List<Country> getCountries() {
		List<Country> result = new LinkedList<>(this.countries);
		Collections.sort(result);
		return result;
	}
	
	public int maxYear() {
		return dao.maxYear();
	}
	
	public int minYear() {
		return dao.minYear();
	}
	
	public int getStepSimulazione() {
		return this.stepSimulazione;
	}

	public List<RisultatoCalcolaConfini> calcolaConfini(int year) {
		countries = dao.loadAllCountries(map, year);
		graph= new SimpleGraph<>(DefaultEdge.class);
		
		Graphs.addAllVertices(this.graph, this.countries);
		
		for(Border b : dao.loadAllBorderFromYear(map, year)) {
			this.graph.addEdge(b.getCountry1(), b.getCountry2());
		}
		
//		System.out.println(this.graph.vertexSet().size());
//		System.out.println(this.graph.edgeSet().size());
		
		List<RisultatoCalcolaConfini> result = new LinkedList<>();
		for(Country c : this.graph.vertexSet()) {
			result.add(new RisultatoCalcolaConfini(c, Graphs.neighborListOf(this.graph, c).size()));
		}
		Collections.sort(result);
		
		return result;
	}
	
	public List<RisultatoSimulazione> simula(Country country) {
		Simulazione sim = new Simulazione(country, this.graph);
		sim.init();
		sim.run();
		this.stepSimulazione=sim.getStep();
		List<RisultatoSimulazione> result = new LinkedList<>(sim.getRisultato());
		Collections.sort(result);
		return result;
 	}
	
}

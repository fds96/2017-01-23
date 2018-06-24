package it.polito.tdp.borders.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Simulazione {
	
	private Country iniziale;
	private int numeroIniziale;
	private Graph<Country, DefaultEdge> graph;
	private Map<Country,RisultatoSimulazione> risultato;
	private Map<Country,Event> daMuovere;
	private int step;

	public Simulazione(Country country, Graph<Country, DefaultEdge> graph) {
		this.iniziale=country;
		this.numeroIniziale=1000;
		this.graph=graph;
		this.step=0;
		this.daMuovere=new TreeMap<>();
		this.risultato= new HashMap<>();
	}
	
	

	public List<RisultatoSimulazione> getRisultato() {
		return new ArrayList<>(risultato.values());
	}



	public int getStep() {
		return step;
	}



	public void init() {
		this.step++;
		this.risultato.put(iniziale, new RisultatoSimulazione(iniziale, this.numeroIniziale/2));
		int numeroVicini = Graphs.neighborListOf(this.graph, iniziale).size();
		int daMuovere = (int) Math.floor((this.numeroIniziale/2)/numeroVicini);
		for(Country c : Graphs.neighborListOf(this.graph, iniziale)) {
			this.daMuovere.put(c, new Event(c,daMuovere));
		}
		int rimasti = (this.numeroIniziale/2)%numeroVicini;
		this.risultato.get(iniziale).incrementaStanziali(rimasti);
	}
	
	public void run() {
		do {
			this.step++;
			List<Event> successivi = new ArrayList<>(this.daMuovere.values());
			this.daMuovere.clear();
			for(Event evento : successivi) {	//Calcolo subito correnti, stanziali e non stanziali
				Country c = evento.getCountry();
				int correnti = evento.getDaMuovere();
				int stanziali = (int) Math.floor(correnti/2);
				int nonStanziali=correnti - stanziali;
				
//				System.out.println(c+ " " + correnti + " " +stanziali +" "+ nonStanziali);
				
				if(this.risultato.containsKey(c)) {	//Aggiungo gli stanziali
					this.risultato.get(c).incrementaStanziali(stanziali);
				}
				else {
					this.risultato.put(c, new RisultatoSimulazione(c, stanziali));
				}
				
				int numeroVicini = Graphs.neighborListOf(this.graph, c).size();
				int daMuovere = (int) Math.floor(nonStanziali/numeroVicini);
				if(daMuovere>=numeroVicini) {	//Se i non stanziali sono superiori ai vicini allora li faccio muovere
					for(Country country : Graphs.neighborListOf(this.graph, c)) {
						
						if(this.daMuovere.containsKey(country)) {	//Controllando che lo stato verso cui si devono muovere sia presente in quelli da muovere al prossimo passo
							this.daMuovere.get(country).incrementaDaMuovere(daMuovere);
						}
						else {
							this.daMuovere.put(country, new Event(country,daMuovere));
						}
						
					}
					int rimasti = nonStanziali%numeroVicini;	//Alla fine aggiungo i rimanenti che non sono stati mossi
//					System.out.println(rimasti);
					this.risultato.get(c).incrementaStanziali(rimasti);
				}
				else this.risultato.get(c).incrementaStanziali(nonStanziali);	//Altrimenti diventano tutti stanziali
			}
		}
		while(this.daMuovere.size()!=0);
//		System.out.println(step);
//		System.out.println(this.risultato.values().size());
	}
	
	
}

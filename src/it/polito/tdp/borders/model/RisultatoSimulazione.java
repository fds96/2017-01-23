package it.polito.tdp.borders.model;

public class RisultatoSimulazione implements Comparable<RisultatoSimulazione>{
	
	private Country country;
	private int stanziali;
	public RisultatoSimulazione(Country country, int stanziali) {
		super();
		this.country = country;
		this.stanziali = stanziali;
	}
	public RisultatoSimulazione(Country country) {
		super();
		this.country = country;
	}
	public void incrementaStanziali(int quantita) {
		this.stanziali+=quantita;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public int getStanziali() {
		return stanziali;
	}
	public void setStanziali(int stanziali) {
		this.stanziali = stanziali;
	}
	public String toString() {
		return String.valueOf(String.format("Lo stato %s ha %d stanziali", this.country, this.stanziali));
	}
	@Override
	public int compareTo(RisultatoSimulazione other) {
		return other.stanziali-this.stanziali;
	}
	

}

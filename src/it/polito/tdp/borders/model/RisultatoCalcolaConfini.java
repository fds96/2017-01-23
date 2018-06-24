package it.polito.tdp.borders.model;

public class RisultatoCalcolaConfini implements Comparable<RisultatoCalcolaConfini>{
	
	private Country country;
	private int numeroConfinanti;
	public RisultatoCalcolaConfini(Country country, int numeroConfinanti) {
		super();
		this.country = country;
		this.numeroConfinanti = numeroConfinanti;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public int getNumeroConfinanti() {
		return numeroConfinanti;
	}
	public void setNumeroConfinanti(int numeroConfinanti) {
		this.numeroConfinanti = numeroConfinanti;
	}
	public String toString() {
		return this.country+ " ha un numero di stati confinanti pari a: "+this.numeroConfinanti;
	}
	@Override
	public int compareTo(RisultatoCalcolaConfini other) {
		return other.numeroConfinanti-this.numeroConfinanti;
	}
	

}

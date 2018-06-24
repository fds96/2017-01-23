package it.polito.tdp.borders.model;

public class Event {
	
	private Country country;
	private int daMuovere;
	public Event(Country country, int daMuovere) {
		super();
		this.country = country;
		this.daMuovere = daMuovere;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public int getDaMuovere() {
		return daMuovere;
	}
	public void setDaMuovere(int daMuovere) {
		this.daMuovere = daMuovere;
	}
	public void mossi() {
		this.daMuovere=0;
	}
	public void incrementaDaMuovere(int daMuovere) {
		this.daMuovere+=daMuovere;
	}
	

}

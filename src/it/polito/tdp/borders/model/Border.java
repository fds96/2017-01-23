package it.polito.tdp.borders.model;

public class Border {

	private Country country1;
	private Country country2;
	public Border(Country country1, Country country2) {
		super();
		this.country1 = country1;
		this.country2 = country2;
	}
	public Country getCountry1() {
		return country1;
	}
	public void setCountry1(Country country1) {
		this.country1 = country1;
	}
	public Country getCountry2() {
		return country2;
	}
	public void setCountry2(Country country2) {
		this.country2 = country2;
	}
	
	public String toString() {
		return this.country1 + " - " + this.country2;
	}
}

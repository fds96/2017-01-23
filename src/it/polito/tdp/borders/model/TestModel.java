package it.polito.tdp.borders.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		for(RisultatoCalcolaConfini c : model.calcolaConfini(1996)) {
			System.out.println(c);
		}
		
		int cnt=0;
		for(RisultatoSimulazione r : model.simula(model.getCountries().get(78))) {
			System.out.println(r);
			cnt+=r.getStanziali();
		}
		System.out.println(cnt);
	}

}

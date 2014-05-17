package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;

public class Recinti {
	private ArrayList<PedinaSuStrada> recintiIniziali;
	private ArrayList<PedinaSuStrada> recintiFinali;

	public Recinti() {
		recintiIniziali = new ArrayList<PedinaSuStrada>();
		recintiFinali = new ArrayList<PedinaSuStrada>();
	}
	
	public void aggiungi(Strada strada) {
		if (recintiIniziali.size() > Costanti.NUM_RECINTI)
			recintiIniziali.add(new PedinaSuStrada(strada));
		else
			recintiFinali.add(new PedinaSuStrada(strada));
	}
	
	public ArrayList<PedinaSuStrada> getRecinti() {
		ArrayList<PedinaSuStrada> recinti = new ArrayList<PedinaSuStrada>();
		recinti.addAll(recintiIniziali);
		recinti.addAll(recintiFinali);
		
		return recinti;
	}
	
}

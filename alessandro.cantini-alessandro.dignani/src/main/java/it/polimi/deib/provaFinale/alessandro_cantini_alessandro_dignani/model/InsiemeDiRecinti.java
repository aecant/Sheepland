package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;

public class InsiemeDiRecinti {
	private ArrayList<PedinaSuStrada> recintiIniziali;
	private ArrayList<PedinaSuStrada> recintiFinali;

	public InsiemeDiRecinti() {
		recintiIniziali = new ArrayList<PedinaSuStrada>();
		recintiFinali = new ArrayList<PedinaSuStrada>();
	}
	
	public void aggiungi(Strada posizione) {
		if (recintiIniziali.size() < Costanti.NUM_RECINTI_INIZIALI)
			recintiIniziali.add(new PedinaSuStrada(posizione));
		else
			recintiFinali.add(new PedinaSuStrada(posizione));
	}
	
	public ArrayList<PedinaSuStrada> getRecinti() {
		ArrayList<PedinaSuStrada> recinti = new ArrayList<PedinaSuStrada>();
		recinti.addAll(recintiIniziali);
		recinti.addAll(recintiFinali);
		
		return recinti;
	}

	public ArrayList<PedinaSuStrada> getRecintiIniziali() {
		return recintiIniziali;
	}

	public ArrayList<PedinaSuStrada> getRecintiFinali() {
		return recintiFinali;
	}
	
	
}

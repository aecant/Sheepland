package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;

public class Recinti {
	private static Recinti istanza;
	private ArrayList<PedinaSuStrada> recintiIniziali;
	private ArrayList<PedinaSuStrada> recintiFinali;

	private Recinti() {
		recintiIniziali = new ArrayList<PedinaSuStrada>();
		recintiFinali = new ArrayList<PedinaSuStrada>();
	}

	public static Recinti getRecinti() {
		if (istanza == null)
			istanza = new Recinti();
		return istanza;
	}
	
	public void aggiungi(Strada strada) {
		//TODO controllo che non sia immesso in una strada già occupata
		if (recintiIniziali.size() > Costanti.NUM_RECINTI)
			recintiIniziali.add(new PedinaSuStrada(strada));
		else
			recintiFinali.add(new PedinaSuStrada(strada));
	}
	
	public void reset() {
		istanza = new Recinti();
	}
}

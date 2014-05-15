package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;

public class Gregge {
	private static Gregge istanza = null;
	ArrayList<Pecora> pecore;
	
	public ArrayList<Pecora> getPecore() {
		return pecore;
	}

	private Gregge() {
		pecore = new ArrayList<Pecora>();
	}
	
	public static Gregge getGregge() {
		if (istanza == null)
			istanza = new Gregge();
		return istanza;
	}
	
	public void inserisci(Pecora pec) {
		pecore.add(pec);
	}
	
	public void elimina(Pecora pec) {
		pecore.remove(pec);
	}
	
	/**
	 * Trasforma l'agnello passato come parametro in pecora con un casting
	 * @param agnello
	 */
	public void trasformaAgnelloInPecora(Agnello agnello) {
		pecore.set(pecore.indexOf(agnello), (Pecora) agnello);
	}
}

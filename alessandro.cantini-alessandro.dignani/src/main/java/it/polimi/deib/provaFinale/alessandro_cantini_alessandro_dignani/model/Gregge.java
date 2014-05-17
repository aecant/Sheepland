package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;

public class Gregge {
	private ArrayList<Pecora> pecore;
	private Animale pecoraNera;


	public Gregge() {
		pecore = new ArrayList<Pecora>();
		pecoraNera = new Animale(Costanti.POS_INIZIALE_PECORA_NERA);
	}

	public void inserisci(Pecora p) {
		pecore.add(p);
	}

	public void elimina(Pecora p) {
		pecore.remove(p);
	}

	/**
	 * Trasforma l'agnello passato come parametro in pecora con un casting
	 * 
	 * @param agnello
	 */
	public void trasformaAgnelloInPecora(Agnello agnello) {
		pecore.set(pecore.indexOf(agnello), (Pecora) agnello);
	}
	
	public Animale getPecoraNera() {
		return pecoraNera;
	}

	public ArrayList<Pecora> getPecore() {
		return pecore;
	}
}

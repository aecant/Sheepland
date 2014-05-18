package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import java.util.ArrayList;

public class Gregge {
	private ArrayList<Pecora> pecore;
	private Animale pecoraNera;


	public Gregge() {
		pecore = new ArrayList<Pecora>();
		pecoraNera = new Animale(Costanti.POS_INIZIALE_PECORA_NERA);
	}

	public void aggiungi(Pecora p) {
		pecore.add(p);
	}

	/**
	 * Rimuove dal gregge la pecora passata come parametro
	 * 
	 * @param p
	 * @throws IllegalArgumentException se la pecora non e' presente
	 */
	public void rimuovi(Pecora p) throws IllegalArgumentException{
		if(!pecore.remove(p))
			throw new IllegalArgumentException("La pecora non e' presente nel gregge");
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

	/**
	 * Restituisce una copia del gregge in modo da non poter essere modificata all'esterno
	 * @return ArrayList di pecore clonato
	 */
	public ArrayList<Pecora> getPecore() {
		ArrayList<Pecora> temp = new ArrayList<Pecora>();
		temp.addAll(pecore);
		return temp;
	}
}

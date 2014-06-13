package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che contiene la lista di pecore di una partita e i metodi per gestirle
 */
public class Gregge {
	private List<Pecora> pecore;
	private Animale pecoraNera;

	/**
	 * Istanzia l'ArrayList di pecore e la pecora nera
	 */
	public Gregge() {
		pecore = new ArrayList<Pecora>();
		pecoraNera = new Animale(Costanti.POS_INIZIALE_PECORA_NERA);
	}

	/**
	 * Aggiunge una pecora al gregge
	 * 
	 * @param p
	 *            la pecora da aggiungere
	 * 
	 */
	public void aggiungi(Pecora p) {
		pecore.add(p);
	}

	/**
	 * Rimuove dal gregge la pecora passata come parametro
	 * 
	 * @param p
	 *            la pecora da rimuovere
	 * @throws IllegalArgumentException
	 *             se la pecora non e' presente
	 */
	public void rimuovi(Pecora p) throws IllegalArgumentException {
		if (!pecore.remove(p)) {
			throw new IllegalArgumentException("La pecora non e' presente nel gregge");
		}
	}

	/**
	 * Trasforma un agnello in pecora
	 * 
	 * @param agnello
	 *            l'agnello da trasformare
	 */
	public void trasformaAgnelloInPecora(Agnello agnello) throws IllegalArgumentException {
		if (!pecore.contains(agnello)) {
			throw new IllegalArgumentException("L'agnello non e' presente");
		}
		pecore.set(pecore.indexOf(agnello), agnello);
	}

	/**
	 * Restituisce la pecora nera
	 * 
	 * @return la pecora nera
	 */
	public Animale getPecoraNera() {
		return pecoraNera;
	}

	public List<Pecora> getPecore() {
		return pecore;
	}

	/**
	 * Restituisce la lista di tutti gli ovini, compresa la pecora nera
	 * 
	 * @return la lista di tutti gli ovini
	 */
	public List<Animale> getPecoreCompresaLaNera() {
		List<Animale> pecore = new ArrayList<Animale>();
		pecore.addAll(pecore);
		pecore.add(pecoraNera);
		return pecore;
	}
}

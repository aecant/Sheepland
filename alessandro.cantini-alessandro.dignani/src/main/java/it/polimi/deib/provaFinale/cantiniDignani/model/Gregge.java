package it.polimi.deib.provaFinale.cantiniDignani.model;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;

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
	 * Aggiunge una pecora alla lista
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
	 * Trasforma l'agnello passato come parametro in pecora con un casting
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
	 * Restituisce la lista delle pecore che stanno su un determinato territorio
	 * 
	 * @param t
	 *            il territorio su cui stanno le pecore
	 * @return la lista delle pecore sul territorio passato come parametro
	 */
	public ArrayList<Pecora> pecoreSuTerritorio(Territorio t) {
		ArrayList<Pecora> pecore = new ArrayList<Pecora>();

		for (Pecora p : getPecore()) {
			if (p.getPosizione().equals(t)) {
				pecore.add(p);
			}
		}
		return pecore;
	}

	/**
	 * Restituisce la pecora nera
	 * 
	 * @return la pecora nera
	 */
	public Animale getPecoraNera() {
		return pecoraNera;
	}

	/**
	 * Restituisce una copia del gregge
	 * 
	 * @return una copia dell'ArrayList pecore
	 */
	public ArrayList<Pecora> getPecore() {
		return Utilita.copia(pecore);
	}
}

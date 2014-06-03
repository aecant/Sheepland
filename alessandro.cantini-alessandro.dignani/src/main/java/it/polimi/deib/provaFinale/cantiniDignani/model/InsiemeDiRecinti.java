package it.polimi.deib.provaFinale.cantiniDignani.model;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;

import java.util.ArrayList;

/**
 * Classe che fornisce i metodi per la gestione dei recinti
 */
public class InsiemeDiRecinti {
	private ArrayList<PedinaSuStrada> recintiIniziali;
	private ArrayList<PedinaSuStrada> recintiFinali;

	public InsiemeDiRecinti() {
		recintiIniziali = new ArrayList<PedinaSuStrada>();
		recintiFinali = new ArrayList<PedinaSuStrada>();
	}

	/**
	 * Aggiunge un recinto su una strada
	 * 
	 * @param posizione
	 *            la strada su cui viene aggiunto il recinto
	 */
	public void aggiungi(Strada posizione) {
		if (recintiIniziali.size() < Costanti.NUM_RECINTI_INIZIALI) {
			recintiIniziali.add(new PedinaSuStrada(posizione));
		} else {
			recintiFinali.add(new PedinaSuStrada(posizione));
		}
	}

	/**
	 * Costruisce e restituisce la lista di tutti i recinti, iniziali e finali
	 * 
	 * @return la lista di tutti i recinti
	 */
	public ArrayList<PedinaSuStrada> getListaRecinti() {
		ArrayList<PedinaSuStrada> recinti = new ArrayList<PedinaSuStrada>();
		recinti.addAll(recintiIniziali);
		recinti.addAll(recintiFinali);

		return recinti;
	}

	/**
	 * Restituisce una copia dei recinti iniziali
	 * 
	 * @return una copia della la lista dei reciniti iniziali
	 */
	public ArrayList<PedinaSuStrada> getRecintiIniziali() {
		return Utilita.copia(recintiIniziali);
	}

	/**
	 * Restituisce una copia dei recinti finali
	 * 
	 * @return una copia della la lista dei reciniti finali
	 */
	public ArrayList<PedinaSuStrada> getRecintiFinali() {
		return Utilita.copia(recintiFinali);
	}

}

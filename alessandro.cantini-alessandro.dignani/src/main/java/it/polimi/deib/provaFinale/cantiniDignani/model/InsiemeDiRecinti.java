package it.polimi.deib.provaFinale.cantiniDignani.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che fornisce i metodi per la gestione dei recinti
 */
public class InsiemeDiRecinti {
	private final List<PedinaSuStrada> recintiIniziali;
	private final List<PedinaSuStrada> recintiFinali;

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
	public List<PedinaSuStrada> getListaRecinti() {
		List<PedinaSuStrada> recinti = new ArrayList<PedinaSuStrada>();
		recinti.addAll(recintiIniziali);
		recinti.addAll(recintiFinali);

		return recinti;
	}

	public List<PedinaSuStrada> getRecintiIniziali() {
		return recintiIniziali;
	}

	public List<PedinaSuStrada> getRecintiFinali() {
		return recintiFinali;
	}

}

package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;

/**
 * Contiene i metodi per gestire una fase della partita
 */
public abstract class FasePartita {

	protected Partita partita;
	protected GestorePartita gestore;

	public FasePartita(GestorePartita gestore) {
		this.gestore = gestore;
		partita = gestore.getPartita();
	}
	
	
	public abstract void esegui();
	
}

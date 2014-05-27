package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;

public class GestorePartita implements Runnable {

	Partita partita;
	
	public GestorePartita(Partita partita) {
		this.partita = partita;
	}

	public void run() {
		inizializzaPartita();

	}

	private void inizializzaPartita() {
		//distribuisci denari, disponi pecore e pecora nera e lupo
	}

}

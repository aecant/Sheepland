package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import java.util.Vector;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Partita;

public class GestorePartita implements Runnable {

	Vector<String> nomiGiocatori;
	Partita partita;

	public GestorePartita(Vector<String> nomiGiocatori) {
		this.nomiGiocatori = nomiGiocatori;
		partita = new Partita();
		for (String nome : nomiGiocatori)
			partita.aggiungiGiocatore(nome);
	}

	public void run() {
		inizializzaPartita();

	}

	private void inizializzaPartita() {
		//distribuisci denari, disponi pecore e pecora nera e lupo
	}

}

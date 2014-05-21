package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;

public abstract class Mossa implements Evento {
	private static final long serialVersionUID = 1032317303850055693L;
	private Giocatore giocatore;

	public Mossa(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public abstract void aggiornaDati();

	public abstract void visualizza();

}

package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Pecora;

public class Abbattimento extends Mossa {

	private static final long serialVersionUID = 1L;
	private Pecora pecora;

	public Abbattimento(Giocatore giocatore, Pecora pecora) {
		super(giocatore);
		this.pecora = pecora;
	}
	
	public Pecora getPecora() {
		return pecora;
	}
}

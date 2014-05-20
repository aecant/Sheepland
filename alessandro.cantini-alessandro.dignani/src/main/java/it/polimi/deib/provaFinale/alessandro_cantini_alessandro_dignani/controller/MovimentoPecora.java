package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Pecora;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Territorio;

public class MovimentoPecora extends Mossa {
	private static final long serialVersionUID = -5815440396346581844L;
	private Pecora pecora;
	private Territorio origine, destinazione;
	
	public MovimentoPecora(Giocatore giocatore, Pecora pecora, Territorio origine, Territorio destinazione) {
		super(giocatore);
		this.pecora = pecora;
		this.origine = origine;
		this.destinazione = destinazione;
	}

	public Pecora getPecora() {
		return pecora;
	}

	public Territorio getOrigine() {
		return origine;
	}

	public Territorio getDestinazione() {
		return destinazione;
	}

	
}

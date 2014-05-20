package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Pastore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Strada;

public class MovimentoPastore extends Mossa{

	private static final long serialVersionUID = -8968808141497412233L;
	private Pastore pastore;
	private Strada origine, destinazione;
	
	public MovimentoPastore(Giocatore giocatore, Pastore pastore, Strada origine, Strada destinazione) {
		super(giocatore);
		this.pastore = pastore;
		this.origine = origine;
		this.destinazione = destinazione;
	}

	public Pastore getPastore() {
		return pastore;
	}

	public Strada getOrigine() {
		return origine;
	}

	public Strada getDestinazione() {
		return destinazione;
	}
	
}

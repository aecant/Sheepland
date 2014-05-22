package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.eventi;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Territorio;

public abstract class MovimentoAnimale implements Evento {

	private static final long serialVersionUID = 3263700493723371773L;
	private Territorio origine, destinazione;
	
	public MovimentoAnimale(Territorio origine, Territorio destinazione) {
		this.origine = origine;
		this.destinazione = destinazione;
	}

	
	public Territorio getOrigine() {
		return origine;
	}

	public Territorio getDestinazione() {
		return destinazione;
	}

	public abstract void aggiornaDati();

	public abstract void visualizza();
	
}

package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;

public class InizioTurno extends Mossa {

	private static final long serialVersionUID = 1L;

	public InizioTurno(Giocatore giocatore) {
		super(giocatore);
	}

	@Override
	public void aggiornaDati() {
		Client.getDatiPartita().setGiocatoreDiTurno(super.getGiocatore());
	}

	@Override
	public void visualizza() {
		// TODO Auto-generated method stub
		
	}

}

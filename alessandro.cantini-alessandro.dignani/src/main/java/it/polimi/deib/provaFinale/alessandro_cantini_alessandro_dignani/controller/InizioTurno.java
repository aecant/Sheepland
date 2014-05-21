package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

public class InizioTurno extends Mossa {

	private static final long serialVersionUID = 1L;

	public InizioTurno(String giocatore) {
		super(giocatore);
	}

	@Override
	public void aggiornaDati() {
		Client.getDatiPartita().setGiocatoreDiTurno(super.getGiocatore());
	}

	@Override
	public void visualizza() {
		Client.getUI().inizioTurno(super.getGiocatore());
	}

}

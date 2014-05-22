package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.eventi;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.ClientMain;

public class InizioTurno extends Mossa {

	private static final long serialVersionUID = -1669276038393313888L;

	public InizioTurno(String giocatore) {
		super(giocatore);
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().setGiocatoreDiTurno(super.getGiocatore());
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().inizioTurno(super.getGiocatore());
	}

}

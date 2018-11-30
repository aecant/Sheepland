package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

public class InizioTurno extends Mossa {

	private static final long serialVersionUID = -1669276038393313888L;

	public InizioTurno(String giocatore) {
		super(giocatore);
	}

	@Override
	public void aggiornaDati() {
		MainClient.getDatiPartita().setGiocatoreDiTurno(giocatore);
	}

	@Override
	public void visualizza() {
		MainClient.getUI().inizioTurno(giocatore);
	}

	@Override
	public String toString() {
		return "Inizia il turno di " + giocatore;
	}
}

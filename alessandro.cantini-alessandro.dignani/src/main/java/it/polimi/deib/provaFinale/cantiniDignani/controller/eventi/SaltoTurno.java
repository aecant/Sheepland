package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

public class SaltoTurno extends Mossa{

	private static final long serialVersionUID = -123797397179001337L;

	public SaltoTurno(String giocatore) {
		super(giocatore);
	}

	@Override
	public void aggiornaDati() {
		// non ci sono dati da aggiornare
	}

	@Override
	public void visualizza() {
		MainClient.getUI().saltoTurno(giocatore);
	}

}

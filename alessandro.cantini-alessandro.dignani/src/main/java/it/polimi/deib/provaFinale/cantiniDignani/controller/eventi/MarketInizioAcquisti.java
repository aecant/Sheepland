package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

public class MarketInizioAcquisti implements Evento {

	private static final long serialVersionUID = 8017364591320769720L;

	public void aggiornaDati() {
		// non ci sono dati da aggiornare
	}

	public void visualizza() {
		MainClient.getUI().marketInizioAcquisti();
	}

}

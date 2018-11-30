package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

public class MarketInizio implements Evento {

	private static final long serialVersionUID = -8563644895579380609L;

	public void aggiornaDati() {
		// non ci sono dati da aggiornare
	}

	public void visualizza() {
		MainClient.getUI().marketInizio();
	}

}

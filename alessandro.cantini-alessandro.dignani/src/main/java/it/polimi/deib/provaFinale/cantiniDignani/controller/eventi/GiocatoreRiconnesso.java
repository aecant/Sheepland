package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;


public class GiocatoreRiconnesso extends EventoDisconnessione {

	private static final long serialVersionUID = -7623790267022856378L;

	private final String giocatore;
	
	public GiocatoreRiconnesso(String giocatore) {
		this.giocatore = giocatore;
	}

	@Override
	public void visualizza() {
		MainClient.getUI().giocatoreRiconnesso(giocatore);
	}

}

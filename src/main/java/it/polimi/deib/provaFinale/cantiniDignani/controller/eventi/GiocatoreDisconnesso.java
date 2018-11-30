package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

public class GiocatoreDisconnesso extends EventoDisconnessione {
	private static final long serialVersionUID = -3680410443648775120L;

	private final String giocatore;

	public GiocatoreDisconnesso(String giocatore) {
		this.giocatore = giocatore;
	}

	@Override
	public void visualizza() {
		MainClient.getUI().disconnessioneGiocatore(giocatore);
	}

}

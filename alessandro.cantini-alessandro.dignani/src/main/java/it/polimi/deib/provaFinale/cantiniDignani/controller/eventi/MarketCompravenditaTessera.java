package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.TesseraInVendita;

public class MarketCompravenditaTessera extends Mossa {

	private static final long serialVersionUID = 7796921437581648780L;

	private final TesseraInVendita tessera;

	public MarketCompravenditaTessera(String giocatore, TesseraInVendita tessera) {
		super(giocatore);
		this.tessera = tessera;
	}

	@Override
	public void aggiornaDati() {
		;
		// i dati dei giocatori vengono aggiornati con l'invio delle'evento
		// del pagamento
	}

	@Override
	public void visualizza() {
		MainClient.getUI().marketCompravenditaTessera(giocatore, tessera);
	}

}

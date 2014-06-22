package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.TesseraInVendita;

public class MarketMessaInVendita implements Evento {
	
	private static final long serialVersionUID = -6514209873833163479L;
	
	private TesseraInVendita tessera;

	public MarketMessaInVendita(TesseraInVendita tessera) {
		this.tessera = tessera;
	}

	public void aggiornaDati() {
		;
	}

	public void visualizza() {
		MainClient.getUI().marketMessaInVendita(tessera);
	}
	
}

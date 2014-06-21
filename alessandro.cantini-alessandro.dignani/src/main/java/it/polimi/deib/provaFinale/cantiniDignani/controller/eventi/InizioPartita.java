package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;

public class InizioPartita implements Evento {
	
	private static final long serialVersionUID = -5582597000875121887L;

	private DatiPartita datiDaAggiornare;
	private boolean riconnessione;

	public InizioPartita(DatiPartita datiDaAggiornare, boolean riconnessione) {
		this.datiDaAggiornare = datiDaAggiornare;
		this.riconnessione = riconnessione;
	}

	public InizioPartita(DatiPartita datiDaAggiornare) {
		this(datiDaAggiornare, false);
	}
	
	public void aggiornaDati() {
		MainClient.setDatiPartita(datiDaAggiornare);
	}

	public void visualizza() {
		MainClient.getUI().inizioPartita(riconnessione);
	}

	@Override
	public String toString() {
		return "Inizio partita";
	}

}

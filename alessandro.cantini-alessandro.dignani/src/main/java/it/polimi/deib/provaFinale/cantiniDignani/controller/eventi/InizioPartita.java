package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;

public class InizioPartita implements Evento {
	private static final long serialVersionUID = -5582597000875121887L;

	private DatiPartita datiDaAggiornare;

	public InizioPartita(DatiPartita datiDaAggiornare) {
		this.datiDaAggiornare = datiDaAggiornare;
	}

	public void aggiornaDati() {
		MainClient.setDatiPartita(datiDaAggiornare);
	}

	public void visualizza() {
		MainClient.getUI().inizioPartita();
	}

	@Override
	public String toString() {
		return "Inizio partita";
	}

}

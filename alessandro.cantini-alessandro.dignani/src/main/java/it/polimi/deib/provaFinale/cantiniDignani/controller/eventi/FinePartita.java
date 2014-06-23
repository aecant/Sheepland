package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

import java.util.Map;

public class FinePartita implements Evento {
	private static final long serialVersionUID = 3143410847718044022L;

	private final Map<String, Integer> punteggio;

	public FinePartita(Map<String, Integer> punteggio) {
		this.punteggio = punteggio;
	}


	public void aggiornaDati() {
		// non ci sono dati da aggiornare
	}

	
	public void visualizza() {
		MainClient.getUI().finePartita(punteggio);
	}

}

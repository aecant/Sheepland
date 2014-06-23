package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

/**
 * Rappresenta un particolare tipo di evento che presuppone una risposta da
 * parte del client
 */
public abstract class Richiesta implements Evento {

	private static final long serialVersionUID = 8125665824548264902L;

	public final void aggiornaDati() {
	}

	public final void visualizza() {
		int risposta = interagisci();
		MainClient.getConnessione().inviaMossa(risposta);
	}

	protected abstract int interagisci();
}

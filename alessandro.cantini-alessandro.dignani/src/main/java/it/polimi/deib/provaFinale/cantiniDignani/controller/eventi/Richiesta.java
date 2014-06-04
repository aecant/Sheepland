package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;

/**
 * Rappresenta un particolare tipo di evento che presuppone una risposta da parte del client.
 *
 */

public abstract class Richiesta implements Evento {

	private static final long serialVersionUID = 8125665824548264902L;

	final public void aggiornaDati() {
		;
	}

	final public void visualizza() {
		Mossa risposta = interagisci();
		ClientMain.getConnessione().inviaMossa(risposta);
	}

	abstract protected Mossa interagisci();
}

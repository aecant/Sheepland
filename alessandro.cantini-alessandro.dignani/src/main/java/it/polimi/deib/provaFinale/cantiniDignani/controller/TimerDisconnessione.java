package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;

public class TimerDisconnessione extends Timer {

	private GestorePartita gestore;

	public TimerDisconnessione(long millisecondi, GestorePartita gestore) {
		super(millisecondi);
		this.gestore = gestore;
	}

	/**
	 * Notifica il pastore passato come parametro
	 */
	@Override
	public void agisci() {
		synchronized (gestore) {
			gestore.notify();
		}
	}

}

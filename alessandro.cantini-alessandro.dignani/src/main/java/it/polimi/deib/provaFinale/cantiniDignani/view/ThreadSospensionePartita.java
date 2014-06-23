package it.polimi.deib.provaFinale.cantiniDignani.view;

import it.polimi.deib.provaFinale.cantiniDignani.controller.CostantiController;

public abstract class ThreadSospensionePartita extends Thread {

	protected final String giocatore;
	private boolean on = true;

	public ThreadSospensionePartita(String giocatore) {
		this.giocatore = giocatore;
	}

	public void run() {
		for (int i = CostantiController.SECONDI_INTERRUZIONE_DISCONNESSIONE; i >= 0 && on; i--) {
			visualizzaMessaggio(i);
		}
	}

	protected abstract void visualizzaMessaggio(int secondo);

	public void ferma() {
		on = false;
	}
}

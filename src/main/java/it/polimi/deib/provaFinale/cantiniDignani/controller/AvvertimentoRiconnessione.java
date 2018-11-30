package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.InizioPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AvvertimentoRiconnessione extends Thread {

	private static final Logger LOGGER = Logger.getLogger(AvvertimentoRiconnessione.class.getName());

	private GestorePartita gestore;
	private Utente utente;

	public AvvertimentoRiconnessione(GestorePartita gestore, Utente utente) {
		this.gestore = gestore;
		this.utente = utente;
	}

	/**
	 * Aspetta un tempo sufficiente per permettere al giocatore riconnesso di
	 * completare la registrazione, invia all'utente l'inizio della partita e
	 * notifica il gestore partita che aspettava la riconnessione
	 */
	@Override
	public void run() {
		try {
			// lascia il tempo di completare la registrazione
			sleep(300);
			utente.inviaEvento(new InizioPartita(Estrattore.datiPartita(gestore.getPartita()), true));

			synchronized (gestore) {
				gestore.notify();
			}

		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, "interruzione nell'avvertimento dei giocatori", e);
		}

	}

}

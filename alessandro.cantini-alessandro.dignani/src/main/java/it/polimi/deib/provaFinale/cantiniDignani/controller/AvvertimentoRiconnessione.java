package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.InizioPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AvvertimentoRiconnessione extends Thread {

	private GestorePartita gestore;
	private Utente utente;

	public AvvertimentoRiconnessione(GestorePartita gestore, Utente utente) {
		this.gestore = gestore;
		this.utente = utente;
	}

	@Override
	public void run() {
		try {
			sleep(300); // lascia il tempo di completare la registrazione
			utente.inviaEvento(new InizioPartita(Estrattore.datiPartita(gestore.getPartita()), true));
		} catch (InterruptedException e) {
			Logger.getGlobal().log(Level.SEVERE, "interruzione nell'avvertimento dei giocatori", e);
		}

	}

}

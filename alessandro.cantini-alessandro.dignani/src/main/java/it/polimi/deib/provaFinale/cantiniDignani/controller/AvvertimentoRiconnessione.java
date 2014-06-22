package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.GiocatoreRiconnesso;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.InizioPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;

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
			gestore.inviaEventoATutti(new GiocatoreRiconnesso(utente.getNome()));
			utente.inviaEvento(new InizioPartita(Estrattore.datiPartita(gestore.getPartita())));
		} catch (InterruptedException e) {
			Logger.getGlobal().log(Level.SEVERE, "interruzione nell'avvertimento dei giocatori", e);
		}

	}

}

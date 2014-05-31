package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.ArrayList;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.InizioPartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaServer;

public class GestorePartita implements Runnable {

	private final Partita partita;
	private final ArrayList<String> nomiGiocatori;
	private InterfacciaServer connessione;

	public GestorePartita(Partita partita, InterfacciaServer connessione) {
		this.partita = partita;
		this.connessione = connessione;
		nomiGiocatori = new ArrayList<String>();
		for (Giocatore g : partita.getGiocatori()) {
			nomiGiocatori.add(g.getNome());
		}
	}

	public void run() {
		inizializzaPartita();
		connessione.inviaEvento(new InizioPartita(), nomiGiocatori);
	}

	private void inizializzaPartita() {
		distribuisciDenari();
		disponiPecore();
	}

	private void distribuisciDenari() {
		for (Giocatore g : partita.getGiocatori()) {
			g.aggiungiDenaro(Costanti.DENARO_INIZIALE);
		}
	}

	private void disponiPecore() {
		for (Territorio t : Mappa.getMappa().getTerritori()) {
			partita.getGregge().aggiungi(Sorte.pecoraCasuale(t));
		}
	}

}

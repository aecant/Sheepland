package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.InizioPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaPosizionePastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
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
		iniziaPartita();
		selezionePosizioneIniziale();
		
	}

	private void selezionePosizioneIniziale() {
		for(Giocatore g : partita.getGiocatori()) {
			int[] stradeLibere = Estrattore.stradeLibere();
			connessione.inviaEvento(new RichiestaPosizionePastore(stradeLibere), g.getNome());
		}
	}

	/**
	 * Gestisce la fase iniziale della partita
	 */
	private void iniziaPartita() {
		distribuisciDenari();
		disponiPecore();
		disponiTessereIniziali();
		connessione.inviaEvento(new InizioPartita(), nomiGiocatori);
	}

	/**
	 * Crea uno stack di tessere, di tutti i tipi a parte Sheepsburg, lo mescola
	 * e distribuisce una tessera per ogni giocatore
	 */
	private void disponiTessereIniziali() {
		Stack<Tessera> territoriIniziali = new Stack<Tessera>();
		for (TipoTerritorio tipo : TipoTerritorio.values()) {
			if (tipo != TipoTerritorio.SHEEPSBURG) {
				territoriIniziali.push(new Tessera(tipo, 0));
			}
		}
		
		Collections.shuffle(territoriIniziali);
		
		for (Giocatore g : partita.getGiocatori()) {
			g.aggiungiTessera(territoriIniziali.pop());
		}
	}

	/**
	 * Distribuisce la somma di denaro iniziale a ogni giocatore
	 */
	private void distribuisciDenari() {
		for (Giocatore g : partita.getGiocatori()) {
			g.aggiungiDenaro(Costanti.DENARO_INIZIALE);
		}
	}

	/**
	 * Dispone una pecora, un montone o un agnello per ciascun territorio. 
	 */
	private void disponiPecore() {
		for (Territorio t : Mappa.getMappa().getTerritori()) {
			partita.getGregge().aggiungi(Sorte.pecoraCasuale(t));
		}
	}

}

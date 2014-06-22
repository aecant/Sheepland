package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Estrattore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.InizioPartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.util.Collections;
import java.util.Stack;
import java.util.logging.Logger;

public class PreparazionePartita extends FasePartita {

	private final static Logger LOGGER = Logger.getLogger(PreparazionePartita.class.getName());
	
	public PreparazionePartita(GestorePartita gestore) {
		super(gestore);
	}

	@Override
	public void esegui() {
		distribuisciDenari();
		disponiPecore();
		disponiTessereIniziali();
		gestore.inviaEventoATutti(new InizioPartita(Estrattore.datiPartita(partita)));
		LOGGER.info("Partita iniziata con i giocatori: " + gestore.getTuttiGiocatori());
	}

	/**
	 * Crea uno stack di tessere, di tutti i tipi a parte Sheepsburg, lo mescola
	 * e distribuisce una tessera per ogni giocatore
	 */
	public void disponiTessereIniziali() {
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
	public void distribuisciDenari() {
		for (Giocatore g : partita.getGiocatori()) {
			g.aggiungiDenaro(gestore.denaroIniziale);
		}
	}

	/**
	 * Dispone una pecora, un montone o un agnello per ciascun territorio.
	 */
	public void disponiPecore() {
		for (Territorio t : Mappa.getMappa().getTerritori()) {
			if (t.getTipo() != TipoTerritorio.SHEEPSBURG) {
				partita.getGregge().aggiungi(Estrattore.pecoraRandom(t));
			}
		}
	}

}

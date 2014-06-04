package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.InizioPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.InizioTurno;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.LancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoLupo;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPecoraNera;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.PosizionamentoPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaPosizionePastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaTipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SceltaPastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.ColorePastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaServer;

public class GestorePartita implements Runnable {

	private final Partita partita;
	private final ArrayList<String> nomiGiocatori;
	private final InterfacciaServer connessione;
	private int contColorePastore = 0;
	private boolean dueGiocatori;
	private final int denaroIniziale;

	public GestorePartita(Partita partita, InterfacciaServer connessione) {
		this.partita = partita;
		this.connessione = connessione;
		if (partita.getGiocatori().size() == 2) {
			dueGiocatori = true;
			denaroIniziale = Costanti.DENARO_INIZIALE_DUE_GIOCATORI;
		} else {
			dueGiocatori = false;
			denaroIniziale = Costanti.DENARO_INIZIALE;
		}
		nomiGiocatori = new ArrayList<String>();
		for (Giocatore g : partita.getGiocatori()) {
			nomiGiocatori.add(g.getNome());
		}
	}

	public void run() {
		iniziaPartita();
		selezionePosizioneIniziale();
		gestioneGiro();
	}

	private void gestioneGiro() {
		while (!recintiInizialiFiniti()) {
			for (Giocatore g : partita.getGiocatori()) {
				movimentoPecoraNera();
				gestioneTurno(g);
				movimentoLupo();
				market();
			}
		}
	}

	private void gestioneTurno(Giocatore giocatore) {
		TipoMossa mossaPrecedente = null;
		boolean pastoreMosso = false;
		Pastore pastoreCorrente;
		
		partita.setGiocatoreDiTurno(giocatore);

		connessione.inviaEvento(new InizioTurno(giocatore.getNome()), nomiGiocatori);

		if (dueGiocatori) {
			connessione.inviaEvento(new RichiestaPastore(), giocatore.getNome());
			SceltaPastore scelta = (SceltaPastore) ServerMain.getGestoreEventi().aspettaEvento(SceltaPastore.class);
			pastoreCorrente = scelta.getPastore();
		} else {
			pastoreCorrente = giocatore.getPastori().get(0);
		}

		for (int numMossa = 0; numMossa < 3; numMossa++) {
			Set<TipoMossa> mosseDisponibili = new HashSet<TipoMossa>();
			mosseDisponibili.add(TipoMossa.MUOVIPASTORE);

			// se uno dei due territori ha almeno una pecora aggiungo la mossa
			if (mossaPrecedente != TipoMossa.MUOVIPECORA
					&& (partita.territorioLibero(pastoreCorrente.getStrada().getTerritorio1()) || partita.territorioLibero(pastoreCorrente.getStrada().getTerritorio2()))) {
				mosseDisponibili.add(TipoMossa.MUOVIPECORA);
			}

			if (mossaPrecedente != TipoMossa.COMPRATESSERA
					&& (siPuoAcquistareTessera(pastoreCorrente.getStrada().getTerritorio1(), giocatore.getDenaro()) || siPuoAcquistareTessera(pastoreCorrente.getStrada().getTerritorio2(),
							giocatore.getDenaro()))) {
				mosseDisponibili.add(TipoMossa.COMPRATESSERA);
			}
			
			if (mossaPrecedente != TipoMossa.ABBATTI) {
				//TODO
			}
			
			connessione.inviaEvento(new RichiestaTipoMossa(mosseDisponibili), giocatore.getNome());

		}
	}

	private boolean siPuoAcquistareTessera(Territorio terr, int denaroGiocatore) {
		if (terr.getTipo() == TipoTerritorio.SHEEPSBURG) {
			return false;
		}
		if (partita.getMazzo().getTessereRimaste(terr.getTipo()) == 0) {
			return false;
		}
		if (partita.getMazzo().leggiTesseraInCima(terr.getTipo()).getCosto() > denaroGiocatore) {
			return false;
		}

		return true;
	}

	private void movimentoPecoraNera() {
		int lancio = lancioDado();

		Territorio origine = partita.getGregge().getPecoraNera().getPosizione();
		Territorio destinazione = Mappa.getMappa().transizione(origine, lancio);
		if (movimentoPossibile(origine, lancio)) {
			partita.getGregge().getPecoraNera().muoviIn(destinazione);
			connessione.inviaEvento(new MovimentoPecoraNera(origine.getCodice(), destinazione.getCodice()), nomiGiocatori);
		}
	}

	private int lancioDado() {
		int lancio = Sorte.lanciaDado();
		connessione.inviaEvento(new LancioDado(lancio), nomiGiocatori);
		return lancio;
	}

	private void movimentoLupo() {
		boolean tutteStradeOccupate = true;

		int lancio = lancioDado();

		Territorio origine = partita.getLupo().getPosizione();
		Territorio destinazione = Mappa.getMappa().transizione(origine, lancio);

		for (int i = 0; i < 5; i++) {
			if (movimentoPossibile(origine, i)) {
				tutteStradeOccupate = false;
				break;
			}
		}

		if (movimentoPossibile(origine, lancio) || tutteStradeOccupate) {
			partita.getLupo().muoviIn(destinazione);
			connessione.inviaEvento(new MovimentoLupo(origine.getCodice(), destinazione.getCodice()), nomiGiocatori);
		}
	}

	private boolean movimentoPossibile(Territorio origine, int dado) {
		Territorio destinazione = Mappa.getMappa().transizione(origine, dado);
		if (!origine.equals(destinazione) && partita.stradaLibera(new Strada(origine, destinazione))) {
			return true;
		}
		return false;
	}

	private void market() {
		// TODO Auto-generated method stub

	}

	private boolean recintiInizialiFiniti() {
		return partita.getRecinti().getRecintiIniziali().size() == Costanti.NUM_RECINTI_INIZIALI;
	}

	private void selezionePosizioneIniziale() {
		giroPosizionamentoPastore();
		if (dueGiocatori) {
			contColorePastore = 0;
			giroPosizionamentoPastore();
		}
	}

	private void giroPosizionamentoPastore() {
		for (Giocatore g : partita.getGiocatori()) {
			int[] stradeLibere = Estrattore.stradeLibere();
			connessione.inviaEvento(new RichiestaPosizionePastore(stradeLibere), g.getNome());
			PosizionamentoPastore risposta = (PosizionamentoPastore) ServerMain.getGestoreEventi().aspettaEvento(PosizionamentoPastore.class);

			Strada strada = Mappa.getMappa().getStrade()[risposta.getStrada()];
			ColorePastore colore = ColorePastore.values()[contColorePastore++];
			g.aggiungiPastore(new Pastore(strada, colore));

			connessione.inviaEvento(new PosizionamentoPastore(g.getNome(), risposta.getStrada()), nomiGiocatori);
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
			g.aggiungiDenaro(denaroIniziale);
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

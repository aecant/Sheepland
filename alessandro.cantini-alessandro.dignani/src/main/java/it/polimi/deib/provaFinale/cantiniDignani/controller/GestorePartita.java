package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.*;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaServer;

public class GestorePartita implements Runnable {

	private final Partita partita;
	private List<String> tuttiGiocatori;
	private final InterfacciaServer connessione;
	private final GestoreMossa gestoreMossa;
	private final GestoreEventi gestoreEventi;

	private int contColorePastore = 0;
	private boolean dueGiocatori;
	private final int denaroIniziale;

	public GestorePartita(Partita partita, InterfacciaServer connessione, GestoreEventi gestoreEventi) {
		this.partita = partita;
		this.connessione = connessione;
		this.gestoreEventi = gestoreEventi;
		if (partita.getGiocatori().size() == 2) {
			dueGiocatori = true;
			denaroIniziale = Costanti.DENARO_INIZIALE_DUE_GIOCATORI;
		} else {
			dueGiocatori = false;
			denaroIniziale = Costanti.DENARO_INIZIALE;
		}
		tuttiGiocatori = new ArrayList<String>();
		for (Giocatore g : partita.getGiocatori()) {
			tuttiGiocatori.add(g.getNome());
		}
		gestoreMossa = new GestoreMossa(this);

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
		Pastore pastore;

		partita.setGiocatoreDiTurno(giocatore);

		inviaEventoATutti(new InizioTurno(giocatore.getNome()));

		if (dueGiocatori) {
			inviaEvento(new RichiestaPastore(), giocatore);
			SceltaPastore scelta = (SceltaPastore) gestoreEventi.aspettaEvento(SceltaPastore.class);
			pastore = scelta.getPastore();
		} else {
			pastore = giocatore.getPastori().get(0);
		}

		for (int numMossa = 1; numMossa <= Costanti.NUM_MOSSE; numMossa++) {
			Set<TipoMossa> mosseDisponibili = gestoreMossa.creaMosseDisponibili(numMossa, pastoreMosso, mossaPrecedente, pastore, giocatore.getDenaro());

			inviaEvento(new RichiestaTipoMossa(mosseDisponibili), giocatore);

			TipoMossa tipoMossa = ((SceltaMossa) gestoreEventi.aspettaEvento(SceltaMossa.class)).getMossa();

			gestoreMossa.effettuaMossa(pastore, tipoMossa);
		}
	}

	private void movimentoPecoraNera() {
		int lancio = lanciaDado();

		Territorio origine = partita.getGregge().getPecoraNera().getPosizione();
		Territorio destinazione = Mappa.getMappa().transizione(origine, lancio);
		if (movimentoPossibile(origine, lancio)) {
			partita.getGregge().getPecoraNera().muoviIn(destinazione);
			connessione.inviaEvento(new MovimentoPecoraNera(origine.getCodice(), destinazione.getCodice()), tuttiGiocatori);
		}
	}

	public int lanciaDado() {
		int lancio = Sorte.lanciaDado();
		connessione.inviaEvento(new LancioDado(lancio), tuttiGiocatori);
		return lancio;
	}

	private void movimentoLupo() {
		boolean tutteStradeOccupate = true;

		int lancio = lanciaDado();

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
			connessione.inviaEvento(new MovimentoLupo(origine.getCodice(), destinazione.getCodice()), tuttiGiocatori);
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
			boolean[] stradeLibere = Estrattore.stradeLibere(partita);
			connessione.inviaEvento(new RichiestaPosizioneInizialePastore(stradeLibere), g.getNome());
			PosizionamentoPastore risposta = (PosizionamentoPastore) gestoreEventi.aspettaEvento(PosizionamentoPastore.class);

			Strada strada = Mappa.getMappa().getStrade()[risposta.getStrada()];
			ColorePastore colore = ColorePastore.values()[contColorePastore++];
			g.aggiungiPastore(new Pastore(strada, colore));

			connessione.inviaEvento(new PosizionamentoPastore(g.getNome(), risposta.getStrada()), tuttiGiocatori);
		}
	}

	/**
	 * Gestisce la fase iniziale della partita
	 */
	private void iniziaPartita() {
		distribuisciDenari();
		disponiPecore();
		disponiTessereIniziali();
		connessione.inviaEvento(new InizioPartita(), tuttiGiocatori);
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
			partita.getGregge().aggiungi(Sorte.pecoraRandom(t));
		}
	}

	protected void inviaEventoATutti(Evento e) {
		connessione.inviaEvento(e, tuttiGiocatori);
	}

	protected void inviaEvento(Evento e, Giocatore g) {
		connessione.inviaEvento(e, g.getNome());
	}

	protected GestoreEventi getGestoreEventi() {
		return gestoreEventi;
	}

	protected Partita getPartita() {
		return partita;
	}

	protected InterfacciaServer getConnessione() {
		return connessione;
	}

	protected void pagamento(int somma, Giocatore pagante, Giocatore ricevente) {
		pagante.sottraiDenaro(somma);
		ricevente.aggiungiDenaro(somma);
	}

}

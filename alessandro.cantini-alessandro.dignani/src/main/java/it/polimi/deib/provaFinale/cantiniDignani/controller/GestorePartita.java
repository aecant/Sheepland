package it.polimi.deib.provaFinale.cantiniDignani.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.*;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaServer;

public class GestorePartita implements Runnable {

	private final Partita partita;
	private final ArrayList<String> tutti;
	private final InterfacciaServer connessione;
	private int contColorePastore = 0;
	private boolean dueGiocatori;
	private final int denaroIniziale;
	private GestoreEventi gestoreEventi;

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
		tutti = new ArrayList<String>();
		for (Giocatore g : partita.getGiocatori()) {
			tutti.add(g.getNome());
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
		Pastore pastore;

		partita.setGiocatoreDiTurno(giocatore);

		connessione.inviaEvento(new InizioTurno(giocatore.getNome()), tutti);

		if (dueGiocatori) {
			connessione.inviaEvento(new RichiestaPastore(), giocatore.getNome());
			SceltaPastore scelta = (SceltaPastore) gestoreEventi.aspettaEvento(SceltaPastore.class);
			pastore = scelta.getPastore();
		} else {
			pastore = giocatore.getPastori().get(0);
		}

		for (int numMossa = 1; numMossa <= Costanti.NUM_MOSSE_DISPONIBILI; numMossa++) {
			Set<TipoMossa> mosseDisponibili = creaMosseDisponibili(numMossa, pastoreMosso, mossaPrecedente, pastore, giocatore.getDenaro());

			connessione.inviaEvento(new RichiestaTipoMossa(mosseDisponibili), giocatore.getNome());

			TipoMossa tipoMossa = ((SceltaMossa) gestoreEventi.aspettaEvento(SceltaMossa.class)).getMossa();

			DatiTerritorio[] dati = Estrattore.datiTerritori(partita);
			int codT1 = pastore.getStrada().getTerritorio1().getCodice();
			int codT2 = pastore.getStrada().getTerritorio2().getCodice();
			Set<TipoOvino> oviniT1 = dati[codT1].getTipiOvino();
			Set<TipoOvino> oviniT2 = dati[codT2].getTipiOvino();

			switch (tipoMossa) {

			case MUOVIPASTORE: {
				boolean[] stradeGratis = Estrattore.stradeLibereGratis(partita, pastore.getStrada());
				boolean[] stradeAPagamento = Estrattore.stradeLibereGratis(partita, pastore.getStrada());
				connessione.inviaEvento(new RichiestaPosizionePastore(stradeGratis, stradeAPagamento), giocatore.getNome());

				MovimentoPastore movimento = (MovimentoPastore) gestoreEventi.aspettaEvento(MovimentoPastore.class);
				Strada origine = Mappa.getMappa().getStrade()[movimento.getOrigine()];
				Strada destinazione = Mappa.getMappa().getStrade()[movimento.getDestinazione()];

				pastore.muoviIn(destinazione);
				partita.getRecinti().aggiungi(origine);

				connessione.inviaEvento(movimento, tutti);

				break;
			}
			case MUOVIPECORA: {
				connessione.inviaEvento(new RichiestaPecoraDaMuovere(codT1, oviniT1, codT2, oviniT2), giocatore.getNome());

				MovimentoPecora movimento = (MovimentoPecora) gestoreEventi.aspettaEvento(MovimentoPecora.class);
				Territorio destinazione = Mappa.getMappa().getTerritori()[movimento.getDestinazione()];

				if (movimento.getTipoOvino() == TipoOvino.PECORANERA) {
					partita.getGregge().getPecoraNera().muoviIn(destinazione);
				} else {
					Estrattore.getPecora(partita, movimento.getOrigine(), movimento.getTipoOvino()).muoviIn(destinazione);
				}

				break;
			}

			case ABBATTI: {
				oviniT1.remove(TipoOvino.PECORANERA);
				oviniT2.remove(TipoOvino.PECORANERA);
				connessione.inviaEvento(new RichiestaPecoraDaAbbattere(codT1, oviniT1, codT2, oviniT2), giocatore.getNome());

				Abbattimento abbattimento = (Abbattimento) gestoreEventi.aspettaEvento(Abbattimento.class);

				int lancio = lanciaDado();

				boolean aBuonFine = (lancio == Mappa.getMappa().getDado(pastore.getStrada()));

				if (aBuonFine) {
					Pecora daAbbattere = Estrattore.getPecora(partita, abbattimento.getTerritorio(), abbattimento.getTipoOvino());
					partita.getGregge().rimuovi(daAbbattere);

					for (Pastore pastoreVicino : partita.getPastori()) {
						if (Mappa.getMappa().sonoContigue(pastore.getStrada(), pastoreVicino.getStrada()) && !pastoreVicino.getColore().equals(pastore.getColore())) {
							lancio = lanciaDado();
							if (lancio >= Costanti.DADO_MIN_PER_SILENZIO) {
								int somma = Costanti.COSTO_SILENZIO;
								Giocatore ricevente = partita.getGiocatore(pastoreVicino);
								pagamento(somma, giocatore, ricevente);
								connessione.inviaEvento(new Pagamento(somma, giocatore.getNome(), ricevente.getNome()), tutti);
							}
						}
					}
				}

				connessione.inviaEvento(new Abbattimento(abbattimento, aBuonFine), tutti);

				// TODO gestire il fatto che si compra il silenzio

				break;
			}
			case ACCOPPIA:
				break;
			case COMPRATESSERA:
				break;

			}
		}
	}

	/**
	 * Restituisce un set di mosse disponibili
	 * 
	 * @param numMossa
	 *            il numero della mossa (la prima, la seconda o la terza)
	 * @param pastoreMosso
	 *            se il pastore e' gia' stato mosso
	 * @param mossaPrecedente
	 *            la mossa compiuta prima
	 * @param pastoreCorrente
	 *            il pastore con cui effettuare la mossa
	 * @param denaroDisponibile
	 *            il denaro del giocatore
	 * @return il set di mosse disponibili
	 */
	private Set<TipoMossa> creaMosseDisponibili(int numMossa, boolean pastoreMosso, TipoMossa mossaPrecedente, Pastore pastoreCorrente, int denaroDisponibile) {
		Set<TipoMossa> mosseDisponibili = new HashSet<TipoMossa>();
		boolean entrambiTerritoriLiberi = partita.territorioLibero(pastoreCorrente.getStrada().getTerritorio1()) && partita.territorioLibero(pastoreCorrente.getStrada().getTerritorio2());

		mosseDisponibili.add(TipoMossa.MUOVIPASTORE);

		// se e' la terza mossa e il giocatore non ha ancora mosso il
		// pastore, si puo' solo muovere il pastore
		if (numMossa == 3 && pastoreMosso == false) {
			return mosseDisponibili;
		}

		// se uno dei due territori ha almeno una pecora
		if (mossaPrecedente != TipoMossa.MUOVIPECORA && !entrambiTerritoriLiberi) {
			mosseDisponibili.add(TipoMossa.MUOVIPECORA);
		}

		// se il giocatore ha abbastanza soldi per comprare una delle due
		// tessere
		if (mossaPrecedente != TipoMossa.COMPRATESSERA
				&& (siPuoAcquistareTessera(pastoreCorrente.getStrada().getTerritorio1(), denaroDisponibile) || siPuoAcquistareTessera(pastoreCorrente.getStrada().getTerritorio2(), denaroDisponibile))) {
			mosseDisponibili.add(TipoMossa.COMPRATESSERA);
		}

		// se almento uno dei due territorio vicini contiene sia una pecora
		// che un montone
		if (mossaPrecedente != TipoMossa.ACCOPPIA) {
			DatiTerritorio[] dati = Estrattore.datiTerritori(partita);
			if (contieneSiaPecoraCheMontone(dati[pastoreCorrente.getStrada().getTerritorio1().getCodice()])
					|| contieneSiaPecoraCheMontone(dati[pastoreCorrente.getStrada().getTerritorio2().getCodice()])) {
				mosseDisponibili.add(TipoMossa.ACCOPPIA);
			}
		}

		// se c'e' almeno una pecora da abbattere e il giocatore ha
		// abbastanza soldi per pagare il silenzio dei pastori vicini
		if (mossaPrecedente != TipoMossa.ABBATTI && !entrambiTerritoriLiberi) {
			int denaroDovuto = 0;
			for (Pastore past : partita.getPastori()) {
				if (!past.getColore().equals(pastoreCorrente.getColore()) && Mappa.getMappa().sonoContigue(past.getStrada(), pastoreCorrente.getStrada())) {
					denaroDovuto += 2;
				}
			}
			if (denaroDovuto <= denaroDisponibile) {
				mosseDisponibili.add(TipoMossa.ABBATTI);
			}

		}
		return mosseDisponibili;
	}

	private boolean contieneSiaPecoraCheMontone(DatiTerritorio dati) {
		if (dati.getNumMontoni() > 0 && dati.getNumPecore() > 0) {
			return true;
		}
		return false;
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
		int lancio = lanciaDado();

		Territorio origine = partita.getGregge().getPecoraNera().getPosizione();
		Territorio destinazione = Mappa.getMappa().transizione(origine, lancio);
		if (movimentoPossibile(origine, lancio)) {
			partita.getGregge().getPecoraNera().muoviIn(destinazione);
			connessione.inviaEvento(new MovimentoPecoraNera(origine.getCodice(), destinazione.getCodice()), tutti);
		}
	}

	private int lanciaDado() {
		int lancio = Sorte.lanciaDado();
		connessione.inviaEvento(new LancioDado(lancio), tutti);
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
			connessione.inviaEvento(new MovimentoLupo(origine.getCodice(), destinazione.getCodice()), tutti);
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

			connessione.inviaEvento(new PosizionamentoPastore(g.getNome(), risposta.getStrada()), tutti);
		}
	}

	/**
	 * Gestisce la fase iniziale della partita
	 */
	private void iniziaPartita() {
		distribuisciDenari();
		disponiPecore();
		disponiTessereIniziali();
		connessione.inviaEvento(new InizioPartita(), tutti);
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

	private void pagamento(int somma, Giocatore pagante, Giocatore ricevente) {
		pagante.sottraiDenaro(somma);
		ricevente.aggiungiDenaro(somma);
	}

}

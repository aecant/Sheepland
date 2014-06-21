package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import it.polimi.deib.provaFinale.cantiniDignani.controller.CostantiController;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Estrattore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Abbattimento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Accoppiamento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.AcquistoTessera;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPecora;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Pagamento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaPecoraDaAbbattere;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaPecoraDaMuovere;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaPosizionePastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaTerritorioPerAccoppiamento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaTesseraDaAcquistare;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SaltoTurno;
import it.polimi.deib.provaFinale.cantiniDignani.model.CostantiModel;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GestoreMossa {

	private GestorePartita gestore;
	private Partita partita;

	public GestoreMossa(GestorePartita gestorePartita) {
		this.gestore = gestorePartita;
		this.partita = gestorePartita.getPartita();
	}

	protected void effettuaMossa(Pastore pastore, TipoMossa tipoMossa) {
		Giocatore giocatore = partita.getGiocatore(pastore);

		try {
			switch (tipoMossa) {

			case MUOVI_PASTORE:
				muoviPastore(pastore, giocatore);
				break;

			case MUOVI_PECORA:
				muoviPecora(pastore, giocatore);
				break;

			case ABBATTI:
				abbatti(pastore, giocatore);
				break;

			case ACCOPPIA:
				accoppia(pastore, giocatore);
				break;

			case ACQUISTA_TESSERA:
				acquistaTessera(pastore, giocatore);
				break;

			}
		} catch (GiocatoreDisconnessoException e) {
			gestore.inviaEventoATutti(new SaltoTurno(giocatore.getNome()));
		}
	}

	private void muoviPastore(Pastore pastore, Giocatore giocatore) throws GiocatoreDisconnessoException {
		boolean[] stradeGratis = Estrattore.stradeLibereGratis(partita, pastore.getStrada());
		boolean[] stradeAPagamento = Estrattore.stradeLibereAPagamento(partita, pastore.getStrada());
		gestore.inviaEvento(new RichiestaPosizionePastore(stradeGratis, stradeAPagamento), giocatore);

		int codDest = gestore.aspettaMossa(giocatore);

		if (!stradeGratis[codDest] && !stradeAPagamento[codDest]) {
			throw new MossaNonValidaException(codDest + " non e' una strada valida");
		}

		if (stradeAPagamento[codDest] && giocatore.getDenaro() >= 1) {
			giocatore.sottraiDenaro(1);
		}

		Strada origine = pastore.getStrada();
		Strada destinazione = Mappa.getMappa().getStrade()[codDest];

		pastore.muoviIn(destinazione);

		partita.getRecinti().aggiungi(origine);

		gestore.inviaEventoATutti(new MovimentoPastore(giocatore.getNome(), origine.getCodice(), destinazione.getCodice(), Estrattore.giocatori(partita), Estrattore.recinti(partita)));
	}

	private void muoviPecora(Pastore pastore, Giocatore giocatore) throws GiocatoreDisconnessoException {
		int t1 = pastore.getStrada().getTerritorio1().getCodice();
		int t2 = pastore.getStrada().getTerritorio2().getCodice();

		List<Coppia<Integer, TipoAnimale>> listaOviniSuTerritorio = listaOviniSuTerritorio(pastore, true);

		gestore.inviaEvento(new RichiestaPecoraDaMuovere(listaOviniSuTerritorio), giocatore);

		int indiceScelto = gestore.aspettaMossa(giocatore);

		controllaIndice(indiceScelto, listaOviniSuTerritorio);

		int codOrig = listaOviniSuTerritorio.get(indiceScelto).primo;
		int codDest;
		if (codOrig == t1) {
			codDest = t2;
		} else if (codOrig == t2) {
			codDest = t1;
		} else {
			throw new MossaNonValidaException("Il territorio " + codOrig + " in cui " + giocatore + " vuole spostare la pecora non e' valido");
		}

		TipoAnimale animScelto = listaOviniSuTerritorio.get(indiceScelto).secondo;
		Territorio destinazione = Mappa.getMappa().getTerritori()[codDest];

		if (animScelto == TipoAnimale.PECORA_NERA) {
			partita.getGregge().getPecoraNera().muoviIn(destinazione);
		} else {
			Estrattore.getPecora(partita, codOrig, animScelto).muoviIn(destinazione);
		}

		gestore.inviaEventoATutti(new MovimentoPecora(giocatore.getNome(), animScelto, codOrig, codDest, Estrattore.datiTerritori(partita)));
	}

	private void abbatti(Pastore pastore, Giocatore giocatore) throws GiocatoreDisconnessoException {

		List<Coppia<Integer, TipoAnimale>> listaOviniSuTerritorio = listaOviniSuTerritorio(pastore, false);

		gestore.inviaEvento(new RichiestaPecoraDaAbbattere(listaOviniSuTerritorio), giocatore);

		int indiceScelto = gestore.aspettaMossa(giocatore);

		controllaIndice(indiceScelto, listaOviniSuTerritorio);

		int terrScelto = listaOviniSuTerritorio.get(indiceScelto).primo;
		TipoAnimale animScelto = listaOviniSuTerritorio.get(indiceScelto).secondo;

		int lancio = gestore.lanciaDado(MotivoLancioDado.TENTATIVO_ABBATTIMENTO);

		boolean aBuonFine = (lancio == Mappa.getMappa().getDado(pastore.getStrada()));

		if (aBuonFine) {
			Pecora daAbbattere = Estrattore.getPecora(partita, terrScelto, animScelto);
			partita.getGregge().rimuovi(daAbbattere);

			for (Pastore pastoreVicino : partita.getPastori()) {
				if (Mappa.getMappa().sonoContigue(pastore.getStrada(), pastoreVicino.getStrada()) && !pastoreVicino.getColore().equals(pastore.getColore())) {
					lancio = gestore.lanciaDado(MotivoLancioDado.SILENZIO_ABBATTIMENTO);
					if (lancio >= CostantiController.DADO_MIN_PER_SILENZIO) {
						int somma = CostantiController.COSTO_SILENZIO;
						Giocatore ricevente = partita.getGiocatore(pastoreVicino);
						gestore.pagamento(somma, giocatore, ricevente);
						gestore.inviaEventoATutti(new Pagamento(somma, giocatore.getNome(), ricevente.getNome(), Estrattore.giocatori(partita)));
					}
				}
			}
		}

		gestore.inviaEventoATutti(new Abbattimento(giocatore.getNome(), animScelto, terrScelto, aBuonFine, Estrattore.datiTerritori(partita), Estrattore.giocatori(partita)));
	}

	private void accoppia(Pastore pastore, Giocatore giocatore) throws GiocatoreDisconnessoException {
		DatiTerritorio dati[] = Estrattore.datiTerritori(partita);
		List<Integer> terrDisp = new ArrayList<Integer>();

		int codT1 = pastore.getStrada().getTerritorio1().getCodice();
		int codT2 = pastore.getStrada().getTerritorio2().getCodice();

		if (accoppiamentoPossibile(dati[codT1])) {
			terrDisp.add(codT1);
		}
		if (accoppiamentoPossibile(dati[codT2])) {
			terrDisp.add(codT2);
		}

		gestore.inviaEvento(new RichiestaTerritorioPerAccoppiamento(terrDisp), giocatore);

		int codTerr = gestore.aspettaMossa(giocatore);

		controllaValore(codTerr, terrDisp);

		Territorio terr = Mappa.getMappa().getTerritori()[codTerr];

		int lancio = gestore.lanciaDado(MotivoLancioDado.TENTATIVO_ACCOPPIAMENTO);

		boolean aBuonFine = (lancio == Mappa.getMappa().getDado(pastore.getStrada()));

		if (aBuonFine) {
			partita.getGregge().aggiungi(Estrattore.agnelloRandom(terr));
		}

		gestore.inviaEventoATutti(new Accoppiamento(giocatore.getNome(), codTerr, aBuonFine, Estrattore.datiTerritori(partita)));
	}

	private void acquistaTessera(Pastore pastore, Giocatore giocatore) throws GiocatoreDisconnessoException {
		List<Tessera> tessereDisp = new ArrayList<Tessera>();
		Territorio terr1 = pastore.getStrada().getTerritorio1();
		Territorio terr2 = pastore.getStrada().getTerritorio2();

		Tessera tess1 = tesseraDaAcquistare(terr1, giocatore.getDenaro());
		Tessera tess2 = tesseraDaAcquistare(terr2, giocatore.getDenaro());

		if (tess1 != null) {
			tessereDisp.add(tess1);
		}
		if (tess2 != null && !tess2.equals(tess1)) {
			tessereDisp.add(tess2);
		}

		gestore.inviaEvento(new RichiestaTesseraDaAcquistare(tessereDisp), giocatore);

		int indiceScelta = gestore.aspettaMossa(giocatore);

		controllaIndice(indiceScelta, tessereDisp);

		Tessera tessScelta = tessereDisp.get(indiceScelta);

		TipoTerritorio tipo = tessScelta.getTipo();

		Tessera tesseraAcquistata = partita.getMazzo().prelevaTessera(tipo);

		if (!tesseraAcquistata.equals(tessScelta)) {
			throw new MossaNonValidaException("La tessera che vuole comprare " + giocatore + " e' diversa da quella in cima al mazzo");
		}

		giocatore.aggiungiTessera(tesseraAcquistata);
		
		giocatore.sottraiDenaro(tesseraAcquistata.getCosto());

		gestore.inviaEventoATutti(new AcquistoTessera(giocatore.getNome(), tesseraAcquistata, Estrattore.tessereInCima(partita), Estrattore.giocatori(partita)));
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
	protected List<TipoMossa> creaMosseDisponibili(int numMossa, boolean pastoreMosso, TipoMossa mossaPrecedente, Pastore pastoreCorrente, int denaroDisponibile) {
		List<TipoMossa> mosseDisponibili = new ArrayList<TipoMossa>();
		boolean entrambiTerritoriLiberi = partita.territorioLibero(pastoreCorrente.getStrada().getTerritorio1()) && partita.territorioLibero(pastoreCorrente.getStrada().getTerritorio2());

		mosseDisponibili.add(TipoMossa.MUOVI_PASTORE);

		// se e' l'ultima mossa e il giocatore non ha ancora mosso il
		// pastore, si puo' solo muovere il pastore
		if (numMossa == CostantiModel.NUM_MOSSE_PER_TURNO && pastoreMosso == false) {
			return mosseDisponibili;
		}

		// se uno dei due territori ha almeno una pecora
		if (mossaPrecedente != TipoMossa.MUOVI_PECORA && !entrambiTerritoriLiberi) {
			mosseDisponibili.add(TipoMossa.MUOVI_PECORA);
		}

		// se il giocatore ha abbastanza soldi per comprare una delle due
		// tessere
		if (mossaPrecedente != TipoMossa.ACQUISTA_TESSERA
				&& (tesseraDaAcquistare(pastoreCorrente.getStrada().getTerritorio1(), denaroDisponibile) != null || tesseraDaAcquistare(pastoreCorrente.getStrada().getTerritorio2(), denaroDisponibile) != null)) {
			mosseDisponibili.add(TipoMossa.ACQUISTA_TESSERA);
		}

		// se almento uno dei due territorio vicini contiene sia una pecora
		// che un montone
		if (mossaPrecedente != TipoMossa.ACCOPPIA) {
			DatiTerritorio[] dati = Estrattore.datiTerritori(partita);
			if (accoppiamentoPossibile(dati[pastoreCorrente.getStrada().getTerritorio1().getCodice()]) || accoppiamentoPossibile(dati[pastoreCorrente.getStrada().getTerritorio2().getCodice()])) {
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

	private boolean accoppiamentoPossibile(DatiTerritorio dati) {
		if (dati.getNumMontoni() > 0 && dati.getNumPecore() > 0) {
			return true;
		}
		return false;
	}

	private Tessera tesseraDaAcquistare(Territorio terr, int denaroGiocatore) {
		// se il territorio e' Sheepsburg o se il mazzo di quel tipo e' finito
		TipoTerritorio tipo = terr.getTipo();
		if (tipo == TipoTerritorio.SHEEPSBURG || partita.getMazzo().getTessereRimaste(terr.getTipo()) == 0) {
			return null;
		}

		Tessera daAcquistare = partita.getMazzo().leggiTesseraInCima(tipo);

		if (daAcquistare.getCosto() <= denaroGiocatore) {
			return daAcquistare;
		} else {
			return null;
		}
	}

	/**
	 * Restituisce una lista di coppie territorio-animale con i tipi di ovini
	 * presenti sui territori vicini al pastore
	 * 
	 * @param pastore
	 *            il pastore di cui si vogliono conoscere gli animali vicini
	 * @param pecoraNeraInclusa
	 *            true se si vuole includere la pecora nera, false altrimenti
	 * @return una lista di coppie territorio-animale con i tipi di ovini
	 *         presenti in ogni territorio dell'array passato come parametro
	 */
	private List<Coppia<Integer, TipoAnimale>> listaOviniSuTerritorio(Pastore pastore, boolean pecoraNeraInclusa) {
		DatiTerritorio[] dati = Estrattore.datiTerritori(partita);
		List<Coppia<Integer, TipoAnimale>> lista = new ArrayList<Coppia<Integer, TipoAnimale>>();
		int[] territori = { pastore.getStrada().getTerritorio1().getCodice(), pastore.getStrada().getTerritorio2().getCodice() };

		for (int t : territori) {
			for (TipoAnimale tipo : dati[t].getTipiOvino()) {
				if (tipo != TipoAnimale.PECORA_NERA || pecoraNeraInclusa) {
					lista.add(new Coppia<Integer, TipoAnimale>(t, tipo));
				}
			}
		}

		return lista;
	}



	private void controllaIndice(int indice, List<?> lista) {
		if (indice < 0 || indice >= lista.size()) {
			throw new MossaNonValidaException(indice + " non e' un indice valido per la lista " + lista);
		}
	}

	private void controllaValore(int valore, Collection<Integer> lista) {
		if (!lista.contains(valore)) {
			throw new MossaNonValidaException(valore + " non e' contenuto nella lista " + lista);
		}
	}
}

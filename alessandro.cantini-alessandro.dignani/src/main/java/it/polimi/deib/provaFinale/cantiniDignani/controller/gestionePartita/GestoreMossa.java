package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

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
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
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
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Sorte;

import java.util.ArrayList;
import java.util.List;

public class GestoreMossa {

	private GestorePartita gestorePartita;
	private Partita partita;

	public GestoreMossa(GestorePartita gestorePartita) {
		this.gestorePartita = gestorePartita;
		this.partita = gestorePartita.getPartita();
	}

	protected void effettuaMossa(Pastore pastore, TipoMossa tipoMossa) {
		Giocatore giocatore = partita.getGiocatore(pastore);

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
	}

	private void acquistaTessera(Pastore pastore, Giocatore giocatore) {
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

		gestorePartita.inviaEvento(new RichiestaTesseraDaAcquistare(tessereDisp), giocatore);

		int indiceScelta = gestorePartita.aspettaMossa(giocatore);

		Tessera tessScelta = tessereDisp.get(indiceScelta);

		TipoTerritorio tipo = tessScelta.getTipo();

		Tessera tesseraAcquistata = partita.getMazzo().prelevaTessera(tipo);

		if (!tesseraAcquistata.equals(tessScelta)) {
			throw new MossaNonValidaException("La tessera che vuole comprare " + giocatore + " e' diversa da quella in cima al mazzo");
		}

		giocatore.aggiungiTessera(tesseraAcquistata);

		gestorePartita.inviaEventoATutti(new AcquistoTessera(giocatore.getNome(), tesseraAcquistata, Estrattore.tessereInCima(partita)));
	}

	private void accoppia(Pastore pastore, Giocatore giocatore) {
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

		gestorePartita.inviaEvento(new RichiestaTerritorioPerAccoppiamento(terrDisp), giocatore);

		int codTerr = gestorePartita.aspettaMossa(giocatore);

		Territorio terr = Mappa.getMappa().getTerritori()[codTerr];

		int lancio = gestorePartita.lanciaDado(MotivoLancioDado.TENTATIVO_ACCOPPIAMENTO);

		boolean aBuonFine = (lancio == Mappa.getMappa().getDado(pastore.getStrada()));

		if (aBuonFine) {
			partita.getGregge().aggiungi(Sorte.agnelloRandom(terr));
		}

		gestorePartita.inviaEventoATutti(new Accoppiamento(giocatore.getNome(), codTerr, aBuonFine, Estrattore.datiTerritori(partita)));
	}

	private void abbatti(Pastore pastore, Giocatore giocatore) {

		List<Coppia<Integer, TipoAnimale>> listaOviniSuTerritorio = listaOviniSuTerritorio(pastore, false);

		gestorePartita.inviaEvento(new RichiestaPecoraDaAbbattere(listaOviniSuTerritorio), giocatore);

		int indiceScelto = gestorePartita.aspettaMossa(giocatore);
		int terrScelto = listaOviniSuTerritorio.get(indiceScelto).primo;
		TipoAnimale animScelto = listaOviniSuTerritorio.get(indiceScelto).secondo;

		int lancio = gestorePartita.lanciaDado(MotivoLancioDado.TENTATIVO_ABBATTIMENTO);

		boolean aBuonFine = (lancio == Mappa.getMappa().getDado(pastore.getStrada()));

		if (aBuonFine) {
			Pecora daAbbattere = Estrattore.getPecora(partita, terrScelto, animScelto);
			partita.getGregge().rimuovi(daAbbattere);

			for (Pastore pastoreVicino : partita.getPastori()) {
				if (Mappa.getMappa().sonoContigue(pastore.getStrada(), pastoreVicino.getStrada()) && !pastoreVicino.getColore().equals(pastore.getColore())) {
					lancio = gestorePartita.lanciaDado(MotivoLancioDado.SILENZIO_ABBATTIMENTO);
					if (lancio >= Costanti.DADO_MIN_PER_SILENZIO) {
						int somma = Costanti.COSTO_SILENZIO;
						Giocatore ricevente = partita.getGiocatore(pastoreVicino);
						gestorePartita.pagamento(somma, giocatore, ricevente);
						gestorePartita.inviaEventoATutti(new Pagamento(somma, giocatore.getNome(), ricevente.getNome(), Estrattore.giocatori(partita)));
					}
				}
			}
		}

		gestorePartita.inviaEventoATutti(new Abbattimento(giocatore.getNome(), animScelto, terrScelto, aBuonFine, Estrattore.datiTerritori(partita), Estrattore.giocatori(partita)));
	}

	private void muoviPecora(Pastore pastore, Giocatore giocatore) {
		int t1 = pastore.getStrada().getTerritorio1().getCodice();
		int t2 = pastore.getStrada().getTerritorio2().getCodice();

		List<Coppia<Integer, TipoAnimale>> listaOviniSuTerritorio = listaOviniSuTerritorio(pastore, true);

		gestorePartita.inviaEvento(new RichiestaPecoraDaMuovere(listaOviniSuTerritorio), giocatore);

		int indiceScelto = gestorePartita.aspettaMossa(giocatore);

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
		
		gestorePartita.inviaEventoATutti(new MovimentoPecora(giocatore.getNome(), animScelto, codOrig, codDest, Estrattore.datiTerritori(partita)));
	}

	private void muoviPastore(Pastore pastore, Giocatore giocatore) {
		boolean[] stradeGratis = Estrattore.stradeLibereGratis(partita, pastore.getStrada());
		boolean[] stradeAPagamento = Estrattore.stradeLibereAPagamento(partita, pastore.getStrada());
		gestorePartita.inviaEvento(new RichiestaPosizionePastore(stradeGratis, stradeAPagamento), giocatore);

		int codDest = gestorePartita.aspettaMossa(giocatore);
		
		if(stradeAPagamento[codDest] && giocatore.getDenaro() >= 1) {
			giocatore.sottraiDenaro(1);
		}
		
		Strada origine = pastore.getStrada();
		Strada destinazione = Mappa.getMappa().getStrade()[codDest];

		pastore.muoviIn(destinazione);

		partita.getRecinti().aggiungi(origine);

		gestorePartita.inviaEventoATutti(new MovimentoPastore(giocatore.getNome(), origine.getCodice(), destinazione.getCodice(), Estrattore.giocatori(partita), Estrattore.recinti(partita)));
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
		if (numMossa == Costanti.NUM_MOSSE_PER_TURNO && pastoreMosso == false) {
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

	/**
	 * Eccezione che segnala che il client ha risposto con una mossa non valida
	 */
	public class MossaNonValidaException extends RuntimeException {

		private static final long serialVersionUID = -270481645032368246L;

		public MossaNonValidaException() {
			super();
		}

		public MossaNonValidaException(String message, Throwable cause) {
			super(message, cause);
		}

		public MossaNonValidaException(String message) {
			super(message);
		}

	}

}

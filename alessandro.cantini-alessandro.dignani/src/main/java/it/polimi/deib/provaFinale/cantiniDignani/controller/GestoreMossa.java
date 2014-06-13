package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Abbattimento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Accoppiamento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.AcquistoTessera;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class GestoreMossa{

	private GestorePartita gestorePartita;
	private Partita partita;
	private GestoreCoda<Evento> gestoreEventi;
	private int codT1, codT2;
	private Collection<TipoAnimale> oviniT1, oviniT2;
	private DatiTerritorio[] dati;

	public GestoreMossa(GestorePartita gestorePartita) {
		this.gestorePartita = gestorePartita;
		this.partita = gestorePartita.getPartita();
		this.gestoreEventi = gestorePartita.getGestoreEventi();
	}

	protected void effettuaMossa(Pastore pastore, TipoMossa tipoMossa) {
		Giocatore giocatore = partita.getGiocatore(pastore);
		dati = Estrattore.datiTerritori(partita);
		codT1 = pastore.getStrada().getTerritorio1().getCodice();
		codT2 = pastore.getStrada().getTerritorio2().getCodice();
		oviniT1 = dati[codT1].getTipiAnimale();
		oviniT2 = dati[codT2].getTipiAnimale();

		switch (tipoMossa) {

		case MUOVI_PASTORE:
			muoviPastore(giocatore, pastore);
			break;

		case MUOVI_PECORA:
			muoviPecora(giocatore);
			break;

		case ABBATTI:
			abbatti(giocatore, pastore);
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
		Collection<Tessera> tessereDisp = new HashSet<Tessera>();
		Territorio terr1 = pastore.getStrada().getTerritorio1();
		Territorio terr2 = pastore.getStrada().getTerritorio2();

		Tessera tess1 = tesseraDaAcquistare(terr1, giocatore.getDenaro());
		Tessera tess2 = tesseraDaAcquistare(terr2, giocatore.getDenaro());

		if (tess1 != null) {
			tessereDisp.add(tess1);
		}
		if (tess2 != null) {
			tessereDisp.add(tess2);
		}

		gestorePartita.inviaEvento(new RichiestaTesseraDaAcquistare(tessereDisp), giocatore);

		AcquistoTessera acq = (AcquistoTessera) gestoreEventi.aspetta(AcquistoTessera.class);

		TipoTerritorio tipo = acq.getTessera().getTipo();

		Tessera tesseraAcquistata = partita.getMazzo().prelevaTessera(tipo);

		if (!tesseraAcquistata.equals(acq.getTessera())) {
			throw new RuntimeException("C'e' un problema: la tessera che vuole comprare il client e' diversa da quella in cima al mazzo");
		}

		giocatore.aggiungiTessera(tesseraAcquistata);

		gestorePartita.inviaEventoATutti(acq);
	}

	private void accoppia(Pastore pastore, Giocatore giocatore) {
		List<Integer> terrDisp = new ArrayList<Integer>();
		if (accoppiamentoPossibile(dati[codT1])) {
			terrDisp.add(codT1);
		}
		if (accoppiamentoPossibile(dati[codT2])) {
			terrDisp.add(codT2);
		}

		gestorePartita.inviaEvento(new RichiestaTerritorioPerAccoppiamento(terrDisp), giocatore);

		Accoppiamento acc = (Accoppiamento) gestoreEventi.aspetta(Accoppiamento.class);

		int codTerr = acc.getTerritorio();
		Territorio terr = Mappa.getMappa().getTerritori()[codTerr];

		int lancio = gestorePartita.lanciaDado();

		boolean aBuonFine = (lancio == Mappa.getMappa().getDado(pastore.getStrada()));

		if (aBuonFine) {
			partita.getGregge().aggiungi(Sorte.agnelloRandom(terr));
		}

		gestorePartita.inviaEventoATutti(new Accoppiamento(acc, aBuonFine));
	}

	private void abbatti(Giocatore giocatore, Pastore pastore) {
		oviniT1.remove(TipoAnimale.PECORA_NERA);
		oviniT2.remove(TipoAnimale.PECORA_NERA);
		gestorePartita.inviaEvento(new RichiestaPecoraDaAbbattere(codT1, oviniT1, codT2, oviniT2), giocatore);

		Abbattimento abb = (Abbattimento) gestoreEventi.aspetta(Abbattimento.class);

		int lancio = gestorePartita.lanciaDado();

		boolean aBuonFine = (lancio == Mappa.getMappa().getDado(pastore.getStrada()));

		if (aBuonFine) {
			Pecora daAbbattere = Estrattore.getPecora(partita, abb.getTerritorio(), abb.getTipoOvino());
			partita.getGregge().rimuovi(daAbbattere);

			for (Pastore pastoreVicino : partita.getPastori()) {
				if (Mappa.getMappa().sonoContigue(pastore.getStrada(), pastoreVicino.getStrada()) && !pastoreVicino.getColore().equals(pastore.getColore())) {
					lancio = gestorePartita.lanciaDado();
					if (lancio >= Costanti.DADO_MIN_PER_SILENZIO) {
						int somma = Costanti.COSTO_SILENZIO;
						Giocatore ricevente = partita.getGiocatore(pastoreVicino);
						gestorePartita.pagamento(somma, giocatore, ricevente);
						gestorePartita.inviaEventoATutti(new Pagamento(somma, giocatore.getNome(), ricevente.getNome()));
					}
				}
			}
		}

		gestorePartita.inviaEventoATutti(new Abbattimento(abb, aBuonFine));
	}

	private void muoviPecora(Giocatore giocatore) {
		gestorePartita.inviaEvento(new RichiestaPecoraDaMuovere(codT1, oviniT1, codT2, oviniT2), giocatore);

		MovimentoPecora movimento = (MovimentoPecora) gestoreEventi.aspetta(MovimentoPecora.class);
		Territorio destinazione = Mappa.getMappa().getTerritori()[movimento.getDestinazione()];

		if (movimento.getTipoOvino() == TipoAnimale.PECORA_NERA) {
			partita.getGregge().getPecoraNera().muoviIn(destinazione);
		} else {
			Estrattore.getPecora(partita, movimento.getOrigine(), movimento.getTipoOvino()).muoviIn(destinazione);
		}
	}

	private void muoviPastore(Giocatore giocatore, Pastore pastore) {
		boolean[] stradeGratis = Estrattore.stradeLibereGratis(partita, pastore.getStrada());
		boolean[] stradeAPagamento = Estrattore.stradeLibereAPagamento(partita, pastore.getStrada());
		gestorePartita.inviaEvento(new RichiestaPosizionePastore(stradeGratis, stradeAPagamento, pastore.getStrada().getCodice()), giocatore);

		MovimentoPastore movimento = (MovimentoPastore) gestoreEventi.aspetta(MovimentoPastore.class);
		Strada origine = Mappa.getMappa().getStrade()[movimento.getOrigine()];
		Strada destinazione = Mappa.getMappa().getStrade()[movimento.getDestinazione()];

		pastore.muoviIn(destinazione);
		partita.getRecinti().aggiungi(origine);

		gestorePartita.inviaEventoATutti(movimento);
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
	protected Collection<TipoMossa> creaMosseDisponibili(int numMossa, boolean pastoreMosso, TipoMossa mossaPrecedente, Pastore pastoreCorrente, int denaroDisponibile) {
		List<TipoMossa> mosseDisponibili = new ArrayList<TipoMossa>();
		boolean entrambiTerritoriLiberi = partita.territorioLibero(pastoreCorrente.getStrada().getTerritorio1()) && partita.territorioLibero(pastoreCorrente.getStrada().getTerritorio2());

		mosseDisponibili.add(TipoMossa.MUOVI_PASTORE);

		// se e' l'ultima mossa e il giocatore non ha ancora mosso il
		// pastore, si puo' solo muovere il pastore
		if (numMossa == Costanti.NUM_MOSSE && pastoreMosso == false) {
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

}

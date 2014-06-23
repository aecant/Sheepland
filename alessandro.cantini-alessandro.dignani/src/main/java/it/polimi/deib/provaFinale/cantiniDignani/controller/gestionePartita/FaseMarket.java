package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import it.polimi.deib.provaFinale.cantiniDignani.controller.CostantiController;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MarketCompravenditaTessera;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MarketInizio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MarketInizioAcquisti;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MarketMessaInVendita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MarketRichiestaPrezzo;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MarketRichiestaTesseraDaAcquistare;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MarketRichiestaTesseraDaVendere;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Sorte;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Utilita;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FaseMarket extends FasePartita {

	private static final String GIOCATORE_DISCONNESSO = "giocatore disconnesso";

	private static final Logger LOGGER = Logger.getLogger(FaseMarket.class.getName());

	private final List<TesseraInVendita> tessereGlobali = new LinkedList<TesseraInVendita>();

	public FaseMarket(GestorePartita gestore) {
		super(gestore);
	}

	@Override
	public void esegui() {
		faseVendita();
		faseAcquisto();
	}

	private void faseVendita() {
		gestore.inviaEventoATutti(new MarketInizio());

		for (Giocatore giocatore : gestore.getPartita().getGiocatori()) {
			if (gestore.giocatoreOffline(giocatore)) {
				break;
			}

			Collection<Tessera> tessereTemp = Utilita.copia(giocatore.getTessere());

			while (true) {
				List<TipoTerritorio> territoriPossibili = generaTesserePossibili(tessereTemp);
				if(territoriPossibili.isEmpty()) {
					break;
				}
				
				gestore.inviaEvento(new MarketRichiestaTesseraDaVendere(territoriPossibili), giocatore);

				int scelta;
				try {
					scelta = gestore.aspettaMossa(giocatore);
				} catch (GiocatoreDisconnessoException e) {
					LOGGER.log(Level.FINE, GIOCATORE_DISCONNESSO, e);
					break;
				}
				if (scelta == CostantiController.TERMINATORE_MARKET) {
					break;
				}

				GestoreMossa.controllaIndice(scelta, territoriPossibili);

				TipoTerritorio tipoScelto = territoriPossibili.get(scelta);

				rimuoviTessera(tessereTemp, tipoScelto);

				gestore.inviaEvento(new MarketRichiestaPrezzo(tipoScelto), giocatore);

				int prezzoScelto;
				try {
					prezzoScelto = gestore.aspettaMossa(giocatore);
				} catch (GiocatoreDisconnessoException e) {
					LOGGER.log(Level.FINE, GIOCATORE_DISCONNESSO, e);
					break;
				}

				if (prezzoScelto < CostantiController.MIN_PREZZO_MARKET || prezzoScelto > CostantiController.MAX_PREZZO_MARKET) {
					throw new MossaNonValidaException();
				}

				TesseraInVendita tesseraInVendita = new TesseraInVendita(giocatore.getNome(), tipoScelto, prezzoScelto);
				tessereGlobali.add(tesseraInVendita);
				gestore.inviaEventoATutti(new MarketMessaInVendita(tesseraInVendita));
			}

		}
	}

	private void rimuoviTessera(Collection<Tessera> tessereTemp, TipoTerritorio tipoScelto) {
		Iterator<Tessera> iter = tessereTemp.iterator();
		while (iter.hasNext()) {
			Tessera tess = iter.next();
			if (tess.getTipo() == tipoScelto) {
				iter.remove();
				break;
			}
		}
	}

	private List<TipoTerritorio> generaTesserePossibili(Collection<Tessera> tessereTemp) {
		List<TipoTerritorio> territori = new LinkedList<TipoTerritorio>();
		for (Tessera tess : tessereTemp) {
			if (!territori.contains(tess.getTipo())) {
				territori.add(tess.getTipo());
			}
		}

		return territori;
	}

	private void faseAcquisto() {
		gestore.inviaEventoATutti(new MarketInizioAcquisti());

		int numGiocatori = partita.getGiocatori().size();
		int indicePrimo = Sorte.numeroCasuale(0, numGiocatori - 1);

		int i = indicePrimo;
		do {
			gestisciFaseAcquisto(partita.getGiocatori().get(i));
			i++;
			i %= numGiocatori;
		} while (i != indicePrimo);

	}

	private void gestisciFaseAcquisto(Giocatore giocatore) {
		while (true) {
			if (gestore.giocatoreOffline(giocatore)) {
				return;
			}
			List<TesseraInVendita> tessereDisponibili = (List<TesseraInVendita>) Utilita.copia(tessereGlobali);
			for (TesseraInVendita tess : tessereGlobali) {
				if (tess.getPrezzo() > giocatore.getDenaro() || giocatore.getNome().equals(tess.getGiocatore())) {
					tessereDisponibili.remove(tess);
				}
			}
			if(tessereDisponibili.isEmpty()) {
				return;
			}
			
			gestore.inviaEvento(new MarketRichiestaTesseraDaAcquistare(tessereDisponibili), giocatore);
			int scelta;
			try {
				scelta = gestore.aspettaMossa(giocatore);
			} catch (GiocatoreDisconnessoException e) {
				LOGGER.log(Level.FINE, GIOCATORE_DISCONNESSO, e);
				return;
			}
			if (scelta == CostantiController.TERMINATORE_MARKET) {
				return;
			}
			GestoreMossa.controllaIndice(scelta, tessereDisponibili);
			TesseraInVendita tesseraComprata = tessereDisponibili.get(scelta);
			tessereGlobali.remove(tesseraComprata);
			gestore.inviaEventoATutti(new MarketCompravenditaTessera(giocatore.getNome(), tesseraComprata));
			Giocatore venditore = gestore.getPartita().getGiocatore(tesseraComprata.getGiocatore());
			giocatore.aggiungiTessera(venditore.rimuoviTessera(tesseraComprata.getTipo()));
			gestore.pagamento(tesseraComprata.getPrezzo(), giocatore, venditore);
		}
	}

}

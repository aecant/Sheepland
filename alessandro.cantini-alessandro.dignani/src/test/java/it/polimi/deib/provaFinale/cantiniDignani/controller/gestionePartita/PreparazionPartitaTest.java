package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Estrattore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.PreparazionePartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PreparazionPartitaTest {
	List<String> listaNomi = new ArrayList<String>();
	Partita partita;
	PreparazionePartita preparazionePartita;
	GestorePartita gestore;

	@Before
	public void setUp() {
		Collections.addAll(listaNomi, "esempio1", "esempio2", "esempio3", "esempio4");
		partita = new Partita(listaNomi);
		gestore = new GestorePartita(partita, null, null);
		preparazionePartita = gestore.getPreparazionePartita();
	}

	/**
	 * Controlla che i denari siano correttamente distribuiti per ogni
	 * configurazione di giocatori
	 */
	@Test
	public void testDistribuisciDenari() {
		for (int i = Costanti.NUM_MAX_GIOCATORI; i >= Costanti.NUM_MIN_GIOCATORI; i--) {
			partita = new Partita(listaNomi);
			gestore = new GestorePartita(partita, null, null);
			preparazionePartita = gestore.getPreparazionePartita();
			preparazionePartita.distribuisciDenari();

			for (Giocatore g : partita.getGiocatori()) {
				if (partita.getGiocatori().size() == 2) {
					assertTrue(g.getDenaro() == Costanti.DENARO_INIZIALE_DUE_GIOCATORI);
				} else {
					assertTrue(g.getDenaro() == Costanti.DENARO_INIZIALE);
				}
			}

			listaNomi.remove(0);
		}

	}

	/**
	 * Controlla che ogni territorio abbia esattamente una pecora, un montone o
	 * un agnello
	 */
	@Test
	public void testDisponiPecore()  {
		preparazionePartita.disponiPecore();

		DatiTerritorio[] dati = Estrattore.datiTerritori(partita);
		
		//parto da 1 perche' su Sheepsburg c'e' solo la pecora nera
		for (int i = 1; i < dati.length; i++) {
			assertEquals(dati[i].getNumOvini(), 1);
		}
	}

	/**
	 * Controlla che dopo aver disposto le tessere iniziali ogni giocatore abbia
	 * esattamente una tessera e che non siano state distribuite tessere
	 * duplicate
	 */
	@Test
	public void testDisponiTessereIniziali() {
		preparazionePartita.disponiTessereIniziali();

		List<Tessera> listaTessere = new ArrayList<Tessera>();

		for (Giocatore g : partita.getGiocatori()) {
			assertEquals(g.getTessere().size(), 1);
			listaTessere.addAll(g.getTessere());
		}

		assertFalse(Utilita.contieneDuplicati(listaTessere));

	}

}
